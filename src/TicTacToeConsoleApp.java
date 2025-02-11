interface T3Model{
    public enum Player {X, O, __}
    Player getPlayer(int i,int j);
}

public class TicTacToeConsoleApp {
    public static void main(String[] args) {
        T3Model model = null;
do{
        // Display board
      /*
            |     |
        -----------------
            |     |
        -----------------
            |     |
       */
        for (int i = 0; i < 3; i++) {

            System.out.println("--------------");
            for (int j = 0; j < 3; j++)
                System.out.print(model.getPlayer(i, j) + "  |  ");
        }
        // Display current player
        System.out.printf("Player is %s", model.currentPlayer());
        // Prompt user A1-C3
        // Make Move
        int row = 1, col = 1;
        model.makeMove(row, col);
        // ck if winner
        Player winner = model.getWinner();
        if (winner != T3Model.Player.__) {
            System.out.printf("Player %s won the game!", winner);
        }
        // if winner display winner, end game
        // else change player and repeat
    }while (winner == T3Model.Player.__);


    }
}
