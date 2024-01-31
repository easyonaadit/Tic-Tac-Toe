import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User extends DriverClass{
    public boolean gameStatus;
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    public void playMove(Game game){
        while (true){
            System.out.println("Enter the coordinates:");
            int x, y, index = 0;
            try{
                x = Integer.parseInt(stdin.readLine());
                y = Integer.parseInt(stdin.readLine());
                index = getIndex(x, y);
            }
            catch (Exception e){
                System.out.println("Exception occurred, try again.");
                continue;
            }

                if(index >=9 || index < 0){
                    System.out.println("Invalid coordinates");
                    continue;
                }
                if(game.Board[index] != 0){
                    System.out.println("Cell is already occupied.");
                    continue;
                }

                game.Board[index] = 1;
                game.displayBoard();
                break;




        }

    }
}
