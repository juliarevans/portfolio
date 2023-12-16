/**
 * This class manages magic square objects.
 * @author Julia Evans
 * @version 1.0
 */
public class MagicSquare
{
    private short choices;

    /**
     * Default constructor
     * Instantiates a magic square object.
     * Holds a 16 bit integer that contains all numbers chosen for a player.
     * Each of the first nine bits, from right-to-left, represent the numbers 1-9.
     */
    public MagicSquare()
    {
        choices = 0;
    }

    /**
     * Accepts a byte value ranging between 1-9.
     * This will set the bit at the index selection to "on."
     * This method then returns true if the bit was changed
     * False if the selection has already been chosen
     * And throws an IllegalArgumentException if selection is less than 1 or greater than 9.
     * @param selection byte
     * @return boolean
     */
    public boolean choose(byte selection)
    {
        if(isValidChoice(selection))
        {
            short mask = createMask(selection);

            //set the bit of interest to 1
            choices = (short) (choices | mask);
            return true;
        }
        return false;
    }

    /**
     * Helper method for choose()
     * Checks that the selection is a valid number
     * @param selection byte
     * @return boolean
     */
    private boolean isValidChoice(byte selection)
    {
        if(selection < 1 || selection > 9)
        {
            System.out.println(selection + " is not between 1 and 9");
            throw new IllegalArgumentException(selection + " is not between 1 and 9");
        }
        else if(hasAlreadyChosen(selection))
        {
            System.out.println("A player has already chosen " + selection);
            return false;
        }

        return true;
    }

    /**
     * Returns true if the bit at index selection is set to the "on" (1) position,
     * Or false if the bit is in the "off" (0) position.
     * @param selection byte
     * @return boolean
     */
    public boolean hasAlreadyChosen(byte selection)
    {
        short mask = createMask(selection);
        return (choices & mask) != 0;
    }

    /**
     * Helper method
     * This method creates a mask to be used later for bit operations
     * @param index bit of interest
     * @return short mask
     */
    private short createMask(int index)
    {
        //create a mask where the bit of interest (index) is 1
        short mask = 1; //assign decimal 1

        //shift the mask over index shifts
        mask = (short) (mask << index - 1);
        return mask;
    }

    /**
     * Getter for the choices field.
     * This number can then be examined to determine if the player has won the match or not.
     * @return short
     */
    public short getChoices()
    {
        return this.choices;
    }

    /**
     * Prints a string representation of the magic square.
     * Each number is printed in the position located in the first image at the top of this document.
     * Any number not chosen will have an underscore in that position.
     */
    public void printChoices()
    {
        int[] magicValues = new int[]{2, 7, 6, 9, 5, 1, 4, 3, 8};

        for (int i = 0; i < magicValues.length; i++)
        {
            if(hasAlreadyChosen((byte) magicValues[i]))
            {
                System.out.print(magicValues[i] + " ");
            }
            else
            {
                System.out.print("_ ");
            }

            //print results in rows of 3
            if((i + 1) % 3 == 0)
            {
                System.out.println();
            }
        }
    }

    @Override
    public String toString()
    {
        return "MagicSquare{" +
                "choices=" + choices +
                '}';
    }
}
