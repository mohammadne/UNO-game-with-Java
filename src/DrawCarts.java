import java.util.ArrayList;

enum LineType {
    Border, None, Text
}

public class DrawCarts {
    public static void draw(ArrayList<String> carts) {
        for (int i = 0; i < carts.size() / 5; i++) {
            String[] itemsArray = new String[5];
            itemsArray = carts.subList(i * 5, (i + 1) * 5).toArray(itemsArray);
            println(itemsArray, LineType.Border);
            System.out.println();
            println(itemsArray, LineType.None);
            System.out.println();
            println(itemsArray, LineType.Text);
            System.out.println();
            println(itemsArray, LineType.None);
            System.out.println();
            println(itemsArray, LineType.Border);
            System.out.println();
        }
        if (carts.size() % 5 != 0) {
            int remaining = carts.size() % 5;
            String[] itemsArray = new String[remaining];
            itemsArray = carts.subList(carts.size() - remaining + 1, carts.size() + 1).toArray(itemsArray);
            println(itemsArray, LineType.Border);
            System.out.println();
            println(itemsArray, LineType.None);
            System.out.println();
            println(itemsArray, LineType.Text);
            System.out.println();
            println(itemsArray, LineType.None);
            System.out.println();
            println(itemsArray, LineType.Border);
            System.out.println();

        }
    }

    private static void println(String[] carts, LineType lineType) {
        for (int i = 0; i < carts.length; i++) {
            String text = carts[i];
            char cartType = text.charAt(0);
            String color;
            if (cartType == 'R')
                color = Colors.ANSI_RED;
            else if (cartType == 'Y')
                color = Colors.ANSI_YELLOW;
            else if (cartType == 'G')
                color = Colors.ANSI_GREEN;
            else if (cartType == 'B')
                color = Colors.ANSI_BLUE;
            else
                color = Colors.ANSI_WHITE;

            if (lineType == LineType.Border) {
                System.out.print(color + "|$$$$$$$$$$$$$$$|\t" + Colors.ANSI_RESET);
            } else if (lineType == LineType.None) {
                System.out.print(color + "|               |\t" + Colors.ANSI_RESET);
            } else if (lineType == LineType.Text) {
                StringBuilder str = new StringBuilder();
                int textLength = text.length();
                int startingIndex = 7 - textLength / 2;
                str.append("|");
                for (int j = 0; j < startingIndex; j++) {
                    str.append(" ");
                }
                str.append(text);
                for (int j = str.length(); j <= 15; j++) {
                    str.append(" ");
                }
                str.append("|\t");
                System.out.print(color + str.toString() + Colors.ANSI_RESET);
            }
        }
    }

}

class Colors {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
}