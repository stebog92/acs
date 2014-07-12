package inter;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pool extends Remote {
    public Job getJob() throws RemoteException;
    public void putJob(Job j, Observer o) throws RemoteException;
    public void jobDone(Job j) throws RemoteException;
}
