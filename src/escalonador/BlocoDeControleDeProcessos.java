package escalonador;

public class BlocoDeControleDeProcessos {
	Processo processo;
	String contadorDePrograma;
	String prioridade;
	int registradorX;
	int registradorY;
	
	public BlocoDeControleDeProcessos(Processo processo, String contadorDePrograma, 
			String prioridade, int registradorX, int registradorY) {
		this.processo = processo;
		this.contadorDePrograma = contadorDePrograma;
		this.prioridade = prioridade;
		this.registradorX = registradorX;
		this.registradorY = registradorY;
	}

	
}