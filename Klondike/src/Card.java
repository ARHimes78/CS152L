/**
 * @version date (in_ISO_8601 format: 2017-04-28)
 * @author Alan Himes
 *
 */
public class Card {
    private Rank rank;
    private Suit suit;
    private boolean faceUp;
    
    /**
     * Parameterless constructor which initializes the card to face up.
     */
    public Card() {
        faceUp = true;
    }
    
    /**
     * Constructor which sets a card's rank an suit.
     * @param rank The rank assigned to a Card object
     * @param suit The suit assigned to a Card object
     */
    public Card( Rank rank, Suit suit ) {
        this();
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * Returns the Card's rank.
     * @return The Card's rank.
     */
    public Rank getRank() {
        return rank;
    }
    
    /**
     * Returns the Card's suit.
     * @return The Card's suit.
     */
    public Suit getSuit() {
        return suit;
    }
    
    /**
     * Returns whether or not the Card is facing up.
     * @return Whether or not the Card is facing up.
     */
    public boolean isFaceUp() {
        return faceUp;
    }
    
    /**
     * Returns whether or not the card is a black suit.
     * @return Whether or not the card is a black suit.
     */
    public boolean isBlack() {
        return (suit == Suit.CLUBS || suit == Suit.SPADES);
    }
    
    /**
     * Sets the Card to either face up or not face up.
     * @param faceUp Used to set which side the Card faces.
     */
    public void setFaceUp ( boolean faceUp ) {
        this.faceUp = faceUp;
    }
}
