package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a1502824
 */
public class ThreadCliente implements Runnable {
    
    Socket cliente;
    Thread thread;
    ThreadServidor2 threadServidor2;
    int porta;
        
    public ThreadServidor2 getThreadServidor2() {
        return threadServidor2;
    }
    
    @Override
    public void run() {
        try {
            Conectar();
        } catch (IOException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Conectar() throws IOException {
        porta = Integer.parseInt(SocketCliente.tfPorta.getText());
        cliente = new Socket(SocketCliente.tfIp.getText(), porta);
        SocketCliente.textArea.append("Conectado ao servidor!");
        
        SocketCliente.tfIp.setEditable(false);
        SocketCliente.tfPorta.setEditable(false);
        SocketCliente.textArea.setEnabled(true);
        SocketCliente.textAreaEnviar.setEnabled(true);
        SocketCliente.btEnviar.setEnabled(true);
        SocketCliente.btEncerrar.setEnabled(true);
        
        threadServidor2 = new ThreadServidor2();
        thread = new Thread(threadServidor2);
        thread.start();
    }
    
    public void Desconectar() throws IOException {
        getThreadServidor2().Desconectar();
    }
    
    public void EnviaMsg() throws IOException {
        String novaMsg = SocketCliente.textAreaEnviar.getText();
        PrintStream saida = new PrintStream(cliente.getOutputStream());   
        saida.println(novaMsg);
        SocketCliente.textArea.append("\nVocê escreveu: " + novaMsg);
        SocketCliente.textAreaEnviar.setText("");
    }
}
