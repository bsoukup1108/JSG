package request;

public class CustomParser {

  public static ParsedRequest parse(String request){
    ParsedRequest locate = new ParsedRequest();
    String run = request;
    String[] words = run.split("[\\n\\s\\t\\r\\?\\=]");
    locate.setPath(words[1]);
    locate.setMethod(words[0]);


    locate.setPath(words[1]);
    locate.setQueryParam(words[2], words[3]);

    locate.setBody(words[words.length - 1]);


    return locate;
  }
}