package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

import encryption.*;

public interface IServer extends Remote {

    public Task getTask(String id) throws RemoteException;
    public void haveResult(Result r) throws RemoteException;
    public Dictionary getDictionary() throws RemoteException;
    public void test() throws RemoteException;
}
