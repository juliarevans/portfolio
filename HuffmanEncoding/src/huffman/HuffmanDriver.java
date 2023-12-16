package huffman;

import java.util.Scanner;

/**
 * This class starts a small program that allows the
 * user to provide a string value which is then encoded
 * using the Huffman Encoding algorithm.
 *
 * @author Julia Evans
 * @version 1.0
 */
public class HuffmanDriver
{
    private static final Scanner CONSOLE = new Scanner(System.in);
    public static final int SMALL_BIT = 8;
    public static final int BIG_BIT = 16;
    private static String input;
    private static HuffmanEncoding sourceText;
    private static String encodedText;
    private static String decodedText;

    /**
     * The entry point of the application
     * @param args command line arguments (not used)
     */
    public static void main(String[] args)
    {
        welcomeUser();
        encodeAndDecodeString();
        showResults();
    }

    //Welcomes user and gets input string from console
    private static void welcomeUser()
    {
        System.out.println("Welcome to my Huffman Encoding Program!");
        System.out.println("***************************************");
        System.out.println();
        System.out.print("Please enter a string: ");
        input = CONSOLE.nextLine();
    }

    //Encodes and decodes the user input string
    private static void encodeAndDecodeString()
    {
        sourceText = new HuffmanEncoding(input);
        encodedText = sourceText.encode();
        decodedText = sourceText.decode(encodedText);
    }

    //Show result of all huffman operations
    private static void showResults()
    {
        sourceText.printCharacterFrequencies();
        sourceText.printEncodingMap();

        System.out.println();
        System.out.println("Original text: " + input);
        //Java uses between 8-16 bits to store characters using the Unicode encoding scheme
        System.out.println("Original text length: " +
                (input.length() * SMALL_BIT) + " - " +
                (input.length() * BIG_BIT) + " bits");

        System.out.println();
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Encoded text length: " + encodedText.length());

        System.out.println();
        System.out.println("Decoded text: " + decodedText);

    }
}

