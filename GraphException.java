/**
 * The GraphException class will implement the exceptions that will be
 * thrown by the methods of the Graph and Map classes
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 *
 */
public class GraphException extends Exception {

	/**
	 * GraphException constructor will initialize the message that will be used when the exception is thrown
	 * @param mssg - the message associated with this exception
	 */
	public GraphException(String mssg) {
		super(mssg);
	}
}