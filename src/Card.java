public class Card {
    private Value value;
    private Shape shape;

    public Card(Value card, Shape shape) {
        this.value = card;
        this.shape = shape;
    }

    public Value getCardVal() {
        return value;
    }

    public Shape getCardShape() {
        return shape;
    }

    @Override
    public String toString() {
        return "Card Value : " + getCardVal() + " || Card Shape : " + getCardShape() + "\n";
    }
}
