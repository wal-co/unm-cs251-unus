/**
 * Corey Walker
 * A Numbers card can be Red, Green, Blue, or Yellow. They:
 * -have a value between 0 and 9, inclusive
 * -match to other cards of the same color or value
 * -do nothing
 */
public class Numbers extends Card {
    private final int n;

    public Numbers(Color color, int n) {
        super(color);

        if (!(n >= 0 && n <= 9)) {
            throw new IllegalArgumentException(n + " must be between [0,9]");
        }

        this.n = n;
    }

    /**
     * getter for the number on the card
     * @return int value of the card
     */
    public int getN() {
        return n;
    }

    @Override
    public void doAction(Game game) {}

    /**
     * checks if the number on this card matches with the number on another card
     * @param other Card on top of the playArea
     * @return true if the numbers match, false otherwise
     */
    @Override
    public boolean matchValue(Card other) {
        if (other instanceof Numbers o) { return getN() == o.getN(); }
        else { return false; }
    }

    @Override
    public String strRep() { return Integer.toString(n); }
}