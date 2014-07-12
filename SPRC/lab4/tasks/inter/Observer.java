package inter;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
    public void jobDone(Job j) throws RemoteException;
    public Job getJob() throws RemoteException;

}
