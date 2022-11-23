package classmates;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupsControllerTest {
	
	private ClassmatesController classmatesController;
	
	private GroupsController groupsController;

	@BeforeEach
	void setUp() {
		this.classmatesController = new ClassmatesController();
		this.groupsController = new GroupsController(classmatesController);
		
		this.classmatesController.registerClassmate(
				"100",
				"Sir Davos Seaworth",
				"Engenharia Mecânica"
		);
		this.classmatesController.registerClassmate(
				"200",
				"Stannis Baratheon",
				"Artes cênicas"
		);
		this.classmatesController.registerClassmate(
				"300",
				"Theon Greyjoy",
				"Ciência da Computação"
		);
		this.classmatesController.registerClassmate(
				"400",
				"Robert Baratheon",
				"Medicina"
		);
	}

	@AfterEach
	void tearDown() {
		this.groupsController = null;
		this.classmatesController = null;
	}

	@Test
	void registerNotRestrictedGroupTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a operação produza a mensagem de sucesso"
				+ " esperada.";
		
		String expectedMessage = "CADASTRO REALIZADO!";
		
		OperationResult result = this
				.groupsController
				.registerGroup("Programation OO");
		
		assertEquals(1, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}

	@Test
	void registerRestrictedGroupTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a operação produza a mensagem de sucesso"
				+ " esperada.";
		
		String expectedMessage = "CADASTRO REALIZADO!";
		
		OperationResult result = this
				.groupsController
				.registerGroup("Lists", 3);
		
		assertEquals(1, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}

	@Test
	void registerGroupWithExistentNameTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro"
				+ " esperada.";
		
		String expectedMessage = "GRUPO JÁ CADASTRADO!";
		
		this.groupsController.registerGroup("Lists");
		
		OperationResult result = this
				.groupsController
				.registerGroup("Lists", 5);
		
		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void groupAlreadyExistsTest() {
		String msg = "Espera-se que a afirmação 'O grupo Maps já existe'"
				+ " seja verdadeira.";
		
		this.groupsController.registerGroup("Maps");
		
		boolean result = this
				.groupsController
				.groupAlreadyExists("Maps");
		
		assertTrue(result, msg);
	}
	
	@Test
	void groupDoesntExistsTest() {
		String msg = "Espera-se que a afirmação 'O grupo Maps já existe'"
				+ " seja falsa.";
		
		boolean result = this
				.groupsController
				.groupAlreadyExists("Lists");
		
		assertFalse(result, msg);
	}
	
	@Test
	void addClassmateOnGroupTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a operação produza a mensagem de sucesso"
				+ " esperada.";
		
		String groupName = "Programation OO";
		String expectedMessage = "ALUNO ALOCADO!";
		
		Classmate classmate1 = this.classmatesController.getClassmate("100");
		Classmate classmate2 = this.classmatesController.getClassmate("200");
		
		this.groupsController.registerGroup(groupName, 3);
		
		OperationResult result = this
								.groupsController
								.addClassmateToGroup(
										classmate1.getRegistrationNumber(),
										groupName
										);
		
		assertEquals(1, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
		
		OperationResult result2 = this
				.groupsController
				.addClassmateToGroup(
						classmate2.getRegistrationNumber(),
						groupName
						);

		assertEquals(1, result2.getStatus(), msg);
		assertEquals(expectedMessage, result2.getMessage(), msg2);
	}
	
	@Test
	void addClassmateAlreadyOnGroupTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String groupName = "Programation OO";
		String expectedMessage = "O ALUNO JÁ FAZ PARTE DO GRUPO!";
		
		Classmate classmate1 = this.classmatesController.getClassmate("100");
		Classmate classmate2 = this.classmatesController.getClassmate("200");
		
		this.groupsController.registerGroup(groupName, 3);
		
		this
			.groupsController
			.addClassmateToGroup(classmate1.getRegistrationNumber(), groupName);
		
		this
			.groupsController
			.addClassmateToGroup(classmate2.getRegistrationNumber(), groupName);
		
		OperationResult result = this
				.groupsController
				.addClassmateToGroup(classmate1.getRegistrationNumber(), groupName);

		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void addInexistentClassmateOnGroupTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedMessage = "ALUNO NÃO CADASTRADO.";
		String groupName = "Programation OO";
		
		this.groupsController.registerGroup(groupName, 3);
		
		Classmate classmate = new Classmate (
				"500",
				"Khal Drogo",
				"Ciência da Computação"
				);
		
		this
			.groupsController
			.addClassmateToGroup(classmate.getRegistrationNumber(), groupName);
		
		OperationResult result = this
				.groupsController
				.addClassmateToGroup(classmate.getRegistrationNumber(), groupName);

		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void addClassmateOnInexistentGroupTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedMessage = "GRUPO NÃO CADASTRADO.";
		String groupName = "Sets";
		
		Classmate classmate = this.classmatesController.getClassmate("100");
		
		this
			.groupsController
			.addClassmateToGroup(classmate.getRegistrationNumber(), groupName);
		
		OperationResult result = this
				.groupsController
				.addClassmateToGroup(classmate.getRegistrationNumber(), groupName);

		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void addClassmateOnRestrictedGroupTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedMessage = "GRUPO CHEIO!";
		String groupName = "Collections";
		
		this.groupsController.registerGroup(groupName, 1);
		
		Classmate classmate1 = this.classmatesController.getClassmate("100");
		Classmate classmate2 = this.classmatesController.getClassmate("200");
		
		this
			.groupsController
			.addClassmateToGroup(classmate1.getRegistrationNumber(), groupName);
		
		OperationResult result = this
				.groupsController
				.addClassmateToGroup(classmate2.getRegistrationNumber(), groupName);

		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void classmateBelongsToGroupTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a operação produza"
				+ " a mensagem de sucesso esperada.";
		String msg3 = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg4 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedSuccessMessage = "ALUNO PERTENCE AO GRUPO.";
		String expectedErrorMessage = "ALUNO NÃO PERTENCE AO GRUPO.";
		String groupName = "Lists";
		
		this.groupsController.registerGroup(groupName);
		
		Classmate classmate1 = this.classmatesController.getClassmate("100");
		Classmate classmate2 = this.classmatesController.getClassmate("200");
		
		this
			.groupsController
			.addClassmateToGroup(classmate1.getRegistrationNumber(), groupName);
		
		OperationResult result = this
				.groupsController
				.classmateBelongsToGroup(
						classmate1.getRegistrationNumber(),
						groupName
						);

		assertEquals(1, result.getStatus(), msg);
		assertEquals(expectedSuccessMessage, result.getMessage(), msg2);
		
		OperationResult result2 = this
				.groupsController
				.classmateBelongsToGroup(
						classmate2.getRegistrationNumber(),
						groupName
						);
		
		assertEquals(0, result2.getStatus(), msg3);
		assertEquals(expectedErrorMessage, result2.getMessage(), msg4);
	}
	
	@Test
	void classmateBelongsToInexistentGroupTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedMessage = "GRUPO NÃO CADASTRADO.";
		String groupName = "Software Architecture";
		
		Classmate classmate = this.classmatesController.getClassmate("100");
		
		OperationResult result = this
				.groupsController
				.classmateBelongsToGroup(
						classmate.getRegistrationNumber(),
						groupName
						);

		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void inexistentClassmateBelongsToGroupTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedMessage = "ALUNO NÃO CADASTRADO.";
		String groupName = "GRASP";
		
		this.groupsController.registerGroup(groupName);
		
		Classmate classmate = new Classmate("500", "Jaqen H'ghar", "Filosofia");
		
		OperationResult result = this
				.groupsController
				.classmateBelongsToGroup(
						classmate.getRegistrationNumber(),
						groupName
						);

		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void displayNotInGroupsClassmateGroupsTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a operação produza a mensagem esperada.";
		
		String expectedMessage = "Grupos:\n";
		
		Classmate classmate = this.classmatesController.getClassmate("100");
		
		OperationResult result = this
				.groupsController
				.displayClassmateGroups(classmate.getRegistrationNumber());

		assertEquals(1, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void displayClassmateGroupsTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a operação produza a mensagem esperada.";
		
		String expectedMessage = "Grupos:\n"
				+ "- poo 1/10\n"
				+ "- lists 1/10\n";
		String firstGroupName = "POO";
		String secondGroupName = "Lists";
		
		this.groupsController.registerGroup(firstGroupName, 10);
		this.groupsController.registerGroup(secondGroupName, 10);
		
		Classmate classmate = this.classmatesController.getClassmate("100");
		
		this.groupsController.addClassmateToGroup(
				classmate.getRegistrationNumber(),
				firstGroupName
				);
		this.groupsController.addClassmateToGroup(
				classmate.getRegistrationNumber(),
				secondGroupName
				);
		
		OperationResult result = this
				.groupsController
				.displayClassmateGroups(classmate.getRegistrationNumber());

		assertEquals(1, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void displayInexistentClassmateGroupsTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedMessage = "ALUNO NÃO CADASTRADO.";
		String groupName = "POO";
		
		this.groupsController.registerGroup(groupName, 10);
		
		Classmate classmate = new Classmate(
				"500",
				"Jojen Reed",
				"Medicina"
				);
		
		OperationResult result = this
				.groupsController
				.displayClassmateGroups(classmate.getRegistrationNumber());

		assertEquals(0, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
	
	@Test
	void displayGroupNonRepeatedCoursesTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a operação "
				+ "produza a mensagem de sucesso esperada.";
		
		String expectedMessage = "Cursos de cada grupo:\n"
				+ "poo: Engenharia Mecânica 1 Artes cênicas 1 /\n"
				+ "lists: Ciência da Computação 1 Medicina 1 /\n";
		
		String groupName = "POO";
		String secondGroupName= "Lists";
		
		this.groupsController.registerGroup(groupName, 2);
		this.groupsController.registerGroup(secondGroupName, 2);
		
		this.groupsController.addClassmateToGroup("100", groupName);
		this.groupsController.addClassmateToGroup("200", groupName);
		this.groupsController.addClassmateToGroup("300", secondGroupName);
		this.groupsController.addClassmateToGroup("400", secondGroupName);
		
		OperationResult result = this.groupsController.displayGroupsCourses();
		
		assertEquals(1, result.getStatus(), msg);
		assertEquals(expectedMessage, result.getMessage(), msg2);
	}
}
