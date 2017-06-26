package SimulacaoIII;

public class Atendente {

    private Cliente atendido;//cliente que estah sendo atendido
    private int quantum;//fatia dde tempo de atendimento
        
    public Atendente(int quantum) {
        this.quantum = quantum;
        atendido = null;
    } 
    
    /*
        faz o atendimento pelo tempo definido pelo quantum
        ou pelo tempo de atendimento atual do cliente caso
        este for menor que o quantum
    */
    public int atendimentoComQuantum(Cliente cliente, int tempoSis){
        
        atendido = cliente;
        
        if(atendido.getTempoAtendimentoAtual() < quantum){
            tempoSis = tempoSis + atendido.getTempoAtendimentoAtual();
            atendido.setTempoAtendimentoAtual(0);
             
        }
        else{
            tempoSis = tempoSis + quantum;
            atendido.atualizaTempoAtendimentoAtual(quantum);
        }
        return tempoSis;
    
    }
    
    /*
        faz o atendimento ate pelo tempo total de atendimento do cliente
    */
    public int atendimentoNormal(Cliente cliente, int tempoSis){
        
        atendido = cliente;
        tempoSis = tempoSis + atendido.getTempoAtendimentoAtual();
        atendido.setTempoAtendimentoAtual(0);
        return tempoSis;
    
    }
    
    /*
        faz o atendimento por 1 unidade de tempo
    */
    public int atendimentoPreemptivo(Cliente cliente, int tempoSis){
    
        atendido = cliente;
        tempoSis = tempoSis + 1;
        atendido.atualizaTempoAtendimentoAtual(1);
        return tempoSis;
    }
    
    public void setAtendido(Cliente cliente){
        atendido = cliente;
    }
    public Cliente getAtendido() {
        return atendido;
    }
    
}
