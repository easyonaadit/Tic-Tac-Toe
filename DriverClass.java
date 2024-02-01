public abstract class DriverClass {
    abstract public void playMove(Game game);

    public int getIndex(int x, int y) {
        return x * 3 + y;
    }


}
