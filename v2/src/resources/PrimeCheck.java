package resources;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by emil on 2017-04-23.
 */
public interface PrimeCheck extends Remote {
    boolean isPrime(int number) throws RemoteException;
}
