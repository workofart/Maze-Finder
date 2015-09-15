import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

/**
 * The Map class represents the road map built from the input file and contains
 * methods for getting the graph, finding the path from the starting position to
 * the destination.
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 * 
 */
public class Map {
	/**
	 * -------------------Variables---------------
	 * G - the graph used to store the map
	 * gridSize - Size of each square in the grid representation of the graph
	 * numNodes - Number of nodes in the graph
	 * tollsAllowed - Number of toll roads allowed in solution
	 * tollsUsed - Number of toll roads used to find the best path
	 * mapWidth - Width of the map (number of nodes across)
	 * mapLength - Length of the map (number of nodes in a column of the graph)
	 * map - the map storing the road map from the content of the input file
	 * startVertex - the start vertex of every map
	 * endVertex - the destination vertex of every map
	 * S - the stack storing the vertices along the path from start to end
	 */
	private Graph G; 
	private int gridSize; 
	private int numNodes; 
	private int tollsAllowed; 
	private int tollsUsed = 0;
	private int mapWidth;
	private int mapLength;
	private char[][] map; 
	private Node startVertex; 
	private Node endVertex;
	private Stack<Node> S = new Stack<Node>(); 

	/**
	 * Map constructor for building a road map from the content of the input file.
	 * 
	 * @param inputFile - the input map that will be used to construct the map
	 * @throws MapException - if the input file doesn't exist
	 */
	public Map (String inputFile) throws MapException {
		// declare and initialize variables to store the content of the input file
		BufferedReader input = null;
		String line;

		// declare and initialize the coordinates inside the map
		int i, row = 0;

		try {
			// Process first four lines of the file and store them into the
			// corresponding variables for later use
			input = new BufferedReader(new FileReader(inputFile));
			gridSize = Integer.parseInt(input.readLine());
			mapWidth = Integer.parseInt(input.readLine());
			mapLength = Integer.parseInt(input.readLine());
			tollsAllowed = Integer.parseInt(input.readLine());
		} catch (FileNotFoundException e) {
			throw new MapException("The input file doesn't exist");
		}catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// construct a new graph that will store the coordinates of the input file
		numNodes = mapWidth * mapLength;
		this.G = new Graph(numNodes);

		try {
			// initialize new map array
			map = new char[2*mapLength-1][2*mapWidth-1]; 
			// Read the road map from the file, line by line
			for (;;) {
				line = input.readLine();
				if (line == null) { // End of file, close the input stream
					input.close();
					break;
				}
				// insert the symbols located at the corresponding locations in
				// the road map into the map array
				for (i = 0; i < line.length(); ++i) 
					map[row][i] = line.charAt(i);
				++row;
			}
		}
		catch (Exception e) {
			System.out.println("Error reading input file ");
			e.printStackTrace();
			return;
		}


		// retrieve the positions of the starting and end points and different
		// types of roads from the map array
		for (row = 0; row < (2*mapLength-1); row++){
			for (i = 0; i < (2*mapWidth-1); i++){
				switch(map[row][i]){

				// case of the starting point
				case 's':
					try {
						this.startVertex = G.getNode((mapWidth)*(row/2) + (i/2));
					} catch (GraphException e1) {
						e1.printStackTrace();
					}
					break;

					// case of the destination
				case 'e':
					try {
						this.endVertex = G.getNode((mapWidth)*(row/2) + (i/2));
					} catch (GraphException e1) {
						e1.printStackTrace();
					}
					break;

					// case of horizontal free road (same row, left and right adjacent columns)
				case '-':
					try {
						G.insertEdge(G.getNode((mapWidth)*(row/2) + (i-1)/2) ,G.getNode((mapWidth)*(row/2) + (i+1)/2) , "free");
					} catch (GraphException e) {
						e.printStackTrace();
					}
					break;

					// case of vertical free road (same column, top and down adjacent rows)
				case '|':
					try {
						G.insertEdge(G.getNode((mapWidth)*(row-1)/2 + i/2), G.getNode((mapWidth)*(row+1)/2 + i/2) , "free");
					} catch (GraphException e) {
						e.printStackTrace();
					}
					break;

					// case of block of houses
					// don't insert an edge, because you can't go through a block of houses

					// case of horizontal toll road
				case 'h':
					try {
						G.insertEdge(G.getNode((mapWidth)*(row/2) + (i-1)/2), G.getNode((mapWidth)*(row/2) + (i+1)/2) , "toll");
					} catch (GraphException e) {
						e.printStackTrace();
					}
					break;

					// case of vertical toll road
				case 'v':
					try {
						G.insertEdge(G.getNode((mapWidth)*(row-1)/2 + i/2), G.getNode((mapWidth)*(row+1)/2 + i/2), "toll");
					} catch (GraphException e) {
						e.printStackTrace();
					}
					break;

				} // end switch statement

			} // end inner for loop - columns

		} // end outer for loop - rows

	} // end map constructor

