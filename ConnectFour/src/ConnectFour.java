import java.awt.Color;

/** 
 * Class to play a game of Connect 4.
 * Computer plays black, human plays white.
 */
public class ConnectFour {

    /** Number of columns on the board. */
    public static final int COLUMNS = 7;

    /** Number of rows on the board. */
    public static final int ROWS = 6;
    
    /** Color for computer player's pieces */
    public static final Color COMPUTER = Color.BLACK;

    /** Color for human player's pieces */
    public static final Color HUMAN = Color.RED;

    /** Color for blank spaces. */
    public static final Color NONE = Color.WHITE;


    /**
     * Drops a piece of given Color in column.  Modifies the board
     * array. Assumes the move is legal.
     *
     * @param board The game board.
     * @param color Color of piece to drop.
     * @param column Column in which to drop the piece.
     */
    public static void dropPiece(Color[][] board, Color color, int column) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][column] == NONE) {
                board[i][column] = color;
                break;
            }
        }
    }

    /** 
     * Checks if the board is full.
     * @param board The game board.
     * @return True if board is full, false if not.
     */
    public static boolean isFull(Color[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == NONE)
                    return false;
            }
        }
        
        return true;
    }
    
    /** 
     * Checks if dropping a piece into the given column would be a
     * legal move.
     *
     * @param board The game board.
     * @param column The column to check.
     * @return true if column is neither off the edge of the board nor full.
     */
    public static boolean isLegal(Color[][] board, int column) {
        if (column < 0 || column >= COLUMNS)
            return false;
        for (int i = 0; i < ROWS; i++) {
            if (board[i][column] == NONE)
                return true;
        }
        return false;
    }

    /**
     * Given the color of a player, return the color for the opponent.
     * Returns human player color when given computer player color.
     * Returns computer player color when given human player color.
     * @param color Player color.
     * @return Opponent color.
     */
    public static Color getOpposite(Color color) {
        return (color == HUMAN) ? COMPUTER : HUMAN;
    }

    /**
     * Check for a win starting at a given location and heading in a
     * given direction.
     * 
     * Returns the Color of the player with four in a row starting at
     * row r, column c and advancing rowOffset, colOffset each step.
     * Returns NONE if no player has four in a row here, or if there
     * aren't four spaces starting here.
     * 
     * For example, if rowOffset is 1 and colOffset is 0, we would
     * advance the row index by 1 each step, checking for a vertical
     * win. Similarly, if rowOffset is 0 and colOffset is 1, we would
     * check for a horizonal win.
     * @param board The game board.
     * @param r Row index of where win check starts
     * @param c Column index of where win check starts
     * @param rowOffset How much to move row index at each step
     * @param colOffset How much to move column index at each step
     * @return Color of winner from given location in given direction
     *         or NONE if no winner there.
     */
    public static Color findLocalWinner(Color[][] board, int r, int c,
                                        int rowOffset, int colOffset) {
        int count = 1;
        Color startingColor = board[r][c];
        if (startingColor == NONE)
            return NONE;
        
        while ((r >= 0 && r < ROWS) && (c >= 0 && c < COLUMNS)) {
            if ((r + rowOffset < 0 || r + rowOffset == ROWS ||
                    c + colOffset < 0 || c + colOffset == COLUMNS))
                break;
            if (board[r][c] == board[r + rowOffset][c + colOffset]) {
                count++;
                if (count == 4) {
                    return board[r][c];
                }
            }
            else {
                break;
            }
            r += rowOffset;
            c += colOffset;
        }
        
        return NONE;
    }

    /**
     * Checks entire board for a win (4 in a row).
     * 
     * @param board The game board.
     * @return color (HUMAN or COMPUTER) of the winner, or NONE if no
     * winner yet.
     */
    public static Color findWinner(Color[][] board) {
        int pieceRow = -1;
        int pieceCol = -1;
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] != NONE) {
                    pieceRow = i;
                    pieceCol = j;
                    if (pieceRow != -1 || pieceCol != -1) {
                        Color horizontalCheck = findLocalWinner(board, pieceRow, pieceCol, 0, 1);
                        Color horizontalCheck2 = findLocalWinner(board, pieceRow, pieceCol, 0, -1);
                        Color verticalCheck = findLocalWinner(board, pieceRow, pieceCol, 1, 0);
                        Color verticalCheck2 = findLocalWinner(board, pieceRow, pieceCol, -1, 0);
                        Color diagonalCheck1 = findLocalWinner(board, pieceRow, pieceCol, 1, 1);
                        Color diagonalCheck2 = findLocalWinner(board, pieceRow, pieceCol, -1, 1);
                        Color diagonalCheck3 = findLocalWinner(board, pieceRow, pieceCol, -1, -1);
                        Color diagonalCheck4 = findLocalWinner(board, pieceRow, pieceCol, 1, -1);
                        
                        Color[] checks = {horizontalCheck, horizontalCheck2, verticalCheck, verticalCheck2,
                                diagonalCheck1, diagonalCheck2, diagonalCheck3, diagonalCheck4};
                        for (Color check : checks) {
                            if (check != NONE)
                                return check;
                        }
                    }
                }
            }
        }
        
        return NONE;
    }

    /**
     * Returns computer player's best move.
     * @param board The game board.
     * @param maxDepth Maximum search depth.
     * @return Column index for computer player's best move.
     */
    public static int bestMoveForComputer(Color[][] board, int maxDepth) {
        Color win = NONE;
        int bestMove = -1;
        
        if (maxDepth <= 0)
            return bestMove;
        
        for (int i = 0; i < COLUMNS; i++) {
            if (isLegal(board, i)) {
                dropPiece(board, COMPUTER, i);
                //printBoard(board);
                bestMove = bestMoveForComputer(board, maxDepth - 1);
                win = findWinner(board);
                if (win != NONE) {
                    //System.out.println("!");
                    undoDrop(board, i);
                    return i;
                }
                undoDrop(board, i);
                dropPiece(board, HUMAN, i);
                //printBoard(board);
                bestMove = bestMoveForComputer(board, maxDepth - 1);
                win = findWinner(board);
                if (win != NONE) {
                    //System.out.println("!");
                    undoDrop(board, i);
                    return i;
                }
                if (bestMove != -1) {
                    undoDrop(board, i);
                    return bestMove;
                }
                undoDrop(board, i);
            }
        }
        return bestMove;
        
        
    }
    
    public static void printBoard(Color[][] board) {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == HUMAN)
                    System.out.print("O");
                else if (board[i][j] == COMPUTER)
                    System.out.print("X");
                else
                    System.out.print("-");
                
            }
            System.out.println();
        }
    }


    /**
     * Returns the value of board with computer to move:
     *     10 if computer can force a win,
     *     -10 if computer cannot avoid a loss,
     *     0 otherwise.
     * 
     * @param board The game board.
     * @param maxDepth Maximum search depth.
     * @param depth Current search depth.
     */
    public static int maxScoreForComputer(Color[][] board, int maxDepth, int depth) {
        Color winner = findWinner(board);
        if (winner == COMPUTER) {
            return 10;
        } else if (winner == HUMAN) {
            return -10;
        } else if (isFull(board) || (depth == maxDepth)) {
            return 0;
        } else {
            int bestResult = -20;

            for (int c = 0; c < COLUMNS; c++) {
                if (isLegal(board, c)) {
                    dropPiece(board, COMPUTER, c);
                    int result = minScoreForHuman(board, maxDepth, depth + 1);
                    undoDrop(board, c);

                    if (result >= bestResult) {
                        bestResult = result;
                    }
                }
            }
            return bestResult;
        }
    }
    
    
    /**
     * Returns the value of board with human to move: 
     *    10 if human cannot avoid a loss,
     *    -10 if human can force a win,
     *     0 otherwise.
     * 
     * @param board The game board.
     * @param maxDepth Maximum search depth.
     * @param depth Current search depth.
     */
    public static int minScoreForHuman(Color[][] board, int maxDepth, int depth) {

        // The comments in this method are rather verbose to help you
        // understand what is going on. I don't expect you to be so
        // wordy in your own code.

        // First, see if anyone is winning already
        Color winner = findWinner(board);
        if (winner == COMPUTER) {
            // computer is winning, so human is stuck
            return 10;
        } else if (winner == HUMAN) {
            // human already won, no chance for computer
            return -10;
        } else if (isFull(board) || (depth == maxDepth)) {
            // We either have a tie (full board) or we've searched as
            // far as we can go. Either way, call it a draw.
            return 0;
        } else {
            // At this point, we know there isn't a winner already and
            // that there must be at least one column still available
            // for play. We'll search all possible moves for the human
            // player and decide which one gives the lowest (best for
            // human) score, assuming that the computer would play
            // perfectly.

            // Start off with a value for best result that is larger
            // than any possible result.
            int bestResult = 20;

            // Loop over all columns to test them in turn.
            for (int c = 0; c < COLUMNS; c++) {
                if (isLegal(board, c)) {
                    // This column is a legal move. We'll drop a piece
                    // there so we can see how good it is.
                    dropPiece(board, HUMAN, c);
                    // Call maxScoreForComputer to see what the value would be for the
                    // computer's best play. The maxScoreForComputer method will end
                    // up calling minScoreForHuman in a similar fashion in order to
                    // figure out the best result for the computer's
                    // turn, assuming the human will play perfectly in
                    // response.
                    int result = maxScoreForComputer(board, maxDepth, depth + 1);
                    // Now that we have the result, undo the drop so
                    // the board will be like it was before.
                    undoDrop(board, c);

                    if (result <= bestResult) {
                        // We've found a new best score. Remember it.
                        bestResult = result;
                    }
                }
            }
            return bestResult;
        }
    }


    /**
     * Removes the top piece from column. Modifies board. Assumes
     * column is not empty.
     * @param board The game board.
     * @param column Column with piece to remove.
     */
    public static void undoDrop(Color[][] board, int column) {
        // We'll start at the top and loop down the column until we
        // find a row with a piece in it. 
        int row = ROWS - 1;
        while(board[row][column] == NONE && row > 0) {
            row--;
        }

        // Set the top row that had a piece to empty again.
        board[row][column] = NONE;
    }

    /** Creates board array and starts game GUI. */
    public static void main(String[] args) {
        // create array for game board
        Color[][] board = new Color[ROWS][COLUMNS];
        // fill board with empty spaces
        for(int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = NONE;
            }
        }
        // show the GUI and start the game
        ConnectFourGUI.showGUI(board, HUMAN, 5);
    }

}
