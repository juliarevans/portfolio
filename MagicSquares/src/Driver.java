import java.util.Scanner;

/**
 * This class lists the rules of the game and prompts for two players.
 * @author Julia Evans
 * @version 1.0
 */
public class Driver
{
    private static String player1;
    private static String player2;
    private static MagicSquare magicSquare1;
    private static MagicSquare magicSquare2;
    private static MagicSquare magicSquare3; //both players
    private static final Scanner INPUT = new Scanner(System.in);
    private static final int VICTORY_CONDITION1 = 98; //A1 + A2 + A3
    private static final int VICTORY_CONDITION2 = 273; //B1 + B2 + B3
    private static final int VICTORY_CONDITION3 = 140; //C1 + C2 + C3
    private static final int VICTORY_CONDITION4 = 266; //A1 + B1 + C1
    private static final int VICTORY_CONDITION5 = 84; //A2 + B2 + C2
    private static final int VICTORY_CONDITION6 = 161; //A3 + B3 + C3
    private static final int VICTORY_CONDITION7 = 146; //A1 + B2 + C3
    private static final int VICTORY_CONDITION8 = 56; //A3 + B2 + C1
    private static final int DRAW_CONDITION = 511; //Binary: 111111111

    /**
     * Main method to run the program.
     * @param args command line
     */
    public static void main(String[] args)
    {
        printRules();
        getPlayerNames();
        playGame();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////print rules
    /**
     * This method prints the rules of the magic squares game.
     */
    private static void printRules()
    {
        System.out.println("\nWelcome to the game of Magic Squares");
        System.out.println("*************************************");
        System.out.println("Rules:");
        System.out.println("2 players play the game.");
        System.out.println("Each player takes turns picking a number from 1-9.");
        System.out.println("No number can be chosen twice.");
        System.out.println("The first player to have 3 numbers that sum to 15 wins!");
        System.out.println("2 7 6");
        System.out.println("9 5 1");
        System.out.println("4 3 8");
        System.out.println("*************************************");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////get player names
    /**
     * This method gets the player names from the console.
     */
    private static void getPlayerNames()
    {
        System.out.println("Enter a name for player #1");
        player1 = INPUT.next();

        System.out.println("Enter a name for player #2");
        player2 = INPUT.next();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////play game
    /**
     * This method gives each player a turn to choose a number until the game is over.
     */
    private static void playGame()
    {
        magicSquare1 = new MagicSquare();
        magicSquare2 = new MagicSquare();
        magicSquare3 = new MagicSquare();

        do
        {
            getPlayerSelection(player1, magicSquare1);
            getPlayerSelection(player2, magicSquare2);
        }
        while(!gameOver());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////get player selection
    /**
     * This method gets and validates a player's number choice.
     * @param playerName name of player
     * @param magicSquare magic square board
     */
    private static void getPlayerSelection(String playerName, MagicSquare magicSquare)
    {
        byte playerSelection;

        do
        {
            System.out.print("\n" + playerName + ", please enter a number: ");
            playerSelection = Byte.parseByte(INPUT.next());
        }
        while(!magicSquare.choose(playerSelection));

        magicSquare.printChoices();
        magicSquare3.choose(playerSelection);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////game over
    /**
     * This methods checks if the game is over either by a player win or a draw
     * @return boolean
     */
    private static boolean gameOver()
    {
        if(isWinningSquare(magicSquare1))
        {
            System.out.println("\n" + player1 + " wins!");
            return true;
        }

        if(isWinningSquare(magicSquare2))
        {
            System.out.println("\n" + player2 + " wins!");
            return true;
        }

        if(isDrawGame())
        {
            System.out.println("\nThe game is a draw!");
            return true;
        }

        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////is winning square
    /**
     * This method checks if a magic square has a winning combination of 15.
     * @param magicSquare magic square board
     */
    private static boolean isWinningSquare(MagicSquare magicSquare)
    {
        final Short[] victoryConditions = new Short[]
        {
            VICTORY_CONDITION1,
            VICTORY_CONDITION2,
            VICTORY_CONDITION3,
            VICTORY_CONDITION4,
            VICTORY_CONDITION5,
            VICTORY_CONDITION6,
            VICTORY_CONDITION7,
            VICTORY_CONDITION8
        };

        for (Short victoryCondition : victoryConditions)
        {
            if((magicSquare.getChoices() & victoryCondition) == victoryCondition)
            {
                return true;
            }
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////is draw game
    /**
     * This method checks the 3rd magic square to see if the game was a draw
     * @return boolean
     */
    private static boolean isDrawGame()
    {
        return (magicSquare3.getChoices() & DRAW_CONDITION) == DRAW_CONDITION;
    }


}
