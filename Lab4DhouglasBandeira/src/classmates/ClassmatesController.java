package classmates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/** Representação de um controlador de alunos.
 * Responsável por toda a regra de negócio e manipulação
 * de alunos.
 * 
 * @author Dhouglas Bandeira Nóbrega
 *
 */
public class ClassmatesController {

	/**
	 * Mapa de alunos { número de matrícula: Aluno }
	 */
	private HashMap<String, Classmate> ClassmatesMap;
	
	/**
	 * Lista ordenada de alunos que responderam às perguntas.
	 */
	private ArrayList<Classmate> answeredClassmates;
	
	/**
	 * Cria um Controlador de alunos.
	 */
	public ClassmatesController() {
		this.ClassmatesMap = new HashMap<>();
		this.answeredClassmates = new ArrayList<Classmate>();
	}
	

	/** Registra um aluno através do número de matrícula.
	 * 
	 * @param Número de matrícula
	 * @param nome
	 * @param curso
	 * @return sucesso do cadastro.
	 */
	public OperationResult registerClassmate(
			String registrationNumber,
			String name,
			String course
			) {
		if (this.classmateAlreadyExists(registrationNumber)) {
			return new OperationResult(0, "MATRÍCULA JÁ CADASTRADA!");
		}
		Classmate classmate = new Classmate(registrationNumber, name, course);
		this.ClassmatesMap.put(registrationNumber, classmate);
		return new OperationResult(1, "CADASTRO REALIZADO!");
	}
	
	/** Retorna o aluno
	 * 
	 * @param Número da matrícula.
	 * @return aluno.
	 */
	public Classmate getClassmate(String registrationNumber) {
		return this.ClassmatesMap.get(registrationNumber);
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
	public OperationResult displayClassmate(String registrationNumber) {
	    Classmate classmate = this.ClassmatesMap.get(registrationNumber);
	    if (classmate == null) {
	    	return new OperationResult(0, "ALUNO NÃO CADASTRADO.");
	    }
	    return new OperationResult(1, classmate.toString());
	}
	
	/** Vai registrar na lista, a resposta de um aluno
	 * 
	 * @param Número de matrícula
	 * @return resultado da operação
	 */
	public OperationResult registerClassmateAnswer(String registrationNumber) {
		Classmate classmate = this.ClassmatesMap.get(registrationNumber);
		
		if (classmate == null) {
			return new OperationResult(0, "Aluno não cadastrado.");
		}
		
		this.answeredClassmates.add(classmate);
		return new OperationResult(1, "ALUNO REGISTRADO!");
	}
	
	/** Exibe a lista de alunos que responderam às perguntas.
	 * 
	 * @return Lista de alunos
	 */
	public OperationResult displayAnsweredClassmates() {
		String result = "Alunos:\n";
		for (int index = 0; index < this.answeredClassmates.size(); index++) {
			Classmate classmate = this.answeredClassmates.get(index);
			result += (index + 1) +
					". " + classmate.getRegistrationNumber() +
					" - " + classmate.getName() +
					" - " + classmate.getCourse() + "\n";
		}
		return new OperationResult(1, result);
	}
	
	/** Retorna a lista de alunos que não participam de grupos.
	 * 
	 * @return lista de alunos
	 */
	public OperationResult displayClassmatesWithoutGroups() {
		String result = "Alunos sem grupo:\n";
		for(Classmate classmate: this.ClassmatesMap.values()) {
			if (classmate.getNumberOfGroups() == 0) {
				result += classmate.getName() + "\n";
			}
		}
		
		return new OperationResult(1, result);
	}
	
	/** Mostra a lista de alunos que participam de mais grupos.
	 * 
	 * @return resultado da operação com a lista.
	 */
	public OperationResult displayMostActiveClassmates() {
		String result = "Alunos que participam de mais grupos:\n";
		String[] mostActive = this.getMostActiveClassmates();
		for (int index = 0; index < mostActive.length; index++) {
			result += mostActive[index];
		}
		return new OperationResult(1, result);
	}
	
	/** Pega a lista de alunos que pertencem a mais grupos.
	 * 
	 * @return Lista de alunos e quantidade de grupos.
	 */
	private String[] getMostActiveClassmates() {
		if (this.ClassmatesMap.size() == 0) {
			return new String[0];
		}
		ArrayList<String> namesList = new ArrayList<>();
		ArrayList<Classmate> classmatesList = new ArrayList<>();
		for (Classmate classmate: this.ClassmatesMap.values()) {
			classmatesList.add(classmate);
		}
		Collections.sort(classmatesList, Comparator.comparing(Classmate::getNumberOfGroups));
		int moreGroups = classmatesList.get(classmatesList.size() - 1).getNumberOfGroups();
		for (int index = classmatesList.size() - 1; index >= 0; index--) {
			if (classmatesList.get(index).getNumberOfGroups() == moreGroups) {
				namesList.add(
						classmatesList.get(index).getName() 
						+ " - " 
						+ moreGroups + " grupos.\n"
						);
			}
		}
		
		return namesList.toArray(new String[] {});
	}
}
