import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum Turn {
    ClOCKWISE, ANTIClOCKWISE,
}

public class Main {

    static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
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
        List<String>[] arrays = new List[playersCount];
        for (int index = 0; index < playersCount; index++) {
            arrays[index] = new ArrayList<String>();
            for (int alter = 1; alter <= 7; alter++) {
                int elementIndex = carts.size() - index * 7 - alter;
                arrays[index].add(carts.get(elementIndex));
                carts.remove(elementIndex);
            }
        }
        DrawCarts.draw(arrays[0]);
        // for (String s : itemsArray)
        // System.out.println(s);
    }
}
