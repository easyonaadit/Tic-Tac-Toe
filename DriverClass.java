public abstract class DriverClass {
    abstract public void playMove(Game game);

    public int getIndex(int x, int y){
        return x*3 + y;
    }

    public int[] getCoordinates(int a){
        int x = (int)a/3;
        int y = (int)a%3;
        int arr[] = new int[2];
        arr[0] = x;
        arr[1] = y;
        return arr;

    }

    public boolean hasWon(boolean gameStatus){
        return false;
    }
}
