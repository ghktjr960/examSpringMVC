package spring;

public class AlreadyExistingMemberException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AlreadyExistingMemberException(String message) {
		super(message);
	}
	
}
