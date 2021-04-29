package sina.shariati.enums;

import java.util.HashMap;

/**
 * A singleton enum like class for mapping every card number to it's score
 */
public class CardScoreManger {
    private final HashMap<CardNumberEnum, Integer> numberToScoreEnum = new HashMap<>();

    /**
     * Instantiates a new Card score manger.
     */
    public CardScoreManger() {
        this.numberToScoreEnum.put(CardNumberEnum.ACE, 11);
        this.numberToScoreEnum.put(CardNumberEnum.TWO, 2);
        this.numberToScoreEnum.put(CardNumberEnum.THREE, 3);
        this.numberToScoreEnum.put(CardNumberEnum.FOUR, 4);
        this.numberToScoreEnum.put(CardNumberEnum.FIVE, 5);
        this.numberToScoreEnum.put(CardNumberEnum.SIX, 6);
        this.numberToScoreEnum.put(CardNumberEnum.SEVEN, 15);
        this.numberToScoreEnum.put(CardNumberEnum.EIGHT, 8);
        this.numberToScoreEnum.put(CardNumberEnum.NINE, 9);
        this.numberToScoreEnum.put(CardNumberEnum.TEN, 10);
        this.numberToScoreEnum.put(CardNumberEnum.JACK, 12);
        this.numberToScoreEnum.put(CardNumberEnum.QUEEN, 12);
        this.numberToScoreEnum.put(CardNumberEnum.KING, 13);
    }

    /**
     * Gets number to score map.
     *
     * @return the number to score map
     */
    public HashMap<CardNumberEnum, Integer> getNumberToScoreMap() {
        return numberToScoreEnum;
    }

}

