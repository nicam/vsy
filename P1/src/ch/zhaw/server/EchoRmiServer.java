package ch.zhaw.server;

import ch.zhaw.CommandInterpreter;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Created by adrianbertschi on 18.02.14.
 */
public class EchoRmiServer extends UnicastRemoteObject implements CommandInterpreter {

    public EchoRmiServer() throws RemoteException {}

    public static void main(String[] args) throws Exception {
        EchoRmiServer server = new EchoRmiServer();
        Naming.rebind("VSYEchoRmiServer", server);
    }

    @Override
    public String interpret(String command) throws RemoteException {
        System.out.println(Thread.currentThread().getId() + " : interpret cmd: '" + command + "'");
        if(command.toLowerCase().equals("endless")){
            while(true){}
        } else if(command.toLowerCase().equals("exception")){
            int i = 3/0;
        } else if(command.toLowerCase().equals("threading")){
            return "" + Thread.currentThread().getId();
        }
        return Thread.currentThread().getId() + " : " + new Date() + ": echo: " + command;
    }
}
