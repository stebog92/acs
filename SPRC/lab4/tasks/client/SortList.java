package client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;
import inter.*;

public class SortList implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Integer> list;
    private boolean solved = true;
    private int begin, end;
    public SortList(ArrayList<Integer> list, int begin, int end) {
        this.list = new ArrayList<Integer>(list);
        this.solved = false;
        this.begin = begin;
        this.end = end;
    }

    public int hashCode() {
        return begin * 10 + end;
    }
    
    public boolean equals(Object other) {
        for (Integer x : ((SortList)other).getList()) {
            if (!list.contains(x)) {
                return false;
            }
        }
        return true;
    }
    public String toString() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i) + " ";
        }
        return s;
    }

    public boolean isSolved() {
        return this.solved;
    }

    public Job[] divide() {
        Job [] jobs;
        int middle;
        if (list.size() > 1) {
            middle = list.size()/2;
            jobs = new Job[2];
            jobs[0] = new SortList(new ArrayList<Integer>(list.subList(0, middle)), 0, middle);
            jobs[1] = new SortList(new ArrayList<Integer>(list.subList(middle, list.size())), middle, list.size());
        } else {
            jobs = new Job[1];
            jobs[0] = this;
        }
        return jobs;
    }

    public ArrayList<Integer> getList() {
        return this.list;
    }

    public void conquer(Job[] jobs) {
        ArrayList<Integer> l1 = ((SortList)jobs[0]).getList();
        ArrayList<Integer> l2 = ((SortList)jobs[1]).getList();
        list.clear();
        int i = 0, j = 0;
        while(i < l1.size() && j < l2.size()) {
            if (l1.get(i) < l2.get(j)) {
                list.add(l1.get(i));
                i++;
            } else {
                list.add(l2.get(j));
                j++;
            }
        }

        while(i < l1.size()) {
            list.add(l1.get(i));
            i++;
        }
        while(j < l2.size()) {
            list.add(l2.get(j));
            j++;
        }
        solved = true;
    }
}
