public class Game {

  int columns = 0;
  int rows = 0;
  String board[][];

  public Game(int columns, int rows) {
    this.columns = columns;
    this.rows = rows;
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

}