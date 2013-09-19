package escalonador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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

	public void ordenaBlocoProntos () {
		Collections.sort(prontos);
	}
	
	public void imprimirPrioridades () {
		Iterator<BlocoDeControleDeProcessos> it = prontos.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().prioridade);
		}
	}
	
}
