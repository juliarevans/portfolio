import static org.junit.Assert.*;
import org.junit.*;
import java.io.ByteArrayOutputStream; // needed to redirect Standard Out
import java.io.PrintStream; // needed to redirect Standard Out

/**
 * This class is for jUnit tests for MagicSquare
 * @author Julia Evans
 * @version 1.0
 */
public class MagicSquareTest
{
    private static final byte[] selections = {2, 7, 6, 9, 5, 1, 4, 3, 8};
    private static final short[] cummulativeChoices =
            {
                0b0_0000_0010,
                0b0_0100_0010,
                0b0_0110_0010,
                0b1_0110_0010,
                0b1_0111_0010,
                0b1_0111_0011,
                0b1_0111_1011,
                0b1_0111_1111,
                0b1_1111_1111
            };

    /**
     * Method for testing constructor
     */
    @Test
    public void testConstructor()
    {
        MagicSquare magicSquare = new MagicSquare();
        assertEquals("choices should be 0", (short) 0, magicSquare.getChoices());
    }

    /**
     * Method for testing output for empty magic square
     */
    @Test
    public void testPrintChoicesEmptySquare()
    {
        MagicSquare magicSquare = new MagicSquare();

        // redirect output from console window into a PrintStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // invoke printChoices which now prints to the PrintStream instead of the console window
        magicSquare.printChoices();

        String expectedConsoleOutput = "_ _ _ "+ System.lineSeparator()+"_ _ _ "+System.lineSeparator()+"_ _ _ "+System.lineSeparator();
        assertEquals("print choices incorrect output", expectedConsoleOutput, out.toString());

    }

    /**
     * Method for testing choose() method
     */
    @Test
    public void testChoose()
    {
        MagicSquare magicSquare = new MagicSquare();

        // for every selection choice in values array
        for (int i = 0; i < selections.length; i++) {

            // choose num
            magicSquare.choose((byte) selections[i]);

            // verify that getChoices returns proper cummulative selections
            assertEquals("choice was noted incorrectly", cummulativeChoices[i], magicSquare.getChoices());
        }
    }

    /**
     * Method for testing hasAlreadyChosen() method
     */
    @Test
    public void testHasAlreadyChosen()
    {
        MagicSquare magicSquare = new MagicSquare();
        magicSquare.choose((byte) 1);

        assertFalse("Incorrect result", magicSquare.choose((byte) 1));
        assertTrue("Incorrect result", magicSquare.choose((byte) 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidChoice()
    {
        MagicSquare magicSquare = new MagicSquare();
        magicSquare.choose((byte) -7);
        magicSquare.choose((byte) 10);
    }

    /**
     * Method for testing output if magic square is full
     */
    @Test
    public void testPrintChoicesFullSquare()
    {
        MagicSquare magicSquare = new MagicSquare();

        // choose every selection in the values array
        for (byte selection : selections)
        {
            magicSquare.choose((byte) selection);
        }

        // redirect output from console window into a PrintStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // invoke printChoices which now prints to the PrintStream instead of the console window
        magicSquare.printChoices();

        String expectedConsoleOutput = "2 7 6 "+System.lineSeparator()+"9 5 1 "+System.lineSeparator()+"4 3 8 "+System.lineSeparator();
        assertEquals("print choices incorrect output", expectedConsoleOutput, out.toString());
    }

}