package sina.shariati.card;

import sina.shariati.enums.CardDeckEnum;
import sina.shariati.enums.CardNumberEnum;

import java.util.Objects;

/**
 * The type Card abstract.
 */
public abstract class Card {
    private final CardNumberEnum number;
    private final CardDeckEnum deck;
    private final int score;

    /**
     * Instantiates a new abstract Card.
     *
     * @param number the number
     * @param deck   the deck
     * @param score  the score
     */
    public Card(CardNumberEnum number, CardDeckEnum deck, int score) {
        this.number = number;
        this.deck = deck;
        this.score = score;
    }

    /**
     * Get card number.
     *
     * @return the number
     */
    public CardNumberEnum getNumber() {
        return number;
    }

    /**
     * Gets card deck.
     *
     * @return the deck
     */
    public CardDeckEnum getDeck() {
        return deck;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return score == card.score && number == card.number && deck == card.deck;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, deck, score);
    }
}
