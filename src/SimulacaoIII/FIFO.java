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
                
        //transicao = clientes.get(0);/*O primeiro é tratado diferente dos outros clientes.*/
        //transicao.setTempoEspera(0);/*tempo de espera, do primeiro cliente, é 0, pois sendo o primeiro, o mesmo não precisou esperar*/
        //transicao.setTempoInicioAtendimento(transicao.getTempoChegadaSistema());/*Assim que ele entra no sistema já é atendido, por isso o tempo de chegada e entrada são os mesmos*/
        //super.incrementaOscioso(transicao.getTempoChegadaSistema() - super.getTempoSistema());
        //super.setTempoSistema(transicao.getTempoChegadaSistema());//atualiza o tempo do sistema para o tempo em que chegou o clinete
        //super.setTempoSistema(atendente.atendimentoNormal(transicao, super.getTempoSistema()));
        //transicao.setTempoSaidaAtendimento(super.getTempoSistema());
        //mostraAtendimento(transicao);
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
