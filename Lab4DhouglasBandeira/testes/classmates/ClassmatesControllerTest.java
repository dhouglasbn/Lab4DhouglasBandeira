package classmates;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClassmatesControllerTest {
	
	private ClassmatesController classmatesController;

	@BeforeEach
	void setUp() {
		this.classmatesController = new ClassmatesController();
	}

	@AfterEach
	void tearDown() {
		this.classmatesController = null;
	}

	@Test
	void registerClassmateTest() {
		String msg = "Espera-se que a operação seja bem sucedida(status: 1).";
		String msg2 = "Espera-se que a mensagem da operação seja 'CADASTRO REALIZADO!'";
		
		OperationResult result = this.classmatesController.registerClassmate(
				"100",
				"Rhaenyra Targaryen",
				"Engenharia Espacial"
		);
		
		assertEquals(result.getStatus(), 1, msg);
		assertEquals(result.getMessage(), "CADASTRO REALIZADO!", msg2);
	}
	
	@Test
	void registerAlreadyExistentClassmateTest() {
		String msg = "Espera-se que a operação seja mal sucedida(status: 0).";
		String msg2 = "Espera-se que a mensagem da operação"
				+ " seja 'MATRÍCULA JÁ CADASTRADA!'";
		
		this.classmatesController.registerClassmate(
				"100",
				"Aemon Targaryen",
				"História"
		);
		
		OperationResult result = this.classmatesController.registerClassmate(
				"100",
				"Arya Stark",
				"Engenharia de Minas"
		);
		
		assertEquals(result.getStatus(), 0, msg);
		assertEquals(result.getMessage(), "MATRÍCULA JÁ CADASTRADA!", msg2);
	}

	@Test
	void classmateAlreadyExistsTest() {
		String msg = "Espera-se que a afirmação 'O aluno de matrícula "
				+ "100 já está cadastrado' seja verdadeira.";
		
		this.classmatesController.registerClassmate(
				"100",
				"Cersei Lannister",
				"Psicologia"
		);
		
		boolean result = this.classmatesController.classmateAlreadyExists("100");
		
		assertTrue(result, msg);
	}
	
	@Test
	void classmateDoesntExistsTest() {
		String msg = "Espera-se que a afirmação 'O aluno de matrícula "
				+ "100 já está cadastrado' seja falsa.";
		
		boolean result = this.classmatesController.classmateAlreadyExists("100");
		
		assertFalse(result, msg);
	}
	
	@Test
	void displayClassmateTest() {
		String msg = "Espera-se que a operação seja bem sucedida (status: 1).";
		String msg2 = "Espera-se que a operação produza a mensagem esperada "
				+ "no resultado.";
		
		String expectedMessage = "Aluno: 100 - Ramsay Bolton - Medicina";
		
		this.classmatesController.registerClassmate(
				"100",
				"Ramsay Bolton",
				"Medicina"	
		);
		
		OperationResult result = this.classmatesController.displayClassmate("100");
		
		assertEquals(result.getStatus(), 1, msg);
		assertEquals(result.getMessage(), expectedMessage, msg2);
	}
	
	@Test
	void displayNonExistentClassmateTest() {
		String msg = "Espera-se que a operação seja mal sucedida (status: 0).";
		String msg2 = "Espera-se que a operação produza a mensagem de erro esperada.";
		
		String expectedMessage = "ALUNO NÃO CADASTRADO.";
		
		OperationResult result = this.classmatesController.displayClassmate("100");
		
		assertEquals(result.getStatus(), 0, msg);
		assertEquals(result.getMessage(), expectedMessage, msg2);
	}
	
	@Test
	void registerClassmateAnswerTest() {
		String msg = "Espera-se que a operação seja bem sucedida (status: 1).";
		String msg2 = "Espera-se que a operação produza a "
				+ "mensagem de sucesso esperada.";
		
		String expectedMessage = "ALUNO REGISTRADO!";
		
		this.classmatesController.registerClassmate(
				"100",
				"Larys Strong",
				"Ciências Biológicas"
		);
		
		OperationResult result = this.classmatesController.registerClassmateAnswer("100");
		
		assertEquals(result.getStatus(), 1, msg);
		assertEquals(result.getMessage(), expectedMessage, msg2);
	}
	
	@Test
	void registerNonExistentClassmateAnswerTest() {
		String msg = "Espera-se que a operação seja mal sucedida (status: 0).";
		String msg2 = "Espera-se que a operação produza a "
				+ "mensagem de erro esperada.";
		
		String expectedMessage = "Aluno não cadastrado.";
		
		OperationResult result = this.classmatesController.registerClassmateAnswer("100");
		
		assertEquals(result.getStatus(), 0, msg);
		assertEquals(result.getMessage(), expectedMessage, msg2);
	}
	
	@Test
	void displayAnsweredClassmatesTest() {
		String msg = "Espera-se que a operação seja bem sucedida (status: 1).";
		String msg2 = "Espera-se que a operação produza a "
				+ "mensagem esperada.";
		
		String expectedMessage = "Alunos:\n"
				+ "1. 100 - Tormund Giantsbane - Ciências Biológicas\n"
				+ "2. 110 - Brandon Stark - Medicina\n"
				+ "3. 120 - Walder Frey - Ciências Espaciais\n"
				+ "4. 110 - Brandon Stark - Medicina\n"
				+ "5. 100 - Tormund Giantsbane - Ciências Biológicas\n"
				+ "6. 120 - Walder Frey - Ciências Espaciais\n";
		
		this.classmatesController.registerClassmate(
				"100",
				"Tormund Giantsbane",
				"Ciências Biológicas"
		);
		this.classmatesController.registerClassmate(
				"110",
				"Brandon Stark",
				"Medicina"
		);
		this.classmatesController.registerClassmate(
				"120",
				"Walder Frey",
				"Ciências Espaciais"
		);
		
		this.classmatesController.registerClassmateAnswer("100");
		this.classmatesController.registerClassmateAnswer("110");
		this.classmatesController.registerClassmateAnswer("120");
		this.classmatesController.registerClassmateAnswer("110");
		this.classmatesController.registerClassmateAnswer("100");
		this.classmatesController.registerClassmateAnswer("120");
		
		OperationResult result = this
				.classmatesController
				.displayAnsweredClassmates();
		
		assertEquals(result.getStatus(), 1, msg);
		assertEquals(result.getMessage(), expectedMessage, msg2);
	}
}
