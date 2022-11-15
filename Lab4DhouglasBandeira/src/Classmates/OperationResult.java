package Classmates;

public class OperationResult {

	private int status;
	
	private String message;
	
	public OperationResult(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getMessage() {
		return this.message;
	}
}
