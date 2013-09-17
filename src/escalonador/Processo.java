package escalonador;

public class Processo {
	static final char PRONTO = 'p';
	static final char EXECUTANDO = 'e';
	static final char BLOQUEADO = 'b';
	final String [] instrucao; 
	private String nome;
	private char estado;
	
	public Processo(String nome, char estado) {
		this.nome = nome;
		this.estado = estado;
		instrucao = new String[21];
	}
}
