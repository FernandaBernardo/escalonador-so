package escalonador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TabelaProcessos {
	static ArrayList<BCP> prontos;
	static ArrayList<BCP> bloqueados;	

	public TabelaProcessos(	) {
		prontos = new ArrayList<>();
		bloqueados = new ArrayList<>();
	}

	public void adicionaBlocoProntos(BCP bloco) {
		prontos.add(bloco);
	}
	
	public void adicionaBlocoBloqueados (BCP bloco) {
		bloqueados.add(bloco);
	}

	public static void ordenaBlocoProntos () {
		Collections.sort(prontos);
	}
	
	public void imprimirPrioridades () {
		Iterator<BCP> it = prontos.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().prioridade);
		}
	}
	
	public static boolean zerouCredito () {
		for (Iterator<BCP> iterator = prontos.iterator(); iterator.hasNext();) {
			if (iterator.next().credito != 0) return false;
		}
		return true;
	}
	
	public static void redistribuiCredito () {
		for (Iterator<BCP> iterator = prontos.iterator(); iterator.hasNext();) {
			BCP aux = iterator.next();
			aux.credito = aux.prioridade;
		}
	}
}
