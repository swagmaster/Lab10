import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * MorseTree.java
 * CSSSKL 143 Binary Search Tree Lab
 * Author: Rob Nash
 * 
 * This class reads in data from a text file ("data.txt") and populates a binary tree with an 
 * ordering constraint.  See the lab instructions for more information, but in general, dots go right 
 * and dashes go left when constructing or traversing a Morse code tree.  Search for //TODO
 * in the code to see what code you have to implement.
 * 
 * Start with the constructor. In your constructor read each line in from the textfile first, 
 * calling add() for each {letter, morseCodeStr} pair.
 * 
 * 
 */

public class MorseTree {
	
	private TreeNode<Character> root;

	public MorseTree() {
		Scanner fin;
		try {
			fin = new Scanner(new FileInputStream("data.txt"));

			while(fin.hasNext()) {
				String fileLine = fin.nextLine();
				String[] morseCodes = fileLine.split(" ");
				add(morseCodes[1], morseCodes[0].charAt(0));
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void add(String morseStr, char letter) {
		root = insertInSubtree(morseStr, letter, root);
	}

	private TreeNode<Character> insertInSubtree(String morseStr, char letter, TreeNode<Character> subtree) {
		if(subtree == null) {
			subtree = new TreeNode<Character>(letter, null, null);
			return subtree;
		}else if(morseStr.length() == 0) {
			return null;
		}else if(morseStr.charAt(0) == '.'){
			subtree.right = insertInSubtree(morseStr.substring(1), letter, subtree.right);
			subtree.right = new TreeNode<Character>(letter,subtree,null);
			System.out.println("added " + letter + " to the tree");
			return subtree.right;
		}else{
			subtree.left = insertInSubtree(morseStr.substring(1), letter, subtree.left);
			subtree.left = new TreeNode<Character>(letter,null,subtree);
			System.out.println("added " + letter + " to the tree");
			return subtree.left;
		}
	}

	public Character translate(String morseStr) {
		return findInSubtree(morseStr, root);
	}

	private Character findInSubtree(String morseStr, TreeNode<Character> subtree) {
		if(subtree == null){
			return null;
		}else if(morseStr.length() == 0){
			return (Character) subtree.data;
		}else if(morseStr.charAt(0) == '.'){
			return findInSubtree(morseStr.substring(1), subtree.right);
		}else{
			return findInSubtree(morseStr.substring(1), subtree.left);
		}
	}

	public String translateString(String tokens) {
		String retVal = " ";
		Scanner newLine = new Scanner(tokens);
		while(newLine.hasNext()){
			String morseStr = newLine.next();
			retVal += translate(morseStr);
		}
		return retVal;
	}


	public String toMorseCode(Character c) {

		//walk the tree looking for the TreeNode with the char c in it
		//preorder walk?
		//inorder walk?
		//postorder walk?

		//when you've found the char c, report the path from the root to the node
		//and build the morse code by adding a "." when you go right, "-" when you go left
		return new String("You wish.");
	}

	/***/
	public String toString() {
		return inorderWalk(root);
	}

	/**
	 * @param subtree 
	 * @return
	 */
	private String inorderWalk(TreeNode<Character> subtree) {  
		String retVal = "";
		if(subtree != null) {
			inorderWalk(subtree.left);
			retVal += subtree.data.toString() + " ";
			inorderWalk(subtree.right);
			retVal += subtree.data.toString();
		}
		return retVal;
	}

	public static void main(String[] args) {
		MorseTree mt = new MorseTree();  //builds our tree using data from a file
		//System.out.println(mt.toString());
		System.out.println(mt.translate("..."));  //prints out S
		System.out.println(mt.translate("---"));  //prints out O
		System.out.println(mt.translate(".......-"));  //prints out null

		System.out.println(mt.translateString("... --- ..."));  //SOS
		System.out.println(mt.toMorseCode('S'));  //find where we are in the tree, remember path to root
	}

	/**
	 * @author Ian Bryan
	 * @version Dec 10th 2018
	 * @param <Character> - 
	 * 
	 * Inner class to create the linked structure>
	 */
	private class TreeNode<Character> {

		/**
		 * Inner class data members.
		 * */
		Object data;
		TreeNode right;
		TreeNode left;

		/**
		 * Constructor that takes no arguments. Overloads default, no argument constructor.
		 * Sets all inner class data variables to null;
		 * */
		public TreeNode() {
			this.data = null;
			this.right = null;
			this.left = null;
		}

		/**
		 * @param newData - An object container for a piece of data.
		 * @param newLeft - A node with a data value less than the previous.
		 * @param newRight - A node with data value greater than the previous.
		 * 
		 * Constructor with three parameters that are data to add to any newly constructed subtrees.
		 * */
		public TreeNode(Object newData, TreeNode<Character> newLeft, TreeNode<Character> newRight) {
			data = newData;
			left = newLeft;
			right = newRight;
		}

		public void setRight(TreeNode rightNode) {
			this.right = rightNode;
		}

		public void setLeft(TreeNode leftNode) {
			this.left = leftNode;
		}
	}
}