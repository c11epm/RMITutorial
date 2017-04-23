package src.Server;

import src.resourses.AliveCheck;
import src.resourses.BindItem;
import src.resourses.Hello;
import src.resourses.PropertyReader;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by c11epm on 9/10/14.
 */
public class newServer implements Hello, AliveCheck {

    @Override
    public String sayHello() throws RemoteException {
        System.err.println("HELLO MISTER");
        return "I'm saying hello from another server";
    }

    public newServer(){

    }

    public static void main(String args[]) {
        int defaultPort = 1099;
        String host = "";
        try {
            defaultPort = Integer.parseInt(PropertyReader.getInstance().readRegistryProperties("registry.properties", "port"));
            host = PropertyReader.getInstance().readRegistryProperties("registry.properties", "host");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        //    Registry ownReg = LocateRegistry.createRegistry(2000);
            newServer obj = new newServer();
            AliveCheck stub = (AliveCheck) UnicastRemoteObject.exportObject(obj, 0);
        //    ownReg.rebind("NewHello", stub);

            Registry registry = LocateRegistry.getRegistry(host, defaultPort);
            String nameToBind = PropertyReader.getInstance().readMessageProperties("message.properties", "hello");
            System.err.println(nameToBind);
            BindItem server = (BindItem) registry.lookup(nameToBind);
            boolean asd = server.bindToMe("NewHello", stub);

            System.err.println("Server ready on port: "+ defaultPort);
        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }
    }

    @Override
    public boolean isAlive() throws RemoteException {
        return true;
    }
}
