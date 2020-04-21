import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

enum Direction {
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

        /// creates all carts
        ArrayList<String> carts = CartsManager.createCarts();
        /// shuffle the carts
        Collections.shuffle(carts, new Random());

        /// how many players do we have
        int playersCount = InitGame.getPlayers();
        /// game direction
        Direction direction = Direction.ClOCKWISE;
        /// Wild Color
        /// Z => means null
        String color = "Z";
        /// first random turn
        int turn = new Random().nextInt(playersCount);
        clearConsole();

        /// creates a list of carts for all the players
        List<String>[] playersCart = new List[playersCount];
        /// initiate players carts
        for (int index = 0; index < playersCount; index++) {
            playersCart[index] = new ArrayList<String>();
            for (int alter = 1; alter <= 7; alter++) {
                int elementIndex = carts.size() - index * 7 - alter;
                playersCart[index].add(carts.get(elementIndex));
                carts.remove(elementIndex);
            }
        }

        /// last played carts for each player
        String[] lastPlayedCart = new String[playersCount];
        for (int j = 0; j < playersCount; j++) {
            lastPlayedCart[j] = "Empty";
        }

        while (true) {
            /// validated carts
            List<Integer>[] validCarts = ValidatedCarts.validate(carts.get(carts.size() - 1), playersCart, color);

            /// end of game
            /// if someOnes cart is finished
            for (List<String> tempList : playersCart) {
                if (tempList.isEmpty()) {
                    clearConsole();
                    WinnerTable.draw(playersCart);
                }
            }
            /// if all players don't have valid carts
            int tempNum = 1;
            for (List<Integer> tempList : validCarts) {
                if (tempList.isEmpty())
                    tempNum++;
            }
            if (tempNum == playersCount) {
                clearConsole();
                WinnerTable.draw(playersCart);
            }

            /// print console part
            if (turn == 0) {
                clearConsole();
                /// print last played carts
                for (int index = 0; index < playersCount; index++) {
                    String playerName;
                    if (index == 0)
                        playerName = "You";
                    else
                        playerName = "Player " + index;
                    System.out.println("Last Played Cart Of " + playerName + " is: " + lastPlayedCart[index]);
                }
                /// draw centered cart
                System.out.println("\nCentered Cart Is:");
                DrawCarts.printSingleCart(carts.get(carts.size() - 1));
                /// print direction
                System.out.println("\nThe Direction is: " + direction);
                /// print turn
                System.out.println("It is your turn");
                /// print my carts
                System.out.println("\nYour Carts Are:");
                DrawCarts.draw(playersCart[0]);
                /// print what to do
                System.out.println("\nEnter index of your carts from 0-" + (playersCart[0].size() - 1) + " :");
                System.out.println("If you can't , type PICKUP");
                /// hint
                System.out.println("\nHint: You Could Enter the carts with index:");
                for (Integer index : validCarts[0]) {
                    System.out.print(" " + index);
                }
                System.out.print(" :");
            }
            /// calculation part
            int nextTurn = goToNextTurn(direction, turn, playersCount);
            if (turn == 0) {
                final String str = new Scanner(System.in).nextLine();
                if (validCarts[0].isEmpty()) {
                    if (str.toLowerCase().equals("pickup")) {
                        playersCart[0].add(carts.get(carts.size() - 1));
                        carts.remove(carts.size() - 1);
                        turn = nextTurn;
                        continue;
                    }
                } else {
                    try {
                        int index = Integer.parseInt(str);
                        if (validCarts[0].contains(index)) {
                            String cart = playersCart[0].get(index);
                            /// remove cart index
                            playersCart[0].remove(index);
                            /// add this cart on top of carts
                            carts.add(cart);
                            lastPlayedCart[0] = cart;
                            String a = cart.split(" ")[0];
                            String b = cart.split(" ")[1];
                            if (a != "W") {
                                color = "Z";
                                if (b.equals("Skip")) {
                                    turn = nextTurn;
                                    turn = nextTurn;
                                } else if (b.equals("Reverse")) {
                                    direction = toggleDirection(direction);
                                    turn = nextTurn;
                                } else if (b.equals("Draw2")) {
                                    playersCart[nextTurn].add(carts.get(carts.size() - 1));
                                    carts.remove(carts.size() - 1);
                                    playersCart[nextTurn].add(carts.get(carts.size() - 1));
                                    carts.remove(carts.size() - 1);
                                    turn = nextTurn;
                                    turn = nextTurn;
                                } else {
                                    turn = nextTurn;
                                }
                            } else {
                                /// Wilds carts
                                if (b.equals("Color")) {
                                    String tempColor = getColor();
                                    if (tempColor != null) {
                                        color = tempColor;
                                        turn = nextTurn;
                                    }
                                } else {
                                    String tempColor = getColor();
                                    if (tempColor != null) {
                                        color = tempColor;
                                        playersCart[nextTurn].add(carts.get(carts.size() - 1));
                                        carts.remove(carts.size() - 1);
                                        playersCart[nextTurn].add(carts.get(carts.size() - 1));
                                        carts.remove(carts.size() - 1);
                                        playersCart[nextTurn].add(carts.get(carts.size() - 1));
                                        carts.remove(carts.size() - 1);
                                        playersCart[nextTurn].add(carts.get(carts.size() - 1));
                                        carts.remove(carts.size() - 1);
                                        turn = nextTurn;
                                        turn = nextTurn;
                                    }
                                }
                            }
                        } else {
                            continue;
                        }
                    } catch (NumberFormatException nfe) {
                        continue;
                    }
                }
            } else {
                if (validCarts[turn].isEmpty()) {
                    playersCart[turn].add(carts.get(carts.size() - 1));
                    carts.remove(carts.size() - 1);
                    turn = nextTurn;
                } else {
                    int index = validCarts[turn].get(new Random().nextInt(validCarts[turn].size()));
                    String cart = playersCart[turn].get(index);
                    /// remove cart index
                    playersCart[turn].remove(index);
                    /// add this cart on top of carts
                    carts.add(cart);
                    lastPlayedCart[turn] = cart;
                    String a = cart.split(" ")[0];
                    String b = cart.split(" ")[1];
                    if (a != "W") {
                        color = "Z";
                        if (b.equals("Skip")) {
                            turn = nextTurn;
                            turn = nextTurn;
                        } else if (b.equals("Reverse")) {
                            direction = toggleDirection(direction);
                            turn = nextTurn;
                        } else if (b.equals("Draw2")) {
                            playersCart[nextTurn].add(carts.get(carts.size() - 1));
                            carts.remove(carts.size() - 1);
                            playersCart[nextTurn].add(carts.get(carts.size() - 1));
                            carts.remove(carts.size() - 1);
                            turn = nextTurn;
                            turn = nextTurn;
                        } else {
                            turn = nextTurn;
                        }
                    } else {
                        /// Wilds carts
                        if (b.equals("Color")) {
                            color = computerGetColor();
                            turn = nextTurn;

                        } else {
                            color = computerGetColor();
                            playersCart[nextTurn].add(carts.get(carts.size() - 1));
                            carts.remove(carts.size() - 1);
                            playersCart[nextTurn].add(carts.get(carts.size() - 1));
                            carts.remove(carts.size() - 1);
                            playersCart[nextTurn].add(carts.get(carts.size() - 1));
                            carts.remove(carts.size() - 1);
                            playersCart[nextTurn].add(carts.get(carts.size() - 1));
                            carts.remove(carts.size() - 1);
                            turn = nextTurn;
                            turn = nextTurn;

                        }
                    }
                }

            }
        }

    }

    static Direction toggleDirection(Direction direction) {
        if (direction == Direction.ClOCKWISE) {
            return Direction.ANTIClOCKWISE;
        }
        return Direction.ClOCKWISE;
    }

    static int goToNextTurn(Direction direction, int currentTurn, int playersCount) {
        int turn = currentTurn;
        if (direction == Direction.ClOCKWISE) {
            turn++;
            turn %= playersCount;
        } else {
            turn--;
            if (turn < 0) {
                turn += playersCount;
            }
        }
        return turn;
    }

    static String getColor() {
        System.out.print("Insert Your Color: ");
        final String inputChar = new Scanner(System.in).nextLine();
        if (inputChar.equals("R") || inputChar.equals("r"))
            return "R";
        else if (inputChar.equals("Y") || inputChar.equals("y"))
            return "Y";
        else if (inputChar.equals("G") || inputChar.equals("g"))
            return "G";
        else if (inputChar.equals("B") || inputChar.equals("b"))
            return "B";
        else
            return null;
    }

    static String computerGetColor() {
        List<String> givenList = Arrays.asList("R", "Y", "G", "B");
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    }

}
