package old;

import cards.StandardCard;
import decks.StandardDeck;
import java.lang.*;
import java.util.*;

/**
 * This class simulates rounds of the Blackjack card game.
 * @author Julia Evans
 * @version 1.0
 */
public class BlackJackClient
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////fields
    private static StandardDeck deck;
    private final static BlackJackPlayer USER = new BlackJackPlayer();
    private final static BlackJackPlayer DEALER = new BlackJackPlayer();
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Map<String, Integer> FACE_CARDS_MAP = new HashMap<>()
    {{
        put("Jack", 10);
        put("Queen", 10);
        put("King", 10);
        put("Ace", 11);
    }};

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////main
    /**
     * Main method
     * @param args command line
     */
    public static void main(String[] args)
    {
        welcomeUser();
        createNewDeck();
        shuffleDeck();
        playGame();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////welcome user
    /**
     * Welcomes the user to the game by printing a message
     */
    private static void welcomeUser()
    {
        System.out.println("\nWelcome to my Blackjack console program!");
        System.out.println("*****************************************\n");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////create new deck
    /**
     * Instantiates a standard deck of cards and prints a message
     */
    private static void createNewDeck()
    {
        deck = new StandardDeck();
        System.out.println("Generated a new standard deck of cards");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////shuffle deck
    /**
     * Shuffles the standard deck of cards and prints a message
     */
    private static void shuffleDeck()
    {
        deck.shuffle();
        System.out.println("Shuffling deck...\n");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////play game
    /**
     * Starts a new game of black jack
     * If user does not bust, dealer will play a round
     * Asks user if they want to play again
     */
    private static void playGame()
    {
        userPlayGame();
        if(!USER.wentBust())
        {
            dealerPlayGame();
        }
        playAnotherHand();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////user play game
    /**
     * Starts a new game of blackjack for user
     */
    private static void userPlayGame()
    {
        dealCard(USER);

        boolean chooseHit;
        do
        {
            dealCard(USER);
            if(USER.wentBust())
            {
                System.out.println("\nYou went bust, you lose!");
                break;
            }
            else
            {
                System.out.print("Your total is " + USER.getRunningTotal() + ", hit?: ");
                chooseHit = SCANNER.nextBoolean();
            }
        }
        while(chooseHit);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////dealer play game
    /**
     * Starts a new game of blackjack for dealer and determines winner
     */
    private static void dealerPlayGame()
    {
        while(!gameOver())
        {
            dealCard(DEALER);
            System.out.println("Dealer total is " + DEALER.getRunningTotal());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////game over
    /**
     * Checks if the game is over
     * If so, prints a message saying who is the winner
     * @return boolean
     */
    private static boolean gameOver()
    {
        if(USER.isWinner() && DEALER.isWinner())
        {
            System.out.println("\nTie game!");
            return true;
        }
        else if(DEALER.isWinner())
        {
            System.out.println("\nDealer wins!");
            return true;
        }
        else if(DEALER.wentBust())
        {
            System.out.println("\nDealer went bust, you win!");
            return true;
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////play another hand
    /**
     * Asks the user if they want to play another hand
     * If so, resets card count and starts a new game
     */
    private static void playAnotherHand()
    {
        System.out.print("\nPlay another hand? (true/false): ");
        boolean playAnotherHand = SCANNER.nextBoolean();

        if(playAnotherHand)
        {
            USER.setRunningTotal(0);
            DEALER.setRunningTotal(0);
            playGame();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////deal card
    /**
     * Checks if deck needs replenishing
     * Deals a new card
     * Updates player's running total
     * Prints a message
     */
    private static void dealCard(BlackJackPlayer player)
    {
        replenishDeck();
        StandardCard card = deck.dealTopCard();

        if(player.equals(USER))
        {
            System.out.println("You are dealt a " + card);
            updateRunningTotal(USER, card);
        }
        else
        {
            System.out.println("\nDealer is dealt a " + card);
            updateRunningTotal(DEALER, card);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////replenish deck
    /**
     * This method checks if deck needs replenishing
     * If so, shuffles deck and prints a message
     */
    private static void replenishDeck()
    {
        if(deck.cardCount() == 0)
        {
            deck.shuffle();
            System.out.println("\nNo more cards, shuffling deck...");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////update running total
    /**
     * This method updates a player's running total after they are dealt a new card
     * @param player player
     * @param card card
     */
    private static void updateRunningTotal(BlackJackPlayer player, StandardCard card)
    {
        int newCardValue = getCardValue(card);
        int currentTotal = player.getRunningTotal();
        player.setRunningTotal(currentTotal + newCardValue);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////get card value
    /**
     * Getter for numeric card value
     * @param card standard card object
     * @return int value of card
     */
    private static int getCardValue(StandardCard card)
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////is face card
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


}
