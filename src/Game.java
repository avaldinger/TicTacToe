public class Game {

  int columns = 0;
  int rows = 0;
  String board[][];

  public Game(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.board = new String[columns][rows];
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

}
