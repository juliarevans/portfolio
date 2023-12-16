package cards;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Card class
 * @author Julia Evans
 * @version 1.0
 */
public class CardTest
{
    private final Card card = new Card("Queen of hearts");

    /**
     * This method tests the Card constructor method
     */
    @Test
    public void testConstructor()
    {
        assertEquals("Card text is incorrect", "Queen of hearts", card.getCardText());
    }

    /**
     * This method tests the Card getCardText method
     */
    @Test
    public void testGetCardText()
    {
        assertEquals("getCardText method is incorrect", "Queen of hearts", card.getCardText());
    }

    /**
     * This method tests the Card toString method
     */
    @Test
    public void testToString()
    {
        assertEquals("toString method is incorrect", "Queen of hearts", card.toString());
    }

}