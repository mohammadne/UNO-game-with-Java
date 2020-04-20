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
        System.out.println(9/5);

        InitGame.printRules();
        boolean start = InitGame.start();
        if (!start)
            System.exit(0);
        clearConsole();
        ArrayList<String> carts = CartsManager.createCarts();
        Collections.shuffle(carts, new Random());
        int playersCount = InitGame.getPlayers();
        Turn turn = InitGame.getTurn();
        clearConsole();

        DrawCarts.draw(carts);
        // for (String s : itemsArray)
        // System.out.println(s);
    }
}
