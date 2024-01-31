import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int win = 0, lose = 0, draw = 0;
        do {

        Game game = new Game();
        User user = new User();
        Computer computer = new Computer();
        while(game.isAlive()){
            user.playMove(game);
            game.evaluateBoard();
            if(game.isUserWin()){
                System.out.println(ConsoleColours.YELLOW.getColourCode()+"You Win!"+ConsoleColours.RESET.getColourCode());
                game.displayBoard();
                lose++;

                game.setAlive(false);
                continue;
            }
            if (game.isDraw()) {
                System.out.println(ConsoleColours.YELLOW.getColourCode()+"Draw."+ConsoleColours.RESET.getColourCode());
                game.displayBoard();
                draw++;

                game.setAlive(false);
                continue;
            }

            computer.playMove(game);
            game.evaluateBoard();
            if(game.isComputerWin()){
                System.out.println(ConsoleColours.YELLOW.getColourCode()+"Computer Wins!"+ConsoleColours.RESET.getColourCode());
                game.displayBoard();
                win++;
                game.setAlive(false);
                continue;
            }
            if (game.isDraw()) {
                System.out.println(ConsoleColours.YELLOW.getColourCode()+"Draw."+ConsoleColours.RESET.getColourCode());
                game.displayBoard();
                draw++;
                game.setAlive(false);
                continue;
            }
        }
        System.out.println("Want to play again? (Y/N)");
        char ch = (char)stdin.read();;



        if(ch == 'n' || ch == 'N'){
            try {
                File myObj = new File("scores.txt");
                Scanner myReader = new Scanner(myObj);
                int oldWin = 0, oldLose = 0, oldDraw = 0;
                for (int i = 0; i < 3; i++){
                    int data = Integer.parseInt(myReader.nextLine());

                    if(i == 0) oldWin = data;
                    if(i == 1) oldLose = data;
                    if(i == 2) oldDraw = data;
                }
                win += oldWin;
                lose += oldLose;
                draw += oldDraw;

                myReader.close();
                FileWriter filewrite;
                try
                {
                    filewrite = new FileWriter("scores.txt");

                    // Initialing BufferedWriter
                    BufferedWriter bufferwrite = new BufferedWriter(filewrite);
                    System.out.println("Writing scores to storage.");

                    // Use of write() method to write the value in 'ABC' file
                    bufferwrite.write(win+"\n");
                    bufferwrite.write(lose+"\n");
                    bufferwrite.write(draw+"");

                    // Closing BufferWriter to end operation
                    bufferwrite.close();
//                    System.out.println("Win % of Computer: " + (win/(win+lose+draw) * 100 + "%"));
                    DecimalFormat df = new DecimalFormat("0.00");
                    double winPercentage = ((double) win / (win + lose + draw) * 100);
                    System.out.println(ConsoleColours.CYAN.getColourCode()+"Win % of Computer: " + df.format(winPercentage) + "%"+ConsoleColours.RESET.getColourCode());
                }
                catch (IOException excpt)
                {
                    excpt.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            break;
        }

        } while (true);
    }
}
