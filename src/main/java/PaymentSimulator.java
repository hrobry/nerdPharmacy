
import java.util.Random;
import java.util.Scanner;


public class PaymentSimulator {
   // public static void main(String[] args) {
 public static  double amount = 0;
    Scanner scanner = new Scanner(System.in);
    OptymalizedPaymentProcessor processor = new MockCardPaymentProcessor();
  public PaymentSimulator() throws PaymentException {


    }

public void firstStepPayment() throws NumberFormatException{

    System.out.println("== Symulacja płatności kartą ==");
    System.out.print("Podaj kwotę do zapłaty (PLN): ");


        amount = Double.parseDouble(scanner.nextLine().replace(',', '.'));

}

    public void secondStepPayment() throws PaymentException{

        System.out.print("Numer karty (12 cyfr): ");
        String number = scanner.nextLine().trim();
        System.out.print("Data ważności (MM/YY): ");
        String expiry = scanner.nextLine().trim();
        System.out.print("CVV (3 cyfry): ");
        String cvv = scanner.nextLine().trim();
        CardData card = new CardData(number, expiry, cvv);
        System.out.println("\nPrzetwarzam płatność... proszę czekać.");

            boolean ok = processor.authorize(card, amount);
            if (ok) {
                // Maskowanie numeru karty: pokazujemy tylko ostatnie 4 cyfry
                String masked = "**** **** **** " + number.substring(12);
                System.out.println("Płatność zatwierdzona! Karta: " + masked);
                System.out.printf("Pobrano: %.2f PLN%n", amount);
            } else {
                System.out.println("Płatność odrzucona przez bank.");
            }


    }






    }
//}