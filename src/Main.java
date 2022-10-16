import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Menu();
    }
    public static   void  createGame(){
        AppCore game = new AppCore();
        game.createCards();
        game.startGame();
    }
    public static  void  leave(){
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+----------------------------------+");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|---------  See you soon  ---------|");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t+----------------------------------+");
    }


    public static void  Menu(){
        Scanner scanner =new Scanner(System.in);
        int chose =10;
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
                    createGame();
                    break;
                case 0:
                    leave();
                    break;
            }
        }while (chose!=0);
    }
}
