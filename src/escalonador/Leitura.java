package escalonador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {
	private TabelaProcessos tabelaProcessos;
	private int[] prioridades;
	private int numProcesso = 0;
	
	public Leitura() throws Exception {
		tabelaProcessos = new TabelaProcessos();
		prioridades = new int[10];
		lerPrioridades();
		lerArquivos();
		tabelaProcessos.ordenaBlocoProntos();
		tabelaProcessos.imprimirPrioridades();
	}
	
	private void lerPrioridades() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/processos/prioridades.txt")));
		for (int i = 0; i < prioridades.length; i++) {
			prioridades[i] = Integer.parseInt(br.readLine());
		}
		br.close();
	}

	private void lerArquivos() throws IOException {
		for (int i = 1; i <= 10; i++) {
			FileReader arquivo = new FileReader(i != 10 ? "src/processos/0"+i+".txt" : "src/processos/10.txt");
			numProcesso = i-1;
			leituraProcesso(arquivo);
		}
	}
	
	private void leituraProcesso (FileReader arquivo) throws IOException {
		BufferedReader br = new BufferedReader(arquivo);
		String nome = null;
		String[] instrucao = new String[21];
		nome = br.readLine();
		int cont = 0;
		String aux = null;
		int regX = -1;
		int regY = -1;
		while (!"SAIDA".equals(aux) && cont<21) {
			aux = br.readLine();
			if (aux != null) {
				instrucao[cont] = aux;
				if (aux.contains("X=")) regX = aux.charAt(2);
				else if (aux.contains("Y=")) regY = aux.charAt(2);
				
				cont++;
			}
		}
		Processo processo = new Processo(nome, instrucao);
		
		criaBCP(processo, regX, regY);
		br.close();
	}

	private void criaBCP(Processo processo, int regX, int regY) {
		BlocoDeControleDeProcessos bcp = new BlocoDeControleDeProcessos(
				processo, 0, prioridades[numProcesso], regX, regY);
		tabelaProcessos.adicionaBlocoProntos(bcp);
	}

	public TabelaProcessos getTabelaProcessos() {
		return tabelaProcessos;
	}

	public void setTabelaProcessos(TabelaProcessos tabelaProcessos) {
		this.tabelaProcessos = tabelaProcessos;
	}
}
