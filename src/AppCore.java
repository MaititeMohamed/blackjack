import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Tiles --> Hearts --> Speads --> Clovers
public class AppCore {
    private int balance = 2_100; // $
    private int chosenBet;
    private ArrayList<Card> cardsList = new ArrayList<>();
    //array List for card chose by user
    private  ArrayList<Card> UserCards = new ArrayList<>();
    //array List for card chose by Dealer
    private  ArrayList<Card> DealerCards = new ArrayList<>();
    //total Cards values  of user
    private byte totalPlayerCardsValue;
    //total Cards values  of Dealer
    private byte totalDealerCardsValue;


    private void ClearData() {
        this.DealerCards.clear(); this.UserCards.clear();
        this.totalDealerCardsValue = 0; this.totalPlayerCardsValue = 0;
    }
    public  void whoIsTheWinner(){
        if( this.totalPlayerCardsValue == this.totalDealerCardsValue ) {
            //Draw
            this.winnerMessage((byte) 0);
        }
        else if ( this.totalPlayerCardsValue > this.totalDealerCardsValue && this.totalPlayerCardsValue <= 21 ){
            this.winnerMessage((byte) 1);
            //player win
        } else if( this.totalPlayerCardsValue > this.totalDealerCardsValue && this.totalPlayerCardsValue > 21 ){
            this.winnerMessage((byte) -1);
            //dealer win
        }
        else if ( this.totalDealerCardsValue > this.totalPlayerCardsValue && this.totalDealerCardsValue <= 21 ) {
            this.winnerMessage((byte) -1);
            //dealer win
        } else if ( this.totalDealerCardsValue > this.totalPlayerCardsValue && this.totalDealerCardsValue > 21 ){
            this.winnerMessage((byte) 1);
            //player win
        }
    }
    public void createCards(){
        for (Shape shape : Shape.values()){
            for ( Value value : Value.values() ){
                this.cardsList.add(new Card(value , shape));
            }
        }
    }
    public Card hitCard(){
        //hit card using random method for get card randomly
        Card card;
        //if cardlist not empty
        if(!this.cardsList.isEmpty()) {
            byte cardIndex = (byte) (Math.random() * this.cardsList.size());
            card = this.cardsList.get(cardIndex);
            this.cardsList.remove(cardIndex);
        }else {
            //if cardlist  empty  create new cardlist
            this.createCards();
            this.shuffleCards();
            //  Recursive Method
            card = this.hitCard();
        }
        return card;
    }
    public void hitOrStand(){
        int chosenOption ;
        int NumberCartHit = 3;
        boolean  isStand = false;
        Scanner scanner =new Scanner(System.in);
        Card card;
        do {
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t##################" + cardsList.size());
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[1]  Hit Card");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[2]  Stand");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tEnter Your Choice :");
            chosenOption=scanner.nextInt();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t##################\n");
            switch (chosenOption) {
                case 1:
                    card = hitCard();
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Player Card Number [ " + NumberCartHit +" ] : \t " + card.getCardVal().getName() + " \tOf \t "+ card.getCardShape().getName() + " \t Value [ " + getCardValue(card , true) +" ]\n");
                    this.UserCards.add(card);
                    this.totalPlayerCardsValue = TotalCardsValue(true);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tCurrent Total Points Of Your Cards : [ " + this.totalPlayerCardsValue + " ]");
                    break;
                case 2:
                    isStand = true;
                    break;

                case 4:
            }
            if (this.totalPlayerCardsValue > 20) isStand = true;
            NumberCartHit++;
        }while (!isStand);
    }
    public void hitCardfordealer(){
        byte NumberCartHit = 3;
        while( this.totalDealerCardsValue <= 16 ){
                Card card = hitCard();
                this.DealerCards.add(card);
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t  Dealer Hit  Card : \n");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Dealer Card Number [ " + NumberCartHit +" ] : \t " + card.getCardVal().getName() + " \tOf \t "+ card.getCardShape().getName() + " \t Value  [ " + getCardValue(card , false) +" ]\n");
                this.totalDealerCardsValue = TotalCardsValue(false);
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t Current Total Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                NumberCartHit++;
        }
    }
    public void shuffleCards(){
        Collections.shuffle(this.cardsList);
    }
    public void winnerMessage(byte Message){
        switch (Message){
            case -1 :
                this.balance -= this.chosenBet;
                // Dealer Wins
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t##################  You Lost  \n");
                System.out.println(" \t\t\t\t\t\t\t\t\t\t\t\tYour Total Points [ "+ this.totalPlayerCardsValue +" ]   Dealer Total Points [ "+ totalDealerCardsValue +" ] ");
                break;
            case 0 :
                //  Draw
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t################## Draw  ##################\n");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t################## Your Total Points [ "+ this.totalPlayerCardsValue +" ]  Dealer Total Points [ "+ totalDealerCardsValue +" ] ");
                break;
            case 1 :
                this.balance += this.chosenBet;
                // Player Wins
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t##################  Nice , You Won   ##################\n");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t################## Your Total Points [ "+ this.totalPlayerCardsValue +" ]  Dealer Total Points [ "+ totalDealerCardsValue +" ] ");
        }
    }
    public byte TotalCardsValue(boolean playerType){
        // call UserCards to store it in Card arr
        ArrayList<Card> arr = playerType ? this.UserCards : this.DealerCards;
        byte sum = 0;
        for( Card card : arr ){
            if( card.getCardVal().getName().equals("Las") && sum <= 10) {
                sum += 11;
            }else {
                sum += card.getCardVal().getCardGameVal();
            }
        }
        return sum;
    }
    public byte getCardValue(Card card , boolean playerType){
        byte total = playerType ? this.totalPlayerCardsValue : this.totalDealerCardsValue ;
        byte cardVal = card.getCardVal().getCardGameVal();
        if( card.getCardVal().getName().equals("Las") && total <= 10) {
            cardVal += 10;
        }
        return cardVal;
    }
    public void startGame(){
        int BalanceBet ;
        String  errorBalance;
        do {
            if(this.balance == 0) errorBalance = "  Your Balance not enough "; else errorBalance = "";
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+-------------------------------------+");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tYour Balance : " + NumberFormat.getCurrencyInstance().format(this.balance)+ errorBalance +"\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+-------------[I want Bet]------------+");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|   [1] Bet  $100   [4] Bet  $400     |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|   [2] Bet  $200   [5] Bet  $500     |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|   [3] Bet  $300   [6] Bet  $600     |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|   [0] Back To Menu                  |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+-------------------------------------+");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t  ");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Enter your choice  ");
            Scanner scanner =new Scanner(System.in);
            BalanceBet = scanner.nextInt();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+----------------------------------+");
            if(BalanceBet == 0) break;
            if(BalanceBet==1){
                this.chosenBet =100;
            } else if (BalanceBet==2) {
                this.chosenBet =200;
            } else if (BalanceBet==3) {
                this.chosenBet=300;
            } else if (BalanceBet==4) {
                this.chosenBet=400;
            }else if (BalanceBet==5) {
                this.chosenBet=500;
            }else if (BalanceBet==6) {
                this.chosenBet=600;
            }

            if(this.balance - this.chosenBet <=0) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t Your Balance not enough  ");
            }else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t you Bet  [ "+ NumberFormat.getCurrencyInstance().format( this.chosenBet) +" ]  ");
                this.shuffleCards();
                //user
                //add Two Cards hit to UserCards arralist
                Collections.addAll(this.UserCards , this.hitCard() , this.hitCard());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t  User Cards : ");
                Card card1 = this.UserCards.get(0);
                Card card2 = this.UserCards.get(1);
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Player Card Number [ 1 ] : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value  [ " + getCardValue(card1 , true) +" ]\n");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Player Card Number [ 2 ] : \t " + card2.getCardVal().getName() + " \tOf \t "+ card2.getCardShape().getName() + " \t Value  [ " + getCardValue(card2 , true) +" ]\n");
                this.totalPlayerCardsValue = this.TotalCardsValue(true);
                if(this.totalPlayerCardsValue == 21) {
                    System.out.println("\n \t\t\t\t\t\t\t\t\t\t\t\t ################## [Nice You Got A Black Jack ] ################## \t \t \t \t\n");
                }
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tTotal Points Of Your Cards : [ " + this.totalPlayerCardsValue + " ]\n");
                // Dealer Play
                Collections.addAll(this.DealerCards , this.hitCard() , this.hitCard());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t Dealer Cards : \n");
                card1 = this.DealerCards.get(0);
                card2 = this.DealerCards.get(1);
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Dealer Card Number  [1]  : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value [ " + getCardValue(card1 , false) +" ]\n");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Dealer Card Number  [2] : \t    Hidden  Card  \n");

                // User Choice
                if (this.totalPlayerCardsValue == 21){
                    if(this.totalDealerCardsValue == 21) {
                        // we have a  Draw hear
                        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Dealer Card Number [ 2 ] : \t " + card2.getCardVal().getName() + " \tOf \t "+ card2.getCardShape().getName() + " \t Value [ " + getCardValue(card2 , false) +" ]\n");
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t ################## :  [Black Jack for Dealer] ################## \t \t \t \t\n");
                        this.totalDealerCardsValue = this.TotalCardsValue(false);
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t Total Points Of  Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                        this.winnerMessage((byte) 0);
                    } else {
                        // Player Wins
                        this.chosenBet *= 1.5;
                        this.winnerMessage((byte) 1);
                    }
                } else  {
                    this.hitOrStand();
                    // Showing The  hidene Card for Dealer
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Dealer Card Number [2] : \t " + card2.getCardVal().getName() + " \tOf \t "+ card2.getCardShape().getName() + " \t Value  [ " + getCardValue(card2 , false) +" ]\n");
                    this.totalDealerCardsValue = this.TotalCardsValue(false);
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tCurrent Total Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                }

                // hit for dealer
                if ( this.totalPlayerCardsValue <= 21 ) {
                    this.hitCardfordealer();
                }

                whoIsTheWinner();
                ClearData();

            }

        }while (chosenBet != 0);
    }

}