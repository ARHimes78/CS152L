/**
 * @version date (in_ISO_8601 format: 2017-04-28)
 * @author Alan Himes
 *
 */
public class Deck {
    private Card[] cards;
    private int numCards;
    
    /**
     * Constructor which initializes a deck and how many cards are started out with.
     */
    public Deck() {
        cards = new Card[52];
        numCards = 0;
    }
    /**
     * Places a Card on the first empty (null) index found on the Card array, which serves as the deck.
     * @param card The card object which will be added to the deck
     */
    public void add(Card card) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                cards[i] = card;
                numCards = i + 1;
                break;
            }
        }
    }
    
    /**
     * Creates a card deck containing every possible rank and suit.
     */
    public void fill() {
        Rank[] allRanks = Rank.values();
        Suit[] allSuits = Suit.values();
        int count = 0;
        
        for (int suits = 0; suits < allSuits.length; suits++) {
            for (int ranks = 0; ranks < allRanks.length; ranks++) {
                cards[count] = new Card(allRanks[ranks], allSuits[suits]);
                numCards++;
                count++;
            }
        }
    }
    
    /**
     * Returns a selected Card.
     * @param n The number referencing a Card which will be returned.
     * @return The selected Card.
     */
    public Card get( int n ) {
        if (n < 0 || n > 51) {
            return null;
        }
        
        return cards[n];
    }
    
    /**
     * Adds a top Card from current Deck to another Deck, then removes the Card from the current Deck.
     * @param other Another Deck object.
     */
    public void move ( Deck other ) {
        other.add(cards[size() - 1]);
        cards[size() - 1] = null;
        numCards--;
    }
    
    /**
     * Adds a selection of Cards from current Card to another Card, then removes selection from current Card.
     * @param other Another Deck object.
     * @param n Number of cards being selected.
     */
    public void move ( Deck other, int n ) {
        Card[] stack = new Card[n];
        int index = 0;
        
        for (int i = size() - n; i < size(); i++) {
            stack[index] = cards[i];
            cards[i] = null;
            index++;
        }
        
        numCards -= n;
        
        for (int i = 0; i < stack.length; i++) {
            other.add(stack[i]);
        }
    }
    
    /**
     * Returns how many Cards objects are in the Card array.
     * @return The number of Cards in the Card array.
     */
    public int size() {
        return numCards;
    }
    
    /**
     * Returns top card, or last Card object on the Card array.
     * @return The top card.
     */
    public Card getTop() {
        if (size() == 0) {
            return null;
        } 
        else {
            return cards[size() - 1];
        }
    }
    
    /**
     * Rearranges the order of the Card array with several steps.
     */
    public void shuffle() {
        //A Card array that will receive cards using another array's exclusive random numbers.
        Card[] randomCards = new Card[size()];
        int[] randomIndexes = new int[size()];
        
        //Initializing the random indexes array with -1s because a 0 will be detected as "already used".
        for (int i = 0; i < randomIndexes.length; i++) {
            randomIndexes[i] = -1;
        }
        
        //Randomize numbers within the range of available cards, add them to indexes array if not already added to it.
        for (int i = 0; i < randomIndexes.length; i++) {
            int randomNumber = (int)(Math.random() * randomIndexes.length);
            while (true) {
                if (alreadyUsed(randomNumber, randomIndexes)) {
                    randomNumber = (int)(Math.random() * randomIndexes.length);
                }
                else {
                    randomIndexes[i] = randomNumber;
                    break;
                }
            }
        }
        
        //Use the array of randomized numbers to create shuffled copy of the Cards array
        for (int i = 0; i < size(); i++) {
            randomCards[i] = cards[randomIndexes[i]];
        }
        
        cards = randomCards;
    }
    
    /**
     * Extra method to help with the shuffling process.
     * @param num Number which will be compared to every other number in an array
     * @param nums An array which will have its indexes compared with a number
     * @return Whether or not the num parameter is found in the nums array.
     */
    private boolean alreadyUsed(int num, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                return true;
            }
        }
        
        return false;
    }
}
