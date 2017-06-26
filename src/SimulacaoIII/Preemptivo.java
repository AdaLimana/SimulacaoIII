package SimulacaoIII;

import java.util.ArrayList;

public class Preemptivo extends Atendimento {
    
    private ArrayList<Cliente> fila;
    
    public Preemptivo(ArrayList<Cliente> clientes, Atendente atendente) {
        super(clientes, atendente);
        
        atendente.setAtendido(null);
        fila = new ArrayList();
        mostraCabecalhoTabela();
        atendimento();
        geraEstatistica();
        mostraEstatistica();
    }

    public void atendimento() {
        ArrayList <Cliente> clientes = super.getClientes();
        Atendente atendente = super.getAtendente();
        
        
        Cliente atendido;
        Cliente transicao;
        int indiceMaiorPrioridade;
                
        int i = 0;
        
        while(true){
        
            if(i < clientes.size()){
                transicao = clientes.get(i);
                while(transicao.getTempoChegadaSistema() <= super.getTempoSistema()){//pega todos os clientes que chegaran ate  o tempo do sistema
              
                    fila.add(transicao);
                    i++;
                    if(i < clientes.size()){
                        transicao = clientes.get(i);
                    }
                    else{
                        break;
                    }
                }
            }
            
            atendido = atendente.getAtendido();//recebe que esta em atendimento
            
            if(atendido != null){//se tiver alguem em atendimento
                if(atendido.getTempoAtendimentoAtual() == 0){//se o tempo de atendimento terminou, tira o cliente do atendimmento
                
                    atendido.setTempoSaidaAtendimento(super.getTempoSistema());
                    mostraAtendimento(atendido);
                    atendente.setAtendido(null);
                    
                }
                else if(fila.isEmpty()){//se nao tiver ninguem esperando, continua atendendo o mesmo
                    super.setTempoSistema(atendente.atendimentoPreemptivo(atendido, super.getTempoSistema()));
                }
                else{//caso nao, verifica se nao tem alguem com prioridade maior na fila, se sim, tira o cliente de atendiento e o coloca na fila
                    indiceMaiorPrioridade = indiceMaiorPrioridadeFila();
                    if(indiceMaiorPrioridade < 0){
                        System.out.println("Erro no Preemptivo");
                        break;
                    }
                
                    transicao = fila.get(indiceMaiorPrioridade);
                    if(atendido.getPrioridade() < transicao.getPrioridade()){//se a prioridade do que esta sendo atendido for menor que a de um cliente da fila
                        atendido.setTempoSaidaAtendimento(super.getTempoSistema());
                        mostraAtendimento(atendido);
                        fila.add(atendido);
                        atendente.setAtendido(null);                    
                    }
                    else{//senao continua atendendo o mesmo
                        super.setTempoSistema(atendente.atendimentoPreemptivo(atendido, super.getTempoSistema()));
                    }
                }    
            }
            else if(!fila.isEmpty()){
                indiceMaiorPrioridade = indiceMaiorPrioridadeFila();
                if(indiceMaiorPrioridade < 0){
                    System.out.println("Erro no Preemptivo");
                    break;
                }
                transicao = fila.remove(indiceMaiorPrioridade);
                
                if(transicao.getTempoSaidaAtendimento() > 0){//se jah foi atendido uma vez acumula o tempo de espera
                    transicao.setTempoEspera(transicao.getTempoEspera() + super.getTempoSistema() - transicao.getTempoSaidaAtendimento());
                }
                else{//caso ainda nao foi atendido
                    transicao.setTempoEspera(super.getTempoSistema() - transicao.getTempoChegadaSistema());
                }
                        
                transicao.setTempoInicioAtendimento(super.getTempoSistema());
                super.setTempoSistema(atendente.atendimentoPreemptivo(transicao, super.getTempoSistema()));
            }
            else if(i < clientes.size()){//se nao tiver ninguem na fila avanaca o tempoSistema para o tempo de chegado do proximo cliente que entrara no sistema
                                         // a diferenca entre o tempoSistema e o tempoChegada do proximo cliente eh somada ao tempo de osciosidade    
                transicao = clientes.get(i);
                super.incrementaOcioso(transicao.getTempoChegadaSistema() - super.getTempoSistema());
                super.setTempoSistema(transicao.getTempoChegadaSistema());
                fila.add(transicao);
                i++;
            }
            else{
                break;
            }
        
        }
    }
    
    private int indiceMaiorPrioridadeFila(){
    
        Cliente maior; 
        int indiceMaior;
        Cliente aux;
        
        if(!fila.isEmpty()){
            maior = fila.get(0);
            indiceMaior = 0;
        }else{
            return -1;
        }
       
        int i = 1;
        
        while(i < fila.size()){
            aux = fila.get(i);
            
            if(maior.getPrioridade() < aux.getPrioridade() ){
            
                maior = aux;
                indiceMaior = i;
            }
            i++;
            
        }
        
        return indiceMaior;
    }
    
     public void mostraCabecalhoTabela(){
    
        System.out.printf("\n###############################################################################################\n"
                        +   "#                               TABELA ATENDIMENTO PREEMPTIVO                                 #\n"
                        +   "###############################################################################################\n"
                        );
        super.mostraCabecalhoTabela();
    }
}
