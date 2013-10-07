package escalonador;

import static escalonador.TabelaProcessos.bloqueados;
import static escalonador.TabelaProcessos.prontos;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Escalonador {
                
	private static int quantum; //quantidade de quantum para cada processo
	private static int interrompido = 0; //contador para a média de processos interrompidos 
	private static int instrucaoQuantum = 0; //contador para a média do numero de instruções por quantum
	private static int numQuantum = 0;//contador geral de quantuns, em relação a todos os processos
	
	public static void main(String[] args) throws Exception {
            Leitura leitura = new Leitura();
            quantum = leitura.getQuantum();
            String numero = Integer.toString(quantum);
            
            //Criando o arquivo LOG
            if(numero.length()==1){
                System.setOut(new PrintStream(new FileOutputStream("0"+quantum+".txt")));   
            }else{
                System.setOut(new PrintStream(new FileOutputStream(quantum+".txt")));
            }
                    
            //Carregando os processos após serem ordenados conforme sua prioridade 
            for (BCP bcp : prontos) {
                System.out.println("Carregando " + bcp.processo.nome);
            }
		
            //Enquanto a fila de processos prontos ou a fila de processos bloqueados forem maiores que 0:
            while (prontos.size() > 0 || bloqueados.size() > 0) {
                //Se a fila de prontos não estiver vazia: 
                if (prontos.size()>0) {
                    int cont = 0;
                    boolean saida = false;
                    boolean parou = false;
                    BCP atual = TabelaProcessos.removePrimeiroProntos(); //O bcp do atual processo é removido da fila de prontos
                    atual.decrementaCredito(); //O crédito de sua prioridade é decrementado
                    System.out.println("Executando " + atual.processo.nome);
                    //Enquanto o processo atual ainda precisar ser rodado e ainda estiver dentro de seu período de quantum: 
                    while (atual != null && cont < quantum) {			
                        String instrucao = atual.processo.instrucao[atual.contadorDePrograma];
                        //Se for uma instrução de entrada e saída, então: 
                        if ("E/S".equals(instrucao)) {
                            if (atual.flag == 0) {
				System.out.println("E/S iniciada em " + atual.processo.nome);
				entradaSaida(atual);
				parou = true;
				break;
                            }
                            else {
                                atual.flag = 0;
                            }
                        }
                        //Se a instrução for "SAIDA" indicqa que tal processo chegou ao seu fim: 
			else if ("SAIDA".equals(instrucao)) {
                            System.out.println(atual.processo.nome + " terminado. X=" +atual.registradorX + ". Y=" +atual.registradorY);
                            prontos.remove(atual);
                            parou = true;
                            saida = true;
                            break;
			}
                        //Se a instrução for de atribuição ao contador X:  
			else if (instrucao.contains("X=")) {
                            atual.registradorX = Integer.parseInt(instrucao.substring(2));
			}
                        //Se a instrução for de atribuição ao contador Y:  
			else if (instrucao.contains("Y=")) {
                            atual.registradorY = Integer.parseInt(instrucao.substring(2));
			}
					
			atual.contadorDePrograma++; //incrementa o contador de programas; 
			cont++; //incrementa o contador de instruções;
                    }
                    /*Quando o processo chega nesse ponto ele por algum motivo chegou ao seu final. seja por um processo 
                    de entrada e saida, termino de quantum ou termino de instruções*/
                    if(!saida){
                        if(cont==0 || cont==1 ){
                            System.out.println("Interrompendo " + atual.processo.nome+ " após " + cont + " instrução");
                        }else{
                            System.out.println("Interrompendo " + atual.processo.nome+ " após " + cont + " instruções");
                        }
                    }
                    interrompido++; //contador de interrompido é incrementado; 
                    numQuantum++;//incrementa o quantum 
                    instrucaoQuantum += cont;
                    if (!parou) TabelaProcessos.adicionaBlocoProntos(atual);
                }
		decrementaBloqueados();
		verificaZeroEspera();
		verificaZeroCreditosProntos();
            }
            double mediaTrocas = interrompido/10;
            double mediaInstrucao = instrucaoQuantum/numQuantum;
            System.out.println("media de trocas: " + mediaTrocas);
            System.out.println("media de instruções: " + mediaInstrucao);
            System.out.println("quantum: " +quantum);
	}
	
	private static void entradaSaida (BCP processo) {
            TabelaProcessos.adicionaBlocoBloqueados(processo);
            int index = bloqueados.indexOf(processo);
            bloqueados.get(index).espera = 2;
            bloqueados.get(index).flag = 1;
	}
	
	private static boolean verificaZeroCreditosProntos () {
            for (BCP p : prontos) {
                if (p.credito != 0) return false; 
            }
		
            for (BCP p : prontos) {
                p.credito = p.prioridade;
            }
            
            return true;
	}

	private static void decrementaBloqueados () {
            for (BCP b : bloqueados) {
                if (b.espera > 0) b.espera--;
            }
	}
	
	private static void verificaZeroEspera () {
            int cont = 0;
            for (BCP b : bloqueados) {
                if (b.espera == 0) {
                    TabelaProcessos.adicionaBlocoProntos(b);
                    cont++;
		}
		else break;
            }
		
            for (int i = 0; i < cont; i++) {
                TabelaProcessos.removePrimeiroBloqueados();
            }
	}
}