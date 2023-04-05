import java.util.List;

/**
 * Corey Walker
 * An UnusIterator iterates over a list of type Player
 * The iterator handles
 * - game direction (forward or backward)
 * - who the current player is
 * - who the next player is based on direction
 * - the list of skips played on each player
 */
public final class UnusIterator<T> {
    private final List<T> ls;
    private int curIndex;
    private final int len;

    private Direction dir = Direction.FORWARD;

    private final List<Integer> skips;

    /**
     * Enum for the direction of play
     * - play can be moving forward from the first player to the second player, etc
     * - play can be moving backward from the first player to the last player, etc
     */
    private enum Direction {
        FORWARD(1), BACKWARD(-1);

        private final int adder;

        Direction(int adder) {
            this.adder = adder;
        }

        /**
         * getter for the index manipulation value of the direction
         * @return -1 if going backwards, 1 if going forwards
         */
        public int getAdder() {
            return adder;
        }

        /**
         * switches the direction enum to the opposite direction
         * @return forward if we were going backwards, backwards if we were going forward
         */
        public Direction flip() {
            if (this == FORWARD) {
                return BACKWARD;
            }
            else {
                return FORWARD;
            }
        }
    }

    /**
     * constructor for the iterator
     * @param ls list of players to iterate over
     */
    public UnusIterator(List<T> ls) {
        this.ls = ls;
        this.curIndex = 0;
        this.len = ls.size();
        this.skips = Utils.repeat(this.len, 0);
    }

    /**
     * getter for the direction's index manipulation value
     * @return +1 if moving forward, -1 if moving backwards
     */
    protected int getDir() {return dir.adder;}

    /**
     * Find the next valid player index.
     * This is fairly complicated due to being able
     * to skip any player and being able to stack skips
     * on a given player. This function does the following:
     * - Loops until a valid next player is found
     * - Does not modify the curIndex
     * - Instead manipulate its own internal index
     * - This internal index is initially set to the curIndex + the directional adder
     * - It is then checked if it has gone out of bounds
     *   - If it has gone negative then it should be set to the max value
     *   - If it is above the max value then it should be set to 0
     * - This index is then looked up in the skips list
     *   - If this value is anything but 0 then that means this index must be skipped.
     *   - If decrement is true then the value in the skips list for this index should be decremented by 1
     *   - If it is 0 then you have found the next index and the loop should be exited.
     *   - The internal index should then be returned
     * @return Index of next player in the order respecting skips and reverses
     */
    public int findNextIndex(boolean decrement) {
        boolean foundNext = false;
        int nextPlayer = curIndex + dir.getAdder();
        do{
            // bounds checking
            if (nextPlayer < 0){ nextPlayer = ls.size() - 1; }
            if (nextPlayer > ls.size() - 1){ nextPlayer = 0; }

            if (skips.get(nextPlayer) != 0){
                if (decrement) { skips.set(nextPlayer, skips.get(nextPlayer) - 1); }
                nextPlayer += dir.getAdder();
            } else {
                foundNext = true;
            }
        } while (!foundNext);
        return nextPlayer;
    }

    /**
     * getter for the current player
     * @return Player at the current index
     */
    public T current() {
        return ls.get(curIndex);
    }

    /**
     * getter for the current player's index
     * @return int value of the current player's index
     */
    public int getCurIndex() {
        return curIndex;
    }

    /**
     * calls findNextIndex with the decrement boolean set to true
     * moves the iterator to the next player
     */
    public void next() {
        curIndex = findNextIndex(true);
    }

    /**
     * checks who the next player is by calling findNextIndex with the decrement boolean set to false
     * @return player whose turn is next
     */
    public T peekNext() {
        return ls.get(findNextIndex(false));
    }

    /**
     * Simply calls flip on the dir member variable and
     * overwrites dir with this value
     */
    public void reverse() {
        dir = dir.flip();
    }

    /**
     * Increments the nth element of skips by 1
     * @param n Index in skips list to increment
     */
    public void skip(int n) {
        // bounds checking on skips List
        if (n < 0){
            n = skips.size();
        }
        if (n >= skips.size()){
            n = 0;
        }
        int currentSkips = skips.get(n);
        skips.set(n, currentSkips + 1);

    }
}
