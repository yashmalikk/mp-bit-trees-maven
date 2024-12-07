package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * BitTree whose path stores the mapping of the Braille to English character.
 *
 * @author Yash Malik
 */
public class BitTree {
  /**
   * depth of the tree.
   */
  int depth = 0;
  /**
   * Root of the tree.
   */
  BinaryTreeNode root = new BinaryTreeNode();

  /**
   *
   * @param n depth of the new tree.
   */
  public BitTree(int n) {
    this.depth = n;
  } // constructor

  /**
   * Set the value in a tree.
   *
   * @param bits
   * @param value
   */
  public void set(String bits, String value) {
    // exception cases
    if (bits.length() != this.depth) {
      throw new IllegalArgumentException("The length of the bits"
          + "must be equal to the depth of the tree.");
    } // if condition.
    char[] bitsArray = bits.toCharArray();
    for (int i = 0; i < depth; i++) {
      if (bitsArray[i] != '0' && bitsArray[i] != '1') {
        throw new IllegalArgumentException("The bits must be a binary string.");
      } // if condition.
    } // for loop
    // call helper to set the value
    sethelper(root, bits, value);
  } // set().

  /**
   * Set helper which uses recursion.
   *
   * @param root
   * @param bits
   * @param value
   * @return root of the BitTree
   */
  private BinaryTreeNode sethelper(BinaryTreeNode root, String bits, String value) {
    if (bits.length() == 0) {
      root.value = value;
    } else {
      if (bits.charAt(0) == '0') {
        if (root.left == null) {
          root.left = new BinaryTreeNode();
        } // if condition
        root.left = sethelper(root.left, bits.substring(1), value);
      } else if (bits.charAt(0) == '1') {
        if (root.right == null) {
          root.right = new BinaryTreeNode();
        } // if condition
        root.right = sethelper(root.right, bits.substring(1), value);
      } // else-if condition
    } // else.
    return root;
  } // sethelper().

  /**
   * Get the value in a tree.
   *
   * @param bits
   * @return the value of the bits or a default message if no value is found.
   */
  public String get(String bits) {
    // exception cases
    if (bits.length() != this.depth) {
      System.err.println("Trouble translating because no corresponding value exists for the bits.");
      return "No corresponding value"; // Default message
    } // if condition.

    char[] bitsArray = bits.toCharArray();
    for (int i = 0; i < depth; i++) {
      if (bitsArray[i] != '0' && bitsArray[i] != '1') {
        System.err.println("Trouble translating because no corresponding value exists for the bits.");
        return "No corresponding value"; // Default message
      } // if condition.
    } // for block.

    BinaryTreeNode current = this.root;

    for (int i = 0; i < depth; i++) {
      if (bitsArray[i] == '0') {
        if (current.left == null) {
          System.err.println("Trouble translating because no corresponding value exists for the bits.");
          return "No corresponding value"; // Default message
        } // if condition.
        current = current.left;
      } else if (bitsArray[i] == '1') {
        if (current.right == null) {
          System.err.println("Trouble translating because no corresponding value exists for the bits.");
          return "No corresponding value"; // Default message
        } // if condition
        current = current.right;
      } // else-if condition
    } // for block

    if (current.value == null) {
      System.err.println("Trouble translating because no corresponding value exists for the bits.");
      return "No corresponding value"; // Default message
    }

    return current.value;
  } // get().

  /**
   * Dump the tree to a PrintWriter.
   *
   * @param pen
   */
  public void dump(PrintWriter pen) {
    dumpHelper(pen, root, "");
  } // dump().

  /**
   * Dump helper which uses recursion.
   *
   * @param pen
   * @param root
   * @param bits
   */
  private void dumpHelper(PrintWriter pen, BinaryTreeNode root, String bits) {
    if (bits.length() == this.depth) {
      pen.println(bits + ", " + root.value);
    } // base case
    if (root.left != null) {
      dumpHelper(pen, root.left, bits + "0");
    } // recursive case
    if (root.right != null) {
      dumpHelper(pen, root.right, bits + "1");
    } // recursive case
  } // dumpHelper().

  /**
   * reads a series of lines of the form bits,value and stores them in the tree.
   *
   * @param source
   */
  public void load(InputStream source) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(source));
    String line;

    while ((line = reader.readLine()) != null) {
      String[] fields = line.split(",");
      set(fields[0], fields[1]);
    } // while condition
    source.close();
  } // load()
} // BitTree
