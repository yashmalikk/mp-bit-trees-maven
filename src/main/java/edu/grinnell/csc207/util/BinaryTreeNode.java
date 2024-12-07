package edu.grinnell.csc207.util;

/**
 * Nodes in a binary tree.
 * @author Yash Malik
 */
public class BinaryTreeNode {
  /**
   * Value that the node holds.
   */
  String value;
  /**
   * Left child node.
   */
  BinaryTreeNode left = null;
  /**
   * Right child node.
   */
  BinaryTreeNode right = null;

  /**
   *
   * @param left left node
   * @param right right node
   */
  public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right) {
    this.left = left;
    this.right = right;
  } // BinaryTreeNode()

  /**
   *
   * @param value the node must hold
   */
  public BinaryTreeNode(String value) {
    this.value = value;
  } // BinaryTreeNode().

  /**
   * Just a empty constructor.
   */
  public BinaryTreeNode() {
  } // BinaryTreeNode()
} // class BinaryTreeNode
