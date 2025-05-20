// Interfejs definiujący procesor płatności
interface OptymalizedPaymentProcessor {
    boolean authorize(CardData card, double amount) throws PaymentException;
}

// Wyjątek zwracany przy problemach z płatnością
class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}