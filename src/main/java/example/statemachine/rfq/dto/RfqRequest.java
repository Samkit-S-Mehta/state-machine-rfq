package example.statemachine.rfq.dto;

public class RfqRequest {

    private String buyCurrency;
    private String sellCurrency;
    private int buyCurrencyQuantity;

    public RfqRequest() {}

    public RfqRequest(String buyCurrency, String sellCurrency, int buyCurrencyQuantity) {
        this.buyCurrency = buyCurrency;
        this.sellCurrency = sellCurrency;
        this.buyCurrencyQuantity = buyCurrencyQuantity;
    }

    // Getters and Setters
    public String getBuyCurrency() {
        return buyCurrency;
    }

    public void setBuyCurrency(String buyCurrency) {
        this.buyCurrency = buyCurrency;
    }

    public String getSellCurrency() {
        return sellCurrency;
    }

    public void setSellCurrency(String sellCurrency) {
        this.sellCurrency = sellCurrency;
    }

    public int getBuyCurrencyQuantity() {
        return buyCurrencyQuantity;
    }

    public void setBuyCurrencyQuantity(int buyCurrencyQuantity) {
        this.buyCurrencyQuantity = buyCurrencyQuantity;
    }
}