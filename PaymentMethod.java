public interface PaymentMethod {
    public boolean processPayment(double amount);
    public boolean processRefund(double amount);
    
} 
