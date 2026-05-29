public class Cash implements PaymentMethod{
    private double amountPaid;
    private double change;

    public Cash(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getChange() {
        return change;
    }  
    
    public void setChange(double change) {
        this.change = change;
    }

    @Override
    public boolean processPayment(double amount) {
        if(amount <= amountPaid){
            double change = amountPaid - amount;
            setChange(change);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean processRefund(double amount) {
        if(amount <= 0){
            return false;
        }
        return true;
    }
}
