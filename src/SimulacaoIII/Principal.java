package SimulacaoIII;

import java.util.Scanner;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        int qntCliente;
        int tempoMinimoAtendimento;
        int tempoMaximoAtendimento;
        int quantum;
        int incremento;
        int iniciaEmZero;
        
        Atendente atendente;
        
        ListaCliente listaClientes;
        
        RoundRobin rr;
        FIFO fifo;
        LIFO lifo;
        NaoPreemptivo nPreem;
        Preemptivo preem;
        
        System.out.println("Informe a quantidade de clientes");
        qntCliente = input.nextInt();
    
        System.out.println("Informe o valor de incremento (o intervalo de tempo de criacao entre os clientes)");
        incremento = input.nextInt();
        
        System.out.println("O tempo de chegada do primeiro cliente deve ser ZERO? sim(1) nao(0)");
        iniciaEmZero = input.nextInt();
        
        System.out.println("Informe o tempo mihnimo de atendimento");
        tempoMinimoAtendimento = input.nextInt();
    
        System.out.println("Informe o tempo mahximo de atendimento");
        tempoMaximoAtendimento = input.nextInt();
        
        listaClientes = new ListaCliente(qntCliente, tempoMinimoAtendimento, tempoMaximoAtendimento, incremento, iniciaEmZero);
        
        ArrayList<Cliente> clientes = listaClientes.getClientes();
        
        for(Cliente cliente : clientes){
        
            System.out.println(cliente);
        }
        
        System.out.println("Informe o quantum");
        quantum = input.nextInt();

        atendente = new Atendente(quantum);
        
        fifo = new FIFO(listaClientes.getClientes(), atendente);
        
        listaClientes.reiniciaClientes();
        
        lifo = new LIFO(listaClientes.getClientes(), atendente);
        
        listaClientes.reiniciaClientes();
        
        nPreem = new NaoPreemptivo(listaClientes.getClientes(), atendente);
        
        listaClientes.reiniciaClientes();
        
        preem = new Preemptivo(listaClientes.getClientes(), atendente);
        
        listaClientes.reiniciaClientes();
        
        rr = new RoundRobin(listaClientes.getClientes(), atendente);
        
        listaClientes.reiniciaClientes();
        
        
    }

}
