

 public class KnightsTourProblem  {
 
    private  int N; 
 
    // Possible moves for a knight
    private  final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private  final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    /**
     * Checks if the next move is within the board boundaries and unvisited.
     * @param x Current x-coordinate of the knight.
     * @param y Current y-coordinate of the knight.
     * @param board The current state of the board.
     * @return true if the position is safe to visit, false otherwise.
     */
    private boolean isSafe(int x, int y, int[][] board) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
    }

    /**
     * Prints the board with the knight's tour path.
     * @param board The completed board to be displayed.
     */
    private  void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }
    }

    /**
     * Recursive utility function to solve the Knight's Tour problem.
     * @param x Current x-coordinate of the knight.
     * @param y Current y-coordinate of the knight.
     * @param moveCount Current move number.
     * @param board The current state of the board.
     * @return true if a solution is found, false otherwise.
     */
    private  boolean solveKTUtil(int x, int y, int moveCount, int[][] board) {
        if (moveCount == N * N) {
            return true; // All squares visited
        }

        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isSafe(nextX, nextY, board)) {
                board[nextX][nextY] = moveCount;
                if (solveKTUtil(nextX, nextY, moveCount + 1, board)) {
                    return true;
                }
                board[nextX][nextY] = -1; // Backtrack
            }
        }
        return false;
    }

    /**
     * Initializes the board and attempts to solve the Knight's Tour problem.
     * @param n Size of the board (n x n).
     */
    public  void solveKnightTour(int n) {
        N = n;
        int[][] board = new int[N][N];

        // Initialize the board with -1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = -1;
            }
        }

        // Starting position
        board[0][0] = 0;

        if (solveKTUtil(0, 0, 1, board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists for " + N + "x" + N + " board.");
        }
    }
 }
 