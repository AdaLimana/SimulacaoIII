package SimulacaoIII;

import java.util.ArrayList;
import java.util.Random;
import Porcentagem.Porcentagem;
        
public class ListaCliente {
    
    private ArrayList<Cliente> clientes;
    private int quantidade;
    private int tempoAtendimentoInicial;/*sortea um numero para tempo de atenimento apartir desse valor*/
    private int tempoAtendimentoFinal;/*sortea um numero para tempo de atenimento ateh esse valor*/
    private int incremento;
    private int iniciaEmZero;
    /*#############################CONSTRUTOR##################################*/

    public ListaCliente(int quantidade, int tempoAtendimentoInicial, int tempoAtendimentoFinal, int incremento, int iniciaEmZero) {
        clientes = new ArrayList();
        this.quantidade = quantidade;
        this.tempoAtendimentoInicial = tempoAtendimentoInicial;
        this.tempoAtendimentoFinal = tempoAtendimentoFinal;
        this.incremento = incremento;
        this.iniciaEmZero = iniciaEmZero;
        geraClientes();
    }
    /*########################################################################*/
    
    
    /*########################METODOS SETS E GETS#############################*/
    
    public ArrayList<Cliente> getClientes() {
        
        return clientes;
        
    }
    /*########################################################################*/
    
    
    /*
    Cria n(quantidade) clientes
     */
    private void geraClientes() {
        Cliente cliente;
        int i = 0;
        
        if(iniciaEmZero > 0){//se o primeiro cliente inicia em zero
            cliente = new Cliente();
            inicializaCliente(cliente, 0);
            clientes.add(cliente);
            i++;
        }
        while ( i < quantidade ){
            cliente = new Cliente();
            inicializaCliente(cliente, incremento);
            clientes.add(cliente);
            i++;
        }
        geraPrioridade();
    }
    
    
    private void inicializaCliente(Cliente cliente, int incremento){
        
        geraTempoCriacao(cliente, incremento);
        geraTempoChegadaSistema(cliente);
        geraTempoAtendimento(cliente);
        cliente.reiniciaTempoAtendimentoAtual();
    }
    
    public void reiniciaClientes(){//reinicia tempoAtendimentoAtual tempoInicioAtendimento tempoSaidaAtendimento tempoEspera dos clientes;
        Cliente cliente; 
        for(int i = 0; i < clientes.size(); i++){
            cliente = clientes.get(i);
            cliente.reiniciaTempoAtendimentoAtual();
            cliente.setTempoEspera(0);
            cliente.setTempoInicioAtendimento(0);
            cliente.setTempoSaidaAtendimento(0);
        }
    }
    
    private void geraTempoCriacao(Cliente cliente, int incremento){
    
        cliente.setTempoCricao(incremento);/*o tempo de criacao de todos serah 5*/
    }
    
    private void geraTempoChegadaSistema(Cliente cliente){
    
        Cliente clienteAnterior;
        
        if(!clientes.isEmpty()){/*se jah tiver cliente criado*/
        
            clienteAnterior = clientes.get(clientes.size()-1);/*passa a referencia do ultimo cliente add a lista*/
            
            cliente.setTempoChegadaSistema(clienteAnterior.getTempoChegadaSistema()+cliente.getTempoCricao());
        
        }
        else{
            cliente.setTempoChegadaSistema(cliente.getTempoCricao());
        }
    }
    
    private void geraTempoAtendimento(Cliente cliente){
    
        Random aleatorio;
        
        aleatorio = new Random();
        
        cliente.setTempoAtendimento(tempoAtendimentoInicial+aleatorio.nextInt((tempoAtendimentoFinal-tempoAtendimentoInicial+1)));
    }
    
    private void geraPrioridade(){
    
        int prioridade[];/*Armazena a quantidade de vezes que a prioridade pode*/
        prioridade = new int[4];/*ser sorteada para um cliente*/
        int somatorio = 0;
        
        int porcentagem[] = {10,20,30,40};/*prioridade (1) = 10%, (2) = 20%, (3) = 30%, (4) = 40%*/
        
        Random aleatorio = new Random();
        int valorSorteado;
        
        Cliente cliente;
                
        for(int i = 0; i < prioridade.length; i++ ){/*determina a quantideda de vezes que cada prioridade pode ser sorteada*/
        
            prioridade[i] = Porcentagem.calculoArrendondado(porcentagem[i], clientes.size());
            somatorio+=prioridade[i];
        }
        
        while(somatorio > quantidade){/*se o somatorio da quantidade de prioridade for maior que a quandidade de clientes subtrai 1*/
            
            float proximidade[] = new float[4];
            int indiceMaior = 0;
            
            for(int i = 0; i < prioridade.length; i++){
            
                proximidade[i] = prioridade[i] - Porcentagem.calculo(porcentagem[i], clientes.size());
                
            }
            
            for(int i = 1; i < prioridade.length; i++){//determina o que tem a menor proximidade
                
                if(proximidade[indiceMaior] < proximidade[i]){
                       
                    indiceMaior = i;
                }
            }
            prioridade[indiceMaior]--;
            somatorio--;
       }
       
       
       while(somatorio < quantidade){/*se o somatorio da quantidade de prioridade for menor que a quandidade de clientes soma 1*/
            
            float proximidade[] = new float[4];
            int indiceMenor = 0;
            
            for(int i = 0; i < prioridade.length; i++){
            
                proximidade[i] = prioridade[i] - Porcentagem.calculo(porcentagem[i], clientes.size());
                
            }
            
            for(int i = 1; i < prioridade.length; i++){//determina o que tem a menor proximidade
                
                if(proximidade[indiceMenor] > proximidade[i]){
                       
                    indiceMenor = i;
                }
            }
            prioridade[indiceMenor]++;
            somatorio++;
       } 
        
       for(int i = 0; i < clientes.size(); i++){
            
            cliente = clientes.get(i);
            
            valorSorteado = 1 + aleatorio.nextInt(4);
            if(prioridade[(valorSorteado-1)] > 0){/*verifica se a prioridade ainda pode ser sorteada*/
                cliente.setPrioridade(valorSorteado);
                prioridade[(valorSorteado-1)]--;
            }
            else{/*caso contrario sortea novamente*/
                i--;
            }
            
        }
    
    }
     
}
