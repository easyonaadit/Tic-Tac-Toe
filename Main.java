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
                    printMessage("You Win!");
                    game.displayBoard(1);
                    io.setLose();
                    game.setAlive(false);
                    continue;
                }
                if (game.isDraw()) {
                    printMessage("Draw.");
                    game.displayBoard(1);
                    io.setDraw();
                    game.setAlive(false);
                    continue;
                }

                computer.playMove(game);
                game.evaluateBoard();
                if (game.isComputerWin()) {
                    printMessage("Computer Wins!");
                    game.displayBoard(1);
                    io.setWin();
                    game.setAlive(false);
                    continue;
                }
                if (game.isDraw()) {
                    printMessage("Draw.");
                    game.displayBoard(1);
                    io.setDraw();
                    game.setAlive(false);
                    continue;
                }
            }
            System.out.println("Want to play again? (Y/N)");
            char ch = (char) stdin.read();
            if (ch == 'n' || ch == 'N') {
                io.updateScores();
                break;
            }
            System.gc();

        } while (true);
    }

    public static void printMessage(String s){
        System.out.println(ConsoleColours.YELLOW.getColourCode() + s + ConsoleColours.RESET.getColourCode());
    }


}
