/**
 * @author jpacheco
 * @class CS272 - Data Structures
 * @lab Lab #3
 */

public class IntNode {
	/**
	 * Instance Variables
	 */
	int data;
	IntNode link;

	/**
	 * Default Constructor
	 */
	public IntNode()
	{
		data = 0;
		link = null;
	}

	/**
	 * Constructor with values
	 * @param _data
	 * @param _node
	 */
	public IntNode(int _data, IntNode _node)
	{
		data = _data;
		link = _node;
	}

	/**
	 * Gets data 
	 * @return int data
	 */	
	public int getData()
	{
		return data;
	}

	/**
	 * Gets link 
	 * @return IntNode link
	 */
	public IntNode getLink()
	{
		return link;
	}

	/**
	 * Sets data 
	 * @param _data
	 */
	public void setData(int _data)
	{
		data = _data;
	}

	/**
	 * Sets link 
	 * @param _link
	 */
	public void setLink(IntNode _link)
	{
		link = _link;
	}

	/**
	 * Node Counting Method
	 * @param head
	 * @return ans
	 */
	public static int listLength(IntNode head)
	{
		int ans = 0;
		IntNode crsr;
		for (crsr = head; crsr != null; crsr = crsr.link) 
		{
			ans++;
		}
		return ans;
	}

	/**
	 * Add Node After Method
	 * Creates a new node after calling node with given value
	 * @param _data
	 */
	public void addNodeAfter(int _data)
	{
		//Make new node after calling node
		link = new IntNode(_data, link);
	}

	/**
	 * Remove Node After Method
	 * Removes node after calling node
	 */
	public void removeNodeAfter()
	{
		//Set link pointer to link after next
		//Garbage collector will get non-referenced link
		link = link.link;
	}

	/**
	 * List Search Method
	 * @precon head is not null
	 * @param head 
	 * @param _data
	 * @return boolean found
	 */
	public static boolean search(IntNode head, int _data)
	{
		boolean found = false;

		for(IntNode crsr = head; crsr!=null; crsr = crsr.link)
		{
			if(crsr.data == _data)
			{
				return found = true;
			}
		}

		return found;
	}

	/**
	 * toString Method To Print List
	 * @return String desc
	 */
	public String toString()
	{
		String desc = "";
		for(IntNode crsr = link; crsr != null; crsr = crsr.link)
		{
			desc += crsr.data; 
		}
		return desc;
	}
}