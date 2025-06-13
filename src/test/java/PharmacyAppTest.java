
import static org.junit.Assert.*;
import org.junit.Test;

public class PharmacyAppTest {
    // Test sprawdzający metodę getDescription() w klasie Medicine
    @Test
    public void testMedicineDescription() {
        Medicine med = new Medicine(1, "TestLek", 15.0);
        String desc = med.getDescription();
        assertTrue(desc.contains("TestLek"));

        assertTrue(desc.contains("15.0"));
    }

    // Test sprawdzający poprawność obliczeń dla zamówienia
    @Test
    public void testOrderCalculation() {
        Medicine med = new Medicine(1, "TestLek", 10.0);
        OrderItem item = new OrderItem(med, 3);

        double total = med.getPrice() * item.getQuantity();

        assertEquals(30.0, total, 0.001);
    }
}
