
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/**
 * Corey Walker
 * A Deck has a list of 108 Unus cards and can be
 * - shuffled
 * - drawn from
 * - added to
 */

public final class Deck {
    private final List<Card> cards;

    /**
     * constructor for the Deck
     * adds the list of cards to the deck and then shuffles it
     * @param cards List of type Card
     */
    public Deck(List<Card> cards) {
        this.cards = cards;
        shuffleDeck();
    }

    /**
     * Exception for when the Deck is Empty
     */
    public static class EmptyDeckException extends Exception {}

    /**
     * This function does the following:
     * - Checks if cards is empty
     *   - If it is then throw a new EmptyDeckException
     *   - If not then return and remove the first card in cards
     * @return The top card from the deck
     * @throws EmptyDeckException when the deck is empty.
     */
    public Card drawCard() throws EmptyDeckException {
        if (cards.isEmpty()){
            throw new EmptyDeckException();
        } else {
            Card toDraw = cards.get(0);
            cards.remove(toDraw);
            return toDraw;
        }

    }

    /**
     * shuffles the Deck
     */
    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }

    /**
     * add a collection of type card onto the deck and shuffle it
     * @param cards Collection of type Card
     */
    public void addCards(Collection<Card> cards) {
        this.cards.addAll(cards);
        shuffleDeck();
    }
}
