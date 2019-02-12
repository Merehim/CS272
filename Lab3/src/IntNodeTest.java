/**
 * @author jpacheco
 * @class CS272 - Data Structures
 * @lab Lab #3
 */
public class IntNodeTest extends IntNode{

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) //Move to test file when done 
	{
		IntNode head = new IntNode();
		if (head != null) {System.out.println("Default Constructor Works!");}

		IntNode node = new IntNode(0, null);
		if (node != null) {System.out.println("Valued Constructor Works with null!");}

		IntNode node2 = new IntNode(456, node);
		if (node2 != null) {System.out.println("Valued Constructor Works with link!");}//Makes noob2 point to noob. 

		head.addNodeAfter(21);
		System.out.println("Works. Adds after calling link.");

		head.removeNodeAfter();
		System.out.println("Works. Removes after calling link.");

		IntNode moreNodes = new IntNode();
		head.addNodeAfter(1);
		head.addNodeAfter(2);
		moreNodes.link = head.link; 
		moreNodes.addNodeAfter(3);
		moreNodes.addNodeAfter(4);

		String desc = "";
		desc = head.toString();
		System.out.println(desc); //Should be 21

		desc = moreNodes.toString();
		System.out.println(desc); //Should be 4321

		desc = head.toString();
		System.out.println(desc); //Should be 21
	}
	

}
