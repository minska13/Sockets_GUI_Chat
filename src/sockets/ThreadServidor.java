package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a1502824
 */
public class ThreadServidor implements Runnable {
    
    ServerSocket servidor;
    Socket cliente;
    int porta;
    ThreadCliente2 threadCliente;
    Thread thread;
    static String clienteIp;
    
    public ThreadCliente2 getThreadCliente(){
        return threadCliente;
    }
    
    @Override
    public void run() {
        try {
            Conectar();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void Conectar() throws IOException {
        SocketServidor.tfPorta.setEditable(false);
        SocketServidor.btOk.setEnabled(false);
        SocketServidor.textArea.setEnabled(true);
        SocketServidor.textAreaEnviar.setEnabled(true);
        SocketServidor.btEnviar.setEnabled(true);
        SocketServidor.btEncerrar.setEnabled(true);
        
        porta = (Integer.parseInt(SocketServidor.tfPorta.getText()));
        servidor = new ServerSocket(porta);
        SocketServidor.textArea.append("Porta " + porta + " aberta!");
        cliente = servidor.accept();
        clienteIp = cliente.getInetAddress().getHostAddress();
        SocketServidor.textArea.append("\nConexão estabelecida com o cliente " + clienteIp);
        
        threadCliente = new ThreadCliente2();
        thread = new Thread(threadCliente);
        thread.start();
        
        RecebeMsg();
    }
    
    public void Desconectar() throws IOException {
        servidor.close();
        cliente.close();
    }
    
    public void RecebeMsg() throws IOException {
        Scanner entrada = new Scanner(cliente.getInputStream());
        while (entrada.hasNextLine()) {
            SocketServidor.textArea.append("\n"+cliente.getInetAddress().getHostAddress()+": "+ entrada.nextLine());
        }
    }
    
    public void EnviaMsg() throws IOException {
        getThreadCliente().EnviaMsg();
    }
}
