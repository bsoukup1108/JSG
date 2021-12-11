package dto;

import org.bson.Document;

public class CreditCardPayment extends BasePaymentDto {

    private String number;
    private String securityCode;
    private static String type = "credit";

    public CreditCardPayment(Double amount, String number, String securityCode) {
        super();
        this.amount = amount;
        this.number = number;
        this.securityCode = securityCode;
    }

    @Override
    public Document toDocument() {
        Document creditDocs = new Document();
        creditDocs.put("amount", amount);
        creditDocs.put("type", "credit");
        creditDocs.put("number",number);
        creditDocs.put("securityCode", securityCode);
        return creditDocs;
    }

    public static CreditCardPayment fromDocument(Document document){
        return null;
    }

    public String getNumber() {
        return number;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getType() {
        return type;
    }
}
