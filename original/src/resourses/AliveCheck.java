package src.resourses;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by c11epm on 9/12/14.
 */
public interface AliveCheck extends Remote {
    public boolean isAlive() throws RemoteException;
}
