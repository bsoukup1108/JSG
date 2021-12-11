package dao;

import com.mongodb.client.MongoCollection;
import dto.CashPayment;
import dto.CreditCardPayment;
import dto.BasePaymentDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PaymentDao implements BaseDao<BasePaymentDto> {
    List<BasePaymentDto> test = new ArrayList<BasePaymentDto>();

    private static PaymentDao instance;
    public MongoCollection<Document> collection; // TODO instead of using a list, directly use mongo to load/store

    private PaymentDao(MongoCollection<Document> collection){
        this.collection = collection;
    }

    public static PaymentDao getInstance() {
        if (instance == null) {
            instance = new PaymentDao(MongoConnection.getCollection("Payments"));
        }
        return instance;
    }

    public static PaymentDao getInstance(MongoCollection<Document> collection) {
        instance = new PaymentDao(collection);
        return instance;
    }

    @Override
    public void put(BasePaymentDto basePaymentDto) {
        collection.insertOne(basePaymentDto.toDocument());
    }

    @Override
    public BasePaymentDto get(String id) {
        Document paymentInfo = collection.find(new Document()).first();
        CashPayment cashPayment = new CashPayment();
        CreditCardPayment creditCardPayment = new CreditCardPayment(
                paymentInfo.getDouble("amount"),
                paymentInfo.getString("number"),
                paymentInfo.getString("securityCode")
        );
        if(paymentInfo.get("type").equals("cash")){
            cashPayment.setAmount(paymentInfo.getDouble("amount"));
            cashPayment.setUniqueId(paymentInfo.get("_id").toString());
            return cashPayment;
        }
        else if(paymentInfo.get("type").equals("credit")){
            creditCardPayment.setUniqueId(paymentInfo.get("_id").toString());
            return creditCardPayment;
        }
        return null;
    }


    @Override
    public List<BasePaymentDto> getAll(){
        List<BasePaymentDto> results = new ArrayList<>();
        List<Document> allItems = collection.find()
                .into(new ArrayList<>());
        for (int i = 0; i < allItems.size(); i++) {
            results.add(BasePaymentDto.toDto(allItems.get(i)));
        }

        return results;
    }
}
