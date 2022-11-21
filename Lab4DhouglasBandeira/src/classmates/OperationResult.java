package classmates;

public class OperationResult {

	/**
	 * Nùmero que representa o sucesso da operação.
	 * 0 - falha;
	 * 1 - sucesso;
	 */
	private int status;
	
	/**
	 * Mensagem associada ao resultado da operação
	 */
	private String message;
	
	/** Cria um resultado de operação.
	 * 
	 * @param Estado da operação.
	 * @param Mensagem.
	 */
	public OperationResult(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	/** Pega o estado da operação.
	 * 
	 * @return Estado da operação(0 ou 1).
	 */
	public int getStatus() {
		return this.status;
	}
	
	/** Pega a mensagem associada ao resultado da operação.
	 * 
	 * @return mensagem.
	 */
	public String getMessage() {
		return this.message;
	}
}
