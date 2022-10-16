public class Main {
    public static void main(String[] args) {
        /* *************************** Variables ***************************** */
        String chosenOption;
        /* *************************** Game ***************************** */
        System.out.println("<3 % £> $ &> * §> ========================================= Black Jack Game ======================================= <3 % £> $ &> * §> \n");
        System.out.println("$|-->> Enter The [Start] Method To Begin A New Game");
        System.out.println("$|-->> Enter [Leave] To Leave Applicaton");
        System.out.print("\n \t Please Enter Your Choice Here : ");
        chosenOption = ValidationMet.stringScannerMeth((byte) 0);
        if(chosenOption.equals("start")) {
            Game game = new Game(); // Creating A New Game
            game.createCards(); // Calling The Function That Generate Cards And Storing Them To The CardsList by For Looping in each enum
            game.LaunchingNewGame();
        }
        System.out.println("<3 % £> $ &> * §> ========================================= Left Black Jack Game ======================================= <3 % £> $ &> * §>");
    }
}
