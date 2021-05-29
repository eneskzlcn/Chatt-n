package ClientSide;

import Messages.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

// The client which will be connect to server and play chess.
public class Client {

    public Socket socket;
    public ObjectInputStream sInput;
    public ObjectOutputStream sOutput;
    public String userName;
    public boolean isPaired = false;
    public String serverIP;
    public int serverPort;
    public ClientListenThread listenThread;
    public ChattApp applicationFrame;
    
    public Client(ChattApp frame)
    {
        this.applicationFrame = frame;
    }
    public void Connect(String serverIP, int port) {
        try {
            System.out.println("Connecting to the server");
            this.serverIP = serverIP;
            this.serverPort = port;
            this.socket = new Socket(this.serverIP, this.serverPort);
            System.out.println("Connecting to the server");
            sOutput = new ObjectOutputStream(this.socket.getOutputStream());
            sInput = new ObjectInputStream(this.socket.getInputStream());
            listenThread = new ClientListenThread(this);
            this.listenThread.start();
        } catch (IOException ex) {
            System.out.println("Can not connected to the server.");
        }
    }

    public void Stop() {
        if (this.socket != null) {

            try {
                this.socket.close();
                this.sOutput.flush();
                this.sOutput.close();
                this.sInput.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void Send(Object message) {
        try {
            this.sOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
