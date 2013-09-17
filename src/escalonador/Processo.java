package escalonador;

public class Processo {
	static final char PRONTO = 'p';
	static final char EXECUTANDO = 'e';
	static final char BLOQUEADO = 'b';
	
	private char estado;
	
	public Processo() {

	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
}
