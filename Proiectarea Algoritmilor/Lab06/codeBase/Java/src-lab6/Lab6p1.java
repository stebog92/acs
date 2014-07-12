
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lab6p1 {

    static final int dirLin[] = {-1, 0, 1, 0};
    static final int dirCol[] = {0, 1, 0, -1};

    public static void find_exit(Maze maze, Coord source) {
        /*
         * Pentru a reconstitui drumul, vom folosi o matrice de parinti.
         */
        Coord parent[][] = new Coord[maze.get_height()][maze.get_width()];
        int visited[][] = new int[maze.get_height()][maze.get_width()];
        for (int i = 0; i < maze.get_height(); ++i) {
            for (int j = 0; j < maze.get_width(); ++j) {
                parent[i][j] = Coord.NOWHERE;
                visited[i][j] = 0;
            }
        }
        Coord curent = source;
        LinkedList<Coord> queue = new LinkedList<Coord>();
        queue.addFirst(source);
        visited[source.lin][source.col] = 1;
        while (!queue.isEmpty()) {
            curent = queue.removeLast();
            if (maze.is_exit_point(curent)) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                Coord next = new Coord(curent.lin + dirLin[i], curent.col + dirCol[i]);
                if (maze.is_walkable(next) && visited[next.lin][next.col] != 1) {
                    parent[next.lin][next.col] = curent;
                    queue.addFirst(next);
                    visited[next.lin][next.col] = 1;
                }
            }
        }
        while (parent[curent.lin][curent.col] != Coord.NOWHERE) {
            maze.mark_solution_step(curent);
            curent = parent[curent.lin][curent.col];
        }
        /*
         * TODO: Implementati o cautare care sa depisteze drumul cel mai scurt
         * de la pozitia "source" la o iesire din labiring (vezi functia
         * "is_exit_point").
         *
         * Dupa ce ati calculat drumul, inainte de iesirea din functie, trebuie
         * sa il marcati celula cu celula folosind functia "mark_solution_step"
         * din clasa Maze.
         */
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("Labirint.txt"));

        /*
         * read height, width
         */
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        scanner.nextLine();

        /*
         * allocate & read maze
         */
        Maze maze = new Maze(height, width);
        maze.read(scanner);

        /*
         * read robot location
         */
        int lineTrudy = scanner.nextInt();
        int columnTrudy = scanner.nextInt();
        System.out.println("trudy: " + lineTrudy + " " + columnTrudy);

        /*
         * solve
         */
        find_exit(maze, new Coord(lineTrudy, columnTrudy));

        /*
         * print path
         */
        maze.print();
    }
}
