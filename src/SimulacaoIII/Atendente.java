package SimulacaoIII;

public class Atendente {

    private Cliente atendido;//cliente que estah sendo atendido
    //private char estado;//estado do atendente (O) ocupado ou (L) livre
    private int quantum;//fatia dde tempo de atendimento
    //private int tempoOcupacao;
    
    public Atendente(int quantum) {
        //estado = 'L';
        this.quantum = quantum;
        //tempoOcupacao = 0;
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
            //tempoOcupacao = tempoOcupacao + atendido.getTempoAtendimentoAtual();
            atendido.setTempoAtendimentoAtual(0);
             
        }
        else{
            tempoSis = tempoSis + quantum;
            //tempoOcupacao = tempoOcupacao + quantum;
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

    /*public char getEstado() {
        return estado;
    }*/

    //public int getTempoOcupacao() {
        //return tempoOcupacao;
    //}
    
    
    
    
}
