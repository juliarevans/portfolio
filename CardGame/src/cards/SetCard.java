package cards;

/**
 * This class represents a card in the Set card game
 * @author Julia Evans
 * @version 1.0
 */
public class SetCard extends Card
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////fields
    private final String shape;
    private final int number;
    private final String shading;
    private final String color;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////constructors
    /**
     * Parameterized constructor for SetCard object
     * @param shape card shape
     * @param number card number
     * @param shading card shading
     * @param color card color
     */
    public SetCard(String shape, int number, String shading, String color)
    {
        super(shading + "-" + color + " " + shape + " " + number);
        this.shape = shape;
        this.number = number;
        this.shading = shading;
        this.color = color;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////methods
    /**
     * Getter for card shape
     * @return String representation of card shape
     */
    public String getShape()
    {
        return this.shape;
    }

    /**
     * Getter for card number
     * @return int representation of card number
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * Getter for card shading
     * @return String representation of card shading
     */
    public String getShading()
    {
        return this.shading;
    }

    /**
     * Getter for card color
     * @return String representation of card color
     */
    public String getColor()
    {
        return this.color;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

}
