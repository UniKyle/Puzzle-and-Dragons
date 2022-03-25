/**
 * Tester for PAD Board and Simulator.
 */
import org.junit.*;
import static org.junit.Assert.*;

public class Tester {
    Board b = new Board();

    @Test
    public void testFindMatches() {
        //set up test board
        b.setBoard("DDDDDDHDBGBDHLGBBBLDRGBDDGLHHB");
        b.findMatches();
        System.out.println("yuh");
    }

    @Test
    public void testGetRowLength() {
        //set up test board
        b.setBoard("DDDDDDHDBGBDHLGBBBLDRGBDDGLHHB");

        assertEquals(5, b.getRowLength(b.board.get(0)));
        assertEquals(2, b.getRowLength(b.board.get(16)));
        assertEquals(0, b.getRowLength(b.board.get(6)));
    }

    @Test
    public void testGetColLength() {
        //set up test board
        b.setBoard("HHLDDDHHBGBDGHLBBBGGGLBDGGGGHB");

        assertEquals(2, b.getColLength(b.board.get(1)));
        assertEquals(2, b.getColLength(b.board.get(7)));
    }
}
