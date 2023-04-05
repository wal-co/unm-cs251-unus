import java.util.LinkedList;
import java.util.List;

/**
 * Corey Walker
 * Abstract class all cards are based on.
 * -Color enum: cards can be Red, Yellow, Blue, Green, or Wild
 * -StrRep: the representation of the color to be printed on the card
 */
public abstract class Card {
    private final Color cardColor;

    public enum Color {
        RED("R"), YELLOW("Y"), BLUE("B"), GREEN("G"), WILD("W");

        private final String strRep;

        Color(String strRep) {
            this.strRep = strRep;
        }

        public boolean match(Color other) {
            return this == WILD || other == WILD || this == other;
        }

        @Override
        public String toString() {
            return strRep;
        }
    }

    /**
     * constructor for a Card
     * @param cardColor color from the enum list for the card
     */
    public Card(Color cardColor) {
        this.cardColor = cardColor;
    }

    /**
     * getter for card color
     * @return Color color of card
     */
    public Color getCardColor() {
        return cardColor;
    }

    /**
     * Class for exception to be thrown when a card cannot be played for whatever reason
     */
    public static class CannotPlayCardException extends Exception {}

    public abstract void doAction(Game game);
    public abstract boolean matchValue(Card other);
    public abstract String strRep();

    /**
     * Matches two cards according to Unus rules which state that two cards
     * match if they have the same color or the same symbol/numeric value
     * @param other Card to match this card against
     * @return true if the two cards match and false otherwise
     */
    public boolean match(Card other) {
        return this.getCardColor().match(other.getCardColor()) || this.matchValue(other);
    }

    /**
     * Checks if the card trying to be played matches the top card of the play area
     * - if it does not, throw exception
     * - if it does, call the card's doAction
     * - play the card to the play area
     * @param game the game currently being played
     * @throws CannotPlayCardException when the user tries to play a card that cannot be played
     */
    public void play(Game game) throws CannotPlayCardException {
        if (!match(game.getTopCard())) {
            throw new CannotPlayCardException();
        }

        doAction(game);
        game.playCard(this);
    }

    /**
     * This function does the following:
     * - Creates a List<String>
     * - Adds the top part of the card to this list which is the following string:
     *   "/-------\\"
     * - Then constructs the middle of the card which depends on the length of the strRep()
     *   of the current card. If it is length 3 the following string should be constructed and added to the list:
     *   "| $getCardColor() |$strRep()|"
     *   Otherwise if it is length 1 then the following string should be constructed and added to the list:
     *   "| $getCardColor() | $strRep() |"
     * - Then the bottom part of the card is added to the list which is given by the following string:
     *   "\\-------/"
     * - This list is then returned
     * @return List of lines of this card
     */
    public List<String> prettyPrint() {
        List<String> cardList = new LinkedList<>();
        cardList.add("/-------\\");
        if (strRep().length() == 3){
            cardList.add("| " + getCardColor()  + " |" + strRep() + "|");
        } else if (strRep().length() == 1) {
            cardList.add("| " + getCardColor()  + " | " + strRep() + " |");
        }
        cardList.add("\\-------/");

        return cardList;
    }

    /**
     * Creates a new StringBuilder object
     * Uses prettyPrint to build a string of the card's representation with a new line at the end of each line
     * @return String representation of the card
     */
    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();

        for (String line : prettyPrint()) {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }
}
