import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static spark.Spark.*;

class UserDto{
  public String username;
  public String password;
}

class SignUpResponseDto{
  public Boolean isSuccess;
  public String message;

  public SignUpResponseDto(Boolean isSuccess, String message) {
    this.isSuccess = isSuccess;
    this.message = message;
  }
}

class LoginResponseDto{
  public boolean validUsername;
  public String message;

  public LoginResponseDto(Boolean validUsername, String message){
    this.validUsername = validUsername;
    this.message = message;
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

    post("/api/sign-up", (req, res) ->{
      String body = req.body();
      System.out.println(body);
      UserDto userDto = gson.fromJson(body, UserDto.class);

      boolean isUsernameTaken = users.stream()
              .anyMatch(u -> u.username.equals(userDto.username));

      if(isUsernameTaken){
        var signupRes = new SignUpResponseDto(false, "Username is taken");
        return gson.toJson(signupRes);
      }


      users.add(userDto);
      System.out.println("Total Users " + users.size());
      var signupRes = new SignUpResponseDto(true, null);
      return gson.toJson(signupRes);
    });

    post("/api/login", (req, res) -> {
      String body = req.body();
      System.out.println(body);
      UserDto userDto = gson.fromJson(body, UserDto.class);

      boolean notValidUsername = users.stream()
              .anyMatch(u -> u.username.equals(userDto.username));

      if(notValidUsername){
        var loginRes = new LoginResponseDto(false,"Username is does not exist");
        return  gson.toJson((loginRes));
      }

      var loginRes = new LoginResponseDto(true, "Login Successful!");
      return gson.toJson(loginRes);
    });
  }
}
