// Klasa Medicine dziedziczy po Product – przykład dziedziczenia i polimorfizmu.
class Medicine extends Product {
    private int id;
    public Medicine(int id, String name, double price) {
        super(name, price);
        this.id = id;
    }
    public int getId() { return id; }
    public double getPrice() { return price; }
    @Override
    public String getDescription() {
        return "Lek: " + name + " - " + price + " PLN";
    }
    @Override
    public String toString() {
        return id + ". " + name + " - " + price + " PLN";
    }

    public String getName() {
        return name;
    }
}
