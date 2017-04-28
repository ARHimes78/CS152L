import java.util.Scanner;

/**
 * @version date (in_ISO_8601 format: 2017 -02-07) 
 * @author Alan Himes
 */
public class Pig { 
    public static final int COMPUTER_GOAL = 15;
    public static final int MAXIMUM_SCORE = 100;
    public static Scanner scanIn; //Used as class object because of its involvement in several methods.

    /**
     * 2 players roll a dice and accumulate points unless a 1 has been rolled.
     * @param args Command -line arguments are ignored. 
     */
    public static void main(String[] args) {
        scanIn = new Scanner(System.in);
        int player0Score = 0;
        int player1Score = 0;
        int computerScore = 0;
        boolean computerPlayer = false;
        boolean gameOver = false;

        System.out.print("Would you like to include the computer in this game? (y/n): ");
        computerPlayer = yesOrNo(scanIn.nextLine());
        System.out.println();
        
        do {
            System.out.print("Score: ");
            displayScore(computerPlayer, player0Score, player1Score, computerScore);
            
            for (int i = 0; i < 3; i++) {
                switch (i) {
                case 0:
                    //Used methods because there was very little difference between the code for each player.
                    System.out.println("Player " + i + ", it's your turn.");
                    player0Score = playerTurn(player0Score, false);
                    break;
                case 1:
                    System.out.println("Player " + i + ", it's your turn.");
                    player1Score = playerTurn(player1Score, false);
                    break;
                case 2:
                    if (computerPlayer) {
                        System.out.println("Computer, it's your turn.");
                        computerScore = playerTurn(computerScore, true);
                    }
                    break;
                }
                System.out.println();
                //End game immediately if anyone scores over 100.
                if (scoreCheck(player0Score, player1Score, computerScore)) {
                    gameOver = true;
                    break;
                }
            }
        } while (!gameOver); //I thought of this as "While the game is NOT over"

        System.out.print("Game over. Final score: ");
        displayScore(computerPlayer, player0Score, player1Score, computerScore);
        scanIn.close();
    }

    /**
     * This method uses dice rolls to decide whether to return added or unaffected points for the current player.
     * @param The player's current score.
     * @param Whether or not the computer is playing.
     * @return The score according to what happened when the dice was rolled.
     */
    public static int playerTurn(int score, boolean computerTurn) {
        int dieRoll = 0;
        int originalScore = score;//stored so that it can be returned if a 1 is rolled.
        String keepGoing = "";
        boolean playerContinue = true;
        String[] disappointedReaction = {"D'aww", "Regrettably", "Alas", "@%$#", "Bummer"};

        do {
            dieRoll = (int)(Math.random() * 6) + 1;
            System.out.println("You rolled a " + dieRoll);
            if (dieRoll != 1) {
                score += dieRoll;
                System.out.println("You have " + score + " points.");

                if (score >= MAXIMUM_SCORE) {
                    break;
                }

                System.out.print("Keep going? (y/n): ");
                if (computerTurn) {
                    //The computer's strategy is to score at least 15 points per turn.
                    keepGoing = (score - originalScore < COMPUTER_GOAL) ? "y" : "n";
                    System.out.println(keepGoing);
                }
                else {
                    keepGoing = scanIn.nextLine();
                }
                playerContinue = yesOrNo(keepGoing);
            }
            else {
                //Return unaffected score when 1 is rolled.
                System.out.println(disappointedReaction[(int)(Math.random() * 5)] + 
                        ", your score is " + originalScore + " points.");
                return originalScore;
            }
        } while (playerContinue);
        return score;
    }
    
    /**
     * Tells this program what to do according to user input in a few cases.
     * @param answer Whatever the user entered when asked to answer y/n.
     * @return True for anything beginning with y or Y and Enter (instead of crashing program). False for anything else.
     */
    public static boolean yesOrNo(String answer) {
        try {
            //Anything starting with "y" counts as a yes.
            if (answer.toLowerCase().substring(0, 1).equals("y"))
                return true;
            else 
                return false;
            //This exception happens when responding with Enter, which will count as a "y".
        } catch (StringIndexOutOfBoundsException sioobe) {
            System.out.println("y");
            return true;
        }
    }
    
    /**
     * This method is used to tell the program whether any of the players met or exceeded 100 points.
     * @param p0Score Player 0's score
     * @param p1Score Player 1's score
     * @param compScore The computer's score
     * @return Returns true if any score meets or exceeds 100 points.
     */
    public static boolean scoreCheck(int p0Score, int p1Score, int compScore) {

        return (p0Score >= MAXIMUM_SCORE || p1Score >= MAXIMUM_SCORE || compScore >= MAXIMUM_SCORE);
    }
    
    /**
     * Displays the players' scores.
     * @param compPlayer Whether or not the computer is playing.
     * @param p0Score Player 0's score.
     * @param p1Score Player 1's score.
     * @param compScore The computer's score.
     */
    public static void displayScore(boolean compPlayer, int p0Score, int p1Score, int compScore) {
        if (compPlayer) {
            System.out.println(p0Score + "-" + p1Score + "-" + compScore + "\n");
        }
        else {
            System.out.println(p0Score + "-" + p1Score + "\n");
        }
    }
}