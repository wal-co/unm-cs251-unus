import java.util.ArrayList;

/**
 * Corey Walker
 * Class for skip cards
 * - skip cards only have a color
 * - when played, they can skip either the next player or a specific player
 * - Skips can be stacked on a specific player if the other players are feeling evil
 */
public final class Skip extends Card {

    public Skip(Color cardColor) {
        super(cardColor);
    }

    /**
     * Skip can skip any player except for the player who played it.
     * This function accomplishes the following:
     * - Prompts the user who they would like to skip with the following message:
     *   "Who would you like to skip? (n)ext or (s)pecific user?"
     * - If the answer is "n" then the next player is skipped
     * - If the answer is "s" then a specific player is skipped
     *   - The user must then be prompted with the following prompt:
     *     "Please choose from the following numbers: $playerNumbers"
     *     where playerNumbers are all the indices of players other than the current player separated by spaces
     *   - You must loop until they give a valid index, if they fail output the following message:
     *     "$playerNumber is not valid. $playersToChoose"
     *     where playerNumber is the number they input
     *   - If they give an index that is not a number then output the following message and loop again:
     *     "$n not an int, please try again."
     *     where n is the index they input
     * - You must loop until they give you a valid command, if they fail output the following message:
     *   "$answer is not a recognized command, please try again."
     * @param game the instance of the game being played
     */
    @Override
    public void doAction(Game game) {
        boolean skipSuccessful = false;
        String userInput;
        do {
            userInput = game.interact("Who would you like to skip? (n)ext or (s)pecific user?");
            if (userInput.equals("n")) {
                game.getPlayers().skip(game.getPlayers().getCurIndex() + game.getPlayers().getDir());
                skipSuccessful = true;
            } else if (userInput.equals("s")) {
                StringBuilder validIndexes = new StringBuilder();
                ArrayList<Integer> indices = new ArrayList<>();
                for (int i = 0; i < game.getNumPlayers(); i++) {
                    if (game.getPlayers().getCurIndex() != i) {
                        validIndexes.append(i).append(" ");
                        indices.add(i);
                    }
                }
                userInput = game.interact("Please choose from the following numbers: " + validIndexes);
                try {
                    int index = Integer.parseInt(userInput);
                    if (indices.contains(index)) {
                        game.getPlayers().skip(index);
                        skipSuccessful = true;
                    } else {
                        System.out.println(index + " is not valid. " + validIndexes);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(userInput + " not an int, please try again.");
                }
                if (!skipSuccessful) {
                    System.out.println(userInput + " is not a recognized command, please try again.");
                }

            }
        } while (!skipSuccessful);
    }

    /**
     * Check if the top card of the playArea is a skip card
     * @param other top card of playArea
     * @return true if top card is a skip, false otherwise
     */
    @Override
    public boolean matchValue(Card other) { return other instanceof Skip; }

    @Override
    public String strRep() {
        return "S";
    }
}
