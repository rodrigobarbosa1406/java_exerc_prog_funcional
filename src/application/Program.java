package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Funcionario;
import model.services.FuncionarioServico;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		try {
			List<Funcionario> funcionarios = new ArrayList<>();
			
			System.out.print("Informe o caminho completo do arquivo: ");
			String diretorio = sc.nextLine();
			
			try (BufferedReader br = new BufferedReader(new FileReader(diretorio))){
				String line = br.readLine();
				
				while (line != null) {
					String[] vetor = line.split(",");
					funcionarios.add(new Funcionario(vetor[0], vetor[1], Double.parseDouble(vetor[2])));
					
					line = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.print("Informe o salário: ");
			double salario = sc.nextDouble();
			
			System.out.println();
			
			List<String> emails = funcionarios.stream().filter(f -> f.getSalario() > salario).map(f -> f.getEmail()).collect(Collectors.toList());
			System.out.println("E-mail de pessoas que possuem salário acima de " + String.format("%.2f", salario) + ":");
			emails.forEach(System.out::println);
			
			System.out.println();
			
			System.out.print("Informe a letra: ");
			char letra = sc.next().charAt(0);
			sc.nextLine();
			
			FuncionarioServico fs = new FuncionarioServico();
			double soma = fs.somaFiltrada(funcionarios, f -> f.getNome().charAt(0) == letra);
			
			System.out.print("Soma dos salários das pessoas que possuem o nome começando com a letra " + letra + ": " + String.format("%.2f", soma));
		} catch (RuntimeException e) {
			System.out.println("Erro...");
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

}
