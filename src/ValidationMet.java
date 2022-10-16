import java.text.NumberFormat;
import java.util.Scanner;

public class ValidationMet {

    public static String stringScannerMeth( byte length ){
        Scanner scanerObj = new Scanner(System.in);
        // Defining Variable :
        String Text ;
        do {
            Text = scanerObj.nextLine().toLowerCase();
            if( Text.length() > length && ( Text.equals("start") || Text.equals("leave")) ){
                break;
            }
            System.out.print("Field Cannot Be Empty And Must Be Either (Start) Or (Leave) : ");

        }while(true);

        return Text ;

    }
    public static byte getChoosenUserInputNumber(byte start , byte end , String text){
        // Defining Variables :
        byte choosenNum ;
        Scanner scanerObj = new Scanner(System.in);
        System.out.print(" \n PLease Enter " + text + " Here : ");
        do {
            if (scanerObj.hasNextByte()) { // Checking what users typed before he submittes , to know what type of data he entered
                choosenNum = scanerObj.nextByte();
                if (choosenNum >= start && choosenNum <= end) {
                    break;
                }
            }
            System.out.print(" Please Type A Valid Number : ");
            // Clearing Buffer so it refreshes , to read again from the user
            scanerObj.next();

        } while (true);

        return choosenNum ;
    }
    public static String formatNumber( int num ){
        return  NumberFormat.getCurrencyInstance().format(num);
    }
}
