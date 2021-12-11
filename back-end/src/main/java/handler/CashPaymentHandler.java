package handler;

import com.google.gson.Gson;
import dao.PaymentDao;
import dto.BasePaymentDto;
import dto.CashPayment;
import request.ParsedRequest;
import response.CustomHttpResponse;
import response.ResponseBuilder;

public class CashPaymentHandler  implements BaseHandler{

    private static final Gson gson = new Gson();

    // Only Post
    @Override
    public String handleRequest(ParsedRequest request) {
        //Create an instance variable here
        var cash = PaymentDao.getInstance();
        BasePaymentDto cashPayment = gson.fromJson(request.getBody(), CashPayment.class);
        cash.put(cashPayment.setUniqueId(String.valueOf(Math.random())));
        CustomHttpResponse cashRespond = new ResponseBuilder().setStatus("200 OK").setVersion("HTTP/1.1").build();
        return cashRespond.toString();
    }
}