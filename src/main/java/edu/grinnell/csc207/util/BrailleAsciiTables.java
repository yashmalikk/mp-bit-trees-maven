package edu.grinnell.csc207.util;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

/**
 * Utility class for converting between ASCII, braille, and Unicode braille
 * representations.
 *
 * @author Yash Malik
 * @author Samuel A. Rebelsky
 */
public class BrailleAsciiTables {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Conversions from ASCII to braille.
   */
  static final String A2B = "01000001,100000\n"
      + "01000010,110000\n"
      + "01000011,100100\n"
      + "01000100,100110\n"
      + "01000101,100010\n"
      + "01000110,110100\n"
      + "01000111,110110\n"
      + "01001000,110010\n"
      + "01001001,010100\n"
      + "01001010,010110\n"
      + "01001011,101000\n"
      + "01001100,111000\n"
      + "01001101,101100\n"
      + "01001110,101110\n"
      + "01001111,101010\n"
      + "01010000,111100\n"
      + "01010001,111110\n"
      + "01010010,111010\n"
      + "01010011,011100\n"
      + "01010100,011110\n"
      + "01010101,101001\n"
      + "01010110,111001\n"
      + "01010111,010111\n"
      + "01011000,101101\n"
      + "01011001,101111\n"
      + "01011010,101011\n"
      + "01100001,100000\n"
      + "01100010,110000\n"
      + "01100011,100100\n"
      + "01100100,100110\n"
      + "01100101,100010\n"
      + "01100110,110100\n"
      + "01100111,110110\n"
      + "01101000,110010\n"
      + "01101001,010100\n"
      + "01101010,010110\n"
      + "01101011,101000\n"
      + "01101100,111000\n"
      + "01101101,101100\n"
      + "01101110,101110\n"
      + "01101111,101010\n"
      + "01110000,111100\n"
      + "01110001,111110\n"
      + "01110010,111010\n"
      + "01110011,011100\n"
      + "01110100,011110\n"
      + "01110101,101001\n"
      + "01110110,111001\n"
      + "01110111,010111\n"
      + "01111000,101101\n"
      + "01111001,101111\n"
      + "01111010,101011\n"
      + "00100000,000000\n";

  /**
   * Conversions from braille to ASCII.
   */
  static final String B2A = "100000,A\n"
      + "110000,B\n"
      + "100100,C\n"
      + "100110,D\n"
      + "100010,E\n"
      + "110100,F\n"
      + "110110,G\n"
      + "110010,H\n"
      + "010100,I\n"
      + "010110,J\n"
      + "101000,K\n"
      + "111000,L\n"
      + "101100,M\n"
      + "101110,N\n"
      + "101010,O\n"
      + "111100,P\n"
      + "111110,Q\n"
      + "111010,R\n"
      + "011100,S\n"
      + "011110,T\n"
      + "101001,U\n"
      + "111001,V\n"
      + "101101,X\n"
      + "101111,Y\n"
      + "101011,Z\n"
      + "010111,W\n"
      + "000000, \n";

  /**
   * Conversions from braille to unicode.
   */
  static final String B2U = "000000,2800\n"
      + "100000,2801\n"
      + "010000,2802\n"
      + "110000,2803\n"
      + "001000,2804\n"
      + "101000,2805\n"
      + "011000,2806\n"
      + "111000,2807\n"
      + "000100,2808\n"
      + "100100,2809\n"
      + "010100,280A\n"
      + "110100,280B\n"
      + "001100,280C\n"
      + "101100,280D\n"
      + "011100,280E\n"
      + "111100,280F\n"
      + "000010,2810\n"
      + "100010,2811\n"
      + "010010,2812\n"
      + "110010,2813\n"
      + "001010,2814\n"
      + "101010,2815\n"
      + "011010,2816\n"
      + "111010,2817\n"
      + "000110,2818\n"
      + "100110,2819\n"
      + "010110,281A\n"
      + "110110,281B\n"
      + "001110,281C\n"
      + "101110,281D\n"
      + "011110,281E\n"
      + "111110,281F\n"
      + "000001,2820\n"
      + "100001,2821\n"
      + "010001,2822\n"
      + "110001,2823\n"
      + "001001,2824\n"
      + "101001,2825\n"
      + "011001,2826\n"
      + "111001,2827\n"
      + "000101,2828\n"
      + "100101,2829\n"
      + "010101,282A\n"
      + "110101,282B\n"
      + "001101,282C\n"
      + "101101,282D\n"
      + "011101,282E\n"
      + "111101,282F\n"
      + "000011,2830\n"
      + "100011,2831\n"
      + "010011,2832\n"
      + "110011,2833\n"
      + "001011,2834\n"
      + "101011,2835\n"
      + "011011,2836\n"
      + "111011,2837\n"
      + "000111,2838\n"
      + "100111,2839\n"
      + "010111,283A\n"
      + "110111,283B\n"
      + "001111,283C\n"
      + "101111,283D\n"
      + "011111,283E\n"
      + "111111,283F\n";

