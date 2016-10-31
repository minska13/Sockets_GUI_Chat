/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minska
 */
public class ThreadServidor2 implements Runnable {
    
    ServerSocket servidor2;
    Socket cliente2;
    int porta;

    @Override
    public void run() {
        try {
            Conectar();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Conectar() throws IOException {
        porta = (Integer.parseInt(SocketCliente.tfPorta.getText()));
        servidor2 = new ServerSocket(porta + 1);
        SocketCliente.textArea.append("\nPorta " + (porta + 1) + " aberta!");
        cliente2 = servidor2.accept();
        SocketCliente.textArea.append("\nConexão estabelecida com o cliente " + cliente2.getInetAddress().getHostAddress());
        SocketCliente.textArea.append("\n************************************************************");

        RecebeMsg();
    }
    
    public void Desconectar() throws IOException {
        cliente2.close();
        servidor2.close();
    }
    
    public void RecebeMsg() throws IOException {
        Scanner entrada = new Scanner(cliente2.getInputStream());
        while (entrada.hasNextLine()) {
            SocketCliente.textArea.append("\n"+cliente2.getInetAddress().getHostAddress()+": "+ entrada.nextLine());
        }
    }
}
