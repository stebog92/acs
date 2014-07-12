#ifndef __MAZE_H__
#define __MAZE_H__

#pragma once

#include <iostream>
#include <fstream>

typedef std::pair<int, int> Coord;

class Maze {
 private:
  unsigned int height, width;
  char** cell;

  void allocate() {
    cell = new char*[height];
    for (unsigned int i = 0; i < height; i++) {
      cell[i] = new char[width];
      for (unsigned int j = 0; j < width; j++) {
        cell[i][j] = 1;
      }
    }
  }

 public:

  /**
   * \brief Empty constructor.
   */
  Maze() : height(0), width(0), cell(NULL) {
  }

  /** 
   * \brief Constructor.
   * \param width Latimea labirintului (nr. coloane)
   * \param height Inaltimea labirintului (nr. linii)
   */
  Maze(unsigned int height, unsigned int width) : height(height), width(width) {
    if (height > 0 && width > 0) {
      allocate();
    } else {
      this->height = 0;
      this->width = 0;
    }
  }

  ~Maze() {
    if (cell) {
      for (unsigned int i = 0; i < height; i++) {
        delete[] cell[i];
      }
      delete[] cell;
    }
  }

  /** 
   * \brief Functie pentru determinarea latimii labirintului.
   * \return Latimea labirintului
   */
  inline int get_width() {
    return width;
  }

  /** 
   * \brief Functie pentru determinarea inaltimii labirintului.
   * \return Inaltimea labirintului
   */
  inline int get_height() {
    return height;
  }

  /**
   * \brief Functie care spune daca o celula din labirint este accesibila.
   * \param line Linia din labirint.
   * \param column Coloana din labirint.
   * \return Functia intoarce <b>true</b> daca celula din labirint de la (line,
   * column) este accesibila, sau <b>false</b> daca respectiva celula este zid.
   */
  inline bool is_walkable(unsigned int line, unsigned int column) {
    if (line >= 0 && line < height && column >= 0 && column < width) {
      return cell[line][column] != '#';
    } else {
      return false;
    }
  }

  /**
   * \brief Functie care spune daca o celula din labirint este accesibila.
   * \param coord Linia si coloana din labirint.
   * \return Functia intoarce <b>true</b> daca celula din labirint de la coord
   * este accesibila, sau <b>false</b> daca respectiva celula este zid.
   */
  inline bool is_walkable(Coord coord) {
    return is_walkable(coord.first, coord.second);
  }

  /**
    * \brief Functie care spune daca o celula din labirint este punct de iesire.
    * \param line Linia din labirint.
    * \param column Coloana din labirint.
    * \return Functia intoarce <b>true</b> daca celula din labiring de la (line,
    * column) este acceisbila, sau <b>false</b> daca respectiva celula este zid.
    */
  inline bool is_exit_point(unsigned int line, unsigned int column) {
    if (line >= 0 && line < height && column >= 0 && column < width) {
      return is_walkable(line, column) &&
          (line == 0 || line == height - 1 || column == 0 || column == width - 1);
    } else {
      return false;
    }
  }

  /**
    * \brief Functie care spune daca o celula din labirint este punct de iesire.
    * \param coord Linia si coloana din labirint.
    * \return Functia intoarce <b>true</b> daca celula din labiring de la coord
    * este acceisbila, sau <b>false</b> daca respectiva celula este zid.
    */
  inline bool is_exit_point(Coord coord) {
    return is_exit_point(coord.first, coord.second);
  }

  /**
    * \brief Functie care marcheaza un pas din solutie.
    * \param line Linia din labirint.
    * \param column Coloana din labirint.
    */
  inline void mark_solution_step(unsigned int line, unsigned int column) {
    if (line >= 0 && line < height && column >= 0 && column < width) {
      cell[line][column] = '*';
    }
  }

  /**
    * \brief Functie care marcheaza un pas din solutie.
    * \param coord Linia si coloana din labirint.
    * \return Functia intoarce <b>true</b> daca celula din labiring de la coord
    * este acceisbila, sau <b>false</b> daca respectiva celula este zid.
    */
  inline void mark_solution_step(Coord coord) {
    mark_solution_step(coord.first, coord.second);
  }

  friend std::istream& operator>> (std::istream&, Maze& maze);
  friend std::ostream& operator<< (std::ostream&, const Maze&);
};

#endif

