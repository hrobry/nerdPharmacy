# Apteka dla NERD-a

Lekka konsolowa aplikacja w Javie, która pozwala na:

- **Rejestrację i logowanie** użytkowników  
- **Przeglądanie** dostępnych leków ze statycznej listy lub pobranych z internetu  
- **Symulację zamówień**: dodawanie leków do koszyka, podsumowanie i płatność  
- **Symulowaną płatność kartą** z walidacją danych i losowym wynikiem autoryzacji  
- **Połączenie z bazą MySQL** (konto, leki, zamówienia, pozycje zamówienia)  
- **Web scraping** za pomocą HTMLUnit do pobierania aktualnych cen leków  
- **Obsługę wyjątków**, strumienie, lambdy, klasy abstrakcyjne, interfejsy  

---

## Spis treści

1. [Wymagania](#wymagania)  
2. [Instalacja](#instalacja)  
3. [Konfiguracja bazy danych](#konfiguracja-bazy-danych)  
4. [Uruchomienie aplikacji](#uruchomienie-aplikacji)  
5. [Testy jednostkowe](#testy-jednostkowe)  
6. [Struktura projektu](#struktura-projektu)  
7. [Przyszłe usprawnienia](#przyszłe-usprawnienia)  

---

## Wymagania

- Java 8 lub nowsza  
- Maven (do zarządzania zależnościami i uruchamiania testów)  
- MySQL 5.7+ (lub 8.0)  
- (opcjonalnie) MySQL Workbench / phpMyAdmin do ręcznej inspekcji bazy  

---

## Instalacja

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/<nazwa-użytkownika>/nerd-pharmacy.git
   cd nerd-pharmacy
Zbuduj projekt:

bash
Kopiuj
mvn clean package
Konfiguracja bazy danych
Utwórz nowego użytkownika i bazę:

sql
Kopiuj
CREATE DATABASE IF NOT EXISTS nerdapp;
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'app_password';
GRANT ALL PRIVILEGES ON nerdapp.* TO 'appuser'@'localhost';
FLUSH PRIVILEGES;
Zaktualizuj dane w src/main/java/DatabaseManager.java (URL, użytkownik, hasło):

java
Kopiuj
private static final String URL      = "jdbc:mysql://localhost:3306/nerdapp"
                                    + "?useSSL=false"
                                    + "&allowPublicKeyRetrieval=true"
                                    + "&serverTimezone=UTC";
private static final String USER     = "appuser";
private static final String PASSWORD = "app_password";
Aplikacja sama utworzy tabele (accounts, medicines, orders, order_items) przy pierwszym uruchomieniu.

Uruchomienie aplikacji
bash
Kopiuj
java -cp target/nerd-pharmacy-1.0-SNAPSHOT.jar com.example.PharmacyApp
Po starcie w terminalu pojawi się menu:

Utwórz konto

Zaloguj się

Przeglądaj / Zamawiaj leki

Płatność (symulacja)

Pobierz cenę online
…

Testy jednostkowe
Projekt wykorzystuje JUnit 4. Aby uruchomić wszystkie testy:

bash
Kopiuj
mvn test
Najważniejsze klasy testowe:

DataFetcherTest – sprawdza obsługę nieobsługiwanych nazw i brak błędów przy poprawnym wejściu

MockCardPaymentProcessorTest – walidacja formatu karty oraz probabilistyczne testy autoryzacji

Struktura projektu
bash
Kopiuj
src/
 ├─ main/
 │   ├─ java/
 │   │   ├─ com/example/
 │   │   │   ├─ PharmacyApp.java        # punkt wejścia, menu konsolowe
 │   │   │   ├─ DatabaseManager.java    # inicjalizacja i operacje SQL
 │   │   │   ├─ DataFetcher.java        # web-scraping HTMLUnit
 │   │   │   ├─ CardData.java           # model danych karty
 │   │   │   ├─ PaymentException.java
 │   │   │   ├─ OptymalizedPaymentProcessor.java  # interfejs
 │   │   │   └─ MockCardPaymentProcessor.java     # symulacja płatności
 ├─ test/
 │   ├─ java/
 │   │   ├─ DataFetcherTest.java
 │   │   └─ MockCardPaymentProcessorTest.java
 pom.xml
 README.md
Przyszłe usprawnienia
Podmiana MockCardPaymentProcessor na prawdziwe API (Stripe, PayU)

Obsługa sesji użytkownika, szyfrowanie haseł (bcrypt)

Interfejs graficzny (Swing/JavaFX) lub REST API (Spring Boot)

Caching i lepsza walidacja danych z web-scrapingu