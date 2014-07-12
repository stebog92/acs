import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

enum Color {
  WHITE, GRAY, BLACK
}

class Node implements Comparable<Node> {
  public Color color;
  public int finishTime;
  public String subjectName;
  public int inDegree;
  public Vector<Node> neigh = new Vector<Node>();

  public Node(String subjectName) {
    this.subjectName = subjectName;
  }

  @Override
      public int compareTo(Node o) {
        return o.finishTime - this.finishTime;
      }
}

@SuppressWarnings("serial")
class Graph extends Vector<Node> {
}

public class Lab6p2 {

  static HashMap<String, Integer> subjectToInt = new HashMap<String, Integer>();

  static int time;

  static void dfs(Node node) {
    /* Daca nodul nu este alb, nu il expandam. */
    if (node.color != Color.WHITE) {
      return;
    }

    /* Facem nodul gri si incepem sa-l expandam. */
    node.color = Color.GRAY;
    time++;
    for (int i = 0; i < node.neigh.size(); ++i) {
      dfs(node.neigh.get(i));
    }

    /* Notam timpul la care s-a terminat expandarea nodului curent. */
    time++;
    node.finishTime = time;
  }

  static void topologicalSorting(Graph graph) {
    /* Facem toate nodurile albe. */
    for (int i = 0; i < graph.size(); ++i) {
      graph.get(i).color = Color.WHITE;
    }

    /* Pornim DFS din noduri. */
    time = 0;
    for (int i = 0; i < graph.size(); ++i) {
      if (graph.get(i).color == Color.WHITE) {
        dfs(graph.get(i));
      }
    }

    /*
     * Si acum vom sorta descrescator dupa timpul de finish pentru a obtine
     * o ordonare topologica.
     */
    Collections.sort(graph);
  }

  static void planYears(Graph graph, Vector<Vector<String>> yearlyPlanning) {
    Queue<Node> q = new LinkedList<Node>();

    /*
     * Intai facem o parcugere si punem intr-o coada toate nodurile care au
     * indegree-ul 0.
     */
    for (int i = 0; i < graph.size(); ++i) {
      if (graph.get(i).inDegree == 0) {
        q.add(graph.get(i));
      }
    }

    /*
     * Cat timp mai sunt in coada materii care pot fi facute, incearca sa le
     * planifici.
     */
    while (!q.isEmpty()) {
      /*
       * Vom planifica maxim 5 materii, in limita materiilor ce pot fi
       * facute in acest an (nu trebuie ca in acelasi semestru sa avem
       * materii care depind una de alta.
       */
      Vector<String> year = new Vector<String>();
      int yearSubjectCount = Math.min(q.size(), 5);

      /*
       * Le planificam efectiv, si daca cumva prin parcurgerea lor si alte
       * materii devin disponibile, atunci le adaugam si pe acelea in
       * coada pentru viitor.
       */
      for (int i = 0; i < yearSubjectCount; ++i) {
        Node node = q.peek();
        year.add(node.subjectName);
        /*
         * Scadem gradul intern al materiilor care depindeau de aceasta
         * materie cu o unitate.
         */
        for (int j = 0; j < node.neigh.size(); ++j) {
          node.neigh.get(j).inDegree--;
          /* Daca materia a ajuns independenta, o punem in coada. */
          if (node.neigh.get(j).inDegree == 0) {
            q.add(node.neigh.get(j));
          }
        }
        q.remove();
      }

      /* Si adaugam anul curent in planificarea anuala. */
      yearlyPlanning.add(year);
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    /* Declaram un graf. */
    Graph graph = new Graph();

    /* Citim un graf din fisierul de intrare. */
    Scanner scanner = new Scanner(new File("Materii.txt"));
    int edgeCount = scanner.nextInt();

    String subjectA, subjectB;
    for (int i = 0; i < edgeCount; ++i) {

      subjectA = scanner.next();
      scanner.next();
      subjectB = scanner.next();

      /*
       * Daca n-am mai vazut niciodata subjectA, atunci creaza-i un cod
       * unic.
       */
      if (subjectToInt.containsKey(subjectA) == false) {
        int code = subjectToInt.size();
        subjectToInt.put(subjectA, code);
        graph.add(new Node(subjectA));
      }

      /* La fel facem si cu subjectB. */
      if (subjectToInt.containsKey(subjectB) == false) {
        int code = subjectToInt.size();
        subjectToInt.put(subjectB, code);
        graph.add(new Node(subjectB));
      }

      /* Adaugam subjectB ca vecin al lui subjectA. */
      graph.get(subjectToInt.get(subjectA)).neigh.add(graph
                                                      .get(subjectToInt.get(subjectB)));
      graph.get(subjectToInt.get(subjectB)).inDegree++;
    }

    topologicalSorting(graph);

    System.out.println("O posibila sortare topologica este: ");
    for (int i = 0; i < graph.size(); ++i) {
      System.out.println("\t" + graph.get(i).subjectName);
    }

    /* Facem impartirea pe ani. */
    Vector<Vector<String>> yearlyPlanning = new Vector<Vector<String>>();
    planYears(graph, yearlyPlanning);

    System.out.println();
    System.out.println("Iar o impartire pe ani ar putea fi urmatoarea: ");
    for (int i = 0; i < yearlyPlanning.size(); ++i) {
      System.out.println("Anul " + (i + 1));
      Vector<String> subjects = yearlyPlanning.get(i);
      for (int j = 0; j < subjects.size(); ++j) {
        System.out.println("\t" + subjects.get(j));
      }
    }
  }
}
