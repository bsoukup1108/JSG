package dto;

import org.bson.Document;

public abstract class BasePaymentDto {

    private String uniqueId;
    public Double amount;

    public BasePaymentDto() {
    }

    public BasePaymentDto(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueId(){
        return uniqueId;
    }

    public BasePaymentDto setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public BasePaymentDto setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public abstract Document toDocument();

    public static BasePaymentDto toDto(Document document){

        CashPayment cashPayment = new CashPayment();
        CreditCardPayment creditCardPayment = new CreditCardPayment(
                document.getDouble("amount"),
                document.getString("number"),
                document.getString("securityCode")
        );
        if (document.get("type").equals("cash")) {
            cashPayment.setAmount(document.getDouble("amount"));
            cashPayment.setUniqueId(document.get("_id").toString());
            return cashPayment;
        } else if (document.get("type").equals("credit")) {
            creditCardPayment.setUniqueId(document.get("_id").toString());
            return creditCardPayment;
        }
        return null;

    }
}

