import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        handlerClass io = new handlerClass();
        do {
            Game game = new Game();
            User user = new User();
            Computer computer = new Computer();
            while (game.isAlive()) {
                user.playMove(game);
                game.evaluateBoard();
                if (game.isUserWin()) {
                    System.out.println(ConsoleColours.YELLOW.getColourCode() + "You Win!" + ConsoleColours.RESET.getColourCode());
                    game.displayBoard();
                    io.setLose();
                    game.setAlive(false);
                    continue;
                }
                if (game.isDraw()) {
                    System.out.println(ConsoleColours.YELLOW.getColourCode() + "Draw." + ConsoleColours.RESET.getColourCode());
                    game.displayBoard();
                    io.setDraw();
                    game.setAlive(false);
                    continue;
                }

                computer.playMove(game);
                game.evaluateBoard();
                if (game.isComputerWin()) {
                    System.out.println(ConsoleColours.YELLOW.getColourCode() + "Computer Wins!" + ConsoleColours.RESET.getColourCode());
                    game.displayBoard();
                    io.setWin();
                    game.setAlive(false);
                    continue;
                }
                if (game.isDraw()) {
                    System.out.println(ConsoleColours.YELLOW.getColourCode() + "Draw." + ConsoleColours.RESET.getColourCode());
                    game.displayBoard();
                    io.setDraw();
                    game.setAlive(false);
                    continue;
                }
            }
            System.out.println("Want to play again? (Y/N)");
            char ch = (char) stdin.read();
            ;
            if (ch == 'n' || ch == 'N') {
                io.updateScores();
                break;
            }

        } while (true);
    }
}
