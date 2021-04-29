package sina.shariati.card;

import sina.shariati.enums.CardDeckEnum;
import sina.shariati.enums.CardNumberEnum;

public class NormalCard extends Card {
    /**
     * Instantiates a new normal Card.
     *
     * @param number the number
     * @param deck   the deck
     * @param score  the score
     */
    public NormalCard(CardNumberEnum number, CardDeckEnum deck, int score) {
        super(number, deck, score);
    }
}
