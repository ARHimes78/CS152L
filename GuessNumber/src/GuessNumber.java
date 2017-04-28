import java.util.Scanner;
/**
 * @version date (in_ISO_8601 format: 2017 -01-31) 
 * @author Alan Himes
 */
public class GuessNumber {
    /**
     * Compares user input number guess, compares
     * with randomized number. 
     * @param args Command -line arguments are ignored. 
     */ 
    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);
        int randomNum = (int)(Math.random() * 10) + 1;
        int userGuess = 0;
        
        System.out.print("Please enter your name: ");
        String userName = scanIn.nextLine();
        System.out.print(userName + ", please guess a number between 1 and 10: ");
        do {
            try {
        	    /*Decided to use this method instead of scanIn.nextInt()
        	     *in case user enters a non-number. Catching a resulting
        	     *java.util.InputMismatchException would cause an endless loop.
        	     */
                userGuess = Integer.parseInt(scanIn.nextLine());
            } catch (NumberFormatException nfe) { //If a non-number is entered.
                System.out.println("Invalid input!");
            }
            if (userGuess > 0 && userGuess < 11) {
                break;
            }
            else {
                System.out.print("Please enter a number between 1 and 10! ");
            }
        } while (true);
        System.out.println("You guessed " + userGuess + ".\n" 
                + "The correct number is " + randomNum + ".");
        if (userGuess == randomNum) {
            System.out.println("Congratulations, " + userName + "! You guessed the correct number!");
        }
        else {
            System.out.println("I'm sorry, " + userName + ", but you didn't guess the correct number.");
        }
        scanIn.close();
    }
}