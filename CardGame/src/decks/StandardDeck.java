package decks;

import cards.StandardCard;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a standard 52-card deck of playing cards
 * @author Julia Evans
 * @version 1.0
 */
public class StandardDeck extends Deck
{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////constructors
    /**
     * Default constructor for StandardDeck object
     * Creates a standard 52-card deck with each combination of the rank and suit values
     * Ranks: 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace
     * Suits: Hearts, Diamonds, Spades, Clubs
     */
    public StandardDeck()
    {
        ArrayList<String> standardRanks = new ArrayList<>(
                Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));

        ArrayList<String> standardSuits = new ArrayList<>(
                Arrays.asList("hearts", "diamonds", "spades", "clubs"));

        for (String rank : standardRanks)
        {
            for (String suit : standardSuits)
            {
                StandardCard newCard = new StandardCard(rank, suit);
                this.addCard(newCard);
            }
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////methods
    /**
     * This method hides the Deck.dealTopCard() method.
     * It retrieves the card from the parent class by calling dealTopCard() on the parent class and then casts the
     * Card object to a StandardCard object for ease of use.
     * @return StandardCard object
     */
    @Override
    public StandardCard dealTopCard()
    {
        return (StandardCard) super.dealTopCard();
    }
}
