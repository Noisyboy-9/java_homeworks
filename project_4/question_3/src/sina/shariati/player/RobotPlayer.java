package sina.shariati.player;

import sina.shariati.card.Card;
import sina.shariati.game.controller.GameController;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Robot player.
 */
public class RobotPlayer extends Player {
    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public RobotPlayer(String name) {
        super(name);
    }

    /**
     * Make a valid random generated move for robot player.
     */
    public Card makeMove() {
        Card topCard = GameController.getSingletonInstance().getTopCard();
        ArrayList<Card> playableCards = super.getPlayableCards(topCard);

        if (playableCards.size() == 0) {
            return null;
        }

//        generate a random index to choose from available cards
//         upperbound has to be one bigger than size to be inclusive of the last item
        int randomIndex = new Random().nextInt(playableCards.size());

        return playableCards.get(randomIndex);
    }
}
