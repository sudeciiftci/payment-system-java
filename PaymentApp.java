import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PaymentApp extends Application {

    private ComboBox<String> methodBox;
    private TextField amountField;
    private VBox dynamicFields;
    private Label resultLabel;

    private TextField cardNumberField;
    private TextField limitField;

    private TextField givenAmountField;

    private TextField qrCodeField;

    private TextField balanceField;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(12);
        root.setPadding(new Insets(24));
        root.setMaxWidth(400);
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Make a Payment");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label methodLabel = new Label("Payment method");
        methodBox = new ComboBox<>();
        methodBox.getItems().addAll("Credit Card", "Cash", "QR Code", "Gift Card");
        methodBox.setPromptText("Select...");
        methodBox.setMaxWidth(Double.MAX_VALUE);
        methodBox.setOnAction(e -> updateFields());

        Label amountLabel = new Label("Amount ($)");
        amountField = new TextField();
        amountField.setPromptText("0.00");

        dynamicFields = new VBox(8);
        dynamicFields.setPadding(new Insets(12));
        dynamicFields.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 8;");
        dynamicFields.setVisible(false);
        dynamicFields.setManaged(false);

        Button payButton = new Button("Pay");
        payButton.setMaxWidth(Double.MAX_VALUE);
        payButton.setStyle("-fx-background-color: #222; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px; -fx-background-radius: 8;");
        payButton.setOnAction(e -> processPayment());

        resultLabel = new Label();
        resultLabel.setWrapText(true);
        resultLabel.setVisible(false);

        root.getChildren().addAll(
            title,
            methodLabel, methodBox,
            amountLabel, amountField,
            dynamicFields,
            payButton,
            resultLabel
        );

        Scene scene = new Scene(root, 420, 500);
        stage.setTitle("Payment System");
        stage.setScene(scene);
        stage.show();
    }

    private void updateFields() {
        dynamicFields.getChildren().clear();
        resultLabel.setVisible(false);
        String selected = methodBox.getValue();
        if (selected == null) return;

        switch (selected) {
            case "Credit Card":
                cardNumberField = new TextField();
                cardNumberField.setPromptText("1234 5678 9012 3456");
                limitField = new TextField();
                limitField.setPromptText("Available limit");
                dynamicFields.getChildren().addAll(
                    new Label("Card number"), cardNumberField,
                    new Label("Available limit ($)"), limitField
                );
                break;
            case "Cash":
                givenAmountField = new TextField();
                givenAmountField.setPromptText("Amount given");
                dynamicFields.getChildren().addAll(
                    new Label("Amount given ($)"), givenAmountField
                );
                break;
            case "QR Code":
                qrCodeField = new TextField();
                qrCodeField.setPromptText("Enter QR code");
                dynamicFields.getChildren().addAll(
                    new Label("QR code"), qrCodeField
                );
                break;
            case "Gift Card":
                balanceField = new TextField();
                balanceField.setPromptText("Card balance");
                dynamicFields.getChildren().addAll(
                    new Label("Card balance ($)"), balanceField
                );
                break;
        }

        dynamicFields.setVisible(true);
        dynamicFields.setManaged(true);
    }

    private void processPayment() {

        try {

            double amount = Double.parseDouble(amountField.getText());
            String selected = methodBox.getValue();

            if (selected == null) {
                resultLabel.setText("Please select a payment method.");
                resultLabel.setVisible(true);
                return;
            }

            boolean result = false;

            switch (selected) {

                case "Credit Card":
                    String cardNumber = cardNumberField.getText();
                    double limit = Double.parseDouble(limitField.getText());

                    CreditCard creditCard = new CreditCard(cardNumber, limit);

                    result = creditCard.processPayment(amount);

                    break;

                case "Cash":
                    double givenAmount = Double.parseDouble(givenAmountField.getText());
                    Cash cash = new Cash(givenAmount);
                    result = cash.processPayment(amount);
                    if (result) {
                        double change = givenAmount - amount;
                        resultLabel.setText(
                            "Payment successful!\nChange: $" + change
                        );
                        resultLabel.setVisible(true);
                        return;
                    }
                    break;

                case "QR Code":
                    int qrCode = Integer.parseInt(qrCodeField.getText());
                    QRCode qCode = new QRCode(qrCode);
                    result = qCode.processPayment(amount);
                    break;
                case "Gift Card":
                    double balance = Double.parseDouble(balanceField.getText());
                    GiftCard giftCard = new GiftCard(balance);
                    result = giftCard.processPayment(amount);
                    break;
            }
            if (result) {
                resultLabel.setText("Payment successful!");
            } else {
                resultLabel.setText("Payment failed!");
            }
            resultLabel.setVisible(true);

        } catch (Exception ex) {

            resultLabel.setText("Invalid input!");
            resultLabel.setVisible(true);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}