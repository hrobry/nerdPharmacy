
import java.util.Random;
import java.util.Scanner;



// Implementacja symulująca zewnętrzny serwis płatności
class MockCardPaymentProcessor implements OptymalizedPaymentProcessor {
    private Random rnd = new Random();

    @Override
    public boolean authorize(CardData card, double amount) throws PaymentException {
        // 1) Sprawdzenie formatu numeru (16 cyfr)
        if (!card.number.matches("\\d{16}")) {
            throw new PaymentException("Nieprawidłowy numer karty");
        }
        // 2) Sprawdzenie formatu daty ważności (MM/YY)
        if (!card.expiry.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            throw new PaymentException("Nieprawidłowy format daty ważności (MM/YY)");
        }
        // 3) Sprawdzenie CVV (3 cyfry)
        if (!card.cvv.matches("\\d{3}")) {
            throw new PaymentException("Nieprawidłowy kod CVV");
        }
        // 4) Symulacja opóźnienia sieciowego
        try {
            Thread.sleep(1000 + rnd.nextInt(2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 5) Symulacja decyzji autoryzacji (90% szans na sukces)
        return rnd.nextDouble() < 0.9;
    }
}