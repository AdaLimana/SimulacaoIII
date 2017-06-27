package SimulacaoIII;


import java.util.ArrayList;
import java.util.Random;

public abstract class Atendimento {

    
    private ArrayList<Cliente> clientes;
    private int totalTEspera;
    private int totalTAtendimento;
    private int tempoSistema;
    private int ocioso;
    private Atendente atendente;
    
    /*Construtor padrao da classe
    TabAtendimentoCliente*/
    Atendimento(ArrayList<Cliente> clientes, Atendente atendente){
    
        tempoSistema = 0;
        totalTEspera = 0;
        totalTAtendimento = 0;
        ocioso = 0;
        this.clientes = clientes;
        
        this.atendente = atendente;
        
      
    }

    public int getTempoSistema() {
        return tempoSistema;
    }

    public void setTempoSistema(int tempoSistema) {
        this.tempoSistema = tempoSistema;
    }

    public int getOcioso() {
        return ocioso;
    }

    public void setOcioso(int ocioso) {
        this.ocioso = ocioso;
    }
    
    public void incrementaOcioso(int incremento){
    
        setOcioso(getOcioso() + incremento);
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
    
    
    
    /*Metodo para permitir acesso
    a lista de clientes*/
    public ArrayList<Cliente> getClientes(){return clientes;}
    
    /*Metodo Abstrato responsavel por
    setar os demais atributos restantes
    dos clientes, quem extender esta
    classe devera implementah-lo*/
    public abstract void atendimento();
    
    public void geraEstatistica(){
    
        totalTEspera = 0;
        totalTAtendimento = 0;
        int i = 0;
        Cliente c;
        while(i < clientes.size()){
        
            c = clientes.get(i);
            totalTEspera = totalTEspera + c.getTempoEspera();
            totalTAtendimento = totalTAtendimento + c.getTempoAtendimento();
            i++;
        
        }
    
    }
    
    /*Metodo responsavel por imprimir
    a tabela*/
    public void mostraCabecalhoTabela(){
        
        System.out.printf(  "#ID   = Numero identificador                                                                  #\n"
                        +   "#TCH  = Tempo de chegada ao sistema                                                           #\n"
                        +   "#TINA = Tempo de entrada em atendimento                                                       #\n"
                        +   "#TAT  = Tempo de atendimento total                                                            #\n"
                        +   "#TAA  = Tempo de atendimento atual (tempo que falta para ser totalmente atendido)             #\n"
                        +   "#TSA  = Tempo de saida Atendimento                                                            #\n"
                        +   "#TES  = Tempo de espera no sistema                                                            #\n"
                        +   "#P    = Prioridade                                                                            #\n"
                        +   "###############################################################################################");
    }
    
    public void mostraAtendimento(Cliente c){
        
        System.out.printf("\n# ID = %3d # TCH = %3d # TINA = %3d # TAT = %3d # TAA = %3d # TSA = %3d # TES = %3d # P = %3d #", c.getId(), c.getTempoChegadaSistema(),c.getTempoInicioAtendimento(), c.getTempoAtendimento(), c.getTempoAtendimentoAtual(), c.getTempoSaidaAtendimento(), c.getTempoEspera(), c.getPrioridade());
        
        
    }
    
    public void mostraEstatistica(){
        System.out.printf("\n###############################################################################################");
        System.out.printf("\nTempo Ocioso = %d\n"
                        + "Tempo total de espera em fila = %d\n"
                        + "Tempo medio de espera em fila = %f\n"
                        + "Tempo total de atendimento    = %d\n"
                        + "Tempo medio de atendimento    = %f\n\n",ocioso, totalTEspera, (float)totalTEspera / clientes.size(), totalTAtendimento, (float)totalTAtendimento / clientes.size() );
    }
    
    
}