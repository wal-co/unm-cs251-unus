/**
 * Corey Walker
 * Wild cards can be matched to any color
 * -This version can only be instantiated as a pure wild
 *  in order to get a wild draw 4, make a DrawN card with color "WILD"
 * -This card matches with everything and does nothing
 */
public class Wild extends Card {

    public Wild(Color cardColor) {
        super(cardColor);
    }

    @Override
    public void doAction(Game game) {}

    @Override
    public boolean matchValue(Card other) { return true; }

    @Override
    public String strRep() {
        return "W";
    }
}
