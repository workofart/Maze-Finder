/**
 * The MapException class will implement the exceptions that will be
 * thrown by the methods of the Graph and Map classes
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 *
 */
public class MapException extends Exception {

	/**
	 * MapException constructor will initialize the message that will be used when the exception is thrown
	 * @param mssg - the message associated with this exception
	 */
	public MapException(String mssg) {
		super(mssg);
	}
}