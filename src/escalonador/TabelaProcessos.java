package escalonador;

import java.util.ArrayList;

public class TabelaProcessos {
	private ArrayList<BlocoDeControleDeProcessos> prontos;
	private ArrayList<BlocoDeControleDeProcessos> bloqueados;	

	public TabelaProcessos(	) {
		this.prontos = new ArrayList<>();
		this.bloqueados = new ArrayList<>();
	}

	public void adicionaBlocoProntos(BlocoDeControleDeProcessos bloco) {
		prontos.add(bloco);
	}
	
	public void adicionaBlocoBloqueados (BlocoDeControleDeProcessos bloco) {
		bloqueados.add(bloco);
	}

}
