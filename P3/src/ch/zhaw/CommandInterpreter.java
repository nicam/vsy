package ch.zhaw;

import ch.zhaw.client.MyBooringInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommandInterpreter extends Remote {
    public void interpret(MyBooringInterface command) throws RemoteException;
}