package inter;

public interface Job {
    public Job[] divide();
    public void conquer(Job[] jobs);
    public boolean isSolved();
    public String toString();
    public boolean equals(Object other);
    public int hashCode();
}
