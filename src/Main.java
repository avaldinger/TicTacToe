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

			System.out.println("-----------------------");
			System.out.print("Enter your character: ");
			String currentSymbol = scan.nextLine().toUpperCase();
			System.out.println();


			boolean correctCoordinates = false;

			while (!correctCoordinates) {
				System.out.println("Type in the coordinates for your next step: ");
				int firstCoordinate = Integer.parseInt(scan.nextLine());
				int secondCoordinate = Integer.parseInt(scan.nextLine());
				try {
					if ((0 <= firstCoordinate && firstCoordinate > size) && (0 <= secondCoordinate
							&& secondCoordinate > size)) {

						winner = myGame.move(firstCoordinate, secondCoordinate, size, currentSymbol);
						correctCoordinates = true;
					}
				}catch (IndexOutOfBoundsException e) {
					System.out.println("Coordinates are out of range, please select new ones");
				}

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
	}

}
