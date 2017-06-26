/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulacaoIII;

import java.util.ArrayList;

/**
 *
 * @author imortal77
 */
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
        
        transicao = clientes.get(0);/*O primeiro é tratado diferente dos outros clientes.*/
        transicao.setTempoEspera(0);/*tempo de espera, do primeiro cliente, é 0, pois sendo o primeiro, o mesmo não precisou esperar*/
        transicao.setTempoInicioAtendimento(transicao.getTempoChegadaSistema());/*Assim que ele entra no sistema já é atendido, por isso o tempo de chegada e entrada são os mesmos*/
        super.incrementaOcioso(transicao.getTempoChegadaSistema() - super.getTempoSistema());
        super.setTempoSistema(transicao.getTempoChegadaSistema());
        super.setTempoSistema(atendente.atendimentoComQuantum(transicao, super.getTempoSistema()));
        
        int i = 1;
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
 