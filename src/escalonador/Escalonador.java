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
//		TabelaProcessos.imprimirInstrucoes();
		
		while (prontos.size() > 0 || bloqueados.size() > 0) {
			if (prontos.size()>0) {
				int cont = 0;
				boolean parou = false;
				BCP atual = TabelaProcessos.removePrimeiroProntos();
				while (atual != null && cont < quantum) {
					atual.decrementaCredito();
					
					String instrucao = atual.processo.instrucao[atual.contadorDePrograma];
					System.out.println(instrucao + " " + atual.processo.nome);
					if ("E/S".equals(instrucao)) {
						if (atual.flag == 0) {
							entradaSaida(atual);
							parou = true;
							break;
						}
						else {
							atual.flag = 0;
						}
					}
					else if ("SAIDA".equals(instrucao)) {
						prontos.remove(atual);
						parou = true;
						break;
					}
					else if (instrucao.contains("X=")) {
						atual.registradorX = instrucao.charAt(2);
					}
					else if (instrucao.contains("Y=")) {
						atual.registradorY = instrucao.charAt(2);
					}
					
					atual.contadorDePrograma++;
					cont++;
				}
				if (!parou) TabelaProcessos.adicionaBlocoProntos(atual);
			}
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
			if (b.espera > 0) b.espera--;
		}
	}
	
	private static void verificaZeroEspera () {
		int cont = 0;
		for (BCP b : bloqueados) {
			if (b.espera == 0) {
				TabelaProcessos.adicionaBlocoProntos(b);
				cont++;
			}
			else break;
		}
		for (int i = 0; i < cont; i++) {
			TabelaProcessos.removePrimeiroBloqueados();
		}
	}
}
