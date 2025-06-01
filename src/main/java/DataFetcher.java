// *****************************************************************
// Element 4: Połączenie z internetem i JSON (Gson)
// Klasa DataFetcher – pobiera dane o leku z internetu i parsuje je z JSON.


/*
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class DataFetcher {
    public static Medicine fetchMedicineData(String medicineName) {
        // Mapowanie na URL i XPath
        String url;
        switch (medicineName) {
            case "Apap":
                url = "https://www.lekinfo24.pl/lek/Apap-migrena.html";
                break;
            case "Paracetamol":
                url = "https://www.lekinfo24.pl/lek/Paracetamol-DOZ.html";
                break;
            case "Hemorol":
                url = "https://www.lekinfo24.pl/lek/Hemorol.html";
                break;
            case "Flegamina":
                url = "https://www.lekinfo24.pl/lek/Flegamina-Classic.html";
                break;
            case "IbumForte":
                url = "https://www.lekinfo24.pl/lek/Ibum-Express-Forte.html";
                break;
            default:
                throw new IllegalArgumentException("Nieobsługiwany lek: " + medicineName);
        }
        // XPath do komórki z ceną
                         //*[@id="content"]/div[3]/table/tbody/tr[1]/td[6]/text()
        String xpath = " //*[@id=\"content\"]/div[3]/table/tbody/tr[1]/td[6]/";
                        //*[@id="content"]/div[1]/table/tbody/tr[1]/td[6]
        try (WebClient webClient = new WebClient()) {
            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            HtmlPage page = webClient.getPage(url);
            HtmlElement cell = page.getFirstByXPath(xpath);

            if (cell == null) {
                System.err.println("Nie znaleziono ceny dla: " + medicineName);
                return null;
            }

            String rawPrice = cell.getTextContent().replaceAll("\\s+", "");
            System.out.println("Cena surowa: " + rawPrice);

// Wyciągamy tylko cyfry i przecinek
            String digitsOnly = rawPrice.replaceAll("[^0-9,]", "");
            double priceValue;
            try {
                priceValue = Double.parseDouble(digitsOnly.replace(',', '.'));
            } catch (NumberFormatException nfe) {
                System.err.println("Niepoprawny format ceny: " + rawPrice);
                return null;
            }

// Tworzymy i zwracamy obiekt Medicine
            return new Medicine(1,medicineName, priceValue);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

*/






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
                //*[@id="content"]/div[3]/table/tbody/tr[1]/td[6]/text()
                pharmaURL= "//*[@id=\"content\"]/div[3]/table/tbody/tr[1]/td[6]/text()";
                url = "https://www.lekinfo24.pl/lek/Apap-migrena.html";
                System.out.println("Apap");
                break;
            case "Paracetamol":
                pharmaURL= "//*[@id=\"content\"]/div[3]/table/tbody/tr[1]/td[6]/text()";
                 url ="https://www.lekinfo24.pl/lek/Paracetamol-DOZ.html";
                break;
            case "Hemorol":
                pharmaURL= "//*[@id=\"content\"]/div[3]/table/tbody/tr[1]/td[6]/text()";
                url ="https://www.lekinfo24.pl/lek/Hemorol.html";
                break;
            case "Flegamina":
                pharmaURL= "//*[@id=\"content\"]/div[3]/table/tbody/tr[1]/td[6]/text()";
                url ="https://www.lekinfo24.pl/lek/Flegamina-Classic.html";
                break;
            case "IbumForte":
                pharmaURL= "//*[@id=\"content\"]/div[3]/table/tbody/tr[1]/td[6]/text()";
                url ="https://www.lekinfo24.pl/lek/Ibum-Express-Forte.html";
                break;
            default:
                throw new IllegalArgumentException("Nieobsługiwany lek: " + medicineName);
        }
       // paracetamol//*[@id="content"]/div[2]/table/tbody/tr[1]/td[6]
        ////*[@id="content"]/div[2]/table/tbody/tr[1]/td[6]/text()2

        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);


            HtmlPage page = webClient.getPage(url);

            // Rzutowanie wyniku na HtmlSpan Apap
            DomText priceElement = (DomText) page.getFirstByXPath(pharmaURL);
            System.out.println(priceElement);
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


