import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
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
        return gson.toJson((loginRes));
      }
      if(!notValidPassword){
        var loginRes = new LoginResponseDto(false, false, "Password is incorrect");
      }
      var loginRes = new LoginResponseDto(true,true, null);
      String user = null;
      String[] stringArray = body.split("[ { , }: ? = \n ]+");
      for(int i =0; i < stringArray.length; i++){
        System.out.println(stringArray[i]);
      }
      user = stringArray[1];
      return gson.toJson(user);
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
