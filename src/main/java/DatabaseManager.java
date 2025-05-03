// *****************************************************************
// Element 5: Połączenie z bazą danych (SQLite)
// Klasa DatabaseManager – zarządza połączeniem z bazą i operacjami na niej.

import java.util.*;
import java.util.stream.*; // Dla streamów i lambd
import java.sql.*;        // Połączenie z bazą (SQLite)
import java.net.*;        // Połączenie z internetem
import java.io.*;         // Operacje wejścia/wyjścia
import com.google.gson.*;  // Przetwarzanie JSON (biblioteka Gson)
class DatabaseManager {
    private Connection conn;

    public DatabaseManager() {
        try {
            // Załadowanie sterownika MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Utworzenie połączenia do bazy (plik pharmacy.db)
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/nerdApp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root",
                    "123"
            );

        } catch (Exception e) {
            System.out.println("Błąd połączenia z bazą danych: " + e.getMessage());
        }
    }

    // Wstawianie konta do bazy danych
    public void insertAccount(Customer customer) {
        String sql = "INSERT INTO accounts(username, password) VALUES(?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getUsername());
            pstmt.setString(2, customer.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd wstawiania konta: " + e.getMessage());
        }
    }

    // Usuwanie konta z bazy danych
    public void deleteAccount(String username) {
        String sql = "DELETE FROM accounts WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd usuwania konta: " + e.getMessage());
        }
    }

    // Pobranie hasła konta (dla celów logowania)
    public String getPassword(String username) {
        String sql = "SELECT password FROM accounts WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania konta: " + e.getMessage());
        }
        return null;
    }
}
