package old;

/**
 * This class represents a Blackjack player and keeps track of their card count (running total)
 * @author Julia Evans
 * @version 1.0
 */
public class BlackJackPlayer
{
    public static final int WIN_CONDITION = 21;
    private int runningTotal;

    /**
     * Constructor for BlackJack player object
     */
    public BlackJackPlayer()
    {
        this.runningTotal = 0;
    }

    /**
     * Getter for running total
     * @return int current card count
     */
    public int getRunningTotal()
    {
        return this.runningTotal;
    }

    /**
     * Setter for running total
     * @param runningTotal int card count
     */
    public void setRunningTotal(int runningTotal)
    {
        this.runningTotal = runningTotal;
    }

    /**
     * Checks if the player went bust (game over)
     * @return boolean
     */
    public boolean wentBust()
    {
        return this.getRunningTotal() > WIN_CONDITION;
    }

    /**
     * Checks if the player is a winner
     * @return boolean
     */
    public boolean isWinner()
    {
        return this.getRunningTotal() == WIN_CONDITION;
    }

    @Override
    public String toString()
    {
        return "BlackJackPlayer{" +
                "runningTotal=" + runningTotal +
                '}';
    }

}
