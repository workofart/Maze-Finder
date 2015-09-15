import java.util.Iterator;
import java.util.Stack;

/**
 * Graph class implements the GraphADT and will provide the graph object with methods for inserting edges,
 * getting vertices, finding the incident edges of a given node, getting edges,
 * test if two vertices are adjacent.
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 * 
 */
public class Graph implements GraphADT{
	private int v; // number of nodes/vertices
	private Node vertices[]; // an array that stores all the vertices in the graph
	private Edge adjacencyMatrix[][]; // the adjacencyMatrix array
	
	
	/**
	 * Constructor that creates a graph with n nodes and no edges
	 * The name of the nodes are 0, 1, ..., n-1
	 * @param n - the number of nodes that will be created associated with the graph
	 */
	public Graph(int n){
		this.v = n; // set the number of vertices in the graph to the given n value
		
		// create a new Node array to store all the vertices in the array
		vertices = new Node[n];
		
		// store the new vertices into the corresponding positions based on the name of each vertex
		for (int i = 0; i < n; i++){
			vertices[i] = new Node(i);
		} // end for loop - store vertices into the vertices matrix
		
		// create a new adjacencyMatrix
		adjacencyMatrix = new Edge [n][n];
		
		// initialize all positions in the matrix to be null
		for (int i = 0; i< n; i++) {
			for (int j = 0; j < n; j++) {
				adjacencyMatrix[i][j] = null;
			}
		} // end for loop - initialize adjacencyMatrix
		
	} // end Graph constructor
	
	/**
	 * insertEdge method that will add an edge of the given type to the graph
	 * connecting u and v. The label of the edge is the empty String. 
	 * 
	 * @param u - the first end point of the inserted edge 
	 * @param v - the second end point of the inserted edge
	 * @param edgeType - edge type (free or toll)
	 * @throws GraphException - if either node doesn't exist or if there's a edge
	 * connecting the given nodes in the graph already
	 */
	public void insertEdge(Node u, Node v, String edgeType) throws GraphException{
		Edge newEdge; // declare new edge to be inserted into the adjacency matrix
		Edge newEdge2; // declare the second new edge to be inserted into the adjacency matrix
		
		// if the edge is already in the graph or if the either vertex doesn't exist
		if (!validateNode(u.getName()) || !validateNode(v.getName()) || adjacencyMatrix [u.getName()][v.getName()] != null || adjacencyMatrix [v.getName()][u.getName()] != null)
			throw new GraphException("There's a edge connecting the given nodes in the graph already.");
		else {
			// else insert the edge into the adjacencyMatrix
			newEdge = new Edge(u, v, edgeType); // create a new edge
			newEdge2 = new Edge(v, u, edgeType); // create a new edge (with opposite vertices)
			adjacencyMatrix [u.getName()][v.getName()] = newEdge;
			adjacencyMatrix [v.getName()][u.getName()] = newEdge2;			
		} // end else - the edge is not in the graph and both vertices exists	

	} // end insertEdge method 
	
	/**
	 * getNode method retrieves the node with the specified name
	 * 
	 * @param name - the name of the node/vertex
	 * @return - returns the node if the node exists
	 * @throws GraphException - if no node with this name exists
	 */
	public Node getNode(int name) throws GraphException{
		// if either the name exceed the boundaries of the matrix or the node doesn't exist in the vertices array
		if (!validateNode(name) || vertices[name] == null){
			throw new GraphException("No node with this name exists");
		}
		// else, the node is valid and exists
		else
			return vertices[name];
			
	} // end getNode method
	
	/**
	 * incidentEdges method will retrieves an iterator that contains all the
	 * edges incident on node "u"
	 * 
	 * @param u - the node that is used ot find its incident edges
	 * @return - a Java Iterator storing all the edges incident on node u, or
	 *         null if u doesn't have any edges incident on it.
	 * @throws GraphException - if u is not nodes of the graph 
	 */
	public Iterator incidentEdges(Node u) throws GraphException {
		// create a new stack that will store the edges incident to node u
		Stack <Edge> edgeStack = new Stack<Edge>();
		
		// declare and initialize a counter that is used to loop through the vertex matrix
		int counter = 0;
		
		// while the counter is still within the total number of nodes
		while (counter < v){
			/**
			 * if the two nodes are adjacent, push the edge connecting those two nodes into the stack
			 * check to the counter node to make sure it's not visited before pushing it on to the stack
			 */
			if (areAdjacent(u,getNode(counter)) && getNode(counter).getMark() == false && getEdge(u, getNode(counter)).getLabel() == ""){
				edgeStack.push(getEdge(u,getNode(counter))); // need revision
				counter++; // increment counter after successful push
			}
			// else, the two nodes are not adjacent, increment counter and check the next vertex/node
			else
				counter++;
		} // end while loop
		
		// return null if "u" doesn't have any edges incident on it
		if (edgeStack.isEmpty()){
			return null;
		}
		// else, there are edges incident to u, return the iterator of the edge stack
		else
			return edgeStack.iterator();
		
	} // end incidentEdges method
	
	/**
	 * getEdge method that retrieves the edge connecting u and v
	 * 
	 * @param u - the first end point of the edge 
	 * @param v - the second end point of the edge
	 * @return - the edge connecting u and v
	 * @throws GraphException - if u and v are not nodes of the graph
	 */
	public Edge getEdge(Node u, Node v) throws GraphException{
		// if the two nodes forming the edge are not adjacent, then there is no edge between u and v
		if(areAdjacent(u,v) == false)
			throw new GraphException("There is no edge between u and v.");
		// else, there is an edge between the two given nodes, return the edge connecting them
		else
			return adjacencyMatrix[u.getName()][v.getName()];
	} // end getEdge method
	
	/**
	 * areAdjacent method will check if the given two nodes are adjacent.
	 * 
	 * @param u - one of the vertices we are testing
	 * @param v - one of the vertices we are testing
	 * @return - true if nodes u and v are adjacent, false otherwise
	 * @throws GraphException if one or both of the nodes are invalid 
	 */
	public boolean areAdjacent (Node u, Node v) throws GraphException{
		// either or both node(s) are invalid
		if (!validateNode(u.getName()) || !validateNode(v.getName())){
			throw new GraphException("Invalid node");
		}	
		// else valid and adjacent, return true
		else if (adjacencyMatrix[u.getName()][v.getName()] != null || adjacencyMatrix[v.getName()][u.getName()] != null){
			return true;
		}
		else return false; // valid, but not adjacent, return false
	} // end areAdjacent method
	
	/**
	 * validateNode method will test whether the given name of a node is a valid
	 * node in the graph or not
	 * 
	 * @param n - the name of a given node
	 * @return - true if the given name is valid, false otherwise
	 */
	private boolean validateNode(int n) {
		// if the name of the given node is within the ranges of the vertex matrix, return true
		if (n >=0 && n < v){
			return true;
		}
		// else, invalid node, return false
		else return false;
	} // end validateNode method
	
} // end Graph class
