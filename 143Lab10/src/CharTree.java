import java.io.*;
import java.util.*;

/**
 * Class Description:
 * Class invariant: This code for a binary tree satisfies the
 * binary search tree storage rule.
 * CSSSKL 162
 * @author Adam Atienza  
 * @version 2022
 * 
 * 
 * */
public class CharTree {

	/**
	 * Inner class Node, 2 references(pointers), one data element
	 * The only reason this inner class is static is that it is used in 
	 * the static methods insertInSubtree , isInSubtree , and 
	 * showElementsInSubtree. This class should have more methods. 
	 * This is just a sample of possible methods.
	 */
	private static class TreeNode {

		private char data;
		private TreeNode rightLink, leftLink;

		/**
		 * @param newData
		 * @param newLeftLink
		 * @param newRightLink
		 * 
		 * Parameterized constructor to build a node
		 */
		public TreeNode(char newData, TreeNode newLeftLink, TreeNode newRightLink) {
			// complete the constructor
			data = newData;
			leftLink = newLeftLink;
			rightLink = newRightLink;
		}
	}

	/**
	 * The first node of the tree, called root
	 */
	private TreeNode root;

	/**
	 * Default constructor to build the CharTree
	 */
	public CharTree( ) {
		root = null;
	}

	public void add(char item) {
		root = insertInSubtree(item, root);
	}

	public boolean contains(char item) {
		return isInSubtree(item, root);
	}

	public void showElements( ) {
		showElementsInSubtree(root);
	}

	private static TreeNode insertInSubtree(char item, TreeNode subTreeRoot) {
		if (subTreeRoot == null)
			return new TreeNode(item, null, null);
		else if (item < subTreeRoot.data) {
			subTreeRoot.leftLink = insertInSubtree(item, subTreeRoot.leftLink);
			return subTreeRoot;
		}else { 
			subTreeRoot.rightLink = insertInSubtree(item, subTreeRoot.rightLink);
			return subTreeRoot;
		}
	}

	private static boolean isInSubtree(char item, TreeNode subTreeRoot) {
		if(subTreeRoot == null) {
			return false;
		}else if(subTreeRoot.data == item) {
			return true;
		}else if(item < subTreeRoot.data) {
			isInSubtree(item, subTreeRoot.leftLink);
			return false;
		}else {
			isInSubtree(item,subTreeRoot.rightLink);
			return false;
		}
	}

	private static void showElementsInSubtree(TreeNode subTreeRoot) {
		if (subTreeRoot != null) {
			showElementsInSubtree(subTreeRoot.leftLink);
			System.out.print(subTreeRoot.data + " ");
			showElementsInSubtree(subTreeRoot.rightLink);
		}
	}
	 /**
    Returns the root node of a tree that is the tree with root node
    subTreeRoot, but with a new node added that contains item.
    */

	@SuppressWarnings("javadoc")
	public static void main(String[] args) {
		CharTree tree = new CharTree();
		tree.add('c');
		tree.add('a');
		tree.add('t');
		tree.add('s');
		showElementsInSubtree(tree.root);
	}
}