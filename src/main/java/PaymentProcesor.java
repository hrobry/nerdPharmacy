// *****************************************************************
// Element 2: Interfejsy
// Interfejs PaymentProcessor – definiuje metodę przetwarzania płatności.
interface PaymentProcessor {
    boolean processPayment(double amount) throws Exception;
}

// Implementacja interfejsu PaymentProcessor.
class PaymentProcessorImpl implements PaymentProcessor {

    public boolean processPayment(double amount) throws Exception {
        // Symulacja przetwarzania płatności
        System.out.println("Przetwarzanie płatności: " + amount + " PLN");
        return true;
    }
}