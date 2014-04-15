package ch.zhaw.server;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class ContextMagic {

    public static Context createContext(String host, int port) {
        Context context = null;
        Hashtable details = new Hashtable();

        details.put(InitialContext.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
        details.put(InitialContext.PROVIDER_URL, "iiop://" + host + ":" + port);

        try{
            context = new InitialContext(details);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return context;
    }
}