	/**
	 * getGraph method returns a reference to the graph representing the road map.
	 * Throws MapException if the graph is not defined.
	 * @return - the reference to the graph created from the map
	 * @throws MapException - if the graph is not defined
	 */
	public Graph getGraph() throws MapException{
		if (G == null){
			throw new MapException("Graph not defined");
		}
		else
			return G;
	} // end getGraph method

	/**
	 * Returns a Java Iterator containing the nodes along the path from the
	 * starting point to the destination, if such path exists.
	 * If the path doesn't exist, this method returns the value null.
	 * 
	 * @return - iterator that contains the nodes forming the path from start to end, or null
	 */
	public Iterator findPath() {

		try {
			// Check if there is a path from the starting point to the destination
			// if not, return null
			if (isPath(startVertex,endVertex) == false){
				return null;
			}
		} catch (GraphException e) {
			e.printStackTrace();
		}

		// return the iterator of the stack of vertices that leads to the destination
		return S.iterator(); // despite S being empty or not, return the iterator

	} // end findPath method

	/**
	 * isPath method will determine if there is a path from the given start node to the given destination node.
	 * 
	 * @param start - the start node in a map
	 * @param destination - the destination node in a map
	 * @return - true if there is a path from start to destination, false otherwise
	 * @throws GraphException - if there are no incident edges to a certain node
	 */
	private boolean isPath(Node start, Node destination) throws GraphException{
		Edge e; // the edge variable that will store the next incident edge of the given vertex
		Node v; // the node that will store the end points of a incident edge		
		start.setMark(true);

		S.push(start); // push the starting vertex on to the stack

		// if the "start" vertex is the "destination" vertex, return true, there is a path between them
		if (start == destination && tollsUsed <= tollsAllowed){
			return true;
		}

		// for every edge incident to "start"
		test1: while(G.incidentEdges(start) != null && G.incidentEdges(start).hasNext()){

			e = (Edge) G.incidentEdges(start).next();
			if(e.getLabel() ==""){

				v = e.secondEndpoint(); // store the secondEndpoint of e to v
				// if vertex v is not marked
				if (!v.getMark()){
					e.setLabel("discovery"); // label edge as discovery

					/**
					 * recursively check if there is a path between v and destination
					 * if there is one, let's check the road conditions with our money;
					 * test if the edge between "start" and v is a toll or free
					 */
					if (e.getType() == "toll" && tollsUsed >= tollsAllowed){
						e.setLabel("back");
						continue test1; 
					} // end if - toll check

					else {
						// if the road is free do nothing
						if (e.getType() == "free"){
							return isPath(v,destination);
						} // end if - isPath test

						// else if, we still have enough money to pay the tolls
						else if (tollsUsed <= tollsAllowed && e.getType() == "toll"){
							tollsUsed++; // increment counter
							return isPath(v,destination);
						} 

					} // end if - either the road is free or you have to pay for it
					
					// else there is no path between v and the destination

				} // end if - check mark test

			} // end if - get label check

			// else v is already visited, if reached here, path can't be found this way, try another edge

		} // end while loop

		// there are no more incident edges on node u, start backtracking
		start.setMark(true); // mark the current vertex as visited
		
		// if the current start node doesn't have any incident edges, then there
		// is no path leading to the destination
		if (start.getName() == 0){
			return false;
		}
		// else, if the current position is a dead end, pop twice to back track
		else if (start == S.peek()){
			S.pop();
			start = S.pop();
		}
		// if the current position is not the top element in the stack, pop once
		// and perform recursion on the previous vertex
		else 
			start = S.pop();

		// plug in the start vertex to check recursively if there is a way to
		// the destination
		return isPath(start,destination);

	} // end isPath method

} // end Map class
