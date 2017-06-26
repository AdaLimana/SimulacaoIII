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
public class NaoPreemptivo extends Atendimento{

    private ArrayList<Cliente> fila;
    
    public NaoPreemptivo(ArrayList<Cliente> clientes, Atendente atendente) {
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
                atendido = maiorPrioridadeFila();
                if(atendido == null){
                    System.out.println("Erro no naoPreemptivo");
                    break;
                }
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
    
    private Cliente maiorPrioridadeFila(){
    
        Cliente maior; 
        int indiceMaior;
        Cliente aux;
        
        if(!fila.isEmpty()){
            maior = fila.get(0);
            indiceMaior = 0;
        }else{
            return null;
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
        
        return fila.remove(indiceMaior);
    }
    
     public void mostraCabecalhoTabela(){
    
        System.out.printf("\n###############################################################################################\n"
                        +   "#                               TABELA ATENDIMENTO NAO PREEMPTIVO                             #\n"
                        +   "###############################################################################################\n"
                        );
        super.mostraCabecalhoTabela();
    }
    
}
