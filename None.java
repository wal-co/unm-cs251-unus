/**
 * Corey Walker
 * A None card is a card that is returned when the PlayerArea is empty.
 * It has color WILD so that it can match to anything, has no string rep, and does nothing
 */
public class None extends Card {

    public None() {
        super(Color.WILD);
    }

    @Override
    public void doAction(Game game) {}

    @Override
    public boolean matchValue(Card other) {
        return true;
    }

    @Override
    public String strRep() {
        return "";
    }
}
