
import org.junit.Test;
import static org.junit.Assert.*;

public class DataFetcherTest {

    @Test(expected = IllegalArgumentException.class)
    public void fetchMedicineData_shouldThrow_forUnsupportedName() {
        // przy nieobsługiwanym leku rzucamy wyjątek
        DataFetcher.fetchMedicineData("NieZnamLeku");
    }

    @Test
    public void fetchMedicineData_shouldNotThrow_forSupportedName() {
        // sprawdzamy, że dla "Apap" metoda nie wyrzuca wyjątku
        // i zwraca null (bo tak jest zaimplementowane)
        Medicine result = DataFetcher.fetchMedicineData("Apap");
        assertNull("W obecnej implementacji spodziewamy się null, ale metoda przeszła całą logikę", result);
    }
}