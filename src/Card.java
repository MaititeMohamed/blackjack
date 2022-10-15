public class Card {
    private  Suit suit;
    private  Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }
 //must be change display style letter
    @Override
    public String toString() {
        return
                "suit  -" + suit +
                "value -" + value
               ;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
