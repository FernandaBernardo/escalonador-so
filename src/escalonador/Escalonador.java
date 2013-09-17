package escalonador;

import java.io.FileReader;
import java.io.IOException;

public class Escalonador {
	static Leitura leitura = new Leitura();
	static TabelaProcessos tabelaProcessos;
	
	public static void main(String[] args) throws IOException {
		lerArquivos();
	}

	private static void lerArquivos() throws IOException {
		for (int i = 0; i < 10; i++) {
			FileReader arquivo = new FileReader(i != 10 ? "0"+i : "10");
			leitura.leituraProcesso(arquivo);
		}
	}
}
