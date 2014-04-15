package ch.zhaw.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyBooringInterface extends Remote {
    void setTest(String test) throws RemoteException;
    String getTest() throws RemoteException;
}
