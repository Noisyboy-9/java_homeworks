package sina.shariati.player;

import sina.shariati.card.Card;
import sina.shariati.game.controller.GameController;
import sina.shariati.game.view.GameView;

import java.util.ArrayList;

/**
 * The type Human player.
 */
public class HumanPlayer extends Player {

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Play a valid human selected card.
     *
     * @return the card
     */
    public Card makeMove() {
        System.out.println("All of your cards are: ");
        GameView.getSingletonInstance().printCards(this.getRemainingCards());

        Card topCard = GameController.getSingletonInstance().getTopCard();
        ArrayList<Card> playableCards = this.getPlayableCards(topCard);

        if (playableCards.size() != 0) {
            System.out.println("Available cards are : ");
            GameView.getSingletonInstance().printCards(playableCards);
        } else {
            return null;
        }

        Card chosenCard = GameView.getSingletonInstance().getHumanPlayableCardChoice(this);
        while (!super.isCardPlayable(topCard, chosenCard)) {
            System.out.println("invalid card!");
            chosenCard = GameView.getSingletonInstance().getHumanPlayableCardChoice(this);
        }

        return chosenCard;
    }
}
