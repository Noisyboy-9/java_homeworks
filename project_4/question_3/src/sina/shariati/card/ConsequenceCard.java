package sina.shariati.card;

import sina.shariati.enums.CardDeckEnum;
import sina.shariati.enums.CardNumberEnum;
import sina.shariati.game.controller.GameController;
import sina.shariati.game.model.GameModel;
import sina.shariati.game.view.GameView;
import sina.shariati.player.HumanPlayer;
import sina.shariati.player.Player;
import sina.shariati.player.RobotPlayer;

import java.util.Random;

/**
 * A card that has consequence
 */
public class ConsequenceCard extends Card {
    /**
     * Instantiates a new abstract Card.
     *
     * @param number the number
     * @param deck   the deck
     * @param score  the score
     */
    public ConsequenceCard(CardNumberEnum number, CardDeckEnum deck, int score) {
        super(number, deck, score);
    }

    /**
     * Do card consequence.
     *
     * @param executioner the executioner
     */
    public void doConsequence(Player executioner) {
        switch (this.getNumber()) {
            case ACE -> GameView.getSingletonInstance().printAceBanMessage();
            case TWO -> {
                Player victim;
                Card selectedCard;

                if (executioner instanceof RobotPlayer) {
                    int randomCardIndex = new Random().nextInt(executioner.getRemainingCards().size());
                    selectedCard = executioner.getRemainingCards().get(randomCardIndex);

                    int randomPlayerIndex = new Random().nextInt(GameModel.getSingletonInstance().getPlayers().size());
                    victim = GameModel.getSingletonInstance().getPlayers().get(randomPlayerIndex);

                    while (victim.equals(executioner)) {
                        randomPlayerIndex = new Random().nextInt(GameModel.getSingletonInstance().getPlayers().size());
                        victim = GameModel.getSingletonInstance().getPlayers().get(randomPlayerIndex);
                    }
                } else {
//                    executioner is human
                    GameView.getSingletonInstance().printCards(executioner.getRemainingCards());
                    selectedCard = GameView.getSingletonInstance().getHumanRemainingCardChoice((HumanPlayer) executioner);
                    victim = GameView.getSingletonInstance().getHumanVictimChoice();
                }

                victim.addCard(selectedCard);
                executioner.getRemainingCards().remove(selectedCard);
            }
            case SEVEN -> {
                if (GameController.getSingletonInstance().getWaitingCardWithConsequence().getDeck() == CardDeckEnum.CLUBS) {
//                    if consequence card is seven of clubs executioner must pick up 4 cards
                    Random random = new Random();
                    int randomBoundary = GameController.getSingletonInstance().getRemainingCards().size();
//                    randomize each card selection and remove it from game storage
                    Card card1 = GameController.getSingletonInstance().getRemainingCards().get(random.nextInt(randomBoundary));
                    GameController.getSingletonInstance().getRemainingCards().remove(card1);

                    Card card2 = GameController.getSingletonInstance().getRemainingCards().get(random.nextInt(randomBoundary));
                    GameController.getSingletonInstance().getRemainingCards().remove(card2);

                    Card card3 = GameController.getSingletonInstance().getRemainingCards().get(random.nextInt(randomBoundary));
                    GameController.getSingletonInstance().getRemainingCards().remove(card3);

                    Card card4 = GameController.getSingletonInstance().getRemainingCards().get(random.nextInt(randomBoundary));
                    GameController.getSingletonInstance().getRemainingCards().remove(card4);

//                    adding card to victim
                    executioner.addCard(card1);
                    executioner.addCard(card2);
                    executioner.addCard(card3);
                    executioner.addCard(card4);
                } else {
//                    if it 7 of diamonds, spades, hearts the executioner must pick 2 cards
                    Random random = new Random();
                    int randomBoundary = GameController.getSingletonInstance().getRemainingCards().size();

//                    randomize each card selection and remove it from game storage
                    Card card1 = GameController.getSingletonInstance().getRemainingCards().get(random.nextInt(randomBoundary));
                    GameController.getSingletonInstance().getRemainingCards().remove(card1);

                    Card card2 = GameController.getSingletonInstance().getRemainingCards().get(random.nextInt(randomBoundary));
                    GameController.getSingletonInstance().getRemainingCards().remove(card2);

//                    adding card to victim
                    executioner.addCard(card1);
                    executioner.addCard(card2);
                }
            }
            case EIGHT -> {
                GameController.getSingletonInstance().changeTurnToPreviousHand();
                GameController.getSingletonInstance().setWaitingCardWithConsequence(null);
                GameController.getSingletonInstance().playOneHand();
            }

            case TEN -> GameController.getSingletonInstance().toggleGameDirection();

            case JACK -> {
                if (executioner instanceof RobotPlayer) {
                    int randomIndex = new Random().nextInt(executioner.getRemainingCards().size());
                    Card card = executioner.getRemainingCards().get(randomIndex);
                    GameController.getSingletonInstance().setTopCard(card);
                    GameController.getSingletonInstance().getRemainingCards().add(card);
                    executioner.getRemainingCards().remove(card);
                } else {
                    Card card = GameView.getSingletonInstance().getHumanPlayableCardChoice((HumanPlayer) executioner);
                    GameController.getSingletonInstance().setTopCard(card);
                    GameController.getSingletonInstance().getRemainingCards().add(card);
                    executioner.getRemainingCards().remove(card);
                }
            }
        }
    }
}
