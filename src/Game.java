import java.util.Scanner;

public class Game {

  int columns = 0;
  int rows = 0;
  String board[][];

  public Game(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.board = new String[rows][columns];
  }

  public int getColumns() {
    return columns;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public void createBoard(int rows, int columns) {

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        // initialize with empty space
        board[i][j] = " ";
      }
    }
  }

  public void drawBoard() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {

        // print (row,column) value
        System.out.print(board[i][j]);

        // after that, draw separator characters
        switch (j) {
          case 0:
            System.out.print(" | ");
            break;
          case 1:
            System.out.print(" | ");
            break;
          case 2:
            System.out.print(" ");
            System.out.println();
            break;
        }
      }
    }
  }

  public boolean move (int row, int column, int size, String symbol) {
    Scanner scan =  new Scanner(System.in);

      if (board[row][column].isBlank()) {
        board[row][column] = symbol;
      } else {
        System.out.println("Coordinates are already occupied, please select a new one");

        row = scan.nextInt();
        column = scan.nextInt();

        this.move(row, column, size, symbol);
      }

      return this.checkWinner(row, column, symbol, size);
  }

  public boolean checkWinner (int row, int column, String symbol, int size) {
    boolean won = false;

    // Check rows
    for (int i = 0; i < size; i++) {
      if ( !board[row][i].equals(symbol)) {
        break;
      }
      if (i == size-1) {
        won = true;
      }
    }

    // Check columns
    for (int i = 0; i < size; i++) {
      if ( board[i][column] != symbol) {
        break;
      }
      if (i == size-1) {
        won = true;
      }
    }

    // Check diagonal
    for (int i = 0; i < size; i++) {
      if ( board[i][i] != symbol) {
        break;
      }
      if (i == size-1) {
        won = true;
      }
    }

    // Check reverse diagonal
    for (int i = 0; i < size; i++) {
      if ( board[i][size- (i + 1)] != symbol) {
        break;
      }
      if (i == size-1) {
        won = true;
      }
    }

  return won;
  }

}
