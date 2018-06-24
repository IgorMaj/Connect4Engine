package exception;

public class IllegalMove extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2255275121461931235L;

	@Override
	public String getMessage() {
		return "This move is illegal.";
	}
	
	

}
