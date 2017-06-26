/*
    Classe que determina (cliente), com todos atributos
    necessahrios para o mesmo
*/

package SimulacaoIII;

public class Cliente {
    
    private static int quantidadeCliente = 0;
    private int id;
    private int tempoCricao;
    private int tempoChegadaSistema;
    private int tempoAtendimento;
    private int tempoAtendimentoAtual;
    private int prioridade;
    private int tempoInicioAtendimento;
    private int tempoSaidaAtendimento;
    private int tempoEspera;
    
    /*#############################CONSTRUTOR##################################*/
    public Cliente() {
        id = quantidadeCliente;
        quantidadeCliente++;
    }

    /*########################################################################*/

    
    /*######################METODOS SETS E GETS###############################*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempoCricao() {
        return tempoCricao;
    }

    public void setTempoCricao(int tempoCricao) {
        this.tempoCricao = tempoCricao;
    }

    public int getTempoChegadaSistema() {
        return tempoChegadaSistema;
    }

    public void setTempoChegadaSistema(int tempoChegadaSistema) {
        this.tempoChegadaSistema = tempoChegadaSistema;
    }

    public int getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(int tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getTempoInicioAtendimento() {
        return tempoInicioAtendimento;
    }

    public void setTempoInicioAtendimento(int tempoInicioAtendimento) {
        this.tempoInicioAtendimento = tempoInicioAtendimento;
    }

    public int getTempoSaidaAtendimento() {
        return tempoSaidaAtendimento;
    }

    public void setTempoSaidaAtendimento(int tempoSaidaAtendimento) {
        this.tempoSaidaAtendimento = tempoSaidaAtendimento;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public int getTempoAtendimentoAtual() {
        return tempoAtendimentoAtual;
    }

    public void setTempoAtendimentoAtual(int tempoAtendimentoAtual) {
        this.tempoAtendimentoAtual = tempoAtendimentoAtual;
    }
    /*########################################################################*/
    
    public String toString(){
    
        return String.format("ID= %3d | TC= %3d | TCS= %3d | TA= %3d | TAA= %3d| P= %3d", id, tempoCricao, tempoChegadaSistema, tempoAtendimento, tempoAtendimentoAtual, prioridade);
    }
    
    public void reiniciaTempoAtendimentoAtual(){
    
            tempoAtendimentoAtual = tempoAtendimento;
    }
    
    public void atualizaTempoAtendimentoAtual(int valor){
    
        tempoAtendimentoAtual = tempoAtendimentoAtual - valor;
    } 
    
}
