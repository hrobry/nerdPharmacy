// Klasa OrderItem – reprezentuje element zamówienia (lek oraz ilość).
class OrderItem {
    private Medicine medicine;
    private int quantity;

    public OrderItem(Medicine medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }
    public Medicine getMedicine() { return medicine; }
    public int getQuantity() { return quantity; }
}
