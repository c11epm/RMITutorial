package resources;

import com.sun.org.apache.regexp.internal.RE;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by emil on 2017-04-23.
 */
public interface PerfectCheck extends Remote {
    boolean isPerfect(int number) throws RemoteException;
}
