package Classmates;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClassmatesController classmatesController = new ClassmatesController();
		String choose = "";
		while (true) {
			choose = menu(scanner);
			command(choose, classmatesController, scanner);
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
				"(S)im, quero Fechar o Programa!" +
				"\n" + 
				"Opção> ");
		return scanner.next().toUpperCase();
	}
	
	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void command(
			String opcao,
			ClassmatesController classmatesController,
			Scanner scanner) {
		switch (opcao) {
		case "C":
			registerClassmate(classmatesController, scanner);
			break;
		case "E":
			displayClassmate(classmatesController, scanner);
			break;
//		case "N":
//			exibeContato(scanner);
//			break;
//		case "A":
//			listaFavoritos();
//			break;
//		case "O":
//			adicionaFavorito(scanner);
//			break;
		case "S":
			System.out.println("FINALIZANDO...");
			System.exit(0);
			break;
		default:
			System.out.println("AÇÂO INVÁLIDA!");
			System.exit(0);
		}
	}
	
	public static void registerClassmate(
			ClassmatesController classmatesController, 
			Scanner scanner
			) {
		System.out.println("Matrícula: ");
		scanner.nextLine();
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
	
	public static void displayClassmate(
			ClassmatesController classmatesController,
			Scanner scanner
			) {
		System.out.println("Matrícula: ");
		scanner.nextLine();
		String registrationNumber = scanner.nextLine();
		
		System.out.println(classmatesController
				.getClassmate(registrationNumber)
				.getMessage()
			);
	}
}
