/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minska
 */
public class ThreadCliente2 implements Runnable {
    
    Socket cliente2;
    int porta;
    
    @Override
    public void run() {
        try {
            Conectar();
        } catch (IOException ex) {
            Logger.getLogger(ThreadCliente2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Conectar() throws IOException {
        porta = (Integer.parseInt(SocketServidor.tfPorta.getText()));
        cliente2 = new Socket(SocketServidor.clienteIP, (porta+1));
        SocketServidor.textArea.append("\nConectado ao servidor!");
        SocketServidor.textArea.append("\n************************************************************");
    }
    
    public void EnviaMsg() throws IOException {
        String novaMsg = SocketServidor.textAreaEnviar.getText();
        PrintStream saida = new PrintStream(cliente2.getOutputStream());   
        saida.println(novaMsg);
        SocketServidor.textArea.append("\nVocê escreveu: " + novaMsg);
        SocketServidor.textAreaEnviar.setText("");
    }
}
