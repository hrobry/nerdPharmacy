// Importy niezbędnych bibliotek:
import java.util.*;
import java.util.stream.*; // Dla streamów i lambd
import java.sql.*;        // Połączenie z bazą (SQLite)
import java.net.*;        // Połączenie z internetem
import java.io.*;         // Operacje wejścia/wyjścia
import com.google.gson.*;  // Przetwarzanie JSON (biblioteka Gson)

// *****************************************************************
// Element 1: Klasy abstrakcyjne, Dziedziczenie, Polimorfizm
// Abstrakcyjna klasa Account – reprezentuje konto użytkownika.
abstract class Account {
    protected String username;
    protected String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Metoda abstrakcyjna, którą implementują klasy dziedziczące
    public abstract void showMenu();
}

// Klasa Customer dziedziczy po Account i implementuje metodę showMenu – przykład polimorfizmu.
class Customer extends Account {
    public Customer(String username, String password) {
        super(username, password);
    }
    @Override
    public void showMenu() {
        System.out.println("Menu klienta: 1. Przeglądaj leki, 2. Zamawiaj, ...");
    }
}