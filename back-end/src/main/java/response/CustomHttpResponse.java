package response;

import java.util.Map;
import java.util.Map.Entry;

public class CustomHttpResponse {
  public final Map<String,String> headers;
  public final String status;
  public final String version;
  public final String body;

  public CustomHttpResponse(Map<String, String> headers, String status, String version,
      String body) {
    this.headers = headers;
    this.status = status;
    this.version = version;
    this.body = body;
  }

  public String toString() {

    StringBuilder str = new StringBuilder(this.version + " " + this.status + "\n");
    //enhanced for loop go through map entry compare to headers
    for (Entry<String, String> entry : this.headers.entrySet()) {


    }//need this for getpayment test
    if(this.body != null){
      str.append("\n" + this.body);
    }
    return str.toString();
  }
}