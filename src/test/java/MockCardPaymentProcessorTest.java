import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MockCardPaymentProcessorTest {
    private MockCardPaymentProcessor processor;

    @Before
    public void setUp() {
        processor = new MockCardPaymentProcessor();
    }

    @Test(expected = PaymentException.class)
    public void authorize_shouldThrow_forInvalidCardNumber() throws PaymentException {
        CardData badNumber = new CardData("1234", "12/25", "123");
        processor.authorize(badNumber, 100.0);
    }

    @Test(expected = PaymentException.class)
    public void authorize_shouldThrow_forInvalidExpiryFormat() throws PaymentException {
        CardData badExpiry = new CardData("1234567812345678", "13/25", "123");
        processor.authorize(badExpiry, 50.0);
    }

    @Test(expected = PaymentException.class)
    public void authorize_shouldThrow_forInvalidCvv() throws PaymentException {
        CardData badCvv = new CardData("1234567812345678", "12/25", "12A");
        processor.authorize(badCvv, 10.0);
    }

    @Test
    public void authorize_shouldReturnBoolean_forValidCard() throws PaymentException {
        CardData valid = new CardData("1234567812345678", "12/25", "123");
        boolean result = processor.authorize(valid, 123.45);
        // Dla poprawnych danych wynik to true albo false, ale nie wyjątek
        assertTrue(result == true || result == false);
    }

    @Test
    public void authorize_randomness_shouldOccasionallyFail_andOccasionallyPass() throws PaymentException {
        CardData valid = new CardData("1234567812345678", "01/30", "999");
        int successes = 0, failures = 0;
        // Wykonaj 100 prób; przy 90% progu powinny być oba rezultaty
        for (int i = 0; i < 100; i++) {
            if (processor.authorize(valid, 1.0)) successes++;
            else failures++;
        }
        assertTrue("Oczekuję co najmniej jednego sukcesu", successes > 0);
        assertTrue("Oczekuję co najmniej jednej porażki", failures > 0);
    }
}