import java.util.ArrayList;

/**
 * Corey Walker
 * Every individual player has a name, the game instance they are playing on, and a hand of cards
 */
public class Player {
    private final String name;
    private final Game game;
    public final Hand hand;


    /**
     * Constructor for the player
     * -sets the players name to name
     * -places them in the game
     * @param name string for the players name
     * @param game the game that is being played
     */
    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
        this.hand = new Hand(new ArrayList<>());

    }

    /**
     * This function does the following:
     * - Attempts to draw num number of cards
     * - If a EmptyDeckException is caught then the play area
     *   must be shuffled into the deck. Note this a function of game class
     * - Adds each drawn card to hand
     * @param num Number of cards to be drawn
     */
    public void drawCards(int num) {
        int cardsDrawn = 0;
        while (cardsDrawn != num) {
            try {
                hand.addCard(game.getDeck().drawCard());
                cardsDrawn++;
            } catch (Deck.EmptyDeckException e) {
                game.shufflePlayAreaIntoDeck();
            }
        }

    }

    /**
     * Performs IO to figure out what moves the user
     * wants to make. It does this as follows:
     * - Loops until the user has successfully played a card
     * - Prints out "Play area:\n"
     * - Prints out the top card
     * - Checks to see if the hand has any matches against the top card
     *   - If it does not then print: "Your hand had no matches, a card was drawn."
     *   - Then draw 1 card
     * - Then prints "Hand:\n"
     * - Then prints out the hand
     * - If the hand still has no matches then print: "Your hand still has no matches your turn is being passed"
     *   and ends the turn
     * - Otherwise it asks the user: "Which card would you like to play?" using the game::interact function
     * - The code loops until the user successfully answers this question, the three criteria are:
     *   - A valid int, if not print:
     *     "$cardNumStr is not a valid integer, please try again."
     *     where cardNumStr is the user input
     *   - A valid match, if not print:
     *     "Card $cardNumStr cannot currently be played, please try again."
     *     where cardNumStr is the user input
     *   - A valid index, if not print:
     *     "$cardNumStr is not a valid index, please try again."
     */
    public void takeTurn() {
        boolean cardPlayed = false;
        boolean cardDrawn = false;
        System.out.println("Play area:\n");
        System.out.println(game.getTopCard());

        while (!cardPlayed) {
            // draw a card if the user has no matches and hasn't drawn a card
            if (hand.noMatches(game.getTopCard()) && !cardDrawn && !(game.getTopCard() instanceof None)) {
                System.out.prntln("Your hand had no matches, a card was drawn.");
                drawCards(1);
                cardDrawn = true;
            }
            // if the user still has no matches after drawing a card, show the hand and pass to next player
            else if (hand.noMatches(game.getTopCard()) && !(game.getTopCard() instanceof None)) {
                System.out.println(game.getPlayers().current() + " Hand: \n");
                System.out.println(hand);
                System.out.println("Your hand still has no matches your turn is being passed");
                break;
            }
            // otherwise, let the user play
            else {
                System.out.println(game.getPlayers().current() + " Hand: \n");
                System.out.println(hand);
                String userInput = game.interact("Which card would you like to play?");
                try {
                    hand.playCard(game, Integer.parseInt(userInput));
                    cardPlayed = true;
                } catch (NumberFormatException e) {
                    System.out.println(userInput + " is not a valid integer, please try again.");
                } catch (IndexOutOfBoundsException i) {
                    System.out.println(userInput + " is not a valid index, please try again.");
                } catch (Card.CannotPlayCardException c) {
                    System.out.println("Card " + userInput + " cannot currently be played, please try again.");
                }
            }
        }
    }


    /**
     * checks if the players hand is empty
     * @return boolean true if the players hand is empty false otherwise
     */
    public boolean emptyHand() {
        return hand.numCardsRemaining() == 0;
    }

    @Override
    public String toString() { return name; }
}
