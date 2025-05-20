// Prosta klasa przechowująca dane karty
class CardData {
    String number;   // np. "1234567812345678"
    String expiry;   // MM/YY
    String cvv;      // trzy cyfry

    public CardData(String number, String expiry, String cvv) {
        this.number = number;
        this.expiry = expiry;
        this.cvv = cvv;
    }
}