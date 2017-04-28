/**
 * CS152 Lab 4 -- Welcome to Methods.
 *
 * Implement all the methods described below.
 *
 * Student name: Alan Himes
 */
public class MethodPractice {

    /**
     * Returns the product of its arguments.
     * @param x First argument
     * @param y Second argument
     * @param z Third argument
     * @return Product of x, y and z
     */
    public static int multThree( int x, int y, int z) {
        
        return x*y*z;
    }

    /**
     * Is argument negative?
     * @param x Value to check.
     * @return True if x is a negative number, false otherwise.
     */
    public static boolean isNegative( int x ) {
        if (x < 0)
            return true;
        else
            return false;
    }

    /**
     * Does the given string contain the letter G (either upper or
     * lower case)?
     * @param x String to check
     * @return True if x contains G, false otherwise.
     */
    public static boolean containsG( String x ) {
        boolean foundG = false;//will switch to true is G is found
        
        for (int i = 0; i<x.length(); i++) {
            if (Character.toString(x.charAt(i)).equalsIgnoreCase("g"))
                foundG = true;
        }
        
        return foundG;
    }

    /**
     * Where is the location of the letter G (upper or lower case) in
     * the given string?
     * @param x String to check
     * @return 0 based location of first occurrence of G in x,
     *         -1 if G is not present.
     */
    public static int indexOfG( String x ) {
        x = x.toLowerCase();
        
        return x.indexOf("g");
    }

    /**
     * This method returns a response based on the string input:
     *     Apple => Orange
     *     Hello => Goodbye!
     *     Turing => Machine
     *     Yay! => \o/
     * Any other input should be responded to with:
     *     What should I say? 
     * @param input The input string
     * @return Corresponding output string.
     */
    public static String respond( String input ) {
        String response = "";
        
        switch (input) {
        case "Apple":
            response = "Orange";
            break;
        case "Hello":
            response = "Goodbye!";
            break;
        case "Turing":
            response = "Machine";
            break;
        case "Yay!":
            response = "\\o/";
            break;
        default:
            response = "What should I say?";
        }
        
        return response;
    }

    /**
     * Average up to five positive numbers. Any non-positive values are
     * not included in the average. (Note: zero is not positive.)
     * @param a First value
     * @param b Second value
     * @param c Third value
     * @param d Fourth value
     * @param e Fifth value
     * @return Average of the positive input values. If none are positive, returns -1.
     */
    public static double averagePositives( int a, int b, int c, int d, int e ) {
        double average = 0.0;
        int[] input = {a, b, c, d, e};
        int nonPositiveCounter = 0;
        
        for (int i = 0; i < input.length; i++){
            if (input[i] <= 0) {
                if (input[i] < 0)
                    input[i] = 0;
                nonPositiveCounter++;
            }
            average += input[i];
        }
        
        if (nonPositiveCounter == 5)
            average = -1;
        else
            average /= (input.length - nonPositiveCounter);
        
        return average;
    }


    // WRITE A METHOD FROM SCRATCH
    //
    // Write a method called doubleOddIgnoreEven that returns twice
    // odd numbers and returns even values unchanged.
    //
    // The method should take a single int argument and return an int
    //
    // Don't forget to use the public static modifiers
    public static int doubleOddIgnoreEven( int input ) {
        
        return (input % 2 == 0) ? input : input * 2;
    }


    // WRITE A METHOD FROM SCRATCH
    //
    // Write a method called calculateTotalPayment that takes two
    // arguments, an int meal which is the cost of a meal, and a
    // double tip which represents the tip percentage one would add to
    // the bill.   
    // The method must return a double which equals how much should be
    // paid.
    //
    // int meal must be greater than 0
    // double tip must be 0 or greater and .6 or less (no tips over 60%)
    // If either number is invalid, return -1;
    //
    // Don't forget to use the public static modifiers
    public static double calculateTotalPayment (int meal, double tip) {
        double total = meal + (meal * tip);
        
        if (meal <= 0 || (tip <= 0 || tip > .6))
            total = -1;
        
        return total;
    }




    // This code tests your program's completeness.
    public static void main(String[] args) {
        int numCorrect = 0;

        if( multThree(1,2,3) == 6 ) { numCorrect++; }
        if( multThree(4,-5, 2) == -40 ) { numCorrect++; }
        
        if( isNegative(-3) ) { numCorrect++; }
        if( !isNegative(2) ) { numCorrect++; }
        if( !isNegative(0) ) { numCorrect++; }
        
        if( !containsG( "man" ) ) { numCorrect++; }    
        if( containsG( "dog" ) ) { numCorrect++; }    
        if( containsG( "EGGSHELL" ) ) { numCorrect++; } 

        if( indexOfG( "man" ) == -1 ) { numCorrect++; } 
        if( indexOfG( "EGGSHELL" ) == 1 ) { numCorrect++; } 
        if( indexOfG( "dog" ) == 2 ) { numCorrect++; } 
        if( indexOfG( "xyzggGGggG" ) == 3 ) { numCorrect++; } 
        if( indexOfG( "xyzGGggGGg" ) == 3 ) { numCorrect++; }

        if( respond( "Apple" ).equals( "Orange" ) ) { numCorrect++; } 
        if( respond( "Hello" ).equals( "Goodbye!" ) ) { numCorrect++; } 
        if( respond( "Turing" ).equals( "Machine" ) ) { numCorrect++; } 
        if( respond( "Yay!" ).equals( "\\o/" ) ) { numCorrect++; } 
        if( respond( "xyz" ).equals( "What should I say?" ) ) { numCorrect++; } 

        if( averagePositives( 12,13,12,13,12 ) == 12.4 ) { numCorrect++; } 
        if( averagePositives( 0,0,0,0,0 ) == -1 ) { numCorrect++; } 
        if( averagePositives( 0,0,15,0,-2 ) == 15 ) { numCorrect++; } 
        if( averagePositives( 100,-3,4021,-2,13 ) == 1378 ) { numCorrect++; } 

        // Uncomment these tests AFTER IMPLEMENTING doubleOddIgnoreEven
        if( doubleOddIgnoreEven( 4 ) == 4 ) { numCorrect++; } 
        if( doubleOddIgnoreEven( 0 ) == 0 ) { numCorrect++; } 
        if( doubleOddIgnoreEven( 3 ) == 6 ) { numCorrect++; } 

        // Uncomment these tests AFTER IMPLEMENTING calculateTotalPayment
        if( calculateTotalPayment( 0, .3 ) == -1 ) { numCorrect++; } 
        if( calculateTotalPayment( 10, .2 ) == 12.0 ) { numCorrect++; } 
        if( calculateTotalPayment( 100, .5 ) == 150 ) { numCorrect++; } 
        if( calculateTotalPayment( 100, .65 ) == -1 ) { numCorrect++; } 
        if( calculateTotalPayment( 120, .32 ) == 158.4 ) { numCorrect++; } 
    
        System.out.println( "Your program's completeness is currently: " + numCorrect + "/30" );
    }

}
