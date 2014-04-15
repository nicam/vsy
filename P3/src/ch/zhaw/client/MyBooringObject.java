package ch.zhaw.client;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class MyBooringObject extends PortableRemoteObject implements MyBooringInterface {
    private String test;

    protected MyBooringObject() throws RemoteException {
    }

    public void setTest(String test) throws RemoteException {
        this.test = test;
    }

    public String getTest() throws RemoteException {
        return this.test;
    }

}
