import java.util.ArrayList;

public class CartsManager {

    public static ArrayList<String> createCarts() {
        ArrayList<String> cards = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            char cardType = _cartTypeById(i);
            for (int j = 0; j < 13; j++) {
                if (j < 10) {
                    cards.add("" + cardType + ' ' + j);
                } else {
                    cards.add("" + cardType + ' ' + _innerCartTypeById(j));
                }
            }
            for (int j = 1; j < 13; j++) {
                if (j < 10) {
                    cards.add("" + cardType + ' ' + j);
                } else {
                    cards.add("" + cardType + ' ' + _innerCartTypeById(j));
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                cards.add("W" + ' ' + "Color");
            } else {
                cards.add("W" + ' ' + "Draw");
            }
        }
        return cards;
    }

    private static char _cartTypeById(int id) {
        if (id == 0)
            return 'R';
        else if (id == 1)
            return 'Y';
        else if (id == 2)
            return 'G';
        else
            return 'B';
    }

    private static String _innerCartTypeById(int id) {
        if (id == 10)
            return "Skip";
        else if (id == 11)
            return "Reverse";
        else
            return "Draw2";
    }
}