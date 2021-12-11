package handler;

import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class FallbackHandler implements BaseHandler {

  @Override
  public String handleRequest(ParsedRequest request) {

    CustomHttpResponse paymentRespond = new ResponseBuilder().setStatus("404 Not Found").setVersion("HTTP/1.1").build();
    return paymentRespond.toString();
  }
}