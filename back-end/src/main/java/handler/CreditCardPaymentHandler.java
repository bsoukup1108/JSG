package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.BasePaymentDto;
import dto.CashPayment;
import dto.CreditCardPayment;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class CreditCardPaymentHandler  implements BaseHandler{

    private static final Gson gson = new Gson();

    // Only Post
    @Override
    public String handleRequest(ParsedRequest request) {

        var cash = PaymentDao.getInstance();
        BasePaymentDto pay = gson.fromJson(request.getBody(), CashPayment.class);
        cash.put(pay.setUniqueId(String.valueOf(Math.random())));
        CustomHttpResponse paymentRespond = new ResponseBuilder().setStatus("200 OK").setVersion("HTTP/1.1").build();
        return paymentRespond.toString();
    }

}