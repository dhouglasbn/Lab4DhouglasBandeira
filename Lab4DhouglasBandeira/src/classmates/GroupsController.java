package classmates;

import java.util.HashMap;
import java.util.HashSet;

/** Representação de um controlador de grupos.
 * O controlador é responsável por toda a regra
 * de negócio e manipulação de grupos.
 * 
 * @author Dhouglas Bandeira Nóbrega
 *
 */
public class GroupsController {
	
	/**
	 * Mapa de grupos { nome: Grupo }
	 */
	private HashMap<String, Group> GroupMap;
	
	/**
	 * Controlador de alunos
	 */
	private ClassmatesController classmatesController;
	
	/**
	 * Cria um controlador de grupos.
	 */
	public GroupsController(ClassmatesController classmatesController) {
		this.GroupMap = new HashMap<>();
		this.classmatesController = classmatesController;
	}
	
	/** Registra um grupo no mapa.
	 * 
	 * @param nome do grupo.
	 * @param tamanho do grupo.
	 * @return Estado de sucesso da criação do grupo.
	 */
	public OperationResult registerGroup(String name, int size) {
		String formattedName = name.toLowerCase();
		
		if (this.groupAlreadyExists(formattedName)) {
			return new OperationResult(0, "GRUPO JÁ CADASTRADO!");
		}
		Group group = new Group(formattedName, size);
		this.GroupMap.put(formattedName, group);
		return new OperationResult(1, "CADASTRO REALIZADO!");
	}
	
	/** Registra um grupo sem tamanho limitado no mapa de grupos.
	 * 
	 * @param nome do grupo.
	 * @return Estado de sucesso da criação do grupo.
	 */
	public OperationResult registerGroup(String name) {
		String formattedName = name.toLowerCase();
		
		if (this.groupAlreadyExists(formattedName)) {
			return new OperationResult(0, "GRUPO JÁ CADASTRADO!");
		}
		Group group = new Group(formattedName);
		this.GroupMap.put(formattedName, group);
		return new OperationResult(1, "CADASTRO REALIZADO!");
	}
	
	/** Pega o grupo através do seu nome.
	 * 
	 * @param Nome do grupo.
	 * @return Grupo.
	 */
	public Group getGroup(String groupName) {
		String formattedName = groupName.toLowerCase();
		return this.GroupMap.get(formattedName);
	}
	
	/** Verifica se o grupo existe.
	 * 
	 * @param Nome do grupo.
	 * @return true - o grupo existe, false - o grupo não existe.
	 */
	public boolean groupAlreadyExists(String groupName) {
		String formattedName = groupName.toLowerCase();
		return this.GroupMap.containsKey(formattedName);
	}
	
	/** Adiciona um aluno a um grupo
	 * 
	 * @param Aluno
	 * @param Nome do grupo.
	 * @return Estado de sucesso da adição do aluno ao grupo.
	 */
	public OperationResult addClassmateToGroup(String registrationNumber, String groupName) {
		Classmate classmate = this
				.classmatesController
				.getClassmate(registrationNumber);
		
		if (classmate == null) {
			return new OperationResult(0, "ALUNO NÃO CADASTRADO.");
		}
		if (!this.groupAlreadyExists(groupName)) {
			return new OperationResult(0, "GRUPO NÃO CADASTRADO.");
		}
		
		Group group = this.getGroup(groupName);
		
		if (group.isFull()) {
			return new OperationResult(0, "GRUPO CHEIO!");
		}
		if (group.isClassmateOnGroup(classmate)) {
			return new OperationResult(0, "O ALUNO JÁ FAZ PARTE DO GRUPO!");
		}
		
		group.addClassmate(classmate);
		classmate.addGroup(groupName);
		
		return new OperationResult(1, "ALUNO ALOCADO!");
	}
	
	/** Verifica se o aluno já pertence ao grupo.
	 * 
	 * @param Aluno
	 * @param Nome do grupo.
	 * @return Estado de sucesso da operação.
	 */
	public OperationResult classmateBelongsToGroup(String registrationNumber, String groupName) {
		Classmate classmate = this
				.classmatesController
				.getClassmate(registrationNumber);
		
		if (classmate == null) {
			return new OperationResult(0, "ALUNO NÃO CADASTRADO.");
		}
		if (!this.groupAlreadyExists(groupName)) {
			return new OperationResult(0, "GRUPO NÃO CADASTRADO.");
		}
		
		if (classmate.isOnGroup(groupName)) {
			return new OperationResult(1, "ALUNO PERTENCE AO GRUPO.");
		} else {
			return new OperationResult(0, "ALUNO NÃO PERTENCE AO GRUPO.");
		}
	}
	
	/** Exibe todos os grupos que o aluno participa
	 * 
	 * @param Aluno.
	 * @return Estado de sucesso da operação.
	 */
	public OperationResult displayClassmateGroups(String registrationNumber) {
		Classmate classmate = this
				.classmatesController
				.getClassmate(registrationNumber);
		
		
		if (classmate == null) {
			return new OperationResult(0, "ALUNO NÃO CADASTRADO.");
		}
		String result = "Grupos:\n";
		for (Group group: this.GroupMap.values()) {
			if (group.isClassmateOnGroup(classmate)) {
				result += "- " +
				group.getName() +
				" " +
				group.getCurrentSize() +
				"/" +
				group.getMaxSize() +
				"\n";
			}
		}
		return new OperationResult(1, result);
	}
	
	private String displayGroupCourses(Group group) {
		return null;
	}
	
	public String displayGroupsCourses() {
		String result = "";
		for (Group group: this.GroupMap.values()) {
			result += group.getName() + ": " + this.displayGroupCourses(group) + "\n";
		}
		return result;
	}
	
	private void addCourseOnGroup(String course, Group group) {
		HashMap<String, Integer> groupCourses = group.getGroupCourses();
		
		if (groupCourses.containsKey(course)) {
			group.addCourse(course);
		} else {
			group.putCourse(course);
		}
	}
}
