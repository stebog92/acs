package engine;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import inter.*;


public class PoolEngine implements Pool {

    private HashMap<Job, Observer> pool_jobs;
    private HashMap<Job, Observer> in_process;

    
    public PoolEngine () {
        super();
        pool_jobs = new HashMap<Job, Observer>();
        in_process = new HashMap<Job, Observer>();
    }

    public Job getJob() {
        Job job = null;
        Observer obs = null;
        for (Map.Entry<Job, Observer> x : pool_jobs.entrySet()) {
            if (!x.getKey().isSolved()) {
                job = x.getKey();
                obs = x.getValue();
                break;
            }
        }
        if (job != null) {
            pool_jobs.remove(job);
            in_process.put(job, obs);
        }
        if (job != null) {
            System.out.println("get job: " + job.toString());
        } else {
            //System.out.println("get job: null");
        }
        return job;
    }

    public void putJob(Job j, Observer o) {
        System.out.println("Job added " + j.toString());
        pool_jobs.put(j, o);
    }

    public void jobDone(Job j) {
        System.out.println("Job done: " + j.toString());
        //Observer o = in_process.get(j);
        Observer o;
        
        /*if (o == null) {
            System.out.println("Observer is null");
            for (Map.Entry<Job, Observer> i : in_process.entrySet()) {
                System.out.println(i.getKey().toString());
            }
        }*/
        try {
            String name = ""+ in_process.get(j).getJob().hashCode();
            System.out.println("looking for: " + name);
            o = (Observer)LocateRegistry.getRegistry().lookup(name);
            o.jobDone(j);
        } catch (Exception e) {
            System.err.println("PoolEngine exception:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Pool";
            Pool engine = new PoolEngine();
            Pool stub =
                (Pool) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("PoolEngine bound");
        } catch (Exception e) {
            System.err.println("PoolEngine exception:");
            e.printStackTrace();
        }
    }
}
