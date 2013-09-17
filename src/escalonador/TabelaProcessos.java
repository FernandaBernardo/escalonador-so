package escalonador;

import java.util.ArrayList;

public class TabelaProcessos {
	private ArrayList<BlocoDeControleDeProcessos> todos;
	private ArrayList<BlocoDeControleDeProcessos> prontos;
	private ArrayList<BlocoDeControleDeProcessos> bloqueados;	

	public TabelaProcessos(ArrayList<BlocoDeControleDeProcessos> todos,
			ArrayList<BlocoDeControleDeProcessos> prontos,
			ArrayList<BlocoDeControleDeProcessos> bloqueados) {
		this.todos = new ArrayList<>();
		this.prontos = new ArrayList<>();
		this.bloqueados = new ArrayList<>();
	}


	public void adicionaBlocoTodos (BlocoDeControleDeProcessos bloco) {
		todos.add(bloco);
	}
	
	public void adicionaBlocoProntos(BlocoDeControleDeProcessos bloco) {
		prontos.add(bloco);
	}
	
	public void adicionaBlocoBloqueados (BlocoDeControleDeProcessos bloco) {
		bloqueados.add(bloco);
	}

}
