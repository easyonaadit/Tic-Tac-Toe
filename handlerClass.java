import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class handlerClass {
    private Integer win;
    private Integer lose;
    private Integer draw;

    public handlerClass() {
        this.win = 0;
        this.lose = 0;
        this.draw = 0;
    }

    public void setWin() {
        this.win++;
    }

    public void setLose() {
        this.lose++;
    }

    public void setDraw() {
        this.draw++;
    }

    public void updateScores() {
        int newScores[] = {win, lose, draw};
        try {
            File myObj = new File("scores.txt");
            Scanner myReader = new Scanner(myObj);
            int scores[] = {0, 0, 0}; //win, lose, draw
            for (int i = 0; i < 3; i++) {
                scores[i] = Integer.parseInt(myReader.nextLine());
                scores[i] = scores[i] + newScores[i];
            }
            myReader.close();
            FileWriter filewrite;
            try {
                filewrite = new FileWriter("scores.txt");
                BufferedWriter bufferwrite = new BufferedWriter(filewrite);
                System.out.println("Writing scores to storage.");
                for (int i = 0; i < 3; i++) {
                    bufferwrite.write(scores[i] + "\n");
                }
                bufferwrite.close();
                displayOutput(scores);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void displayOutput(int scores[]) {
        DecimalFormat df = new DecimalFormat("0.00");
        double winPercentage = ((double) scores[0] / (scores[0] + scores[1] + scores[2]) * 100);
        System.out.println(ConsoleColours.CYAN.getColourCode() + "Win % of Computer: " + df.format(winPercentage) + "%" + ConsoleColours.RESET.getColourCode());
    }
}
