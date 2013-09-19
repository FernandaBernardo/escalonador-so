package escalonador;

public class BlocoDeControleDeProcessos implements Comparable<BlocoDeControleDeProcessos>{
	Processo processo;
	int prioridade;
	int contadorDePrograma;
	int registradorX;
	int registradorY;
	
	public BlocoDeControleDeProcessos(Processo processo, int contadorDePrograma, 
			int prioridade, int registradorX, int registradorY) {
		this.processo = processo;
		this.contadorDePrograma = contadorDePrograma;
		this.prioridade = prioridade;
		this.registradorX = registradorX;
		this.registradorY = registradorY;
	}

	@Override
	public int compareTo(BlocoDeControleDeProcessos bcp) {
		if (this.prioridade > bcp.prioridade) return 1;
		else if (this.prioridade < bcp.prioridade) return -1;
		else return 0;
	}

	
}