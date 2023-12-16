package cards;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the StandardCard class
 * @author Julia Evans
 * @version 1.0
 */
public class StandardCardTest
{
    private final StandardCard card = new StandardCard("Queen", "hearts");

    /**
     * This method tests the StandardCard constructor method
     */
    @Test
    public void testConstructor()
    {
        assertEquals("Card text is incorrect", "Queen of hearts", card.getCardText());
    }

    /**
     * This method tests the StandardCard getRank method
     */
    @Test
    public void testGetRank()
    {
        assertEquals("getRank method is incorrect", "Queen", card.getRank());
    }

    /**
     * This method tests the StandardCard getSuit method
     */
    @Test
    public void testGetSuit()
    {
        assertEquals("getSuit method is incorrect", "hearts", card.getSuit());
    }

    /**
     * This method tests the StandardCard getRankValue method
     */
    @Test
    public void testGetRankValue()
    {
        assertEquals("getRankValue method is incorrect", 10, card.getRankValue(card));

        StandardCard card2 = new StandardCard("Ace", "clubs");
        assertEquals("getRankValue method is incorrect", 11, card2.getRankValue(card2));

        StandardCard card3 = new StandardCard("3", "diamonds");
        assertEquals("getRankValue method is incorrect", 3, card3.getRankValue(card3));
    }

    /**
     * This method tests the StandardCard toString method
     */
    @Test
    public void testToString()
    {
        assertEquals("toString method is incorrect", "Queen of hearts", card.toString());
    }

}