public class QRCode implements PaymentMethod {

    private int expectedQrCode = 782003;
    private int qrCode;

    public QRCode(int qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public boolean processPayment(double amount) {
        if(expectedQrCode == qrCode){
            return true;
        }
        return false;
    }

    @Override
    public boolean processRefund(double amount) {
        if(amount <= 0){
            return false;
        }
        return true;
    }
}
