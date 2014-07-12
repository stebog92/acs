package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {

    public int getCompletionStatus() throws RemoteException;
    public void stop() throws RemoteException;
}
