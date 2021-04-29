package sina.shariati.player;

import sina.shariati.card.Card;
import sina.shariati.enums.CardScoreManger;

import java.util.ArrayList;

/**
 * The Abstract type Player.
 */
public abstract class Player {
    private final CardScoreManger scoreManger = new CardScoreManger();
    private final String name;
    private final ArrayList<Card> remainingCards;
    private int finalScore;

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public Player(String name) {
        this.remainingCards = new ArrayList<>();
        this.name = name;
        this.finalScore = 0;
    }


    /**
     * Add card to remaining cards.
     *
     * @param card the card
     */
    public void addCard(Card card) {
        this.remainingCards.add(card);
    }

    /**
     * Gets remaining cards.
     *
     * @return the remaining cards
     */
    public ArrayList<Card> getRemainingCards() {
        return remainingCards;
    }

    /**
     * Calculate and then get player final score.
     *
     * @return the final score
     */
    public int getFinalScore() {
        this.calculateFinalScore();
        return this.finalScore;
    }

    private void calculateFinalScore() {
        int score = 0;

        for (Card card : remainingCards) {
            score += this.scoreManger.getNumberToScoreMap().get(card.getNumber());
        }

        this.finalScore = score;
    }

    /**
     * Get playable cards.
     *
     * @param topCard the top card
     * @return the available cards
     */
    protected ArrayList<Card> getPlayableCards(Card topCard) {
        ArrayList<Card> availableCards = new ArrayList<>();

        for (Card iterationCard : this.remainingCards) {
            if (isCardPlayable(topCard, iterationCard)) {
                availableCards.add(iterationCard);
            }
        }

        return availableCards;
    }

    /**
     * Is card playable boolean.
     *
     * @param topCard       the top card
     * @param iterationCard the iteration card
     * @return the boolean
     */
    public boolean isCardPlayable(Card topCard, Card iterationCard) {
        return topCard.getDeck() == iterationCard.getDeck() ||
                topCard.getNumber() == iterationCard.getNumber();
    }

    public String getName() {
        return this.name;
    }

    public boolean hasCard(Card card) {
        return this.remainingCards.contains(card);
    }

    public abstract Card makeMove();
}
