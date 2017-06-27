package SimulacaoIII;

import java.util.ArrayList;

public class RoundRobin extends Atendimento {
    
    private ArrayList<Cliente> fila;
    
    public RoundRobin(ArrayList<Cliente> clientes, Atendente atendente) {
        
        super(clientes, atendente);
        
        fila = new ArrayList();
                
        atendente.setAtendido(null);
        mostraCabecalhoTabela();
        atendimento();
        geraEstatistica();
        mostraEstatistica();
        
    }
    
    public void atendimento() {
        
        ArrayList <Cliente> clientes = super.getClientes();
        Atendente atendente = super.getAtendente();
        
        Cliente transicao;
        Cliente atendido;
        
        int i = 0;
        while(true){// i < clientes.size() || !fila.isEmpty()
           
            if(i < clientes.size()){
                transicao = clientes.get(i);
                while(transicao.getTempoChegadaSistema() < super.getTempoSistema()){//pega todos os clientes que chegaran ate  o tempo do sistema
              
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
            
            atendido = atendente.getAtendido();
            
            if(atendido != null){
                if(atendido.getTempoAtendimentoAtual() == 0){//se o atendido terminou todo se tempo de atendimento ele sai do sistema
                
                }
                else{//caso contrahrio ele retorna a fila;
                    fila.add(atendido);
                }
                
                atendido.setTempoSaidaAtendimento(super.getTempoSistema());
                mostraAtendimento(atendido);
                atendente.setAtendido(null);
            }    
            
            
            if(!fila.isEmpty()){
                transicao = fila.remove(0);
                
                if(transicao.getTempoSaidaAtendimento() > 0){//se jah foi atendido uma vez acumula o tempo de espera
                    transicao.setTempoEspera(transicao.getTempoEspera() + super.getTempoSistema() - transicao.getTempoSaidaAtendimento());
                }
                else{//caso ainda nao foi atendido
                    transicao.setTempoEspera(super.getTempoSistema() - transicao.getTempoChegadaSistema());
                }
                transicao.setTempoInicioAtendimento(super.getTempoSistema());
                super.setTempoSistema(atendente.atendimentoComQuantum(transicao, super.getTempoSistema()));
            }
            else if(i < clientes.size()){
            
                transicao = clientes.get(i);
                super.incrementaOcioso(transicao.getTempoChegadaSistema() - super.getTempoSistema());
                super.setTempoSistema(transicao.getTempoChegadaSistema());
                fila.add(transicao);
                i++;
            }
            else if(atendido == null){
                break;
            }
                               
        }
    }
    
    public void mostraCabecalhoTabela(){
    
        System.out.printf("\n###############################################################################################\n"
                        +   "#                                TABELA ATENDIMENTO ROUND ROBIN                               #\n"
                        +   "###############################################################################################\n"
                        );
        super.mostraCabecalhoTabela();
    }
    
}
 