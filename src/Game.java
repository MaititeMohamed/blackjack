import java.util.ArrayList;
import java.util.Collections;

// Tiles --> Hearts --> Speads --> Clovers
public class Game {
    private int balance = 20_000; // $
    private int chosenBet;
    private ArrayList<Card> cardsList = new ArrayList<>();
    private short[] avalaibleBets = {1000 , 1500 , 2000 , 2500 , 3000 , 5000};// Storing avalaible bets
    private  ArrayList<Card> pickedUserCards = new ArrayList<>();
    private  ArrayList<Card> pickedDealerCards = new ArrayList<>();
    private byte totalPlayerCardsValue;
    private byte totalDealerCardsValue;

    // Rules : if Both black Jack -> Push /2 * 1.5 But if a black jack happens after first distribution it Only Counts As Double
    public void LaunchingNewGame(){
        /* *************************** Variables ***************************** */
        byte chosenBetNum ;
        String balance_formatted , smallMess;
        /* *************************** Variables ***************************** */
        System.out.println("\n Youre About To Start A New Game ... \n \n");
        do {
            if(this.balance == 0) smallMess = " ( Can't Play With This Balance )"; else smallMess = "";
            System.out.println("\n<3 % £> $ &> * §> <3 % £> $ &> * §> *%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*% <3 % £> $ &> * §> <3 % £> $ &> * §>");
            System.out.println("\t \t \t \t \t \t Current Balance : " + ValidationMet.formatNumber(this.balance) + smallMess +"\n");
            System.out.println("1 |-{> Bet With 1000");
            System.out.println("2 |-{> Bet With 1500");
            System.out.println("3 |-{> Bet With 2000");
            System.out.println("4 |-{> Bet With 2500");
            System.out.println("5 |-{> Bet With 3000");
            System.out.println("6 |-{> Bet With 5000");
            System.out.println("0 |-{> Leave Game <-| |");
            chosenBetNum = ValidationMet.getChoosenUserInputNumber((byte) 0 , (byte) 6 ,"The Number Of Chosen Bet");
            System.out.println("<3 % £> $ &> * §> <3 % £> $ &> * §> *%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*% <3 % £> $ &> * §> <3 % £> $ &> * §>\n");
            if(chosenBetNum == 0) break;
            this.chosenBet =  this.avalaibleBets[--chosenBetNum];
            if( !(this.balance - this.chosenBet >= 0) ) {
                System.out.println("\nYou Don't Have Enough Balance To Play With this Bet Or Your Balance run out  , Try Another Bet Or Come Back Later ... \n");
            }else {
                    balance_formatted = ValidationMet.formatNumber(this.chosenBet);
                    System.out.println("\n You Beted With  { [ "+ balance_formatted +" ] } \t Discounting Bet From Balance ....\n");
                    this.shuffleCards();
                    System.out.println("Distributing Cards .... \n");
                    /************************ User Play ************************/
                    Collections.addAll(this.pickedUserCards , this.hitCard() , this.hitCard());
                    System.out.println("Picked User Cards : \n");
                    Card card1 = this.pickedUserCards.get(0);
                    Card card2 = this.pickedUserCards.get(1);
                    this.totalPlayerCardsValue = this.getTotalCardsValue(true);
                    System.out.print("Given Player Card Number { 1 } : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value |-> { " + getCardValue(card1 , true) +" }\n");
                    System.out.print("Given Player Card Number { 2 } : \t " + card2.getCardVal().getName() + " \tOf \t "+ card2.getCardShape().getName() + " \t Value |-> { " + getCardValue(card2 , true) +" }\n");
                    if(this.totalPlayerCardsValue == 21) {
                        System.out.println("\n \t \t \t \t <3 % £> $ &> * §> <3 % £> $ &> * §> : ) Yeaaaahhh You Got A {{ Black Jack }} <3 % £> $ &> * §> <3 % £> $ &> * §> \t \t \t \t\n");
                    }
                    System.out.println("\nTotal Points Of Your Cards : [ " + this.totalPlayerCardsValue + " ]\n");
                    /************************ User Play ************************/
                    /************************ Dealer Play ************************/
                    Collections.addAll(this.pickedDealerCards , this.hitCard() , this.hitCard());
                    System.out.println("Picked Dealer Cards : \n");
                    card1 = this.pickedDealerCards.get(0);
                    card2 = this.pickedDealerCards.get(1);
                    this.totalDealerCardsValue = this.getTotalCardsValue(false);
                    System.out.print("Given Dealer Card Number { 1 } : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value |-> { " + getCardValue(card1 , false) +" }\n");
                    System.out.print("Given Dealer Card Number { 2 } : \t \t \t ~~ Second Dealer Card Is Flipped ~~ \n");
                    /************************ Dealer Play ************************/
                    /************************ Ask User Choice ************************/
                    if (this.totalPlayerCardsValue == 21){
                        if(this.totalDealerCardsValue == 21) { // Draw
                            System.out.print("Given Dealer Card Number { 2 } : \t " + card2.getCardVal().getName() + " \tOf \t "+ card2.getCardShape().getName() + " \t Value |-> { " + getCardValue(card2 , false) +" }\n");
                            System.out.println("\n \t \t \t \t <3 % £> $ &> * §> <3 % £> $ &> * §> : (  The Dealer Got  A {{ Black Jack }} <3 % £> $ &> * §> <3 % £> $ &> * §> \t \t \t \t\n");
                            System.out.println("\nTotal Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                            this.checkTheWinner((byte) 0);
                        } else {
                            // Player Wins
                            this.chosenBet *= 1.5; // Because Black Jack Happened At First Distribution
                            this.checkTheWinner((byte) 1);
                        }
                    } else  {
                            this.askUserForChoice();
                    }
                    // Showing The Second Flipped Card Of The Dealer
                    System.out.println("~ Flipping Card ~ ...\n");
                    System.out.print("Given Dealer Card Number { 2 } : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value |-> { " + getCardValue(card2 , false) +" }\n");
                    System.out.println("\nCurrent Total Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                // Calling The Function To Handle The Dealer Play
                    if ( this.totalPlayerCardsValue <= 21 ) {
                        this.autoHandleDealerMoves();
                    }
                    // Check The Winner
                    if( this.totalPlayerCardsValue == this.totalDealerCardsValue ) {
                        this.checkTheWinner((byte) 0);
                    }
                    else if ( this.totalPlayerCardsValue > this.totalDealerCardsValue && this.totalPlayerCardsValue <= 21 ){
                        this.checkTheWinner((byte) 1);
                    } else if( this.totalPlayerCardsValue > this.totalDealerCardsValue && this.totalPlayerCardsValue > 21 ){
                        this.checkTheWinner((byte) -1);
                    }
                    else if ( this.totalDealerCardsValue > this.totalPlayerCardsValue && this.totalDealerCardsValue <= 21 ) {
                        this.checkTheWinner((byte) -1);
                    } else if ( this.totalDealerCardsValue > this.totalPlayerCardsValue && this.totalDealerCardsValue > 21 ){
                        this.checkTheWinner((byte) 1);
                    }
                    // Clearing The Picked Dealer And Player Cards Array
                    this.pickedDealerCards.clear(); this.pickedUserCards.clear();
                    this.totalDealerCardsValue = 0; this.totalPlayerCardsValue = 0;
            }

        }while (chosenBet != 0);
    }
    public void createCards(){
        for (Shape shape : Shape.values()){
            for ( Value value : Value.values() ){
                this.cardsList.add(new Card(value , shape));
            }
        }
    }
    public void shuffleCards(){
        System.out.println("Shuffling Card's .... \n");
        Collections.shuffle(this.cardsList);
    }
    public Card hitCard(){
        Card card;
        if(!this.cardsList.isEmpty()) {
            byte cardIndex = (byte) (Math.random() * this.cardsList.size());
            card = this.cardsList.get(cardIndex);
            this.cardsList.remove(cardIndex);
        }else {
            System.out.println("Cards Run Out , Recreating Cards Again .... \n");
            this.createCards();
            this.shuffleCards();
            card = this.hitCard(); // Applied Recursive Method Call Principe
        }
        return card;
    }
    public void askUserForChoice(){
        byte chosenOption , counter = 3;
        boolean hideDouble = false , hasStanded = false;
        Card card;
        do {
            System.out.println("\n<3 % £> $ &> * §> <3 % £> $ &> * §> *%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*% <3 % £> $ &> * §> <3 % £> $ &> * §>" + cardsList.size());
            System.out.println("1 |-{> Hit Card");
            System.out.println("2 |-{> Stand");
            if(!hideDouble) System.out.println("3 |-{> Double Bet { " + this.chosenBet + " x 2 } = [ " + (this.chosenBet * 2) + " ]");
            System.out.println("4 |-{> Split Cards ( Currently Disabled )");
            chosenOption = ValidationMet.getChoosenUserInputNumber((byte) 1, (byte) 3, "The Number Of Chosen Play");
            System.out.println("<3 % £> $ &> * §> <3 % £> $ &> * §> *%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*% <3 % £> $ &> * §> <3 % £> $ &> * §>\n");

            switch (chosenOption) {
                case 1:
                    card = hitCard();
                    System.out.print("Given Player Card Number { " + counter +" } : \t " + card.getCardVal().getName() + " \tOf \t "+ card.getCardShape().getName() + " \t Value |-> { " + getCardValue(card , true) +" }\n");
                    this.pickedUserCards.add(card);
                    this.totalPlayerCardsValue = getTotalCardsValue(true);
                    System.out.println("\nCurrent Total Points Of Your Cards : [ " + this.totalPlayerCardsValue + " ]\n");
                    break;
                case 2:
                    hasStanded = true;
                    break;
                case 3:
                    this.doubleBet(counter);
                    hasStanded = true;
                    break;
                case 4:
            }
            if (this.totalPlayerCardsValue > 20) hasStanded = true;
            counter++;
        }while (!hasStanded);
    }
    public void autoHandleDealerMoves(){
        byte counter = 3;
        while( this.totalDealerCardsValue <= 16 ){
                Card card = hitCard();
                this.pickedDealerCards.add(card);
                System.out.println("\n\t \t The Dealer Hited The Following Card : \n");
                System.out.print("Given Dealer Card Number { " + counter +" } : \t " + card.getCardVal().getName() + " \tOf \t "+ card.getCardShape().getName() + " \t Value |-> { " + getCardValue(card , false) +" }\n");
                this.totalDealerCardsValue = getTotalCardsValue(false);
                System.out.println("\nCurrent Total Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                counter++;
        }
    }
    public void checkTheWinner(byte numState){
        switch (numState){
            case -1 :
                this.balance -= this.chosenBet;// -1 --> Dealer Wins
                System.out.println("\n<3 % £> $ &> * §> <3 % £> $ &> * §> Sadly You Lost This Round :{ , Best Luck Next Time <3 % £> $ &> * §> <3 % £> $ &> * §>\n");
                System.out.println("<3 % £> $ &> * §> <3 % £> $ &> * §> Your Total Points [ "+ this.totalPlayerCardsValue +" ] |-|-| Dealer Total Points [ "+ totalDealerCardsValue +" ] <3 % £> $ &> * §> <3 % £> $ &> * §>");
                break;
            case 0 :
                // 0 --> Draw
                System.out.println("\n<3 % £> $ &> * §> <3 % £> $ &> * §> This Rouned Finished With Draw :| , Best Luck Next Time <3 % £> $ &> * §> <3 % £> $ &> * §>\n");
                System.out.println("<3 % £> $ &> * §> <3 % £> $ &> * §> Your Total Points [ "+ this.totalPlayerCardsValue +" ] |-|-| Dealer Total Points [ "+ totalDealerCardsValue +" ] <3 % £> $ &> * §> <3 % £> $ &> * §>");
                break;
            case 1 :
                this.balance += this.chosenBet; // 1 --> Player Wins
                System.out.println("\n<3 % £> $ &> * §> <3 % £> $ &> * §> Yeeess , You Won This Round :) ! ! ! !  <3 % £> $ &> * §> <3 % £> $ &> * §>\n");
                System.out.println("<3 % £> $ &> * §> <3 % £> $ &> * §> Your Total Points [ "+ this.totalPlayerCardsValue +" ] |-|-| Dealer Total Points [ "+ totalDealerCardsValue +" ] <3 % £> $ &> * §> <3 % £> $ &> * §>");
        }
    }
    public void doubleBet(byte counter){
        Card card = hitCard();
        System.out.print("Given Player Card Number { " + counter +" } : \t " + card.getCardVal().getName() + " \tOf \t "+ card.getCardShape().getName() + " \t Value |-> { " + getCardValue(card , true) +" }\n");
        this.chosenBet *= 2;
        this.pickedUserCards.add(card);
        this.totalPlayerCardsValue = getTotalCardsValue(true);
        System.out.println("\nCurrent Total Points Of Your Cards : [ " + this.totalPlayerCardsValue + " ]\n");
    }
    public void splitCards(){}
    public byte getTotalCardsValue(boolean state){
        ArrayList<Card> arr = state ? this.pickedUserCards : this.pickedDealerCards;
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
    public byte getCardValue(Card card , boolean state){
        byte total = state ? this.totalPlayerCardsValue : this.totalDealerCardsValue ;
        byte cardVal = card.getCardVal().getCardGameVal();
        if( card.getCardVal().getName().equals("Las") && total <= 10) {
            cardVal += 10;
        }
        return cardVal;
    }

}