package escalonador;

import static escalonador.TabelaProcessos.bloqueados;
import static escalonador.TabelaProcessos.prontos;

public class Escalonador {
	
	private static int quantum;
	
	
	public static void main(String[] args) throws Exception {
		Leitura leitura = new Leitura();
		quantum = leitura.getQuantum();
		
		executaQuantum();
		
		System.out.println("prontos: "+ prontos.size());
		System.out.println("bloqueados: "+ bloqueados.size());
		System.out.println("primeiro pronto: "+ prontos.get(0).prioridade);
		
		while (prontos.size() > 0 || bloqueados.size() > 0) {
			
		}
	}
	
	private static void executaQuantum () {
		for (int i = 0; i<quantum; i++) {
			BCP processoExecutando = prontos.get(0);
			processoExecutando.credito--;
			
		}
		TabelaProcessos.ordenaBlocoProntos();
	}
	
	private static void entradaSaida () {
		BCP processo = prontos.remove(0);
		bloqueados.add(processo);
		int index = bloqueados.indexOf(processo);
		bloqueados.get(index).espera = 2;
		bloqueados.get(index).flag = 1;
	}
	

}
