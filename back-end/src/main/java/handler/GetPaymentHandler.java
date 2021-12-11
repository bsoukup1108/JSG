package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class GetPaymentHandler implements BaseHandler {

  private static final Gson gson = new Gson();

  @Override
  public String handleRequest(ParsedRequest request) {

    var cash = PaymentDao.getInstance().getAll();
    //BasePaymentDto pay = gson.fromJson(request.getBody(), CashPayment.class);
    //cash.put(pay.setUniqueId(String.valueOf(Math.random())));
    String pay = gson.toJson(cash.get(0));
    CustomHttpResponse paymentRespond = new ResponseBuilder().setStatus("200 OK").setVersion("HTTP/1.1").setBody(pay).build();
    return paymentRespond.toString();
  }
}
