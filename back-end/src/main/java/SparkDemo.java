import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static spark.Spark.*;

class UserDto{
  public String username;
  public String password;
  public String amount;
}

class SignUpResponseDto{
  public boolean isSuccess;
  public String message;

  public SignUpResponseDto(boolean isSuccess, String message) {
    this.isSuccess = isSuccess;
    this.message = message;
  }
}

class LoginResponseDto{
  public boolean validPassword;
  public boolean validUsername;
  public String message;

  public LoginResponseDto(Boolean validPassword, Boolean validUsername, String message){
    this.validPassword = validPassword;
    this.validUsername = validUsername;
    this.message = message;
  }
}

class userAmountDto {
  public String amount;

  public userAmountDto(String amount) {
    this.amount = amount;

  }
}

public class SparkDemo {

  private static Gson gson = new Gson();
  private static List<UserDto> users = new ArrayList<>();


  public static void main(String[] args) {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    System.out.println("Connected to database:");
    MongoDatabase db = mongoClient.getDatabase("MyDatabase");
    MongoCollection<Document> myCollection = db.getCollection("Users");

    port(1234);
    //So, in order to actually connect it with a server, we need to utilize the URI or something
    /*ServerSocket ding;
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
          String line = in.readLine(); // buffered reader is waiting for an entire line
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

        // Body of our response
        if(firstLine != null){
          String url = firstLine.split(" ")[1];
          writer.println("<h1>Hello, your URL is " + url + "</h1>");
        }
        else {
          writer.println("<h1>Hello World</h1>");
        }

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
*/
    post("/api/sign-up", (req,res) -> {
      String body = req.body();
      System.out.println(body);
      //decode to a java dto
      UserDto userDto = gson.fromJson(body, UserDto.class);

      boolean isUsernameTaken = users.stream()
              .anyMatch(u -> u.username.equals(userDto.username));
      if(isUsernameTaken){
        var signupRes = new SignUpResponseDto(false, "Username is taken");
        return gson.toJson(signupRes);
      }
      users.add(userDto);
      System.out.println("Total Users " + users.size());
      var signupRes = new SignUpResponseDto(true,null);
      return gson.toJson(signupRes); //temporary
    });

    post("/api/login", (req,res) -> {
      String body = req.body();
      System.out.println(body);
      UserDto userDto = gson.fromJson(body, UserDto.class);

      boolean notValidUsername = users.stream()
              .anyMatch(u -> u.username.equals(userDto.username));

      boolean notValidPassword = users.stream()
              .anyMatch(p -> p.password.equals(userDto.password));

      if(!notValidUsername){
        var loginRes = new LoginResponseDto(false,false, "Username does not exist");
        return gson.toJson(loginRes);
      }
      if(!notValidPassword){
        var loginRes = new LoginResponseDto(false, false, "Password is incorrect");
        return gson.toJson(loginRes);
      }
      var loginRes = new LoginResponseDto(true,true, null);
      String user = null;
      String[] stringArray = body.split("[ { , }: ? = \n ]+");
      for(int i =0; i < stringArray.length; i++){
        System.out.println(stringArray[i]);
      }
      user = stringArray[1];
      return gson.toJson(loginRes);
    });

    post("/api/Transact", (req,res) -> {
      String body = req.body();
      System.out.println(body);
      UserDto userDto = gson.fromJson(body, UserDto.class);

      boolean ValidUsername = users.stream()
              .anyMatch(u -> u.username.equals(userDto.username));
      boolean ValidPassword = users.stream()
              .anyMatch( p -> p.password.equals(userDto.password));

      if(!ValidUsername){
        var loginRes = new LoginResponseDto(false,false, "Username does not exist");
        String[] stringArray = body.split("[ { , }: ? = \n ]+");
        for(int i =0; i < stringArray.length; i++){
          System.out.println(stringArray[i]);
        }
        return gson.toJson(loginRes);
      }
      if(!ValidPassword){
        var loginRes = new LoginResponseDto(false,false,"Password is incorrect");
        String[] stringArray = body.split("[ { , }: ? = \n ]+");
        for(int i=0; i < stringArray.length; i++){
          System.out.println(stringArray[i]);
        }
        return gson.toJson(loginRes);
      }
      var loginRes = new LoginResponseDto(true,true, null);
      // System.out.println(parseRequest(body));

      //var amountRes = new userAmountDto()
      return gson.toJson(loginRes);
    });
  }

  public static String parseRequest(String body){
    String amount = null;
    String[] stringArray = body.split("[ ? = \n ]+");
    String newAmount = stringArray[1];
    return amount;
  }
}