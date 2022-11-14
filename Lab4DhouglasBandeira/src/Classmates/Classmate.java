package Classmates;

public class Classmate {
	
	/**
	 * Número de matrícula do aluno.
	 */
	private String registrationNumber;
	
	/**
	 * Nome do aluno
	 */
	private String name;
	
	/**
	 * Curso do aluno
	 */
	private String course;
	
	/** Cria um aluno a partir dos parâmetros.
	 * 
	 * @param Número de matrícula
	 * @param nome
	 * @param curso
	 */
	public Classmate(
			String registrationNumber,
			String name,
			String course
			) {
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.course = course;
	}
	
	/** Retorna o número de matrícula.
	 * 
	 * @return registrationNumber.
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	/** Retorna o nome do aluno.
	 * 
	 * @return name.
	 */
	public String getName() {
		return name;
	}
	
	/** Retorna o curso do aluno.
	 * 
	 * @return course.
	 */
	public String getCourse() {
		return course;
	}

	@Override
	public String toString() {
		return "Aluno: " +
				this.registrationNumber +
				" - " + this.name + " - " + this.course;
	}
}
