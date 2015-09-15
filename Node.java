/**
 * The Node class will represent a node object in the graph. It will also
 * provide methods for setting/getting node mark, getting node name.
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 * 
 */
public class Node {
	private boolean mark; // the mark of a Node either true(visited) or
							// false(not visited)
	private int name; // the name of a Node a integer between 0 and n-1
	
	/**
	 * Constructor for the Node class and it will initialize an unmarked
	 * (unvisited) node with the given name.
	 * 
	 * @param name - an integer value between 0 and n-1, where n is the number of
	 *            nodes in the graph
	 */
	public Node(int name) {
		this.name = name;
		this.mark = false;
	} // end Node constructor
	
	/**
	 * setMark method will mark the node with the either true/false to identify
	 * if the node has been visited or not
	 * 
	 * @param mark - either true (visited) or false (unvisited)
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	} // end setMark method
	
	/**
	 * getMark method will return the value with which the node has been marked
	 * 
	 * @return - the mark of a specified node
	 */
	public boolean getMark() {
		return mark;
	} // end getMark method
	
	/**
	 * getName method will return the name of the node
	 * @return - name of the node
	 */
	public int getName() {
		return name;
	} // end getName method
	
} // end Node class
