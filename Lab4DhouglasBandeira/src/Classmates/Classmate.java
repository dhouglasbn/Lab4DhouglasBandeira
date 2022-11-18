package Classmates;

import java.util.HashSet;

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
	
	public void addGroup(String groupName) {
		this.groups.add(groupName);
	}
	
	public boolean isOnGroup(String groupName) {
		return this.groups.contains(groupName);
	}
	
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
