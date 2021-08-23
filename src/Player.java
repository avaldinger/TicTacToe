public class Player {

  String name;
  String symbol;
  boolean winner;

  public Player() {
    this.winner = false;
  }

  public Player(String name) {
    this.name = name;
    this.winner = false;
  }

  public Player(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
    this.winner = false;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public boolean isWinner() {
    return winner;
  }

  public void setWinner(boolean winner) {
    this.winner = winner;
  }
}
