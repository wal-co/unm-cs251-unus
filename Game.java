import java.util.*;

/**
 * Corey Walker
 * Class for the Game object
 * - has a Scanner for IO
 * - has an UnusIterator
 * - has a number of players in the game
 * - has a deck of cards
 * - has a deque of type Card for the play area
 */

public final class Game {
    private final Scanner io;
    private final UnusIterator<Player> players;
    private final int numPlayers;
    private final Deck deck;
    private final Deque<Card> playArea;

    /**
     * Constructs all the data necessary to run a game.
     * This includes the following:
     * - Create a scanner of System.in and saves it into io
     * - Creates a deck using the createDeck function and saves it into deck.
     * - Creates a list of players with length given by numPlayers
     * - Has each player draw 5 cards
     * - Creates a UnusIterator with the aforementioned player list
     * - Assigns the parameter numPlayers to the instance variable numPlayers
     * - Initializes playArea with a new ArrayDeque
     * @param numPlayers the number of players in the game
     */
    public Game(int numPlayers) {
        this.io = new Scanner(System.in);
        this.deck = createDeck();
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++){
            playerList.add(new Player("Player "+ (i), this ));
            }
        this.players = new UnusIterator<>(playerList);
        // draws removed for testing win condition
        for (Player player : playerList){
            player.drawCards(5);
        }
        this.numPlayers = numPlayers;
        this.playArea = new ArrayDeque<>();

    }

    /**
     * The main game loop function.
     * Does the following:
     * - Loops until the curPlayer's hand is empty
     *   - When this is the case the curPlayer has won the game.
     *   - It then prints: "$curPlayer won!"
     * - The current player is received from the UnusIterator
     * - The player then takes their turn
     * - The UnusIterator is then moved to the next player
     */
    public void start() {
        Player curPlayer;
        System.out.println("Welcome to Unus!\n");
        do {
            curPlayer = players.current();
            curPlayer.takeTurn();
            players.next();
        } while (!curPlayer.emptyHand());
        System.out.println(curPlayer + " won!");

    }

    /**
     * prints information to the console and gets their response from scanner
     * @param toUser question to ask the user
     * @return String user input
     */
    public String interact(String toUser) {
        System.out.println(toUser);
        return io.nextLine();
    }

    /**
     * getter for UnusIterator for Players
     * @return UnusIterator of type Player
     */
    public UnusIterator<Player> getPlayers() {
        return players;
    }

    /**
     * getter for the number of players
     * @return int of the number of players
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * getter for the number of cards in the play area
     * @return int size of the play area Deque
     */
    public int getNumberOfCardsInPlay() {
        return playArea.size();
    }

    /**
     * getter for the Deck
     * @return Deck of cards
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * getter for the top card on the play area
     * @return top card of the play area Deque
     */
    public Card getTopCard() {
        if (playArea.isEmpty()) {
            return new None();
        }

        return playArea.getFirst();
    }

    /**
     * puts the player's chosen card at the top of the play area deque
     * @param card user's chosen card
     */
    public void playCard(Card card) {
        playArea.addFirst(card);
    }

    /**
     * Takes the cards that have been played and adds them back into the deck
     * shuffles the deck
     */
    public void shufflePlayAreaIntoDeck() {
        deck.addCards(playArea);
        deck.shuffleDeck();
    }

    /**
     * Creates the standard 108 card Unus deck.
     * The deck contains the following cards:
     * - 19 red cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 blue cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 green cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 yellow cards
     *   - 1 zero
     *   - 2 of every number
     * - 8 skip cards - two of each color
     * - 8 reverse cards - two of each color
     * - 8 draw 2 cards - two of each color
     * - 4 wild cards
     * - 4 wild draw 4 cards
     * @return A standard Unus deck of 108 cards
     */
    private Deck createDeck() {
        List<Card> cards = new LinkedList<>();
        // add each color card with value zero
        cards.add(new Numbers(Card.Color.BLUE, 0));
        cards.add(new Numbers(Card.Color.RED, 0));
        cards.add(new Numbers(Card.Color.GREEN, 0));
        cards.add(new Numbers(Card.Color.YELLOW, 0));
        // add two of each color with value i
        for (int i = 1; i < 10; i++){
            for (int j = 0; j < 2; j++) {
                cards.add(new Numbers(Card.Color.BLUE, i));
                cards.add(new Numbers(Card.Color.RED, i));
                cards.add(new Numbers(Card.Color.GREEN, i));
                cards.add(new Numbers(Card.Color.YELLOW, i));
            }
        }
        // add two of each color reverse, skip, and draw 2 cards
        for (int i = 0; i < 2; i++) {
            cards.add(new Reverse(Card.Color.BLUE));
            cards.add(new Reverse(Card.Color.RED));
            cards.add(new Reverse(Card.Color.GREEN));
            cards.add(new Reverse(Card.Color.YELLOW));
            cards.add(new Skip(Card.Color.BLUE));
            cards.add(new Skip(Card.Color.RED));
            cards.add(new Skip(Card.Color.GREEN));
            cards.add(new Skip(Card.Color.YELLOW));
            cards.add(new DrawN(Card.Color.BLUE, 2));
            cards.add(new DrawN(Card.Color.RED, 2));
            cards.add(new DrawN(Card.Color.GREEN, 2));
            cards.add(new DrawN(Card.Color.YELLOW, 2));
        }
        // add 4 wild and wild draw 4 cards
        for (int i = 0; i < 4; i++){
            cards.add(new Wild(Card.Color.WILD));
            cards.add(new DrawN(Card.Color.WILD, 4));
        }
        return new Deck(cards);
    }
}
