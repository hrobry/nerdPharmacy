import com.google.gson.Gson;

import java.util.*;

import java.util.*;
import java.util.stream.*; // Dla streamów i lambd
import java.sql.*;        // Połączenie z bazą (SQLite)
import java.net.*;        // Połączenie z internetem
import java.io.*;         // Operacje wejścia/wyjścia
import com.google.gson.*;  // Przetwarzanie JSON (biblioteka Gson)


public class Questions {


    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Customer> users = new HashMap<String, Customer>(); // Przechowywanie kont w pamięci
    private static List<Medicine> medicines = new ArrayList<Medicine>();
    public static Customer currentUser = null;
    private static List<OrderItem> cart = new ArrayList<OrderItem>();
    private static PaymentProcessor paymentProcessor = new PaymentProcessorImpl();
    private static DatabaseManager dbManager = new DatabaseManager();
    private static MedicBrowser medicBrowser = new MedicBrowser();

    public Questions(){

    }

        // Menu dla niezalogowanych użytkowników
        public static void showMainMenu() {

            medicines.add(new Medicine(1, "Apap", 18.00));
            medicines.add(new Medicine(2, "Paracetamol", 7.40));
            medicines.add(new Medicine(3, "Hemorol", 13.70));
            medicines.add(new Medicine(4, "Flegamina", 14.80));
            medicines.add(new Medicine(5, "Ibum", 7.50));

            System.out.println("\n=== Apteka dla NERD-a ===");
            System.out.println("1. Utwórz konto");
            System.out.println("2. Zaloguj się");
            System.out.println("3. Wyjdź");
            System.out.print("Wybierz opcję: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createAccount();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Praca Kacper Kopec nr albumu 18652 / Adam Gontarczyk nr albumu 23443");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
            }
        }
        // Menu dla zalogowanych użytkowników
        public static void showUserMenu() {
            System.out.println("\n=== Witaj " + currentUser.getUsername() + " ===");
            System.out.println("1. Przeglądaj dostępne leki");
            System.out.println("2. Pobierz dane leku z internetu");
            System.out.println("3. Zamów leki");
            System.out.println("4. Przejdź do płatności");
            System.out.println("5. Kasuj swoje konto");
            System.out.println("6. Wyloguj się");
            System.out.print("Wybierz opcję: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    browseMedicines();
                    break;
                case "2":
                    fetchMedicineData();
                    break;
                case "3":
                    orderMedicines();
                    break;
                case "4":
                   processPayment();
                    break;
                case "5":
                    deleteAccount();
                    break;
                case "6":
                    logout();
                    break;
                default:
                    System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
            }
        }

        // Tworzenie konta – zapis zarówno w pamięci, jak i w bazie danych
        public static void createAccount() {
            System.out.print("Podaj nazwę użytkownika: ");
            String username = scanner.nextLine();
            if (users.containsKey(username)) {
                System.out.println("Użytkownik o takiej nazwie już istnieje!");
                return;
            }
            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();
            Customer customer = new Customer(username, password);
            users.put(username, customer);
            // Zapis do bazy danych
            dbManager.insertAccount(customer);
            System.out.println("Konto utworzone pomyślnie!");
        }

        // Logowanie – weryfikacja hasła na podstawie danych z bazy
        public static void login() {
            System.out.print("Podaj nazwę użytkownika: ");
            String username = scanner.nextLine();
            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();
            String storedPassword = dbManager.getPassword(username);
            if (storedPassword != null && storedPassword.equals(password)) {
                currentUser = users.get(username);
                if (currentUser == null) {
                    // Jeśli konto istnieje w bazie, ale nie ma go w pamięci – utwórz obiekt
                    currentUser = new Customer(username, password);
                    users.put(username, currentUser);
                }
                cart.clear();
                System.out.println("Zalogowano pomyślnie!");
            } else {
                System.out.println("Błędna nazwa użytkownika lub hasło.");
            }
        }

        // Wylogowanie użytkownika
        private static void logout() {
            currentUser = null;
            cart.clear();
            System.out.println("Wylogowano pomyślnie.");
        }

        // Przeglądanie dostępnych leków
        private static void browseMedicines() {
            System.out.println("\n=== Lista dostępnych leków ===");
            medicines.forEach(med -> System.out.printf("%d. %s: %.2f PLN\n",med.getId(), med.name, med.price)); //// LAMBDA !!!
        }




    // Zamawianie leków – dodanie leku do koszyka
        private static void orderMedicines() {
            System.out.println("\n=== Zamawianie leków ===");
            browseMedicines();
            System.out.print("Podaj ID leku, który chcesz zamówić: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                Optional<Medicine> selectedOpt = medicines.stream().filter(m -> m.getId() == id).findFirst();
                if (!selectedOpt.isPresent()) {
                    System.out.println("Lek o podanym ID nie istnieje.");
                    return;
                }
                Medicine selected = selectedOpt.get();
                System.out.print("Podaj ilość: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    System.out.println("Ilość musi być większa niż 0.");
                    return;
                }
                cart.add(new OrderItem(selected, quantity));
                System.out.println("Dodano do koszyka: " + selected.name + " x " + quantity);
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowa wartość. Upewnij się, że wpisujesz liczby.");
            }
        }

        // Przetwarzanie płatności z użyciem PaymentProcessor (interfejs)
        private static void processPayment() {
        boolean wrongPayment=false;
            if (cart.isEmpty()) {
                System.out.println("Twój koszyk jest pusty.");
                return;
            }   try {
                PaymentSimulator PaymentCard = new PaymentSimulator();
                PaymentCard.firstStepPayment();
                PaymentCard.secondStepPayment();


            } catch (PaymentException e) {
                wrongPayment=true;
                System.out.println(e.getMessage());


            }
            if (wrongPayment==false) {
                cart.clear();
                System.out.println("Twój koszyk jest pusty.");
            }else{
                System.out.println("podaj numer jeszcze raz");
            }

        }


        // Usuwanie konta – kasowanie konta z pamięci oraz bazy danych
        private static void deleteAccount() {
            System.out.print("Czy na pewno chcesz usunąć swoje konto? (tak/nie): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("tak")) {
                dbManager.deleteAccount(currentUser.getUsername());
                users.remove(currentUser.getUsername());
                currentUser = null;
                cart.clear();
                System.out.println("Konto zostało usunięte.");
            } else {
                System.out.println("Anulowano usunięcie konta.");
            }
        }

        // Pobieranie danych leku z internetu z użyciem DataFetcher oraz konwersja do JSON przy pomocy Gson
        private static void fetchMedicineData() {
            System.out.print("Podaj nazwę leku do wyszukania: ");
            String name = scanner.nextLine();
            Medicine med = DataFetcher.fetchMedicineData(name);


        }


 }
