package src.resourses;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by c11epm on 9/12/14.
 */
public interface BindItem extends Remote {
    boolean bindToMe(String remoteName, AliveCheck toBind) throws RemoteException;
}
