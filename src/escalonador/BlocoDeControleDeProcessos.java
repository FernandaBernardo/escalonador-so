package escalonador;

public class BlocoDeControleDeProcessos implements Comparable<BlocoDeControleDeProcessos>{
	Processo processo;
	int prioridade;
	int contadorDePrograma;
	int registradorX;
	int registradorY;
	
	public BlocoDeControleDeProcessos(Processo processo, int contadorDePrograma, int prioridade) {
		this.processo = processo;
		this.contadorDePrograma = contadorDePrograma;
		this.prioridade = prioridade;
		this.registradorX = -1;
		this.registradorY = -1;
	}

	@Override
	public int compareTo(BlocoDeControleDeProcessos bcp) {
		if (this.prioridade < bcp.prioridade) return 1;
		else if (this.prioridade > bcp.prioridade) return -1;
		else return 0;
	}

	
}