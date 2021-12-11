package handler;

import request.ParsedRequest;

public class HandlerFactory {

  public static BaseHandler getHandler(ParsedRequest request) {
    String path = request.getPath();
    String method = request.getMethod();

    if(path.equals("/makeCreditCardPayment") && method.equals("POST")){
      return new CreditCardPaymentHandler();
    }
    if(path.equals("/makeCashPayment") && method.equals("POST")){
      return new CashPaymentHandler();
    }
    if(path.equals("/getPayment") && method.equals("GET")){
      return new GetPaymentHandler();
    } else {
      return new FallbackHandler();
    }

  }
}