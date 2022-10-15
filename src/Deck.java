import java.util.ArrayList;
import java.util.Random;

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
    //shuffle Deck
  public  void  shuffle(){
       ArrayList<Card> TDeck =new ArrayList<Card>();
       //Random
       Random random =new Random();
       int randomCardIndex =0;
       int Size =this.cards.size();
       for (int i=0;i<Size;i++){
       //generate random index random.nextInt(max-min)+1)+min
           randomCardIndex = random.nextInt((this.cards.size()-1-0)+1)+0;
        TDeck.add(this.cards.get(randomCardIndex));
        //remove from original Deck
          this.cards.remove(randomCardIndex);
       }
      this.cards=TDeck;
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
