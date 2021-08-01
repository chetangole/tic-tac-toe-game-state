/*
 Tic tac toe
 3 X 3 matrix
 there are two players - X and O
 Player X starts the game
 Players take alternate turns to enter their symbol in an empty cell
 Any consecutive 3 horizontal/vertical/diagonal appearances makes that player a winner.
 
 Given a 3 X 3 matrix, identify the state of the game:
 1. X is a winner
 2. O is a winner
 3. Draw
 4. In Progress
 5. Invalid
   
 */

class Solution {
	public static void main(String[] args) {
		
		String[][] matrix0 = { 
				{ "O", "X", "O" },
				{ "X", null, null },
				{ "O", "X", "O" } };

		System.out.println(identifyState(matrix0));
		
		String[][] matrix1 = { 
				{ "O", "X", "X" },
				{ "O", "O", "X" },
				{ "O", "X", "O" } };

		System.out.println(identifyState(matrix1));
		
		String[][] matrix2 = { 
				{ "O", "X", "O" },
				{ "X", "X", "X" },
				{ "O", "O", "X" } };

		System.out.println(identifyState(matrix2));
		
		String[][] matrix3 = { 
				{ "O", "X", "O" },
				{ "X", "O", "X" },
				{ "O", "X", "O" } };

		System.out.println(identifyState(matrix3));
		
		String[][] matrix4 = { 
				{ "O", "X", "O" },
				{ "X", "O", "X" },
				{ "O", "X", null } };

		String state4 = identifyState(matrix4);
		System.out.println(state4);
		
		String[][] matrix5 = { 
				{ null, null, null },
				{ null, null, null },
				{ null, null, null } };

		System.out.println(identifyState(matrix5));
		
		String[][] matrix6 = { 
				{ null, null, "O" },
				{ null, "X", "O" },
				{ null, null, null } };

		System.out.println(identifyState(matrix6));
		
		String[][] matrix7 = { 
				{ "O", "X", "O" },
				{ "X", "X", "O" },
				{ null, "O", "X" } };

		System.out.println(identifyState(matrix7));
		
		String[][] matrix8 = { 
				{ "O", "X", "O" },
				{ "X", "X", "X" },
				{ "O", "O", "X" } };

		System.out.println(identifyState(matrix8));

	}

	private static void print(String[][] matrix) {
		System.out.println("");
		System.out.println("Matrix:");

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	enum STATE {
		WINNER_X, WINNER_O, DRAW, IN_PROGRESS, INVALID;
	}

	//
	// 00 01 02
	// 10 11 12
	// 20 21 22

	private static String identifyState(String[][] matrix) {

		int matrixLength = matrix.length;
		STATE state = STATE.IN_PROGRESS;

		print(matrix);

		int countOfX = 0;
		int countOfO = 0;
		int countOfNull = 0;

		int countOfDiaX = 0;
		int countOfDiaO = 0;

		for (int i = 0; i < matrixLength; i++) {

			int countOfRowNull = 0;
			int countOfRowX = 0;
			int countOfRowO = 0;

			int countOfColX = 0;
			int countOfColO = 0;

			for (int j = 0; j < matrixLength; j++) {

				// rows
				if ("X".equals(matrix[i][j])) {
					countOfRowX++;
				} else if ("O".equals(matrix[i][j])) {
					countOfRowO++;
				} else {
					countOfRowNull++;
				}

				// columns
				if ("X".equals(matrix[j][i])) {
					countOfColX++;
				} else if ("O".equals(matrix[j][i])) {
					countOfColO++;
				}

			}

			// diagonal
			if ("X".equals(matrix[i][i])) {
				countOfDiaX++;
			} else if ("O".equals(matrix[i][i])) {
				countOfDiaO++;
			}

			if (countOfRowX == matrixLength || countOfColX == matrixLength || countOfDiaX == matrixLength) {
				state = STATE.WINNER_X;
			} else if (countOfRowO == matrixLength || countOfColO == matrixLength || countOfDiaO == matrixLength) {
				state = STATE.WINNER_O;
			}

			countOfX = countOfX + countOfRowX;
			countOfO = countOfO + countOfRowO;
			countOfNull = countOfNull + countOfRowNull;

		}

		if (countOfO > countOfX) {
			state = STATE.INVALID;
		} else if (state == STATE.IN_PROGRESS && countOfNull == 1) {
			state = STATE.DRAW;
		}

		return state.toString();

	}

}
