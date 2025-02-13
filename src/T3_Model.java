public class T3_Model {
    public boolean isFull() {
        return false;
    }

    public enum Player {X, O, __}
    // 3x3 board
    private Player board[][] = new Player[3][3];

    private Player currentPlayer;
    public T3_Model()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Player.__;
            }
        }
        currentPlayer = Player.X;
    }
    public Player getPosition(int x, int y){
        // TODO bounds check and vacancy check
        return board[x][y];
    }

    public Player currentPlayer()
    {
        return currentPlayer;
    }
    /**
     * The currentPlayer's move
     * @param x
     * @param y
     */
    public void makeMove(int x, int y){
        // bounds check IllegalARgExc
        // vacancy check throw Invalid Op

    }

    public Player getWinner(){
        return Player.__;
    }


}
