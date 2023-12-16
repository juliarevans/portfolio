package decks;

import cards.Card;
import java.util.ArrayList;

/**
 * This class represents a deck. A deck represents two groups of cards, a dealer pile and a discard pile.
 * Cards are dealt to a player from the dealer pile and then placed in the discard pile afterwards.
 * @author Julia Evans
 * @version 1.0
 */
public class Deck
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////fields
    private final ArrayList<Card> dealerPile;
    private final ArrayList<Card> discardPile;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////constructors
    /**
     * Default constructor for Deck object
     */
    public Deck()
    {
        this.dealerPile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////methods
    /**
     * Adds a card to the dealer pile
     * @param card Card object
     */
    public void addCard(Card card)
    {
        dealerPile.add(card);
    }

    /**
     * Moves all cards from the discard pile to the dealer pile.
     * Then randomizes the position of all cards in the dealer pile.
     */
    public void shuffle()
    {
        //Move all cards from the discard pile to the dealer pile
        while(!discardPile.isEmpty())
        {
            dealerPile.add(discardPile.remove(0));
        }

        //Randomize the position of all cards in the dealer pile
        /*To write the shuffle method you can loop over each index of the array list of cards and swap that position
        with another random position in the list. This can be done using the Random class to repeatedly pick pairs of
        numbers and swapping their positions.*/
        for (int i = 0; i < dealerPile.size(); i++)
        {
            int randomIndex1 = (int) (Math.random() * dealerPile.size());
            int randomIndex2 = (int) (Math.random() * dealerPile.size());

            Card savedCard = dealerPile.get(randomIndex1);
            dealerPile.set(randomIndex1, dealerPile.get(randomIndex2));
            dealerPile.set(randomIndex2, savedCard);
        }

    }

    /**
     * Removes the "top" card from the dealer pile and places it in the discard pile. Then returns the removed card.
     * @return Card object
     */
    public Card dealTopCard()
    {
        if(dealerPile.isEmpty())
        {
            shuffle();
        }
        Card topCard = dealerPile.remove(0);
        discardPile.add(topCard);
        return topCard;
    }

    /**
     * Returns the number of cards in the dealer pile.
     * @return int representation of card count
     */
    public int cardCount()
    {
        return dealerPile.size();
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result
            .append(cardCount()).append(" cards in deck")
            .append(System.lineSeparator())
            .append("*****************")
            .append(System.lineSeparator());
        for(Card card : dealerPile)
        {
            result
                .append(card.toString())
                .append(System.lineSeparator());
        }
        result
            .append(System.lineSeparator())
            .append(discardPile.size()).append(" cards in discard")
            .append(System.lineSeparator())
            .append("*******************")
            .append(System.lineSeparator());
        for(Card card : discardPile)
        {
            result.append(card.toString())
            .append(System.lineSeparator());
        }

        return result.toString();
    }
}
