import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    //create cards
    public  void  createCards(){
        for (Suit suit: Suit.values()){
            for (Value  value :Value.values()) {
                this.cards.add(new Card(suit,value));
            }    
        }
    }

   public  String  tostring(){
    String cardlist ="";
    int i=1;
    for(Card card:this.cards){
        cardlist +="\n"+i+"-"+card.toString();
        i++;

    }

        return cardlist;
   }
}