  // +---------------+-----------------------------------------------
  // | Static fields |
  // +---------------+

  /**
   * Braille to ASCII.
   */
  static BitTree b2a = new BitTree(6);
  /**
   * Braille to Unicode.
   */
  static BitTree b2u = new BitTree(6);
  /**
   * ASCII to Braille.
   */
  static BitTree a2b = new BitTree(8);

  static {
    try {
      b2a.load(new ByteArrayInputStream(BrailleAsciiTables.B2A.getBytes(StandardCharsets.UTF_8)));
      b2u.load(new ByteArrayInputStream(BrailleAsciiTables.B2U.getBytes(StandardCharsets.UTF_8)));
      a2b.load(new ByteArrayInputStream(BrailleAsciiTables.A2B.getBytes(StandardCharsets.UTF_8)));
    } catch (IOException e) {
      e.printStackTrace();
    } // try-catch
  } // Loads all the mappings into BitTrees.

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * Convert an ASCII character to its braille equivalent.
   *
   * @param letter the ASCII character to convert
   * @return the corresponding braille sequence, or an empty string if not found
   */
  static public String toBraille(char letter) {
    if (letter == ' ') {
      return "000000"; // Space character is mapped to braille as "000000"
    } // add the space special case because i cant make it work any other way
    String binary = "0" + Integer.toBinaryString((int) letter);
    String braille = a2b.get(binary);

    // Error handling: check if braille mapping exists for the ASCII character
    if (braille == null || braille.isEmpty()) {
      System.err.println("Error: No braille mapping found for ASCII character '" + letter + "'");
      return "";
    } // if condition

    return braille;
  } // toBraille()

  /**
   * Convert a braille bit sequence to its ASCII equivalent.
   *
   * @param bits the braille bit sequence to convert
   * @return the corresponding ASCII character, or an empty string if not found
   */
  static public String toASCII(String bits) {
    // Error handling: check for invalid input format
    if (bits == null || bits.length() != 6) {
      System.err.println("Error: Invalid braille bit sequence. It should have 6 bits.");
      return "";
    } // if condition
    String ascii = b2a.get(bits);
    // Error handling: check if the braille bit sequence maps to an ASCII character
    if (ascii == null || ascii.isEmpty()) {
      System.err.println("Error: No ASCII mapping found for braille bit sequence '" + bits + "'");
      return "";
    } // if condition
    return ascii;
  } // toASCII()

  /**
   * Convert a braille bit sequence to its Unicode equivalent.
   *
   * @param bits the braille bit sequence to convert
   * @return the corresponding Unicode character as a string, or an empty string
   *         if not found
   */
  static public String toUnicode(String bits) {
    // Error handling: check for invalid input format
    if (bits == null || bits.length() != 6) {
      System.err.println("Error: Invalid braille bit sequence. It should have 6 bits.");
      return "";
    } // if condition

    String unicodeHex = b2u.get(bits); // Get Unicode hex code from the mapping
    if (unicodeHex == null || unicodeHex.isEmpty()) {
      System.err.println("Error: No Unicode mapping found for braille bit sequence '" + bits + "'");
      return "";
    } // if condition

    // Convert the hex code (e.g., "2801") to a code point
    int unicodeCodePoint = Integer.parseInt(unicodeHex, 16); // Convert from hex to int

    // Convert the code point to the actual Braille character
    return new String(Character.toChars(unicodeCodePoint)); // Return the Braille symbol
  } // toUnicode()
} // class BrailleAsciiTables
