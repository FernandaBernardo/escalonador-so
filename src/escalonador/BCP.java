package escalonador;

public class BCP implements Comparable<BCP>{
	Processo processo;
	int prioridade;
	int contadorDePrograma;
	int registradorX;
	int registradorY;
	int credito;
	int espera;
	int flag;
	
	public BCP(Processo processo, int contadorDePrograma, int prioridade) {
		this.processo = processo;
		this.contadorDePrograma = contadorDePrograma;
		this.prioridade = prioridade;
		this.registradorX = 0;
		this.registradorY = 0;
		this.credito = prioridade;
		this.espera = 0;
		this.flag = 0;
	}

	@Override
	public int compareTo(BCP bcp) {
		if (this.credito < bcp.credito) return 1;
		else if (this.credito > bcp.credito) return -1;
		else return 0;
	}

	
}