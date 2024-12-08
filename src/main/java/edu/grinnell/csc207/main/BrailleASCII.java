package edu.grinnell.csc207.main;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * @author Yash Malik
 */
public class BrailleASCII {

  /**
   *
   * @param args that the program was called with.
   */
  public static void main(String[] args) {
    // Check if we have exactly 2 arguments (target and source)
    if (args.length != 2) {
      System.err.println("Usage: java BrailleASCII <target> <source>");
      System.exit(1);
    } // if block

    String target = args[0];
    String source = args[1];

    // Perform translation based on the target
    PrintWriter pen = new PrintWriter(new
        OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
    switch (target.toLowerCase()) {
      case "braille":
        translateToBraille(pen, source);
        break;
      case "ascii":
        translateToASCII(pen, source);
        break;
      case "unicode":
        translateToUnicode(pen, source);
        break;
      default:
        System.err.println("Invalid target character set");
        System.exit(1);
    } // switch-case
  } // main()

  private static void translateToBraille(PrintWriter pen, String input) {
    StringBuilder brailleString = new StringBuilder();

    for (char c : input.toCharArray()) {
      String brailleChar = BrailleAsciiTables.toBraille(c);
      if (brailleChar.isEmpty()) {
        System.err.println("Trouble translating '" + c + "' because no corresponding value.");
        System.exit(1);
      } // if condition
      brailleString.append(brailleChar);
    } // for block

    pen.println(brailleString.toString());
  } // translatetoBraille()

  private static void translateToASCII(PrintWriter pen, String input) {
    if (input.length() % 6 != 0) {
      System.err.println("Invalid length of bit string");
      System.exit(1);
    } // if condition

    for (int i = 0; i < input.length(); i += 6) {
      String brailleChar = input.substring(i, i + 6);
      String asciiChar = BrailleAsciiTables.toASCII(brailleChar);
      if (asciiChar.isEmpty()) {
        System.err.println("Trouble translating '" + brailleChar
            + "' because no corresponding value.");
        System.exit(1);
      } // if condition.
      pen.print(asciiChar);
    } // for block
    pen.println(); // Print a newline after the ASCII output
  } // translatetoASCII().

  private static void translateToUnicode(PrintWriter pen, String input) {
    // Convert the ASCII input to braille
    String brailleInput = "";
    for (int i = 0; i < input.length(); i++) {
      brailleInput += BrailleAsciiTables.toBraille(input.charAt(i));
    } // for loop

    // Process the braille input in chunks of 6 bits and translate to Unicode
    for (int i = 0; i < brailleInput.length(); i += 6) {
      String brailleChar = brailleInput.substring(i, i + 6);
      String unicodeChar = BrailleAsciiTables.toUnicode(brailleChar);

      if (unicodeChar.isEmpty()) {
        System.err.println("Trouble translating '" + brailleChar
            + "' because no corresponding value.");
        System.exit(1);
      } // if block
      pen.print(unicodeChar); // Output the Unicode character (Braille in Unicode)
    } // for loop
    pen.println(); // Print a newline after the Unicode output
  } // translateToUnicode()
} // BrailleASCII
