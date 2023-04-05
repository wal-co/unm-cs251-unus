/**
 * Corey Walker
 * Class for reverse cards
 * - reverse cards only have a color
 * - when played, they switch the order of the players according to the Direction enum
 */

public final class Reverse extends Card {
    public Reverse(Color cardColor) {
        super(cardColor);
    }

    /**
     * This function calls the reverse function
     * on the UnusIterator
     * @param game the game currently being played
     */
    @Override
    public void doAction(Game game) {
        game.getPlayers().reverse();
    }

    /**
     * checks the value of the card on top of the play area against the value of this card
     * @param other card on top of play area
     * @return boolean true if the top card on the play area is a reverse, false otherwise
     */
    @Override
    public boolean matchValue(Card other) {
        return other instanceof Reverse;
    }

    @Override
    public String strRep() {
        return "Rev";
    }
}
