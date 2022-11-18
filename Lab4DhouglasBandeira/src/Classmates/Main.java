package Classmates;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClassmatesController classmatesController = new ClassmatesController();
		GroupsController groupsController = new GroupsController();
		String choose = "";
		while (true) {
			choose = menu(scanner);
			command(
					choose,
					classmatesController,
					groupsController,
					scanner
			);
		}
	}
	
	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println();
		System.out.println(
				"(C)adastrar Aluno\n" +
				"(E)xibir Aluno\n" +
				"(N)ovo Grupo\n" +
				"(A)locar Aluno no Grupo e Verificar pertinência a Grupos\n" +
				"(O)lhaí quais Grupos o Aluno Tá.\n" +
				"(S)im, quero Fechar o Programa!\n" +
				"(R)egistrar Aluno que Respondeu\n" + 
				"(I)mprimir Alunos que Responderam\n" +
				"(M)ostrar Estatísticas\n" +
				"Opção> ");
		return scanner.next().toUpperCase();
	}
	
	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param option   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void command(
			String option,
			ClassmatesController classmatesController,
			GroupsController groupController,
			Scanner scanner) {
		switch (option) {
		case "C":
			registerClassmate(classmatesController, scanner);
			break;
		case "E":
			displayClassmate(classmatesController, scanner);
			break;
		case "N":
			registerGroup(groupController, scanner);
			break;
		case "A":
			System.out.print("(A)locar Aluno ou (P)ertinência a Grupo?");
			String newOption = scanner.next().toUpperCase();
			switch (newOption) {
				case "A":
					allocateClassmateInGroup(
							classmatesController,
							groupController,
							scanner
							);
					break;
				case "P":
					classmateBelongsToGroup(
							classmatesController,
							groupController,
							scanner
							);
					break;
				default:
					System.out.println("AÇÂO INVÁLIDA!");
					System.exit(0);
			}
			break;
		case "O":
			displayClassmateGroups(
					classmatesController,
					groupController,
					scanner
					);
			break;
		case "R":
			registerClassmateAnswer(classmatesController, scanner);
			break;
		case "I":
			displayAnswers(classmatesController, scanner);
			break;
		case "M":
			showData(
					classmatesController,
					groupController,
					scanner
					);
			break;
		case "S":
			System.out.println("FINALIZANDO...");
			System.exit(0);
			break;
		default:
			System.out.println("AÇÂO INVÁLIDA!");
			System.exit(0);
		}
	}

	private static void registerClassmate(
			ClassmatesController classmatesController, 
			Scanner scanner
			) {
		
		scanner.nextLine();
		System.out.println("Matrícula: ");
		String registrationNumber = scanner.nextLine();
		System.out.println("Nome: ");
		String name = scanner.nextLine();
		System.out.println("Curso: ");
		String course = scanner.nextLine();
		
		System.out.println(classmatesController.registerClassmate(
				registrationNumber,
				name,
				course
				).getMessage());
	}
	
	private static void displayClassmate(
			ClassmatesController classmatesController,
			Scanner scanner
			) {
		
		System.out.println("Matrícula: ");
		String registrationNumber = scanner.next();
		
		System.out.println(classmatesController
				.displayClassmate(registrationNumber)
				.getMessage()
			);
	}
	
	private static void registerGroup(
			GroupsController groupsController,
			Scanner scanner
			) {
		
		System.out.println("Grupo: ");
		String name = scanner.next();
		
		scanner.nextLine();
		System.out.println("Tamanho: ");
		String size = scanner.nextLine();
		
		if (size.isBlank()) {
			System.out.println(groupsController
			.registerGroup(name)
			.getMessage());
			return;
		}
		
		System.out.println(groupsController
		.registerGroup(name, Integer.valueOf(size))
		.getMessage()
		);
	}
	
	private static void allocateClassmateInGroup(
			ClassmatesController classmatesController,
			GroupsController groupsController,
			Scanner scanner
			) {
		System.out.println("Matrícula: ");
		String registrationNumber = scanner.next();
		System.out.println("Grupo: ");
		String groupName = scanner.next();
		Classmate classmate = classmatesController.getClassmate(registrationNumber);
		
		System.out.println(
				groupsController
				.addClassmateToGroup(classmate, groupName)
				.getMessage()
		);
	}
	
	private static void classmateBelongsToGroup(
			ClassmatesController classmatesController,
			GroupsController groupsController,
			Scanner scanner
			) {
		System.out.println("Grupo: ");
		String groupName = scanner.next();
		System.out.println("Aluno: ");
		String registrationNumber = scanner.next();
		Classmate classmate = classmatesController.getClassmate(registrationNumber);
		
		System.out.println(
				groupsController
				.classmateBelongsToGroup(classmate, groupName)
				.getMessage()
		);
	}
	
	private static void displayClassmateGroups(
			ClassmatesController classmatesController,
			GroupsController groupsController,
			Scanner scanner
			) {
		System.out.println("Aluno: ");
		String registrationNumber = scanner.next();
		
		Classmate classmate = classmatesController.getClassmate(registrationNumber);
		
		System.out.println(
				groupsController
				.displayClassmateGroups(classmate)
				.getMessage()
		);
	}
	
	private static void registerClassmateAnswer(ClassmatesController classmatesController, Scanner scanner) {
		System.out.println("Matricula: ");
		String registrationNumber = scanner.next();
		
		System.out.println(
				classmatesController
				.registerClassmateAnswer(registrationNumber)
				.getMessage()
				);
	}
	
	private static void displayAnswers(ClassmatesController classmatesController, Scanner scanner) {
		System.out.println(
				classmatesController
				.displayClassmatesAnswers()
				.getMessage()
		);
	}
	
	private static void showData(
			ClassmatesController classmatesController,
			GroupsController groupController,
			Scanner scanner
		) {
		System.out.println("Aluno(a)(s) que pertence(m) a mais grupos:\n");
		for (String name : classmatesController.getMostActiveClassmates()) {
			System.out.println(name + "\n");
		}
		
		System.out.println(classmatesController.displayClassmatesWithoutGroups());
	}
}
