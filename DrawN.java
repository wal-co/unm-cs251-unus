/**
 * Corey Walker
 * A DrawN card is a card of any color that will force the next player to draw a certain number of cards
 * - DrawN can have a draw value of either +2 or +4
 * - Draw +2 can have any color, but Draw +4 must be wild
 */
public class DrawN extends Card {
    private final int n;

    public DrawN(Color cardColor, int n) {
        super(cardColor);
        this.n = n;
    }

    /**
     * getter for the value of cards to be drawn
     * @return int number of cards the next player must draw
     */
    public int getN() {
        return n;
    }

    /**
     * Makes the next player draw n cards
     * @param game Current game state
     */
    @Override
    public void doAction(Game game) {
       game.getPlayers().peekNext().drawCards(n);
    }

    /**
     * Checks if other has the same value as this
     * @param other Other card to match against
     * @return true if other is an instanceof DrawN and our n equals their n, false otherwise
     */
    @Override
    public boolean matchValue(Card other) {
        if (other instanceof  DrawN && ((DrawN) other).getN() == this.n){ return true; }
        else {return false; }
    }

    @Override
    public String strRep() {
        return "D+" + n;
    }
}
