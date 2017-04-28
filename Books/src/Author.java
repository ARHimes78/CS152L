/**
 * @version date (in_ISO_8601 format: 2017-04-16)
 * @author Alan Himes
 *
 */
public class Author {
    private String lastName;
    private String firstName;
    private int birth;
    private int death;
    
    /**
     * Constructor for initializing birth and death years with default values.
     */
    public Author() {
        birth = BookConstants.UNKNOWN_YEAR;
        death = BookConstants.UNKNOWN_YEAR;
    }
    
    /**
     * Constructor which calls the parameterless constructor and assigns name inputs to local variables
     * @param lastName Author's last name.
     * @param firstName Author's first name.
     */
    public Author( String lastName, String firstName ) {
        this();
        this.lastName = lastName;
        this.firstName = firstName;
    }
    
    /**
     * 
     * @return Returns author's year of birth.
     */
    public int getBirth() {
        return birth;
    }
    
    /**
     * 
     * @return Returns author's year of death.
     */
    public int getDeath() {
        return death;
    }
    
    /**
     * Checks validity of arguments and sets author's year of birth.
     * @param birth Author's year of birth.
     */
    public void setYears( int birth ) {
        if (birth > -2000 && birth < 2022) {
            try {
                this.birth = birth;
            } catch ( Exception e ) {
                System.out.println("That's not a number!");
            }
        }
        else {
            System.out.println("Please enter a year between -2000 and 2022.");
        }
    }
    
    /**
     * Checks validity of arguments and sets author's year birth and death.
     * @param birth Author's year of birth.
     * @param death Auther's year of death.
     */
    public void setYears( int birth, int death ) {
        if (birth > death) {
            System.out.println("This author couldn't have died before being born.");
        }
        else if (birth > -2000 && birth < 2022 &&
                death > -2000 && death < 2022) {
            try {
                this.birth = birth;
                this.death = death;
            } catch ( Exception e ) {
                System.out.println("That's not a number!");
            }
        }
        else {
            System.out.println("Please enter a year between -2000 and 2022.");
        }
    }
    
    /**
     * Verifies whether two authors are the same.
     * @param Other The author that's being compared to the current author.
     * @return The verdict on whether the authors are the same.
     */
    public boolean isSame( Author other ) {
        //If both authors first and last name are verbatim, they're the same. If not, still could be the same author.
        if (this.toString().equals(other.toString())) 
            return true;
        //If the last names are different, they're not the same.
        else if (!this.lastName.equals(other.lastName))
            return false;
        
        String authorFirstInitial = this.firstName.substring(0, 1);
        String otherFirstInitial = other.firstName.substring(0, 1);
        
        //If the first initials are the same and a first name was input as the first initial, they're the same author.
        if (authorFirstInitial.equals(otherFirstInitial)) {
            return (this.firstName.equals(authorFirstInitial) || 
                    other.firstName.equals(otherFirstInitial));
        }
        //If the first initials are not the same, they're not the same name author.
        else
            return false;
    }
    
    /**
     * Overrides Object's toString() method and returns a formatted author's name.
     */
    public String toString() {
        return lastName + ", " + firstName;
    }
    
    /**
     * Creates and returns formatted string based on what info is available.
     * @return Formatted string.
     */
    public String infoString() {
        String info = this.toString();
        
        if (birth != BookConstants.UNKNOWN_YEAR && 
                death != BookConstants.UNKNOWN_YEAR) {
            info += " (" + birth + "-" + death + ")";
        }
        else if (birth != BookConstants.UNKNOWN_YEAR) {
            info += " (b. " + birth + ")";
        }
        
        return info;
    }
}
