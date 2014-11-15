package yeah.o.punch.number;

import java.util.Arrays;
import java.util.Random;

import yeah.o.punch.Utilities;

public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        boolean[] flagsRow = new boolean[9];
        boolean[] flagsCol = new boolean[9];
        
        final int LEN = 9;
        for(int i=0; i<LEN; i++) {
            Arrays.fill(flagsRow, false);
            Arrays.fill(flagsCol, false);
            
            for(int j=0; j<LEN; j++) {
            	char charRow = board[i][j];
            	if (charRow>='1' && charRow<='9') {
	            	if (!flagsRow[charRow-'1']) {
	            		flagsRow[charRow-'1'] = true;
	            	} else {
	            		return false;
	            	}
            	}
            	
            	char charCol = board[j][i];
            	if (charCol>='1' && charCol<='9') {
	            	if (!flagsCol[charCol-'1']) {
	            		flagsCol[charCol-'1'] = true;
	            	} else {
	            		return false;
	            	}
            	}
            }
        }
        
        for(int i=0; i<3; i++) {
        	for(int j=0; j<3; j++) {
                Arrays.fill(flagsRow, false);
        		for(int k=0; k<3; k++) {
        			for(int t=0; t<3; t++) {
        				char charCurr = board[i*3+k][j*3+t];
                    	if (charCurr<'1' || charCurr>'9')
        					continue;
        				
                    	if (!flagsRow[charCurr-'1']) {
                    		flagsRow[charCurr-'1'] = true;
                    	} else {
                    		return false;
                    	}
        			}
        		}
        		
        	}
        }

        return true;
    }
    
	public static void main(String[] args) {
/*		Random rand = new Random();
		
		int times = 3;
		for(int t=0; t<times; t++) {
			char[][] board = new char [9][9];
			System.out.println("[Board-" + t + "]:");
			for(int r=0; r<9; r++) {			
				for(int c=0; c<9; c++) {
					boolean yes = rand.nextBoolean();
					if (yes)
						board[r][c] = Utilities.randomCharInRange(1, 9);
					else
						board[r][c] = '.';
					System.out.print(" " + board[r][c] + " ");
				}
				System.out.println();
			}
			boolean valid = isValidSudoku(board);
			String answer = valid ? "This is a" : "This is NOT a";
			System.out.println(answer + " valid Sudoku");
			System.out.println("------------------------------------------------------");
		}
*/		
		char[][] board = {
				{'.','.','4','.','.','.','6','3','.'},
				{'.','.','.','.','.','.','.','.','.'},
				{'5','.','.','.','.','.','.','9','.'},
				{'.','.','.','5','6','.','.','.','.'},
				{'4','.','3','.','.','.','.','.','1'},
				{'.','.','.','7','.','.','.','.','.'},
				{'.','.','.','5','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.'},
				{'.','.','.','.','.','.','.','.','.'}
		};
		boolean valid = isValidSudoku(board);
		String answer = valid ? "This is a" : "This is NOT a";
		System.out.println(answer + " valid Sudoku");
		System.out.println("------------------------------------------------------");
	}

}
