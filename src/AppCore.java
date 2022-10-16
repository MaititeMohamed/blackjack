import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Tiles --> Hearts --> Speads --> Clovers
public class AppCore {
    private int balance = 2_100; // $
    private int chosenBet;
    private ArrayList<Card> cardsList = new ArrayList<>();
    private short[] avalaibleBets = {100 , 200 , 300 , 400 , 500 , 600};// Storing avalaible bets
    private  ArrayList<Card> pickedUserCards = new ArrayList<>();
    private  ArrayList<Card> pickedDealerCards = new ArrayList<>();
    private byte totalPlayerCardsValue;
    private byte totalDealerCardsValue;

    public void startGame(){
        int BalanceBet ;
        String  errorBalance;
        do {
            if(this.balance == 0) errorBalance = "  Your Balance not enough "; else errorBalance = "";
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+----------------------------------+");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tYour Balance : " + NumberFormat.getCurrencyInstance().format(this.balance)+ errorBalance +"\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[1] Bet  $100");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[2] Bet  $200");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[3] Bet  $300");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[4] Bet  $400");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[5] Bet  $500");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[6] Bet  $600");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[0] Back To Menu ");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t  ");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Enter your choice  ");
            Scanner scanner =new Scanner(System.in);
            BalanceBet = scanner.nextInt();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+----------------------------------+");
            if(BalanceBet == 0) break;
            this.chosenBet =  this.avalaibleBets[--BalanceBet];
            if(this.balance - this.chosenBet <=0) {
             System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t Your Balance not enough  ");
            }else {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t you Bet  [ "+ NumberFormat.getCurrencyInstance().format( this.chosenBet) +" ]  ");
                    this.shuffleCards();
                    //user
                    //add Two Cards hit to pickedUsercards arralist
                    Collections.addAll(this.pickedUserCards , this.hitCard() , this.hitCard());
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t  User Cards : ");
                    Card card1 = this.pickedUserCards.get(0);
                    Card card2 = this.pickedUserCards.get(1);
                    System.out.print("Given Player Card Number [ 1 ] : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value  [ " + getCardValue(card1 , true) +" ]\n");
                    System.out.print("Given Player Card Number [ 2 ] : \t " + card2.getCardVal().getName() + " \tOf \t "+ card2.getCardShape().getName() + " \t Value  [ " + getCardValue(card2 , true) +" ]\n");
                    this.totalPlayerCardsValue = this.TotalCardsValue(true);
                    if(this.totalPlayerCardsValue == 21) {
                        System.out.println("\n \t \t \t \t ################## [Nice You Got A Black Jack ] ################## \t \t \t \t\n");
                    }
                    System.out.println("\nTotal Points Of Your Cards : [ " + this.totalPlayerCardsValue + " ]\n");
                    /************************ User Play ************************/
                    /************************ Dealer Play ************************/
                    Collections.addAll(this.pickedDealerCards , this.hitCard() , this.hitCard());
                    System.out.println("Picked Dealer Cards : \n");
                    card1 = this.pickedDealerCards.get(0);
                    card2 = this.pickedDealerCards.get(1);
                    System.out.print("Given Dealer Card Number  [1]  : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value [ " + getCardValue(card1 , false) +" ]\n");
                    System.out.print("Given Dealer Card Number  [2] : \t  Second Dealer Card Is Hidden   \n");
                    /************************ Dealer Play ************************/
                    /************************ Ask User Choice ************************/
                    if (this.totalPlayerCardsValue == 21){
                        if(this.totalDealerCardsValue == 21) { // Draw
                            System.out.print("Given Dealer Card Number { 2 } : \t " + card2.getCardVal().getName() + " \tOf \t "+ card2.getCardShape().getName() + " \t Value [ " + getCardValue(card2 , false) +" ]\n");
                            System.out.println("\n \t \t \t \t ################## : (  The Dealer Got  A[Black Jack] ################## \t \t \t \t\n");
                            this.totalDealerCardsValue = this.TotalCardsValue(false);
                            System.out.println("\nTotal Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                            this.checkTheWinner((byte) 0);
                        } else {
                            // Player Wins
                            this.chosenBet *= 1.5; // Because Black Jack Happened At First Distribution
                            this.checkTheWinner((byte) 1);
                        }
                    } else  {
                            this.askUserForChoice();
                        // Showing The Second Flipped Card Of The Dealer
                        System.out.println(" Flipping Card  \n");
                        System.out.print("Given Dealer Card Number [2] : \t " + card1.getCardVal().getName() + " \tOf \t "+ card1.getCardShape().getName() + " \t Value  [ " + getCardValue(card2 , false) +" ]\n");
                        this.totalDealerCardsValue = this.TotalCardsValue(false);
                        System.out.println("\nCurrent Total Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                    }

                  // Calling The Function To Handle The Dealer Play
                    if ( this.totalPlayerCardsValue <= 21 ) {
                        this.autoHandleDealerMoves();
                    }

                   whoIsTheWinner();
                   ClearData();

            }

        }while (chosenBet != 0);
    }

    private void ClearData() {
        this.pickedDealerCards.clear(); this.pickedUserCards.clear();
        this.totalDealerCardsValue = 0; this.totalPlayerCardsValue = 0;
    }
    public  void whoIsTheWinner(){
        if( this.totalPlayerCardsValue == this.totalDealerCardsValue ) {
            //Draw
            this.checkTheWinner((byte) 0);
        }
        else if ( this.totalPlayerCardsValue > this.totalDealerCardsValue && this.totalPlayerCardsValue <= 21 ){
            this.checkTheWinner((byte) 1);
            //player win
        } else if( this.totalPlayerCardsValue > this.totalDealerCardsValue && this.totalPlayerCardsValue > 21 ){
            this.checkTheWinner((byte) -1);
            //dealer win
        }
        else if ( this.totalDealerCardsValue > this.totalPlayerCardsValue && this.totalDealerCardsValue <= 21 ) {
            this.checkTheWinner((byte) -1);
            //dealer win
        } else if ( this.totalDealerCardsValue > this.totalPlayerCardsValue && this.totalDealerCardsValue > 21 ){
            this.checkTheWinner((byte) 1);
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
    public void shuffleCards(){
        Collections.shuffle(this.cardsList);
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
    public void askUserForChoice(){
        int chosenOption ;
        int NumberCartHit = 3;
        boolean  isStand = false;
        Scanner scanner =new Scanner(System.in);
        Card card;
        do {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\n##################" + cardsList.size());
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[1]  Hit Card");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[2]  Stand");
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tEnter Your Choice :");
            chosenOption=scanner.nextInt();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t##################\n");
            switch (chosenOption) {
                case 1:
                    card = hitCard();
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t Player Card Number [ " + NumberCartHit +" ] : \t " + card.getCardVal().getName() + " \tOf \t "+ card.getCardShape().getName() + " \t Value [ " + getCardValue(card , true) +" ]\n");
                    this.pickedUserCards.add(card);
                    this.totalPlayerCardsValue = TotalCardsValue(true);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\nCurrent Total Points Of Your Cards : [ " + this.totalPlayerCardsValue + " ]\n");
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
    public void autoHandleDealerMoves(){
        byte NumberCartHit = 3;
        while( this.totalDealerCardsValue <= 16 ){
                Card card = hitCard();
                this.pickedDealerCards.add(card);
                System.out.println("\n\t \t The Dealer Hited The Following Card : \n");
                System.out.print("Given Dealer Card Number { " + NumberCartHit +" } : \t " + card.getCardVal().getName() + " \tOf \t "+ card.getCardShape().getName() + " \t Value |-> { " + getCardValue(card , false) +" }\n");
                this.totalDealerCardsValue = TotalCardsValue(false);
                System.out.println("\nCurrent Total Points Of The Dealer Cards : [ " + this.totalDealerCardsValue + " ]\n");
                NumberCartHit++;
        }
    }
    public void checkTheWinner(byte numState){
        switch (numState){
            case -1 :
                this.balance -= this.chosenBet;// -1 --> Dealer Wins
                System.out.println("\n##################  You Lost This Round :  Best Luck Next Time \n");
                System.out.println(" Your Total Points [ "+ this.totalPlayerCardsValue +" ]   Dealer Total Points [ "+ totalDealerCardsValue +" ] ");
                break;
            case 0 :
                // 0 --> Draw
                System.out.println("\n################## This Rouned Finished With Draw :  Best Luck Next Time ##################\n");
                System.out.println("################## Your Total Points [ "+ this.totalPlayerCardsValue +" ]  Dealer Total Points [ "+ totalDealerCardsValue +" ] ##################");
                break;
            case 1 :
                this.balance += this.chosenBet; // 1 --> Player Wins
                System.out.println("\n##################  Nice , You Won This Round  ##################\n");
                System.out.println("################## Your Total Points [ "+ this.totalPlayerCardsValue +" ]  Dealer Total Points [ "+ totalDealerCardsValue +" ] ##################");
        }
    }
    public byte TotalCardsValue(boolean state){
        // call pickedUserCards to store it in Card arr
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