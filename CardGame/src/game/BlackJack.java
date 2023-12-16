package game;

import cards.StandardCard;
import decks.StandardDeck;
import java.util.stream.Stream;

/**
 * This class represents Black Jack
 * @author Julia Evans
 * @version 1.0
 */
public class BlackJack
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////fields
    private final StandardDeck deck;
    private int playerTotal;
    private int dealerTotal;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////methods
    /**
     * Default constructor
     * Should create/shuffle the deck, set playerTotal and dealerTotal to 0, and display the welcome to game message
     */
    public BlackJack()
    {
        welcome();

        deck = new StandardDeck();
        System.out.println("Generated a new standard deck of cards");

        deck.shuffle();
        System.out.println("Shuffling deck...\n");

        setPlayerTotal(0);
        setDealerTotal(0);
    }

    /**
     * Getter for player's hand total
     * @return int
     */
    public int getPlayerTotal()
    {
        return this.playerTotal;
    }

    /**
     * Getter for dealer's hand total
     * @return int
     */
    public int getDealerTotal()
    {
        return this.dealerTotal;
    }

    /**
     * Setter for player's hand total
     * @param playerTotal new total
     */
    public void setPlayerTotal(int playerTotal)
    {
        this.playerTotal = playerTotal;
    }

    /**
     * Setter for dealer's hand total
     * @param dealerTotal new total
     */
    public void setDealerTotal(int dealerTotal)
    {
        this.dealerTotal = dealerTotal;
    }

    /**
     * Deals 2 cards from the deck.
     * If the deck does not have 2 cards to deal, the deck is shuffled.
     * @return stream of standard card objects
     */
    public Stream<StandardCard> dealTwoCards()
    {
        if(deck.cardCount() < 2)
        {
            deck.shuffle();
            System.out.println("\nNo more cards, shuffling deck...");
        }

        return Stream.of(deck.dealTopCard(), deck.dealTopCard());
    }

    /**
     * Deals 1 card from the deck.
     * If the deck does not have 1 card to deal, the deck is shuffled.
     * @return standard card object
     */
    public StandardCard dealOneCard()
    {
        if(deck.cardCount() == 0)
        {
            deck.shuffle();
            System.out.println("\nNo more cards, shuffling deck...");
        }
        return deck.dealTopCard();
    }

    /**
     * Displays a welcome message to the console
     */
    public void welcome()
    {
        System.out.println("\nWelcome to my Blackjack console program!");
        System.out.println("*****************************************\n");
    }

    @Override
    public String toString()
    {
        return "BlackJack{" +
                "playerTotal=" + playerTotal +
                ", dealerTotal=" + dealerTotal +
                ", deck=" + deck +
                '}';
    }
}
