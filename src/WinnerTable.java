import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

        // Sorting the list based on values
        List<Entry<Integer, Integer>> list = new LinkedList<>(scores.entrySet());
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()));
        scores = list.stream()
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

        System.out.println();
        System.out.println("____________________________");
        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
            System.out.println("Player index = " + entry.getKey() + ",   Score = " + entry.getValue());
            System.out.println("____________________________");
        }

        System.exit(0);
    }
}
