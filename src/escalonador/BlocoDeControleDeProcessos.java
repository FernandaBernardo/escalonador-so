package escalonador;

public class BlocoDeControleDeProcessos {
	Processo processo;
	String contadorDePrograma;
	String prioridade;
	String estadoRegistrador;
	String[] referenciaCodigo;
	String nome;
	
	public BlocoDeControleDeProcessos(String contadorDePrograma, Processo processo, 
			String prioridade, String estadoRegistrador, String[] referenciaCodigo, String nome) {
		this.contadorDePrograma = contadorDePrograma;
		this.processo = processo;
		this.prioridade = prioridade;
		this.estadoRegistrador = estadoRegistrador;
		this.referenciaCodigo = referenciaCodigo;
		this.nome = nome;
	}
}