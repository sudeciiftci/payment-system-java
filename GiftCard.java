public class GiftCard implements PaymentMethod{

    private double availableLimit;

    public GiftCard(double availableLimit) {
        this.availableLimit = availableLimit;
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
