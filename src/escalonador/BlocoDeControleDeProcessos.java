package escalonador;

public class BlocoDeControleDeProcessos {
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

	
}