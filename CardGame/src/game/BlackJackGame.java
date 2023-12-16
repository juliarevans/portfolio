package game;

import cards.StandardCard;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * This class simulates a BlackJack game
 * @author Julia Evans
 * @version 1.0
 */
public class BlackJackGame
{
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final BlackJack GAME = new BlackJack();
    private static final int VICTORY_CONDITION = 21;

    /**
     * @param args command line args
     */
    public static void main(String[] args)
    {
        playGame();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////play game
    /**
     * Starts a new game of black jack
     * If player does not bust, dealer will play a round
     * Asks player if they want to play again
     */
    private static void playGame()
    {
        playerPlayGame();
        if(!playerWentBust())
        {
            dealerPlayGame();
        }
        playAnotherHand();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////player play game ????????????
    /**
     * Deals cards to the player and asks if they want to hit or stand
     */
    private static void playerPlayGame()
    {
        Stream<StandardCard> cards = GAME.dealTwoCards();
        cards
                .forEach(c ->
                {
                    System.out.println("You are dealt a " + c.getCardText());
                    GAME.setPlayerTotal(GAME.getPlayerTotal() + c.getRankValue(c));
                });

        System.out.print("Your total is " + GAME.getPlayerTotal() + ", hit?: ");

        boolean chooseHit = SCANNER.nextBoolean();
        while(chooseHit)
        {
            StandardCard card = GAME.dealOneCard();
            System.out.println("You are dealt a " + card.getCardText());
            GAME.setPlayerTotal(GAME.getPlayerTotal() + card.getRankValue(card));

            if(playerWentBust())
            {
                System.out.println("\nYou went bust, you lose!");
                break;
            }
            else
            {
                System.out.print("Your total is " + GAME.getPlayerTotal() + ", hit?: ");
                chooseHit = SCANNER.nextBoolean();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////dealer play game
    /**
     * Starts a new game of blackjack for dealer and determines winner
     */
    private static void dealerPlayGame()
    {
        while(!gameOver())
        {
            StandardCard card = GAME.dealOneCard();
            System.out.println("\nDealer is dealt a " + card.getCardText());
            GAME.setDealerTotal(GAME.getDealerTotal() + card.getRankValue(card));
            System.out.println("Dealer total is " + GAME.getDealerTotal());
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
        if(playerIsWinner() && dealerIsWinner())
        {
            System.out.println("\nTie game!");
            return true;
        }
        else if(dealerIsWinner())
        {
            System.out.println("\nDealer wins!");
            return true;
        }
        else if(dealerWentBust())
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
            GAME.setPlayerTotal(0);
            GAME.setDealerTotal(0);
            playGame();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////player went bust
    /**
     * Checks if the player went bust (game over)
     * @return boolean
     */
    private static boolean playerWentBust()
    {
        return GAME.getPlayerTotal() > VICTORY_CONDITION;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////dealer went bust
    /**
     * Checks if the dealer went bust (game over)
     * @return boolean
     */
    private static boolean dealerWentBust()
    {
        return GAME.getDealerTotal() > VICTORY_CONDITION;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////player is winner
    /**
     * Checks if the player won the game
     * @return boolean
     */
    private static boolean playerIsWinner()
    {
        return GAME.getPlayerTotal() == VICTORY_CONDITION;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////dealer is winner
    /**
     * Checks if the dealer won the game
     * @return boolean
     */
    private static boolean dealerIsWinner()
    {
        return GAME.getDealerTotal() == VICTORY_CONDITION;
    }
}
