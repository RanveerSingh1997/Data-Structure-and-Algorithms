# ♠️♥️ Solution & Architecture: Blackjack Card Game System

**Problem**: Design a casino Blackjack (21) card game with flexible Ace scoring and dealer rules.  
**Difficulty**: Medium | **Target Companies**: Bloomberg, Amazon, Meta, Google  
**Practice Template**: [`OOP/src/DesignBlackJack.java`](../src/DesignBlackJack.java)

---

## 1. Requirements & Clarifications

### Functional Requirements:
1. **Card Modeling**: 4 Suits (`HEARTS`, `DIAMONDS`, `CLUBS`, `SPADES`) $\times$ 13 Ranks (`2`–`10`, `J`, `Q`, `K`, `A`).
2. **Shoe / Multi-Deck**: Combine 1 to 6 standard decks and shuffle.
3. **Dynamic Ace Calculation**:
   - Number cards = Face value; Face cards (`J`, `Q`, `K`) = 10.
   - **Ace flexibility**: Count as 11 by default, but automatically downgrade to 1 if the hand total exceeds 21.
4. **Player & Dealer Turns**:
   - Player can `HIT` or `STAND`.
   - Dealer must hit on scores $< 17$, and must stand on scores $\ge 17$ (Soft 17 rule).
5. **Outcome Determination**: Player Blackjack, Dealer Bust, Player Bust, Higher score wins, or Push.

---

## 2. Dynamic Ace Scoring Algorithm

A common mistake is treating Ace as either 1 or 11 upon dealing. Because subsequent cards can turn a safe hand into a bust, **score calculation must be dynamic**:

```java
public int getScore() {
    int score = 0;
    int aceCount = 0;

    // Step 1: Initial sum treating Ace as 11
    for (Card card : cards) {
        score += card.getValue();
        if (card.getRank() == Rank.ACE) {
            aceCount++;
        }
    }

    // Step 2: Soft downgrade Aces from 11 to 1 (subtract 10) while total > 21
    while (score > 21 && aceCount > 0) {
        score -= 10;
        aceCount--;
    }

    return score;
}
```

---

## 3. Dealer Rule & Winner Evaluation

```java
public class Dealer extends Participant {
    public boolean mustHit() {
        return getScore() < 17;
    }
}

public GameResult evaluateWinner() {
    if (player.getHand().isBlackjack()) {
        return dealer.getHand().isBlackjack() ? GameResult.PUSH : GameResult.PLAYER_BLACKJACK;
    }
    if (player.getHand().isBusted()) return GameResult.PLAYER_BUST;
    if (dealer.getHand().isBusted()) return GameResult.DEALER_BUST;

    int pScore = player.getScore();
    int dScore = dealer.getScore();

    if (pScore > dScore) return GameResult.PLAYER_WIN;
    if (dScore > pScore) return GameResult.DEALER_WIN;
    return GameResult.PUSH;
}
```

---

## 4. Key Interview Pitfalls

1. **Multiple Aces**: A hand with `[Ace, Ace, 9]` has score $11 + 1 + 9 = 21$, NOT $11 + 11 + 9 = 31$ (busted) or $1 + 1 + 9 = 11$.
2. **Dealer Bust Priority**: If the player busts first, the dealer wins immediately, even if the dealer would have busted later.
