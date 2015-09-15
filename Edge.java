/**
 * Edge class will provide the attributes for the edge object and related
 * methods for getting edge type, first/second end points, setting/getting edge
 * label
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 * 
 */
public class Edge {
	private Node u; // the first end point of an edge
	private Node v; // the second end point of an edge
	private String type; // the type of an edge (free or toll)
	private String label; // the label of an edge (discovery or back)
	
	
	/**
	 * The constructor for Edge class will initialize the assigned first and
	 * second end points and the type of edge. Each edge has a String label. The
	 * label is initially set to the empty String
	 * 
	 * @param u - first end point of the edge
	 * @param v - second end point of the edge
	 * @param type - type of the edge, which will either be "free" or "toll"
	 */
	public Edge(Node u, Node v, String type) {
		this.u = u;
		this.v = v;
		this.type = type;
		this.label = "";
	}
	
	/**
	 * firstEndpoint method returns the first end point of the edge
	 * @return - first end point of the edge
	 */
	public Node firstEndpoint() {
		return u;
	} // end firstEndpoint method

	/**
	 * firstEndpoint method returns the second end point of the edge
	 * @return - second end point of the edge
	 */
	public Node secondEndpoint() {
		return v;
	} // end secondEndpoint method
	
	/**
	 * getType method will retrieve the type of the edge
	 * @return - the type of the edge (free or toll)
	 */
	public String getType() {
		return type;
	} // end getType method
	
	/**
	 * setLabel method will set the label of the edge to the specified value
	 * @param label - "discovery" or "back"
	 */
	public void setLabel(String label){
		this.label = label;
	} // end setLabel method
	
	/**
	 * getLabel method will retrieve the label of the edge
	 * @return - the label of the edge
	 */
	public String getLabel(){
		return label;
	} // end getLabel method
}
