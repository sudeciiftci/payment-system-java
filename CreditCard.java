public class CreditCard implements PaymentMethod {

    private String creditCardNumber;
    private double availableLimit;

    public CreditCard(String creditCardNumber, double availableLimit) {
        this.creditCardNumber = creditCardNumber;
        this.availableLimit = availableLimit;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public double getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(double availableLimit) {
        this.availableLimit = availableLimit;
    }

    @Override
    public boolean processPayment(double amount) {

        if(availableLimit >= amount){
            availableLimit -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean processRefund(double amount) {

        if(amount <= 0){
            return false;
        }
        availableLimit += amount;
        return true;
    }
}
