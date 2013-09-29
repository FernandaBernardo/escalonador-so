package escalonador;

import java.util.Iterator;

public class TabelaProcessos {
	static SortedList prontos;
	static SortedList bloqueados;	

	public TabelaProcessos(	) {
		prontos = new SortedList();
		bloqueados = new SortedList();
	}

	public static void adicionaBlocoProntos(BCP bloco) {
		prontos.insertSorted(bloco);
	}
	
	public static void adicionaBlocoBloqueados (BCP bloco) {
		bloqueados.insertSorted(bloco);
	}
	
	public static BCP removePrimeiroProntos() {
		return prontos.remove(0);
	}
	
	public static BCP removePrimeiroBloqueados () {
		return bloqueados.remove(0);
	}
	
	public static void imprimirPrioridades () {
		Iterator<BCP> it = prontos.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().prioridade);
		}
	}
}
