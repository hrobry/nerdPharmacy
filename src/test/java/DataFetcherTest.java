
import org.junit.Test;
import static org.junit.Assert.*;

public class DataFetcherTest {

    @Test
    public void fetchMedicineData_shouldReturnNull_forUnsupportedMedicine() {
        // Dla nazwy, której nie obsługujemy, metoda nie powinna rzucać wyjątku
        // ale zwrócić null
        Medicine result = DataFetcher.fetchMedicineData("NieZnamLeku");
        assertNull("Nieobsługiwany lek powinien zwrócić null", result);
    }

    @Test
    public void fetchMedicineData_shouldNotThrow_forSupportedMedicine() {
        // Dla jednej z obsługiwanych nazw (np. "Apap") metoda nie powinna wyrzucać
        // żadnego wyjątku (zwrotnie null).
        Medicine result = null;
        try {
            result = DataFetcher.fetchMedicineData("Apap");
        } catch (Exception e) {
            fail("Metoda fetchMedicineData rzuciła wyjątek dla 'Apap': " + e.getMessage());
        }
        assertNull("W obecnej implementacji metoda zwraca null, ale nie rzuca wyjątku", result);
    }

    @Test
    public void fetchMedicineData_shouldNotThrow_forSupportedName() {
        // sprawdzamy, że dla "Apap" metoda nie wyrzuca wyjątku
        // i zwraca null (bo tak jest zaimplementowane)
        Medicine result = DataFetcher.fetchMedicineData("Apap");
        assertNull("W obecnej implementacji spodziewamy się null, ale metoda przeszła całą logikę", result);
    }
}