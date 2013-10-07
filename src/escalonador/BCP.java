package escalonador;

/* Classe que representa o BLOCO DE CONTROLE DE PROCESSOS (BCP)
 * O BCP possui todas as informações do processo - tudo que é necessário 
 * para reiniciar um processo do ponto em que o mesmo foi interrompido.
 */

public class BCP implements Comparable<BCP>{
	Processo processo;
	int prioridade;
	int contadorDePrograma;
	int registradorX;
	int registradorY;
	int credito;
	int espera;
	int flag;
	
        /*Construtor do BCP recebe como parâmetro o processo e a sua respectiva prioridade*/
	public BCP(Processo processo, int prioridade) {
		this.processo = processo;
		this.contadorDePrograma = 0;
		this.prioridade = prioridade;
		this.registradorX = 0;
		this.registradorY = 0;
		this.credito = prioridade;
		this.espera = 0;
		this.flag = 0;
	}
        
        /*Para que todos os processos tenham chances de executar,
         a prioridade decrementa um a cada vez que o processo é executado*/
	public void decrementaCredito () {
		credito--;
	}
	
	/*Método sobreescrito para ordenar a tabela de processos pelo numero de créditos*/
        @Override
	public int compareTo(BCP bcp) {
		if (this.credito < bcp.credito) return 1;
		else if (this.credito > bcp.credito) return -1;
		else return 0;
	}
}