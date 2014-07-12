import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab6p1 {

	static final int dirLin[] = { -1, 0, 1, 0 };
	static final int dirCol[] = { 0, 1, 0, -1 };

	public static void find_exit(Maze maze, Coord source) {
		/* Pentru a reconstitui drumul, vom folosi o matrice de parinti. */
		Coord parent[][] = new Coord[maze.get_height()][maze.get_width()];

		for (int i = 0; i < maze.get_height(); ++i) {
			for (int j = 0; j < maze.get_width(); ++j) {
				parent[i][j] = Coord.NOWHERE;
			}
		}

		/*
		 * Vom folosi BFS pentru a determina drumul optim. Cream o coada pe care
		 * o intializam cu prima celula.
		 */
		Queue<Coord> q = new LinkedList<Coord>();

		q.add(source);
		parent[source.lin][source.col] = source;

		while (q.size() > 0) {
			Coord pos = q.remove();

			for (int dir = 0; dir < 4; ++dir) {
				Coord newPos = new Coord(pos.lin + dirLin[dir], pos.col
						+ dirCol[dir]);
				/*
				 * Verificam ca nou pozitie pe care o incercam este o pozitie
				 * valida de pe harta in care se poate muta.
				 */
				if (maze.is_walkable(newPos)) {
					if (maze.is_exit_point(newPos)) {
						parent[newPos.lin][newPos.col] = pos;
						/* Tiparim solutia si iesim. */
						do {
							maze.mark_solution_step(newPos);
							newPos = parent[newPos.lin][newPos.col];
						} while (newPos != source);
						maze.mark_solution_step(source);
						return;
					} else if (parent[newPos.lin][newPos.col] == Coord.NOWHERE) {
						/*
						 * Nu adaugam noua pozitie in coada decat daca nu se
						 * afla deja.
						 */
						parent[newPos.lin][newPos.col] = pos;
						q.add(newPos);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("Labirint.txt"));

		/* read height, width */
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		scanner.nextLine();

		/* allocate & read maze */
		Maze maze = new Maze(height, width);
		maze.read(scanner);

		/* read robot location */
		int lineTrudy = scanner.nextInt();
		int columnTrudy = scanner.nextInt();
		System.out.println("trudy: " + lineTrudy + " " + columnTrudy);

		/* solve */
		find_exit(maze, new Coord(lineTrudy, columnTrudy));
		
		/* print path */
		maze.print();
	}
}
