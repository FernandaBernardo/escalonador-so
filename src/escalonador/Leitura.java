package escalonador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {
	public Processo leituraProcesso (FileReader arquivo) throws IOException {
		BufferedReader br = new BufferedReader(arquivo);
		String nome = null;
		String[] instrucao = new String[21];
		nome = br.readLine();
		int cont = 0;
		String aux = null;
		while (aux != "SAIDA") {
			aux = br.readLine();
			instrucao[cont] = aux;
			cont++;
		}
		Processo processo = new Processo(nome, instrucao);
		
		return processo;
	}
}
