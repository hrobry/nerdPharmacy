// *****************************************************************
// Element 3: Klasy abstrakcyjne i dziedziczenie w kontekście produktów
// Abstrakcyjna klasa Product – przykładowa klasa produktu.
abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public abstract String getDescription();
}
