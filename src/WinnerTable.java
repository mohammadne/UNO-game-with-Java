import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WinnerTable {
    static void draw(List<String>[] playersCart) {
        Map<Integer, Integer> scores = new HashMap<>();

        for (int index = 0; index < playersCart.length; index++) {
            List<String> playerCart = playersCart[index];
            int playerScore = 0;
            for (String cart : playerCart) {
                if (cart.charAt(0) == 'W') {
                    playerScore += 50;
                } else {
                    String text = cart.split(" ")[1];
                    try {
                        int number = Integer.parseInt(text);
                        playerScore += number;
                    } catch (NumberFormatException nfe) {
                        playerScore += 20;
                    }
                }
            }
            scores.put(index, playerScore);
        }

        scores = new TreeMap<>(scores);

        System.out.println("____________________________");
        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
            System.out.println("Player index = " + entry.getKey() + ",   Score = " + entry.getValue());
            System.out.println("____________________________");
        }

        System.exit(0);
    }
}
