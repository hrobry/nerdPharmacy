
import java.util.*;
import java.util.stream.*; // Dla streamów i lambd
import java.sql.*;        // Połączenie z bazą (SQLite)
import java.net.*;        // Połączenie z internetem
import java.io.*;         // Operacje wejścia/wyjścia
import com.google.gson.*;  // Przetwarzanie JSON (biblioteka Gson)



public class MedicBrowser {

    private Connection conn;

    public MedicBrowser() {


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

        // Wstawianie leku do bazy danych
        public void insertMedicine(Medicine medicine) {
            String sql = "INSERT INTO medicines(name, price) VALUES(?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, medicine.getName());
                pstmt.setDouble(2, medicine.getPrice());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Błąd wstawiania konta: " + e.getMessage());
            }
        }

        // Usuwanie leku z bazy danych
        public void deleteMedicine(String name) {
            String sql = "DELETE FROM medicines WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Błąd usuwania leku: " + e.getMessage());
            }
        }

        // Pobranie nazw i cen (dla celów logowania)
        public String getnameIprice(int id) {
            String sql = "SELECT (name, price) FROM medicines WHERE Id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1,id);
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





