import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* *************************** Variables ***************************** */
         Scanner scanner =new Scanner(System.in);
        int chose =10;
        /* *************************** Game ***************************** */
         do {

             System.out.println("");
             System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+---------[ Black Jack Game ]---------+");
             System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|                                     |");
             System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|         Enter [1] for start         |");
             System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|         Enter [0] To Leave          |");
             System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|                                     |");
             System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+-------------------------------------+");
             System.out.println("");
             System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tEnter Your Choice :");
             chose=scanner.nextInt();
             switch (chose){
                 case 1:
                     Game game = new Game(); // Creating A New Game
                     game.createCards(); // Calling The Function That Generate Cards And Storing Them To The CardsList by For Looping in each enum
                     game.LaunchingNewGame();
                 break;
                 case 0:
                     System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+----------------------------------+");
                     System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|---------  See you soon  ---------|");
                     System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+----------------------------------+");
                     break;
             }
         }while (chose!=0);


    }
}
