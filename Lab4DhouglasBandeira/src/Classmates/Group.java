package Classmates;

import java.util.HashSet;

public class Group {
	
	/**
	 * Nome do grupo
	 */
	private String name;

	/**
	 * Conjunto de alunos
	 */
	private HashSet<Classmate> classmatesSet;
	
	/**
	 * Número máximo de alunos no grupo
	 */
	private int maxSize;

	/** Constroi um grupo a partir de seu nome
	 * 
	 * @param nome do grupo
	 */
	public Group(String name) {
		this.name = name;
		this.classmatesSet = new HashSet<>();
		this.maxSize = -1;
	}
	
	/** Constrói um grupo a partir de seu nome e tamanho máximo
	 * 
	 * @param nome do grupo
	 * @param Tamanho máximo do grupo
	 */
	public Group(String name, int maxSize) {
		this.name = name;
		this.classmatesSet = new HashSet<>();
		this.maxSize = maxSize;
	}
	
	/** Retorna o nome do grupo.
	 * 
	 * @return nome do grupo.
	 */
	public String getName() {
		return name;
	}
	
	/** Adiciona um aluno ao grupo.
	 * 
	 * @param aluno.
	 */
	public void addClassmate(Classmate classmate) {
		this.classmatesSet.add(classmate);
	}
	
	/** Verifica se o grupo está cheio.
	 * 
	 * @return true - grupo está cheio, false - grupo não está cheio.
	 */
	public boolean isFull() {
		return this.getMaxSize() == this.getCurrentSize();
	}
	
	/** Verifica se um aluno está no grupo.
	 * 
	 * @param aluno.
	 * @return true - o aluno está no grupo, false - o aluno não está no grupo.
	 */
	public boolean isClassmateOnGroup(Classmate classmate) {
		return this.classmatesSet.contains(classmate);
	}
	
	/** Pega a quantidade atual de alunos no grupo.
	 * 
	 * @return quantidade atual de alunos no grupo.
	 */
	public int getCurrentSize() {
		return this.classmatesSet.size();
	}
	
	/** Pega a quantidade máxima de alunos no grupo.
	 * 
	 * @return quantidade máxima de alunos no grupo.
	 */
	public int getMaxSize() {
		return this.maxSize;
	}
}
