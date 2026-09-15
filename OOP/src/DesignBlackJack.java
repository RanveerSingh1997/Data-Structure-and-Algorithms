import java.util.*;

/**
 * ============================================================================
 * Practice Template: Blackjack Card Game System (LLD)
 * Difficulty: Medium | Target Companies: Bloomberg, Amazon, Meta, Google
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Design a standard casino Blackjack (21) card game between a Player and Dealer.
 * The game uses a standard 52-card deck (or multi-deck shoe).
 * The goal is to get a hand score as close to 21 as possible without busting (> 21).
 *
 * 📥 REQUIREMENTS:
 * 1. Card Modeling: Suit (Hearts, Diamonds, Clubs, Spades) and Rank (2-10, J, Q, K, A).
 * 2. Hand Scoring & Dynamic Ace:
 *    - Number cards = Face value; J, Q, K = 10.
 *    - Ace counts as 11, but automatically downgrades to 1 if the hand total exceeds 21.
 * 3. Game Flow:
 *    - Deal 2 cards to Player and 2 to Dealer (1 hole card face down).
 *    - Player can HIT (draw) or STAND (stop).
 *    - If Player busts (> 21), dealer wins immediately.
 *    - Dealer must hit until score >= 17 (Soft 17 rule).
 * 4. Winner Evaluation: Natural Blackjack, Dealer Bust, Player Bust, Higher score, Push (tie).
 *
 * 💡 INTERVIEW HINTS:
 * - Count aces separately during score calculation: iterate cards, sum values (Ace = 11),
 *   then while score > 21 and aceCount > 0: score -= 10, aceCount--.
 */
public class DesignBlackJack {

    // =========================================================================
    // 1. ENUMS & VALUE OBJECTS
    // =========================================================================

    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
        NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

