package escalonador;

import static escalonador.TabelaProcessos.bloqueados;
import static escalonador.TabelaProcessos.prontos;

public class Escalonador {
	
	private static int quantum;
	
	public static void main(String[] args) throws Exception {
		Leitura leitura = new Leitura();
		
		quantum = leitura.getQuantum();
		
		System.out.println("prontos: "+ prontos.size());
		System.out.println("bloqueados: "+ bloqueados.size());
		System.out.println("primeiro pronto: "+ prontos.get(0).prioridade);
		
		while (prontos.size() > 0 || bloqueados.size() > 0) {
			int cont = 0;
			boolean parouAntesQuantum = false;
			BCP atual = TabelaProcessos.removePrimeiroProntos();
			while (cont <= quantum) {
				atual.decrementaCredito();
				
				String instrucao = atual.processo.instrucao[atual.contadorDePrograma];
				if (instrucao == "E/S" && atual.flag == 0) {
					entradaSaida(atual);
					parouAntesQuantum = true;
					break;
				}
				else if (instrucao == "E/S" && atual.flag == 1) atual.flag = 0;
				
				atual.contadorDePrograma++;
				cont++;
			}
			if (!parouAntesQuantum) TabelaProcessos.adicionaBlocoProntos(atual);
			decrementaBloqueados();
			verificaZeroEspera();
			verificaZeroCreditosProntos();
		}
	}
	
	private static void entradaSaida (BCP processo) {
		TabelaProcessos.adicionaBlocoBloqueados(processo);
		int index = bloqueados.indexOf(processo);
		bloqueados.get(index).espera = 2;
		bloqueados.get(index).flag = 1;
	}
	
	private static boolean verificaZeroCreditosProntos () {
		for (BCP p : prontos) {
			if (p.credito != 0) return false; 
		}
		for (BCP p : prontos) {
			p.credito = p.prioridade;
		}
		return true;
	}

	private static void decrementaBloqueados () {
		for (BCP b : bloqueados) {
			b.espera--;
		}
	}
	
	private static void verificaZeroEspera () {
		for (BCP b : bloqueados) {
			if (b.espera == 0) {
				TabelaProcessos.adicionaBlocoProntos(b);
				TabelaProcessos.removePrimeiroBloqueados();
			}
			else return;
		}
	}
}
