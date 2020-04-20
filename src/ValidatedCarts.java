import java.util.ArrayList;
import java.util.List;

public class ValidatedCarts {
    public static List<Integer>[] validate(String turnedCart, List<String>[] playersCart) {
        int playersCartLength = playersCart.length;
        List<Integer>[] validatedCarts = new List[playersCart.length];

        for (int index = 0; index < playersCartLength; index++) {
            List<String> carts = playersCart[index];
            validatedCarts[index] = new ArrayList<Integer>();
            for (int alter = 0; alter < carts.size(); alter++) {
                if (carts.get(alter).equals("W Draw")) {
                } else if (carts.get(alter).charAt(0) == turnedCart.charAt(0)) {
                    validatedCarts[index].add(alter);
                } else if (carts.get(alter).split(" ")[1].equals(turnedCart.split((" "))[1])) {
                    validatedCarts[index].add(alter);
                }
            }
            if (validatedCarts[index].size() == 0) {
                for (int alter = 0; alter < carts.size(); alter++) {
                    if (carts.get(alter).equals("W Draw")) {
                        validatedCarts[index].add(alter);
                    }

                }
            }
        }

        return validatedCarts;
    }
}