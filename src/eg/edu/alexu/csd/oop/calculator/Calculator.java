package eg.edu.alexu.csd.oop.calculator;
/**
 * 
 * @author HP
 *
 */
public interface Calculator {

	/**
	 * Take input from user.
	 *@param s
	 * the input string
	 **/
	public void input(String s);

	/**
	 * Return the result of the current operations. or throws a runtime
	 * @return the result of the operation exception
	 **/
	public String getResult();

	/**
	 * return the current formula.
	 * @return string current operation.
	 **/
	public String current();

	/**
	 * return the last operation in String format.
	 *  or Null if no more history
	 * @return the prev operation. available
	 **/
	public String prev();

	/**
	 * return the next operation in String format.
	 *  or Null if no more history
	 * @return the next string operation available
	 **/
	public String next();

	/** Save in file the last 5 done Operations. **/
	public void save();

	/** Load from file the saved operations. **/
	public void load();

}
