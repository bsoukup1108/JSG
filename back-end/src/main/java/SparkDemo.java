import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class SparkDemo {

  public static void main(String[] args) throws IOException {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    System.out.println("Connected to database:");

    MongoDatabase db = mongoClient.getDatabase("MyDatabase");
    MongoCollection<Document> myCollection = db.getCollection("Users");

    ServerSocket ding;
    Socket dong = null;
    try {
      ding = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {
        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String firstLine = null;
        try {
          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          firstLine = line;
          System.out.println("----------REQUEST START---------");
          System.out.println(line);
          // read only headers
          line = in.readLine();

          while (line != null && line.trim().length() > 0) {
            int index = line.indexOf(": ");
            if (index > 0) {
              System.out.println(line);
            } else {
              break;
            }
            line = in.readLine();
          }
          System.out.println("----------REQUEST END---------\n\n");
        } catch (IOException e) {
          System.out.println("Error reading");
          System.exit(1);
        }

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // every response will always have the status-line, date, and server name
        writer.println("HTTP/1.1 200 OK");
        writer.println("Server: TEST");
        writer.println("Connection: close");
        writer.println("Content-type: text/html");
        writer.println("");

        if(firstLine == null){
          writer.println("<h1>404 not found</h1>");
        }else{
          System.out.println("First line: " + firstLine);
          String pathParts = firstLine.split(" ")[1];
          System.out.println("Full path + query string: " + pathParts);

          String[] parts = pathParts.split("\\?");
          String path = parts[0];
          String queryString = parts[1];

          Map<String,String> queryArgs = Arrays.stream(queryString.split("&"))
                  .map(keyValuesString -> keyValuesString.split("="))
                  .collect(Collectors.toMap(array -> array[0], array-> array[1]));
          System.out.println(queryArgs);

          if(path.equals("/createUser")){
            String username = queryArgs.get("username");
            String password = queryArgs.get("password");

            Document potentialUser = myCollection.find(new Document("username", username)).first();
            if(potentialUser!=null){
              writer.println("<h1>Username is taken!</h1>");
            }else{
              Document newUser = new Document("username", username)
                      .append("password", password);
              myCollection.insertOne(newUser);
              writer.println("<h1>New user created!</h1>");
            }

          }else if(path.equals("/getUser")){
            String username = queryArgs.get("username");
            String password = queryArgs.get("password");

            Document potentialUser = myCollection.find(new Document("username", username)).first();
            if(potentialUser == null){
              writer.println("<h1>User doesn't exist</h1>");
            }else{
              if(potentialUser.getString("password").equals(password)){
                writer.println("<h1>Login success!</h1>");
              }else{
                writer.println("<h1>Password is incorrect</h1>");
              }
            }

          }else{
            writer.println("<h1>404 not found</h1>");
          }
        }

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }
}
