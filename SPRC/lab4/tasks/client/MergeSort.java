package client;

import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import inter.*;


import java.util.HashMap;

public class MergeSort {
    static class IObserver implements Observer, Serializable {

        private static final long serialVersionUID = 3L;
        private Job j;
        public IObserver(Job j) {
            super();
            this.j = j;
        }

        public Job getJob() {
            return this.j;
        }
        public void jobDone(Job j) {
            System.out.println("Sorted array is: " + ((SortList)j).getList().toString());
        }
    }
    public static void main(String args[]) {
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(3,2,4,5,6,7,1));
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Pool";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Pool comp = (Pool) registry.lookup(name);
            SortList job = new SortList(list, 0, list.size());
            Observer obs = new IObserver(job);
            Observer stub =
                            (Observer) UnicastRemoteObject.exportObject(obs, 0);
            //registry.rebind("" + job.hashCode(), stub);
            comp.putJob(job, obs);
        } catch (Exception e) {
            System.err.println("PoolPi exception:");
            e.printStackTrace();
        }
    }
}
