import com.google.gson.Gson;

<<<<<<< HEAD
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SparkDemo {
=======
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
>>>>>>> e03d773108d3f68ab6fb42284f3fe80ff26957cb

class UserDto{
  public String username;
  public String password;

}

class SignUpResponseDto{
  public boolean isSuccess;
  public String message;

  public SignUpResponseDto(boolean isSuccess, String message) {
    this.isSuccess = isSuccess;
    this.message = message;
  }
}

public class SparkDemo {

  private static Gson gson = new Gson();
  private static List<UserDto> users = new ArrayList<>();

  public static void main(String[] args) {
    port(1234);

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
  }
}