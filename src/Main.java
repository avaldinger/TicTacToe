import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	int columns = 3;
	int rows = 3;

	String board[][] = new String[rows][columns];
	Scanner scan = new Scanner(System.in);

	File file = new File("TicTacToe.txt");

	static boolean won = false;

	public static void main(String[] args) {

		// Scanner object to handle user inputs
		Scanner scan = new Scanner(System.in);

		Game myGame = new Game(3,3);
		Player playerA = new Player("Player 1");
		Player playerB = new Player("Player 2");
		int size = 3;
		System.out.println("Welcome to the game, please enter your names! (between 0 and 10 characters)\n");

		// Setting the player names to default in case it's left empty
		try {
			// Setting player name and limiting it to 10 characters
			String player1 = scan.nextLine();
			playerA.setName(!player1.isEmpty() && player1.length() < 10 ? player1 : player1.substring(0, 10));
			String player2 = scan.nextLine();
			playerB.setName(!player2.isEmpty() && player2.length() < 10 ? player2 : player2.substring(0, 10));
		} catch (IndexOutOfBoundsException e) {
			playerA.setName("Player 1");
			playerB.setName("Player 2");
		}

		System.out.println("Welcome: " + playerA.getName() + " and " + playerB.getName() + "\nLet's begin! \n ");

		myGame.createBoard(myGame.getRows(), myGame.getColumns());
		myGame.drawBoard();

		System.out.println("Please select your character!");

		// Setting the symbols manually in case it's left empty
		try {
			playerA.setSymbol(scan.nextLine().toUpperCase());
			playerB.setSymbol(scan.nextLine().toUpperCase());
		} catch (NullPointerException e) {
			playerA.setSymbol("X");
			playerB.setSymbol("O");
		}
		System.out.println(playerA.getSymbol() + " " + playerB.getSymbol());

		System.out.println("New game! \n");

		int starter = (int) (1 + Math.random() * 2);
		System.out.println(starter);

		boolean winner = false;

		while (!winner) {

			System.out.print("Enter your character: ");
			String currentSymbol = scan.nextLine().toUpperCase();
			System.out.println();
			System.out.println("Type in the coordinates for your next step: ");
			int firstCoordinate = Integer.parseInt(scan.nextLine());
			int secondCoordinate = Integer.parseInt(scan.nextLine());

			if ((0 <= firstCoordinate && firstCoordinate < size) || (0 <= secondCoordinate && secondCoordinate < size)) {

				winner = myGame.move(firstCoordinate, secondCoordinate, 3, currentSymbol);
			} else {
				System.out.println("Coordinates are out of range, please select new ones");

				int newRow = Integer.parseInt(scan.nextLine());
				int newColumn = Integer.parseInt(scan.nextLine());

				winner = myGame.move(newRow, newColumn, size, currentSymbol);
			}
			int counter = 0;
			System.out.println(counter + " : " +winner);
			counter++;
			myGame.drawBoard();
			if (winner) {
				System.out.println("Player with symbol " + currentSymbol + " has won!");
				break;
			}

		}


		System.out.println();

		Main game = new Main();

		game.createBoard(3, 3);
		game.drawBoard();
		// game.writeToFile();
		while (!won) {
			// game.loadFromFile();
			game.assingSymbol();
			game.drawBoard();
			game.checkWinner();
			// game.writeToFile();
		}

	}

	public void drawBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				// print (row,column) value
				System.out.print(board[i][j]); // X

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

	public void createBoard(int rows, int columns) {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				// initialize with empty space
				board[i][j] = " ";
			}

			// scan.close();
		}
	}

	/*
	 * public void assingSymbol() { boolean accepted = false;
	 * System.out.println("Please choose your character (X or O): "); String x =
	 * scan.nextLine(); int row = scan.nextInt(); int column = scan.nextInt(); if
	 * (x.equals("X") || x.equals("O")) { while (accepted) { System.out.
	 * println("\nPlease enter the coordinates where you would like to place it: ");
	 * 
	 * 
	 * if (board[row - 1][column - 1].equals("X") || board[row - 1][column -
	 * 1].equals("O")) { System.out.
	 * println("That site is already occupied, please choose another one!");
	 * accepted = true; }
	 * 
	 * } { if ((row > 0 && row < 4) && (column > 0 && column < 4)) {
	 * 
	 * board[row - 1][column - 1] = x; } else {
	 * System.out.println("Invalid parameters."); } }
	 * 
	 * } else { System.out.println("Invalid character!"); } }
	 */

	public void assingSymbol() {
		boolean accepted = false;
		int row;
		int column;
		// Ask for the characters
		System.out.println("Please choose your character (X or O): ");
		String x = scan.nextLine();
		if (x.equals("X") || x.equals("O")) {
			do {
				// Ask for the cordinates
				System.out.println("\nPlease enter the coordinates (1 to 3) in a separate row (row, column): ");
				// Check if the coordinates are in the table, coordinates are entered row-1 and
				// column - 1
				try {
					row = scan.nextInt();
					column = scan.nextInt();
					if ((row > 0 && row < 4) && (column > 0 && column < 4)) {
						if (board[row - 1][column - 1].equals(" ") || board[row - 1][column - 1].equals(" ")) {
							// assign the character to the position
							board[row - 1][column - 1] = x;
							accepted = true;
							break;
						} else {
							System.out.println("That site is already occupied, please choose another one!");
						}
						// Check if the coordinates are empty or occupied
					} else {
						System.out.println("Invalid parameters.");
					}

				} catch (InputMismatchException ex) {
					System.out.println("Please enter a number!");
					break;
				}

			} while (!accepted);
		} else {
			System.out.println("\nPlease select X or O\n");
		}
	}

	public void writeToFile() {

		try {
			ObjectOutputStream toFile = new ObjectOutputStream(
					new FileOutputStream("C:\\Users\\adamv\\Desktop\\TicTacToe.txt"));
			toFile.writeObject(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadFromFile() {

		try {

			FileInputStream fs = new FileInputStream("C:\\Users\\adamv\\Desktop\\TicTacToe.txt");
			ObjectInputStream read = new ObjectInputStream(fs);

			read.readObject();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void checkWinner() {
		// boolean won = false;

		// board[row][column]
		do {
			// check first row with X
			if ((board[0][0].equals("X") && board[0][1].equals("X") && board[0][2].equals("X"))) {
				System.out.println("\n Player " + board[0][0] + " won.");
				won = true;
				// check the second row with X
			} else if ((board[1][0].equals("X") && board[1][1].equals("X") && board[1][2].equals("X"))) {
				System.out.println("\n Player " + board[1][0] + " won.");
				won = true;
				// check the 3rd row with X
			} else if ((board[2][0].equals("X") && board[2][1].equals("X") && board[2][2].equals("X"))) {
				System.out.println("\n Player " + board[2][0] + " won.");
				won = true;
				// check diagonal from right top to left bottom for X
			} else if ((board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X"))) {
				System.out.println("\n Player " + board[0][0] + " won.");
				won = true;
				// check diagonal from left top to right botom for X
			} else if ((board[2][0].equals("X") && board[1][1].equals("X") && board[0][2].equals("X"))) {
				System.out.println("\n Player " + board[2][0] + " won.");
				won = true;
				// check first row with O
			} else if (board[0][0].equals("O") && board[0][1].equals("O") && board[0][2].equals("O")) {
				System.out.println("\n Player " + board[0][0] + " won.");
				won = true;
				// check the second row with O
			} else if ((board[1][0].equals("O") && board[1][1].equals("O") && board[1][2].equals("O"))) {
				System.out.println("\n Player " + board[1][0] + " won.");
				won = true;
				// check the 3rd row with O
			} else if ((board[2][0].equals("O") && board[2][1].equals("O") && board[2][2].equals("O"))) {
				System.out.println("\n Player " + board[2][0] + " won.");
				won = true;
				// check diagonal from right to left for O
			} else if ((board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O"))) {
				System.out.println("\n Player " + board[0][0] + " won.");
				won = true;
				// check diagonal from left to right for O
			} else if ((board[2][0].equals("O") && board[1][1].equals("O") && board[0][2].equals("O"))) {
				System.out.println("\n Player " + board[2][0] + " won.");
				won = true;
			} else {
				System.out.println("\nPlease keep playing\n");
				break;
			}
		} while (!won);
	}

	/*
	 * public void checkWinner() { // boolean won = false; // Check the first row if
	 * (board[0][0] == board[0][1] && board[0][0] == board[0][2]) { won = true;
	 * System.out.println("\n Player" + board[0][0] + "won."); } // Check the second
	 * row else if (board[1][0] == board[1][1] && board[1][0] == board[1][2]) { won
	 * = true; System.out.println("\n Player" + board[1][0] + "won."); } // Check
	 * the third row else if (board[2][0] == board[2][1] && board[2][0] ==
	 * board[2][2]) { won = true; System.out.println("\n Player" + board[2][0] +
	 * "won."); } // Check the first column else if (board[0][0] == board[1][0] &&
	 * board[0][0] == board[2][0]) { won = true; System.out.println("\n Player" +
	 * board[0][0] + "won."); } // Check the second column else if (board[0][1] ==
	 * board[1][1] && board[0][1] == board[2][1]) { won = true;
	 * System.out.println("\n Player" + board[0][1] + "won."); } // Check the third
	 * column else if (board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
	 * won = true; System.out.println("\n Player" + board[0][2] + "won."); } //
	 * Check the third column else if (board[0][2] == board[1][2] && board[0][2] ==
	 * board[2][2]) { won = true; System.out.println("\n Player" + board[0][2] +
	 * "won."); } // Check the from left to right diagonal else if (board[0][0] ==
	 * board[1][1] && board[0][0] == board[2][2]) { won = true;
	 * System.out.println("\n Player" + board[0][0] + "won."); } // Check the from
	 * right to left diagonal else if (board[0][2] == board[1][1] && board[0][2] ==
	 * board[2][0]) { won = true; System.out.println("\n Player" + board[0][2] +
	 * "won."); } else { System.out.println("Please keep playing"); }
	 * 
	 * }
	 */

}
