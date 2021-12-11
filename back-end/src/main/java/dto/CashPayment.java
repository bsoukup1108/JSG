package dto;

import org.bson.Document;

public class CashPayment extends BasePaymentDto {

    private String type = "cash";

    public CashPayment() {
    }

    public CashPayment(String uniqueId, Double amount) {
        super(uniqueId);
        this.amount = amount;
    }

    public CashPayment(Double amount) {
        super();
        this.amount = amount;
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.put("amount",amount);
        doc.put("type","cash");
        doc.put("uniqueId",getUniqueId());
        return doc;
    }

    public static CashPayment fromDocument(Document document) {
        CashPayment cash = new CashPayment(document.getString("_id"), document.getDouble("amount"));
        return cash;
    }
}