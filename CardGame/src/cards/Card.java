package cards;

/**
 * This class represents a playing card in any type of card game
 * @author Julia Evans
 * @version 1.0
 */
public class Card
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////fields
    private final String cardText;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////constructors
    /**
     * Parameterized constructor for Card object
     * @param cardText card description
     */
    public Card(String cardText)
    {
        this.cardText = cardText;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////methods
    /**
     * Getter for cardText
     * @return String representation of the card description
     */
    public String getCardText()
    {
        return this.cardText;
    }

    @Override
    public String toString()
    {
        return this.getCardText();
    }

}
