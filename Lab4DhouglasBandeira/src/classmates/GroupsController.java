package classmates;

import java.util.HashMap;

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
	 * Cria um controlador de grupos.
	 */
	public GroupsController() {
		this.GroupMap = new HashMap<>();
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
	public OperationResult addClassmateToGroup(Classmate classmate, String groupName) {
		if (classmate == null) {
			return new OperationResult(0, "ALUNO NÃO CADASTRADO.");
		}
		if (!this.groupAlreadyExists(groupName)) {
			return new OperationResult(0, "GRUPO NÃO CADASTRADO");
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
	public OperationResult classmateBelongsToGroup(Classmate classmate, String groupName) {
		if (classmate == null) {
			return new OperationResult(0, "ALUNO NÃO CADASTRADO.");
		}
		if (!this.groupAlreadyExists(groupName)) {
			return new OperationResult(0, "GRUPO NÃO CADASTRADO");
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
	public OperationResult displayClassmateGroups(Classmate classmate) {
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
	
//	public String displayGroupCourses(Group group) {
//		String result = "";
//		HashMap<String, Integer> courses = new HashMap<>();
//		for (Classmate classmate: group.getClassmates()) {
//			if (!courses.containsKey(classmate.getCourse())) {
//				courses.put(classmate.getCourse(), 1);
//			} else {
//				courses.put(classmate.getCourse(), courses.get(classmate.getCourse()) + 1);
//			}
//		}
//		for (Integer numberOfCourses: courses.values()) {
//			result += coursesKeys[index] + ;
//		}
//		return result;
//	}
	
//	public String displayGroupsCourses() {
//		String result = "";
//		for (Group group: this.GroupMap.values()) {
//			result += group.getName() + ": " + this.displayGroupCourses(group) + "\n";
//		}
//		return result;
//	}
}
