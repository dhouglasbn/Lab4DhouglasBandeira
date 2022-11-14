package Classmates;

import java.util.HashMap;

public class ClassmatesController {

	/**
	 * Mapa de alunos
	 */
	private HashMap<String, Classmate> ClassmatesMap;
	
	/**
	 * Cria um Controlador de alunos.
	 */
	public ClassmatesController() {
		this.ClassmatesMap = new HashMap<>();
	}

	/** Registra um aluno através do número de matrícula.
	 * 
	 * @param Número de matrícula
	 * @param nome
	 * @param curso
	 * @return sucesso do cadastro.
	 */
	public String registerClassmate(
			String registrationNumber,
			String name,
			String course
			) {
		if (this.classmateAlreadyExists(registrationNumber)) {
			return "MATRÍCULA JÁ CADASTRADA!";
		}
		Classmate classmate = new Classmate(registrationNumber, name, course);
		this.ClassmatesMap.put(registrationNumber, classmate);
		return "CADASTRO REALIZADO!";
	}
	
	/** Verifica se um aluno já existe
	 * 
	 * @param Número de matrícula
	 * @return true - aluno existe, false - aluno não existe
	 */
	public boolean classmateAlreadyExists(String registrationNumber) {
	    return this.ClassmatesMap.containsKey(registrationNumber);
	}
	
	/** Recupera um aluno através de sua matrícula
	 * 
	 * @param Número de matrícula
	 * @return Aluno
	 */
	public String getClassmate(String registrationNumber) {
	    Classmate classmate = this.ClassmatesMap.get(registrationNumber);
	    if (classmate == null) {
	    	return "ALUNO NÃO CADASTRADO.";
	    }
	    return classmate.toString();
	}

}
