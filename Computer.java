public class Computer extends DriverClass {
    private boolean firstMove = true;

    public void playMove(Game game) {
        int posswin = game.possWin(2);  // returns index of winning position if possible, else returns -1;
        //posswin returns the linear index, need to convert it into 2-D coordinates
        if (posswin >= 0) {
            //play at that position returend by posswin
            game.Board[posswin] = 2;
            displayCompMove(game);
            return;
        }
        posswin = game.possWin(1);
        if (posswin >= 0) {
            //play at that position returend by posswin
            game.Board[posswin] = 2;
            displayCompMove(game);
            return;
        }

        if (firstMove) {
            if (userPlayedCorner(game.Board)) {
                //play center
                //afterthought: if user plays corner, then we should play in a block adjacent to that, this will prevent the situation where the user can make 2 winning possiblilities.
                int playerMove = getPlayerCorner(game.Board);
                game.Board[playerMove] = 2;
                displayCompMove(game);
            } else {
                //play corner
                game.Board[0] = 2;
                displayCompMove(game);
            }
            firstMove = false;
        } else {
            if (diagonalCorners(game.Board)) {
                if (game.Board[getIndex(0, 0)] == 0) game.Board[getIndex(0, 0)] = 2;
                else if (game.Board[getIndex(0, 2)] == 0) game.Board[getIndex(0, 2)] = 2;
                else if (game.Board[getIndex(2, 0)] == 0) game.Board[getIndex(2, 0)] = 2;
                else if (game.Board[getIndex(2, 2)] == 0) game.Board[getIndex(2, 2)] = 2;
                //play corner
                displayCompMove(game);
                return;
            }
            if (adjacentCorners(game.Board)) {
                if (game.Board[getIndex(0, 0)] == 1 && game.Board[getIndex(0, 2)] == 1) game.Board[getIndex(0, 1)] = 2;
                else if (game.Board[getIndex(0, 0)] == 1 && game.Board[getIndex(2, 0)] == 1)
                    game.Board[getIndex(1, 0)] = 2;
                else if (game.Board[getIndex(2, 0)] == 1 && game.Board[getIndex(2, 2)] == 1)
                    game.Board[getIndex(2, 1)] = 2;
                else if (game.Board[getIndex(2, 2)] == 1 && game.Board[getIndex(0, 2)] == 1)
                    game.Board[getIndex(1, 2)] = 2;
                //play between them.
                displayCompMove(game);
            } else {
                //play random move
                while (true){
                    int move = (int)(Math.random()*9);
                    if (game.Board[move] == 0){
                        game.Board[move] = 2;
                        displayCompMove(game);
                        return;
                    }
                }

            }
        }
    }

    private void displayCompMove(Game game) {
        System.out.println("\nComputers Turn:");
        int time = (int) (Math.random() * 1000);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.displayBoard();

    }

    private boolean userPlayedCorner(int[] Board) {
        //00, 02, 20, 22
        if (Board[getIndex(0, 0)] == 1 || Board[getIndex(0, 2)] == 1 || Board[getIndex(2, 0)] == 1 || Board[getIndex(2, 2)] == 1) {
            return true;
        } else return false;

    }

    private boolean diagonalCorners(int[] Board) {
        //00, 02, 20, 22
        if (Board[getIndex(0, 0)] == 1 && Board[getIndex(2, 2)] == 1 || Board[getIndex(2, 0)] == 1 && Board[getIndex(0, 2)] == 1) {
            return true;
        } else return false;

    }

    private boolean adjacentCorners(int[] Board) {
        //00, 02, 20, 22
        if (Board[getIndex(0, 0)] == 1 && Board[getIndex(0, 2)] == 1 || Board[getIndex(2, 0)] == 1 && Board[getIndex(2, 2)] == 1) {
            return true;
        } else return false;

    }
    private int getPlayerCorner(int[] Board){
        int random = (int)Math.floor(Math.random()*2);
        if (Board[0] == 1) if (random == 1) return 1; else return 3;
        if (Board[2] == 1) if (random == 1) return 1; else return 5;
        if (Board[6] == 1) if (random == 1) return 7; else return 3;
        if (Board[8] == 1) if (random == 1) return 7; else return 5;
        return -1;
    }
}
