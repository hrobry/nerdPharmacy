// *****************************************************************
// Główna klasa aplikacji – łączy wszystkie elementy w całość.


import java.util.*;
import java.util.stream.*; // Dla streamów i lambd
import java.sql.*;        // Połączenie z bazą (SQLite)
import java.net.*;        // Połączenie z internetem
import java.io.*;         // Operacje wejścia/wyjścia
import com.google.gson.*;  // Przetwarzanie JSON (biblioteka Gson)



public class PharmacyApp {


    public static void main(String[] args) {


        // Główna pętla aplikacji
        while (true) {
            if (Questions.currentUser == null) {
                Questions.showMainMenu();
            } else {
                Questions.showUserMenu();
            }
        }

    }

}



