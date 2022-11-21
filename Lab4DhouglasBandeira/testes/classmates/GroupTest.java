package classmates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupTest {
	
	private Group group;

	@BeforeEach
	void setUp() {
		this.group = new Group("Hashmaps", 3);
	}

	@AfterEach
	void tearDown() {
		this.group = null;
	}

	@Test
	void addClassmateTest() {
		String msg = "Espera-se que o aluno seja adicionado ao grupo";
		Classmate classmate = new Classmate("100", "Jon Snow", "Engenharia Mecânica");
		
		this.group.addClassmate(classmate);
		
		assertTrue(this.group.getClassmates().contains(classmate), msg);
	}

	@Test
	void isNotFullTest() {
		String msg = "Espera-se que, com 1 pessoas, um grupo não esteja cheio.";
		String msg2 = "Espera-se que, com 2 pessoa, um grupo não esteja cheio.";
		Classmate classmate = new Classmate(
				"100",
				"Sir Criston Cole",
				"Licenciatura em Química"
				);
		Classmate classmate2 = new Classmate("200", "Otto HighTower", "Filosofia");
		
		this.group.addClassmate(classmate);
		assertFalse(this.group.isFull(), msg);
		
		this.group.addClassmate(classmate2);
		assertFalse(this.group.isFull(), msg2);
	}
	
	@Test
	void isFullTest() {
		String msg = "Espera-se que, ao atingir o limite, o grupo esteja cheio.";
		Classmate classmate = new Classmate(
				"100",
				"Tiwin Lannister",
				"Matemática"
				);
		Classmate classmate2 = new Classmate(
				"200",
				"Brienne de Tarth",
				"Engenharia Agrícola"
				);
		Classmate classmate3 = new Classmate(
				"300",
				"Eddard Stark",
				"História"
				);
		
		this.group.addClassmate(classmate);
		this.group.addClassmate(classmate2);
		this.group.addClassmate(classmate3);
		
		assertTrue(this.group.isFull(), msg);
	}
	
	@Test
	void isClassmateOnGroupTest() {
		String msg = "Espera-se que a afirmação 'Daemon Targaryen "
				+ "está no grupo Hashmaps' seja verdadeira.";
		Classmate classmate = new Classmate("100", "Daemon Targaryen", "Física");
		this.group.addClassmate(classmate);
		
		assertTrue(this.group.isClassmateOnGroup(classmate), msg);
	}
	
	@Test
	void isClassmateNotOnGroupTest() {
		String msg = "Espera-se que a afirmação 'Hodor está no grupo HashMaps'"
				+ "seja falsa";
		
		Classmate classmate = new Classmate("100", "Hodor", "Nutrição");
		
		assertFalse(this.group.isClassmateOnGroup(classmate), msg);
	}
}
