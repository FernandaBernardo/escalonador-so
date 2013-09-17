package escalonador;

public class Processo {
	static final char PRONTO = 'p';
	static final char EXECUTANDO = 'e';
	static final char BLOQUEADO = 'b';
	final String [] instrucao; 
	String nome;
	char estado;
	
	public Processo(String nome, String[] instrucao/*, char estado*/) {
		this.nome = nome;
//		this.estado = estado;
		this.instrucao = instrucao;
	}
}
