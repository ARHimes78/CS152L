/**
 * @version date (in_ISO_8601 format: 2017-04-16)
 * @author Alan Himes
 *
 */
public class Book {
    private String title;
    private Author author;
    private int year;
    private String isbn;
    
    /**
     * Sets up default values for the local variables.
     */
    public Book() {
        title = BookConstants.UNKNOWN_TITLE;
        author = BookConstants.UNKNOWN_AUTHOR;
        year = BookConstants.UNKNOWN_YEAR;
        isbn = BookConstants.UNKNOWN_ISBN;
    }
    
    /**
     * Calls parameterless constructor and sets title variable.
     * @param title Title of book.
     */
    public Book( String title ) {
        this();
        this.title = title;
    }
    
    /**
     * Calls other constructors and sets author variable.
     * @param title Title of book.
     * @param author Author of book.
     */
    public Book( String title, Author author ) {
        this(title);
        this.author = author;
    }
    
    /**
     * Sets the title of the book.
     * @param title Book's title.
     */
    public void setTitle( String title ) {
        if (title.equals("")) {
            System.out.println("Please enter a title.");
        }
        else
            this.title = title;
    }
    
    /**
     * Returns title of book.
     * @return Book's title.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets author of book.
     * @param author Author of book.
     */
    public void setAuthor( Author author) {
        this.author = author;
    }
    
    /**
     * Returns author of book.
     * @return Author of book.
     */
    public Author getAuthor() {
        return author;
    }
    
    /**
     * Checks validity of argument and sets the publication year.
     * @param year Year of book's publication.
     */
    public void setPubYear( int year ) {
        if (year > -2000 && year < 2022) {
            try {
                this.year = year;
            } catch ( Exception e ) {
                System.out.println("That's not a number!");
            }
        }
        else {
            System.out.println("Please enter a year between -2000 and 2022.");
        }
    }
    
    /**
     * Returns book's publication year.
     * @return Book's publication year.
     */
    public int getPubYear() {
        return year;
    }
    
    /**
     * Checks validity of argument and sets ISBN.
     * @param isbn Book's International Standard Book Number.
     */
    public void setISBN ( String isbn ) {
        if (isbn.length() != 13) {
            System.out.println("Incorrect ISBN length!");
        }
        else
            this.isbn = isbn;
    }
    
    /**
     * Returns ISBN.
     * @return Book's ISBN.
     */
    public String getISBN () {
        return isbn;
    }
    
    /**
     * Checks whether two books have the same author.
     * @param other Book object being compared with current one.
     * @return
     */
    public boolean sameAuthor( Book other ) {
        return this.author.isSame(other.author);
    }
    
    /**
     * Checks whether two books are the same by seeing if the ISBNs are the same.
     * @param other Book object being compared with current one.
     * @return The verdict on whether or not the two books are the same.
     */
    public boolean equals( Book other ) {
        return this.getISBN().equals(other.getISBN());
    }
    
    /**
     * Overrides Object's toString() method and creates formatted string based on what variables were set.
     */
    public String toString() {
        String info = BookConstants.UNKNOWN_TITLE; //in case title wasn't sent
        
        if (author != BookConstants.UNKNOWN_AUTHOR &&
                year != BookConstants.UNKNOWN_YEAR) {
            info = title + " (" + year + "). " + author.toString() + ".";
        }
        else if (author != BookConstants.UNKNOWN_AUTHOR) {
            info = title + ". " + author.toString() + ".";
        }
        else if (title != BookConstants.UNKNOWN_TITLE) {
            info = title + ".";
        }
        return info;
    }
}
