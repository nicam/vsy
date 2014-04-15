package ch.zhaw.server;

import ch.zhaw.CommandInterpreter;
import ch.zhaw.client.Environment;
import ch.zhaw.client.MyBooringInterface;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class EchoRmiServer extends PortableRemoteObject implements CommandInterpreter {

    public EchoRmiServer() throws RemoteException {}

    public static void main(String[] args) throws Exception {
        EchoRmiServer server = new EchoRmiServer();
        Context context = ContextMagic.createContext(Environment.host, Environment.port);
        context.rebind("EchoRmiServer", server);
    }

    @Override
    public void interpret(MyBooringInterface obj) throws RemoteException {
        obj.setTest("Blubber");
    }
}