        private final int value;
        Rank(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    public enum GameResult {
        PLAYER_BLACKJACK, DEALER_BLACKJACK, PLAYER_BUST, DEALER_BUST,
        PLAYER_WIN, DEALER_WIN, PUSH
    }

    // =========================================================================
    // 2. CARD & DECK
    // =========================================================================

    public static class Card {
        private final Suit suit;
        private final Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public Suit getSuit() { return suit; }
        public Rank getRank() { return rank; }
        public int getValue() { return rank.getValue(); }

        @Override
        public String toString() { return rank + " of " + suit; }
    }

    public static class Deck {
        private final List<Card> cards = new ArrayList<>();

        public Deck(int numberOfDecks) {
            for (int d = 0; d < numberOfDecks; d++) {
                for (Suit suit : Suit.values()) {
                    for (Rank rank : Rank.values()) {
                        cards.add(new Card(suit, rank));
                    }
                }
            }
            shuffle();
        }

        public Deck() {
            this(1);
        }

        public void shuffle() {
            // TODO: Shuffle cards collection
            Collections.shuffle(cards);
        }

        public Card dealCard() {
            // TODO: Remove and return top card from deck
            if (cards.isEmpty()) throw new IllegalStateException("Deck is empty");
            return cards.remove(cards.size() - 1);
        }

        public int remainingCards() { return cards.size(); }
    }

    // =========================================================================
    // 3. BLACKJACK HAND (DYNAMIC ACE SCORING)
    // =========================================================================

    public static class Hand {
        protected final List<Card> cards = new ArrayList<>();

        public void addCard(Card card) {
            cards.add(card);
        }

        public List<Card> getCards() {
            return Collections.unmodifiableList(cards);
        }

        /**
         * Calculates total score with flexible Ace (11 or 1).
         */
        public int getScore() {
            // TODO: Implement Ace dynamic calculation:
            // 1. Sum up card values, treating Ace as 11 and tracking aceCount.
            // 2. While score > 21 and aceCount > 0: score -= 10, aceCount--.
            // 3. Return final score.
            return 0;
        }

        public boolean isBusted() {
            return getScore() > 21;
        }

        public boolean isBlackjack() {
            // TODO: Return true if hand has 2 cards and score is 21
            return cards.size() == 2 && getScore() == 21;
        }

        @Override
        public String toString() { return cards + " (Score: " + getScore() + ")"; }
    }

    // =========================================================================
    // 4. PARTICIPANTS: PLAYER & DEALER
    // =========================================================================

    public static abstract class Participant {
        private final String name;
        protected final Hand hand = new Hand();

        public Participant(String name) { this.name = name; }
        public String getName() { return name; }
        public Hand getHand() { return hand; }
        public void receiveCard(Card card) { hand.addCard(card); }
        public int getScore() { return hand.getScore(); }
    }

    public static class Player extends Participant {
        private double chips;

        public Player(String name, double chips) {
            super(name);
            this.chips = chips;
        }

        public double getChips() { return chips; }
        public void addChips(double amount) { this.chips += amount; }
        public void deductChips(double amount) { this.chips -= amount; }
    }

    public static class Dealer extends Participant {
        public Dealer() { super("Dealer"); }

        public boolean mustHit() {
            // TODO: Return true if dealer's score is < 17 (Soft 17 rule)
            return getScore() < 17;
        }
    }

    // =========================================================================
    // 5. GAME CONTROLLER
    // =========================================================================

    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private boolean playerTurnFinished;

    public DesignBlackJack(Player player) {
        this.deck = new Deck(6);
        this.player = player;
        this.dealer = new Dealer();
        this.playerTurnFinished = false;
    }

    public void startRound() {
        player.getHand().cards.clear();
        dealer.getHand().cards.clear();
        playerTurnFinished = false;

        player.receiveCard(deck.dealCard());
        dealer.receiveCard(deck.dealCard());
        player.receiveCard(deck.dealCard());
        dealer.receiveCard(deck.dealCard());
    }

    public Card playerHit() {
        // TODO: Deal card to player; if busted, mark playerTurnFinished = true
        return null;
    }

    public void playerStand() {
        // TODO: End player turn; trigger dealer turn (dealer hits while mustHit())
        playerTurnFinished = true;
    }

    public GameResult evaluateWinner() {
        // TODO: Compare player and dealer scores and return GameResult:
        // - Natural Blackjack
        // - Busts (PLAYER_BUST, DEALER_BUST)
        // - Higher score wins (PLAYER_WIN, DEALER_WIN, or PUSH)
        return null;
    }

    public Player getPlayer() { return player; }
    public Dealer getDealer() { return dealer; }

    // =========================================================================
    // 6. VERIFICATION TEST HARNESS (Run to test your code!)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Testing: Blackjack Card Game System ===");

        // Test 1: Dynamic Ace Calculation
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));   // 11
        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN)); // 11 + 7 = 18

        if (hand.getScore() == 18) {
            System.out.println("  [PASS] Test 1a: Ace counted as 11 (Hand: Ace + 7 = 18)");
        } else {
            System.out.println("  [TODO] Test 1a: getScore() for Ace not implemented yet.");
        }

        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE));  // 18 + 5 = 23 -> Ace downgrades to 1 -> 13
        if (hand.getScore() == 13) {
            System.out.println("  [PASS] Test 1b: Ace automatically downgraded to 1 (Ace + 7 + 5 = 13)");
        } else {
            System.out.println("  [TODO] Test 1b: Dynamic Ace downgrade not implemented yet.");
        }

        // Test 2: Natural Blackjack recognition
        Hand bjHand = new Hand();
        bjHand.addCard(new Card(Suit.SPADES, Rank.ACE));
        bjHand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        if (bjHand.isBlackjack()) {
            System.out.println("  [PASS] Test 2: Natural Blackjack recognized (Ace + King = 21)");
        } else {
            System.out.println("  [TODO] Test 2: isBlackjack() not passing yet.");
        }
    }
}
