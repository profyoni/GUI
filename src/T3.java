public class T3 {
    public enum Player {X, O, __}
    // 3x3 board
    private Player board[][] = new Player[3][3];

    public Player getPosition(int x, int y){
        // TODO bounds check and vacancy check
        return board[x][y];
    }

    public Player currentPlayer()
    {
        return null; // TODO
    }
    /**
     * The currentPlayer's move
     * @param x
     * @param y
     */
    public void makeMove(int x, int y){}

    public Player getWinner(){
        return Player.__;
    }


}
