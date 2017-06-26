package SimulacaoIII;

import java.util.ArrayList;

public class FIFO extends Atendimento {

    private ArrayList <Cliente> fila;
    
    public FIFO(ArrayList<Cliente> clientes, Atendente atendente) {
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
            
            if(!fila.isEmpty()){
                atendido = fila.remove(0);
                
                atendido.setTempoEspera(super.getTempoSistema() - atendido.getTempoChegadaSistema());
            
                atendido.setTempoInicioAtendimento(super.getTempoSistema());
                super.setTempoSistema(atendente.atendimentoNormal(atendido, super.getTempoSistema()));
                atendido.setTempoSaidaAtendimento(super.getTempoSistema());
                mostraAtendimento(atendido);
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
    
    public void mostraCabecalhoTabela(){
    
        System.out.printf("\n###############################################################################################\n"
                        +   "#                                    TABELA ATENDIMENTO FIFO                                  #\n"
                        +   "###############################################################################################\n"
                        );
        super.mostraCabecalhoTabela();
    }
    
}
