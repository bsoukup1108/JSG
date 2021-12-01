import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.port;
import static spark.Spark.post;

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
