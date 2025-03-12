public class NQueen {
    private  static  int N;
private boolean solutionFound = false;

/**
 * Print the Solution of N-Queen Problem 
 * @param board The current board configuration.
 */
 public void printSolution(int[][] board) {
      try {
        
           if (!solutionFound) {
            for (int[] row : board) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
            System.out.println();
           }
           
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
/**
     * Checks whether it's safe to place a queen at board.
     * It ensures there is no conflict with other queens vertically, 
     * diagonally.
     * @param board The current board configuration.
     * @param row The row index to check.
     * @param col The column index to check.
     * @return True if it's safe to place the queen, otherwise false.
     */
    public boolean isSafe(int[][] board, int row, int col) {
        try {
            for (int i = 0; i < col; i++) {
                if (board[row][i] == 1) {
                    return false;
                }
            }
    
            for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
    
            for (int i = row, j = col; j >= 0 && i < N; i++, j--) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
    
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
/**
     * Attempts to solve the N-Queen problem by placing queens column-by-column.
     *
     * @param board The current board configuration.
     * @param col The current column being processed.
     * @return True if a solution is found, otherwise false.
     */
    public boolean solveNQUtil(int[][] board, int col) {
       try {
        if (col >= N) {
            printSolution(board);
            solutionFound = true;
            return true;
        }

        boolean result = false;
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                result = solveNQUtil(board, col + 1) || result;

                board[i][col] = 0; 
            }
        }
        return result;
       } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
       }
    }

/**
 * Initializes for board and start solving N Queen problem
 * @param n size of board (n x n)
 */
    public void solveNQ(int n){
       try {
        N = n;
        int board[][] = new int[N][N];
        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution not found");
        }
       } catch (Exception e) {
        System.out.println(e.getMessage());
       }
    }

}
