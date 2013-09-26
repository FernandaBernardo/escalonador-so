package escalonador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {
	private int maxProcessos = 10;
	private TabelaProcessos tabelaProcessos;
	private int[] prioridades;
	private int numProcesso = 0;
	
	public Leitura() throws Exception {
		tabelaProcessos = new TabelaProcessos();
		prioridades = new int[maxProcessos];
		lerPrioridades();
		lerArquivos();
		tabelaProcessos.imprimirPrioridades();
	}
	
	private void lerPrioridades() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/processos/prioridades.txt")));
		for (int i = 0; i < maxProcessos; i++) {
			prioridades[i] = Integer.parseInt(br.readLine());
		}
		br.close();
	}

	private void lerArquivos() throws IOException {
		for (int i = 1; i <= 10; i++) {
			FileReader arquivo = new FileReader(i != 10 ? "src/processos/0"+i+".txt" : "src/processos/10.txt");
			numProcesso = i-1;
			armazenaProcesso(arquivo);
		}
	}
	
	private void armazenaProcesso (FileReader arquivo) throws IOException {
		BufferedReader br = new BufferedReader(arquivo);
		String[] instrucao = new String[21];
		String nome = br.readLine();
		int cont = 0;
		String aux = null;
		while (!"SAIDA".equals(aux) && cont<21) {
			aux = br.readLine();
			if (aux != null) {
				instrucao[cont] = aux;
				cont++;
			}
		}
		
		br.close();
		Processo processo = new Processo(nome, instrucao, Processo.PRONTO);
		criaBCP(processo);
	}

	private void criaBCP(Processo processo) {
		BlocoDeControleDeProcessos bcp = new BlocoDeControleDeProcessos(processo, 0, prioridades[numProcesso]);
		tabelaProcessos.adicionaBlocoProntos(bcp);
	}

	public TabelaProcessos getTabelaProcessos() {
		return tabelaProcessos;
	}
}
