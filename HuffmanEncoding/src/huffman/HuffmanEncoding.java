package huffman;

import java.util.*;

/**
 * Handles all calculations for interactions
 * with the Huffman encoding tree used in this
 * application
 *
 * @author Julia Evans
 * @version 1.0
 */
public class HuffmanEncoding
{
    private String sourceText;
    private HuffmanNode huffmanTree;
    private Map<Character, Double> frequencyMap = new TreeMap<>();
    private Map<Character, String> encodingMap = new HashMap<>();

    /**
     * Stores the source string to encode.
     * @param sourceText the source string
     */
    public HuffmanEncoding(String sourceText)
    {
        this.sourceText = sourceText;
        analyzeText();
    }

    private void analyzeText()
    {
        //Calculate the frequency of characters in the source string
        for(int i = 0; i < sourceText.length(); i++){
            char key = sourceText.charAt(i);

            if(frequencyMap.containsKey(key)) //char already exists in map
            {
                double currentCount = frequencyMap.get(key) * sourceText.length(); //convert % to integer count
                currentCount++;
                frequencyMap.put(key, currentCount / sourceText.length()); //convert count back to %
            }
            else //new key, add to map with value of frequency percentage
            {
                frequencyMap.put(key, 1.0 / sourceText.length());
            }
        }
    }

    /**
     * Takes the source string and generates a huffman encoding
     * tree, creating a binary encoding of the source string.
     * @return the binary encoding of the source string
     */
    public String encode()
    {
        //Assemble the huffman encoding tree
        // 1) Create priority queue heap
        Queue<HuffmanNode> pqHeap = new PriorityQueue<>();

        // 2) loop through frequencyMap and construct new HuffmanNode for each key
        // 3) Add node to heap
        for(char key : frequencyMap.keySet())
        {
            HuffmanNode newNode = new HuffmanNode(key, frequencyMap.get(key), null, null);
            pqHeap.add(newNode);
        }

        // 4) Remove 2 lowest frequency nodes
        // 5) Create parent node with null char and put back in heap
        while(pqHeap.size() > 1)
        {
            HuffmanNode left = pqHeap.remove();
            HuffmanNode right = pqHeap.remove();

            HuffmanNode parentNode = new HuffmanNode('\u0000', left.getFrequency() + right.getFrequency(), left, right);
            pqHeap.add(parentNode);
        }

        // 6) Assign last node to be root of huffman tree
        huffmanTree = pqHeap.remove();

        // Generate encoding map by traversing tree
        traverse(huffmanTree, "");

        // Return the encoding for the source string
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < sourceText.length(); i++)
        {
            char key = sourceText.charAt(i);
            result.append(encodingMap.get(key));
        }
        return result.toString();
    }

    private void traverse(HuffmanNode currentNode, String code)
    {
        if(isLeaf(currentNode)) //base case
        {
            //generate encoding map
            encodingMap.put(currentNode.getData(), code);
        }
        else //recursive case
        {
            //assert !isLeaf(currentNode);
            traverse(currentNode.left, code + "1");
            traverse(currentNode.right, code + "0");
        }
    }

    /**
     * Uses the stored huffman encoding tree to decode the
     * input string.
     * @param encodedText a binary string to decode
     * @return the decoded string
     */
    public String decode(String encodedText)
    {
        StringBuilder result = new StringBuilder();
        HuffmanNode currentNode = huffmanTree;

        //Take the input binary string and decode each character
        for(int i = 0; i < encodedText.length(); i++)
        {
            if(encodedText.charAt(i) == '1')
            {
                currentNode = currentNode.left;  //go left
            }
            else //encodedText.charAt(i) == '0'
            {
                currentNode = currentNode.right; //go right
            }
            if(isLeaf(currentNode))
            {
                result.append(currentNode.data);
                currentNode = huffmanTree; //reset
            }
        }

        //Return the decoded string
        return result.toString();
    }

    private boolean isLeaf(HuffmanNode currentNode)
    {
        return currentNode.left == null && currentNode.right == null;
    }

    /**
     * Prints the frequency of characters in the source string
     */
    public void printCharacterFrequencies()
    {
        //Analyze and print frequencies of characters in the source string
        System.out.println();
        System.out.println("Character frequencies from text:");
        for(char key : frequencyMap.keySet())
        {
            System.out.println(key + " -> " + frequencyMap.get(key));
        }
    }

    /**
     * Prints the binary encodings determined by the huffman
     * encoding tree generated based
     */
    public void printEncodingMap()
    {
        //Print the binary digits of characters in the encoding map
        System.out.println();
        System.out.println("Huffman encoding map from text:");
        for(char key : encodingMap.keySet())
        {
            System.out.println(key + " -> " + encodingMap.get(key));
        }
    }

    @Override
    public String toString()
    {
        return "HuffmanEncoding{sourceText= " + sourceText + "}";
    }

    /**
     * This node class represents a huffman pair, with frequency and
     * character.
     * @author Julia Evans
     * @version 1.0
     */
    private static class HuffmanNode implements Comparable<HuffmanNode>
    {
        private char data;
        private double frequency;
        private HuffmanNode left;
        private HuffmanNode right;

        /**
         * Constructor for HuffmanNode
         * @param data the character
         * @param frequency the % frequency in sourceText
         * @param left left node
         * @param right right node
         */
        public HuffmanNode(char data, double frequency, HuffmanNode left, HuffmanNode right)
        {
            this.data = data;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        /**
         * Getter for data field of Huffman node
         * @return data character
         */
        public char getData() {
            return data;
        }

        /**
         * Getter for frequency field of Huffman Node
         * @return % frequency
         */
        public double getFrequency() {
            return frequency;
        }

        @Override
        public String toString()
        {
            return "HuffmanNode{" + "data=" + data + ", frequency=" + frequency + "}";
        }

        /**
         * Compares two huffman nodes based on the frequency of the
         * nodes.
         * @param other the other node
         * @return a negative number if this frequency is smaller,
         * a positive number if this frequency is larger, or otherwise
         * zero
         */
        @Override
        public int compareTo(HuffmanNode other)
        {
            return Double.compare(this.frequency, other.frequency);
        }
    }
}
