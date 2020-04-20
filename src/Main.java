import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

enum Turn {
    ClOCKWISE, ANTIClOCKWISE,
}

public class Main {

    static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        InitUNO.printRules();
        boolean start = InitUNO.start();
        if (!start)
            System.exit(0);
        clearConsole();
        ArrayList<String> carts = CartsManager.createCarts();
        Collections.shuffle(carts, new Random()); 
        int playersCount = InitUNO.getPlayers();
        Turn turn = InitUNO.getTurn();
        clearConsole();
    }
}
