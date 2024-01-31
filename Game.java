import java.io.Console;

public class Game {
    private boolean isAlive;
    private boolean userWin;
    private boolean computerWin;
    private boolean draw;
    public int[] Board; //0: empty cell,   1: user move,   2: computer move
    private int[] MagicSquare = {2, 7, 6, 9, 5, 1, 4, 3, 8};


    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setUserWin(boolean userWin) {
        this.userWin = userWin;
    }

    public void setComputerWin(boolean computerWin) {
        this.computerWin = computerWin;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public Game() {
        this.isAlive = true;
        this.userWin = false;
        this.computerWin = false;
        this.draw = false;
        Board = new int[9];
        setBoard();
    }

    private void setBoard(){
        for (int i = 0; i < 9; i++) Board[i] = 0;
    }

    public boolean isUserWin() {
        return userWin;
    }

    public boolean isComputerWin() {
        return computerWin;
    }

    public boolean isDraw() {
        return draw;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void evaluateBoard(){
        //check user win:
        for (int i = 0; i < 9; i++){
            if(Board[i] != 1) continue;
            for (int j = i+1; j < 9; j++){
                if(Board[j] != 1) continue;
                for (int k = j+1; k < 9; k++){
                    if(Board[k] != 1) continue;
                    if (MagicSquare[i] + MagicSquare[j] + MagicSquare[k] == 15){
                        System.out.println(i+", "+j+", "+k);
                        setUserWin(true);
                        return;
                    }
                }
            }
        }

        //check comp win:
        for (int i = 0; i < 9; i++){
            if(Board[i] != 2) continue;
            for (int j = i+1; j < 9; j++){
                if(Board[j] != 2) continue;
                for (int k = j+1; k < 9; k++){
                    if(Board[k] != 2) continue;
                    if (MagicSquare[i] + MagicSquare[j] + MagicSquare[k] == 15){
                        setComputerWin(true);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < 9; i++){
            if (Board[i] == 0) return;

        }

        setDraw(true);

    }

    public void displayBoard(){
        int i = 0;
        int count = 0;
        while (count < 3){
            displayRow(i);
            i += 3;
            if(count != 2) displayColumn();
            count++;


        }
        System.out.println();
    }

    private void displayRow(int i){
        int j = i+3;
        if(i != 0) System.out.println();
        for (; i < j; i++){
            if(Board[i] == 0){
                System.out.print(" ");
            }
            if(Board[i] == 1){
                System.out.print(ConsoleColours.RED.getColourCode() + "X" + ConsoleColours.RESET.getColourCode());
            }
            if(Board[i] == 2){
                System.out.print(ConsoleColours.BLUE.getColourCode() + "O" + ConsoleColours.RESET.getColourCode());
            }
            if(i != (j-1)){
                System.out.print(" | ");
            }

        }

    }

    private void displayColumn(){
        System.out.println();
        for (int j = 0; j < 9; j++) System.out.print("-");
    }

    public int possWin(int player){
        for (int i = 0; i < 9; i++){
            if (Board[i] != player) continue;
            for (int j = i+1; j < 9; j++){
                if (Board[j] != player) continue;
                for (int k = 0; k < 9; k++){
                    if(k == i || k == j) continue;
                    if(Board[k] != 0) continue;
                    if (MagicSquare[i] + MagicSquare[j] + MagicSquare[k] == 15) return k;
                }
            }
        }
        return -1;
    }
}
