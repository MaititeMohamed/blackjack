


public class Main {


    public static void main(String[] args) {
    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+---------[ Blackjack Game ]----------+");
       //playing Deck cards
        Deck playingDeck=new Deck();
        //create cards
        playingDeck.createCards();
        //shuffle cards
        playingDeck.shuffle();
    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t"+playingDeck.tostring());
    }



}