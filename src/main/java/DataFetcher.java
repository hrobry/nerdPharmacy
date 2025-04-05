// *****************************************************************
// Element 4: Połączenie z internetem i JSON (Gson)
// Klasa DataFetcher – pobiera dane o leku z internetu i parsuje je z JSON.

import java.util.*;
import java.util.stream.*; // Dla streamów i lambd
import java.sql.*;        // Połączenie z bazą (SQLite)
import java.net.*;        // Połączenie z internetem
import java.io.*;         // Operacje wejścia/wyjścia
import com.google.gson.*;  // Przetwarzanie JSON (biblioteka Gson)

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.DomText;
class DataFetcher {
    // Metoda symuluje pobieranie danych leku z internetu
    public static Medicine fetchMedicineData(String medicineName) {
        String pharmaURL = "";
        String url = "";
        switch (medicineName) {
            case "Apap":
                pharmaURL= "//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[6]/text()";
                url = "https://www.lekinfo24.pl/lek/Apap-migrena.html";
                break;
            case "Paracetamol":
                pharmaURL= "//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[6]/text()";
                 url ="https://www.lekinfo24.pl/lek/Paracetamol-DOZ.html";
                break;
            case "Hemorol":
                pharmaURL= "//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[6]/text()";
                url ="https://www.lekinfo24.pl/lek/Hemorol.html";
                break;
            case "Flegamina":
                pharmaURL= "//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[6]/text()";
                url ="https://www.lekinfo24.pl/lek/Flegamina-Classic.html";
                break;
            case "IbumForte":
                pharmaURL= "//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[6]/text()";
                url ="https://www.lekinfo24.pl/lek/Ibum-Express-Forte.html";
                break;
        }
       // paracetamol//*[@id="content"]/div[2]/table/tbody/tr[1]/td[6]
        ////*[@id="content"]/div[2]/table/tbody/tr[1]/td[6]/text()
        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);


            HtmlPage page = webClient.getPage(url);

            // Rzutowanie wyniku na HtmlSpan Apap
            DomText priceElement = (DomText) page.getFirstByXPath(pharmaURL);
            String price="";

            if (priceElement != null) {
                 price= priceElement.getTextContent();
                price = price.replaceAll("\\s", "");
                System.out.println("Cena: " + price);
            } else {
                System.out.println("Nie znaleziono elementu zawierającego cenę.");
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return null ;
    }
}

/*
        try {
            // Przykładowy URL – w rzeczywistości należałoby podać działające API
            URL url = new URL("https://api.example.com/medicines?name=" + URLEncoder.encode(medicineName, "UTF-8"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // Odczyt odpowiedzi z API
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Parsowanie JSON za pomocą biblioteki Gson
            Gson gson = new Gson();
            // Zakładamy, że struktura JSON odpowiada klasie Medicine
            Medicine med = gson.fromJson(response.toString(), Medicine.class);
            return med;
        } catch (Exception e) {
            // *****************************************************************
            // Element 7: Obsługa wyjątków – odpowiednie bloki try-catch
            System.out.println("Błąd pobierania danych: " + e.getMessage());
            return null;
        }
    }
}

*/
