package worker;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;
import inter.*;

public class Worker {
    static class IObserver implements Observer, Serializable {

        private static final long serialVersionUID = 3L;
        private Job j;
        private int size;
        private ArrayList<Job> jobs;
        public IObserver(Job j, int size) {
            super();
            this.j = j;
            jobs = new ArrayList<Job>();
            this.size = size;
        }
        public void jobDone(Job j) {
            System.out.println("job done");
            jobs.add(j);
        };

        public ArrayList<Job> getJobs() {
            return jobs;
        }

        public Job getJob() {
            return this.j;
        }

        public boolean complete() {
            return jobs.size() == size;
        }
    }
    static public void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Pool";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Pool comp = (Pool) registry.lookup(name);
            ArrayList<IObserver> obs = new ArrayList<IObserver>();
            while (true) {
                Job [] jobs; 
                Job j = comp.getJob();
                if (j != null) {
                    jobs = j.divide();

                    if (jobs.length != 1) {
                        Observer o = new IObserver(j, jobs.length);
                        obs.add((IObserver)o);
                        Observer stub =
                            (Observer) UnicastRemoteObject.exportObject(o, 0);
                        registry.rebind("" + j.hashCode(), stub);
                        System.out.println("binded observer: " + j.hashCode());
                        for (int i = 0; i < jobs.length; i++) {
                            comp.putJob(jobs[i],  o);
                        }
                    } else {
                        comp.jobDone(j);
                    }
                } else {
                    Job j1 = null;
                    ArrayList<Job> js = null;
                    IObserver o1 = null;
                    for (IObserver o : obs) {
                        if (o.complete()) {
                            System.out.println("here");
                            j1 = o.getJob();
                            js = o.getJobs();
                            o1 = o;
                            break;
                        }
                    }
                    if (j1 != null) {
                        obs.remove(o1);
                        j1.conquer(Arrays.copyOf(js.toArray(), js.size(), Job[].class));
                        comp.jobDone(j1);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Pool exception:");
            e.printStackTrace();
        }
    }
}
