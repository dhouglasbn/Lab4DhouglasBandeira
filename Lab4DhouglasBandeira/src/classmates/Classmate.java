package classmates;

import java.util.HashSet;

/** Representação de um aluno.
 * Todo aluno precisa de um número de matrícula,
 * nome próprio e nome do curso para ser formado.
 * 
 * @author Dhouglas Bandeira
 *
 */
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
	
	/**
	 * Grupos aos quais o aluno pertence. 
	 */
	private HashSet<String> groups;
	
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
		this.groups = new HashSet<>();
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
	 * @return curso.
	 */
	public String getCourse() {
		return course;
	}
	
	/** Adiciona um grupo para a lista de grupos aos quais o aluno pertence.
	 * 
	 * @param Nome do grupo
	 */
	public void addGroup(String groupName) {
		this.groups.add(groupName);
	}
	
	/** Verifica se o aluno pertence ao grupo
	 * 
	 * @param Nome do grupo
	 * @return true - aluno pertence ao grupo, false - aluno não pertence ao grupo.
	 */
	public boolean isOnGroup(String groupName) {
		return this.groups.contains(groupName);
	}
	
	/** Verifica quantos grupos o aluno pertence
	 * 
	 * @return Quantidade de grupos.
	 */
	public int getNumberOfGroups() {
		return this.groups.size();
	}

	@Override
	public String toString() {
		return "Aluno: " +
				this.registrationNumber +
				" - " + this.name + " - " + this.course;
	}
}
