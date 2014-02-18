import ch.zhaw.CommandInterpreter;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by adrianbertschi on 18.02.14.
 */
public class EchoRmiServer extends UnicastRemoteObject implements CommandInterpreter {

    protected EchoRmiServer() throws RemoteException {}

    public static void main(String[] args) throws Exception {
        EchoRmiServer server = new EchoRmiServer();
        Naming.bind("VSYEchoRmiServer", server);
    }

    @Override
    public String interpret(String command) throws RemoteException {
        return "Echo: " + command;
    }
}
