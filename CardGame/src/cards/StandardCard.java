package cards;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a standard playing card, with a suit and rank, that might be played in a Poker or Blackjack game
 * @author Julia Evans
 * @version 1.0
 */
public class StandardCard extends Card
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////fields
    private final String rank;
    private final String suit;
    private final static Map<String, Integer> FACE_CARDS_MAP = new HashMap<>()
    {{
        put("Jack", 10);
        put("Queen", 10);
        put("King", 10);
        put("Ace", 11);
    }};

    ////////////////////////////////////////////////////////////////////////////////////////////////////////constructors
    /**
     * Parameterized constructor for StandardCard object
     * @param rank card rank
     * @param suit card suit
     */
    public StandardCard(String rank, String suit)
    {
        super("" + rank + " of " + suit);
        this.rank = rank;
        this.suit = suit;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////methods
    /**
     * Getter for card rank
     * @return String representation of card rank
     */
    public String getRank()
    {
        return this.rank;
    }

    /**
     * Getter for card suit
     * @return String representation of card suit
     */
    public String getSuit()
    {
        return this.suit;
    }

    /**
     * This method returns an integer value for the rank of the card
     * @param card standard card
     * @return int
     */
    public int getRankValue(StandardCard card)
    {
        String cardRank = card.getRank();
        int cardValue;

        if(isFaceCard(cardRank))
        {
            cardValue = FACE_CARDS_MAP.get(cardRank);
        }
        else
        {
            cardValue = Integer.parseInt(cardRank);
        }

        return cardValue;
    }

    /**
     * Checks if card is a face card or not
     * Returns true if a face card, false otherwise
     * @param cardRank card rank
     * @return boolean
     */
    private static boolean isFaceCard(String cardRank)
    {
        return FACE_CARDS_MAP.containsKey(cardRank);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
