import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class T3_ModelTest {

    private T3_Model model = new T3_Model();
    @Test
    void getPosition() {
        T3_Model.Player actual = model.getPosition(0,0);
        assertEquals(T3_Model.Player.__, actual);
    }

    @Test
    void currentPlayer() {
        T3_Model.Player actual = model.currentPlayer();
        assertEquals(T3_Model.Player.X, actual);
    }

    @Test
    void makeMove() {
    }


    @Test
    void makeMoveOutOfBounds() {
        boolean exceptionThrown = false;
        try {
            model.makeMove(0, 3);
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        if (!exceptionThrown) {
            fail("Make move failed to throw Exception when out of bounds");
        }
    }
    @Test
    void makeMoveNotVacant() {
    }

    @Test
    void getWinner() {
        // make a number of moves until someone wins
    }
}