import java.util.Scanner;


public class InitGame {
    public static int getPlayers() {
        System.out.print("How Many Players There Are (3-5)? ");
        final int playersCount = new Scanner(System.in).nextInt();
        return playersCount;
    }

    public static Turn getTurn() {
        System.out.print("Insert 1 for clock wise , anything else for anti clock wise: ");
        final String inputChar = new Scanner(System.in).nextLine();
        if (inputChar.equals("1"))
            return Turn.ClOCKWISE;

        return Turn.ANTIClOCKWISE;
    }

    public static void printRules() {
        System.out.println("Game Rules:");
        System.out.println("R:Red , Y:YELLOW , G:GREEN , B:BLUE , W:WILD");
        System.out.println("Then there are 0-9 numbers and Colored Skip,Reverse,Draw2");
        System.out.println("Wild cards are Draw4 and Color");
        System.out.println("To exit game print EXIT during any text inputs");
    }

    public static boolean start()  {
        System.out.println("if you want to start the game , type : YES , anything else to exit.");
        final String inputChar = new Scanner(System.in).nextLine();
        if (inputChar.toLowerCase().equals("yes")) {
            return true;
        }

        return false;
    }

}