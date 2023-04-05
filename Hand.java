import java.util.Arrays;
import java.util.List;

/**
 * Corey Walker
 * Hand is a list of cards that is unique to each player currently in the game.
 * When a player's hand is empty they win the game.
 */
public final class Hand {
    private final List<Card> cards;

    /**
     * constructor for the player's hand
     * @param cards List of cards that are in the player's hand
     */
    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * adds the card to the list of cards in the player's hand
     * @param card Card to be added
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * This function simply gets the number of cards
     * left in the current players hand
     * @return int number of cards in the player's hand
     */
    public int numCardsRemaining() {
        return cards.size();
    }

    /**
     * This function does the following:
     * - Gets the current card using index
     * - Plays the current card
     * - Removes the current card from cards
     * @param game State of the game
     * @param index Index of desired card to play in cards
     * @throws Card.CannotPlayCardException
     */
    public void playCard(Game game, int index) throws Card.CannotPlayCardException {
            Card toPlay = cards.get(index);
            toPlay.play(game);
            game.playCard(toPlay);
            cards.remove(toPlay);

    }

    /**
     * This function checks to see if your hand has any
     * matches to the given card
     * @param topCard Card currently in play
     * @return true if match is found and false otherwise
     */
    public boolean noMatches(Card topCard) {
        for (Card card : cards){
            if (card.match(topCard)){ return false; }
        }
        return true;
    }

    /**
     * Prints out your current hand's cards horizontally.
     * This is accomplished by calling the Card::prettyPrint
     * function on all cards in your hands. The prettyPrint function
     * transforms each card into a List<String> where each element of
     * the list represents a line of output for that card. You must then
     * Append all the first lines together separated by a space
     * then all the second lines separated by spaces
     * etc.
     * You must then put an index label under each card that
     * is centered between each card.
     * Then put a new line below
     * Then return the String you constructed
     * For example if your hand consisted of a red reverse and a blue skip
     * then your output would look like:
     * /-------\ /-------\
     * | R |Rev| | B | S |
     * \-------/ \-------/
     *     0         1
     *
     * @return String representation of the users hand
     */
    @Override
    public String toString() {
        StringBuilder hand = new StringBuilder();
        int index = 0;
        for (Card card : cards) {
            List<String> cardString = card.prettyPrint();
            hand.append(cardString.get(0)).append(" ");
        }
        hand.append("\n");
        for (Card card : cards) {
            List<String> cardString = card.prettyPrint();
            hand.append(cardString.get(1)).append(" ");
        }
        hand.append("\n");
        for (Card card : cards) {
            List<String> cardString = card.prettyPrint();
            hand.append(cardString.get(2)).append(" ");
        }
        hand.append("\n");
        for (Card card : cards) {
            hand.append("    ").append(index).append("     ");
            index++;
        }

        return hand.toString();
    }

    // Code you can use to test your implementation
    public static void main(String[] args) {
        Hand hand = new Hand(Arrays.asList(new Reverse(Card.Color.RED),
                                           new Skip(Card.Color.BLUE)));
        String handStr = hand.toString();
        System.out.println(handStr);
    }
}
