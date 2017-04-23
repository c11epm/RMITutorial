package resources;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by emil on 2017-04-23.
 */
public interface DollarToEuro extends Remote {
    double dollarToEuro(double dollar) throws RemoteException;
}
