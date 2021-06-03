package model.services;

import java.util.List;
import java.util.function.Predicate;

import model.entities.Funcionario;

public class FuncionarioServico {
	public double somaFiltrada(List<Funcionario> funcionarios, Predicate<Funcionario> criterio) {
		double soma = 0.0;
		
		for (Funcionario f : funcionarios) {
			if (criterio.test(f)) {
				soma += f.getSalario();
			}
		}
		
		return soma;
	}
}
