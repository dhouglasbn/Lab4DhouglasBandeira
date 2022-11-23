package classmates;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClassmateTest {
	
	private Classmate classmate;

	@BeforeEach
	void setUp() {
		this.classmate = new Classmate(
				"100",
				"Daenerys Targaryen",
				"Ciências da Computação"
				);
	}

	@AfterEach
	void tearDown() {
		this.classmate = null;
	}

	@Test
	void addGroupTest() {
		String msg = "Espera-se que o aluno esteja em exatamente um grupo";
		String msg2 = "Espera-se que o aluno esteja no grupo 'Tratamento de Erros'";
		String groupName = "Tratamento de Erros";
		
		classmate.addGroup(groupName);
		
		assertEquals(1, classmate.getNumberOfGroups(), msg);
		assertTrue(classmate.isOnGroup(groupName), msg2);
	}
	
	@Test
	void isOnGroupTest() {
		String msg = "Espera-se que a afirmação, 'o aluno está no grupo listas'"
				+ "seja verdadeira";
		String groupName = "Listas";
		
		classmate.addGroup(groupName);
		
		assertTrue(classmate.isOnGroup(groupName), msg);
	}
	
	@Test
	void isNotOnGroupTest() {
		String msg = "Espera-se que a afirmação 'o aluno está no grupo Listas'"
				+ "seja falsa";
		String groupName = "Listas";
		
		assertFalse(classmate.isOnGroup(groupName), msg);
	}
	
	@Test
	void toStringTest() {
		String msg = "Espera-se que a função toString retorne o formato esperado.";
		String expectedResult = "Aluno: 100 - Daenerys Targaryen - " +
		"Ciências da Computação";
		
		assertEquals(expectedResult, classmate.toString(), msg);
	}

}
