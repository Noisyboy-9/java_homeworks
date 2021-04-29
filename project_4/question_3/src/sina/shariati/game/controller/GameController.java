package sina.shariati.game.controller;

import sina.shariati.card.Card;
import sina.shariati.card.ConsequenceCard;
import sina.shariati.card.NormalCard;
import sina.shariati.enums.CardDeckEnum;
import sina.shariati.enums.CardNumberEnum;
import sina.shariati.enums.CardScoreManger;
import sina.shariati.enums.GameDirectionEnum;
import sina.shariati.game.model.GameModel;
import sina.shariati.game.view.GameView;
import sina.shariati.player.Player;
import sina.shariati.player.RobotPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The type Game controller.
 */
public class GameController {
    private static GameController singletonInstance = null;
    private final ArrayList<Card> remainingCards;
    private int playerWithTurnIndex;
    private Player playerWithTurn;
    private GameDirectionEnum gameDirection;
    private Card topCard;
    private ConsequenceCard waitingCardWithConsequence;

    private GameController() {
        this.remainingCards = new ArrayList<>();
        this.playerWithTurn = null;
        this.gameDirection = GameDirectionEnum.CLOCKWISE;

        this.generateGameCards();
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static GameController getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new GameController();
        }

        return singletonInstance;
    }

    /**
     * Gets waiting card with consequence.
     *
     * @return the waiting card with consequence
     */
    public ConsequenceCard getWaitingCardWithConsequence() {
        return waitingCardWithConsequence;
    }

    /**
     * Sets waiting card with consequence.
     *
     * @param waitingCardWithConsequence the waiting card with consequence
     */
    public void setWaitingCardWithConsequence(ConsequenceCard waitingCardWithConsequence) {
        this.waitingCardWithConsequence = waitingCardWithConsequence;
    }

    /**
     * Gets game direction.
     *
     * @return the game direction
     */
    public GameDirectionEnum getGameDirection() {
        return gameDirection;
    }

    /**
     * Randomize starting card.
     */
    public void randomizeStartingCard() {
        int randomIndex = new Random().nextInt(this.remainingCards.size());
        this.topCard = this.remainingCards.get(randomIndex);
    }

    /**
     * Randomize starting player.
     */
    public void randomizeStartingPlayer() {
        int playerCount = GameModel.getSingletonInstance().getPlayers().size();
        int randomIndex = new Random().nextInt(playerCount);
        this.playerWithTurn = GameModel.getSingletonInstance().getPlayers().get(randomIndex);
        this.playerWithTurnIndex = randomIndex;
    }

    /**
     * Randomize game direction.
     */
    public void randomizeGameDirection() {
        GameDirectionEnum[] directions = GameDirectionEnum.values();

        int randomIndex = new Random().nextInt(directions.length);
        this.gameDirection = directions[randomIndex];
    }

    private void generateGameCards() {
        CardDeckEnum[] decks = CardDeckEnum.values();
        CardNumberEnum[] numbers = CardNumberEnum.values();
        HashMap<CardNumberEnum, Integer> cardNumberToScore = new CardScoreManger().getNumberToScoreMap();

        for (CardDeckEnum deck : decks) {
            for (CardNumberEnum number : numbers) {
                switch (number) {
                    case ACE, TWO, SEVEN, EIGHT, TEN, JACK -> this.remainingCards.add(new ConsequenceCard(number, deck, cardNumberToScore.get(number)));
                    default -> this.remainingCards.add(new NormalCard(number, deck, cardNumberToScore.get(number)));
                }
            }


        }
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
     * Gets top card.
     *
     * @return the top card
     */
    public Card getTopCard() {
        return this.topCard;
    }

    /**
     * Sets top card.
     *
     * @param topCard the top card
     */
    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    /**
     * Have winner boolean.
     *
     * @return the boolean
     */
    public boolean haveWinner() {
        for (Player player : GameModel.getSingletonInstance().getPlayers()) {
            if (player.getRemainingCards().size() == 0) {
                return true;
            }
        }

        if (GameController.getSingletonInstance().getRemainingCards().size() == 0) {
//            if there is no card remaining in the storage game is a draw and winner is judged by score
            System.out.println("Storage is empty!");
            System.out.println("No clear winner");
            System.out.println("Player with lowest score wins");
            return true;
        }

        return false;
    }

    /**
     * Play one hand.
     */
    public void playOneHand() {
        if (this.waitingCardWithConsequence == null) {
            Card card = this.playerWithTurn.makeMove();

            if (card == null) {
                int randomIndex = new Random().nextInt(this.remainingCards.size());
                Card punishmentCard = this.remainingCards.get(randomIndex);
                this.playerWithTurn.addCard(punishmentCard);
                this.changeTurnToNextHand();
                return;
            }


//        adding old top card to storage
            this.remainingCards.add(this.topCard);

//         removing the card from user cards
            this.playerWithTurn.getRemainingCards().remove(card);

//        making player chosen card the top card
            this.topCard = card;

//        check if it has consequence
            if (card instanceof ConsequenceCard) {
                this.waitingCardWithConsequence = (ConsequenceCard) card;
            }

//            if user with turn is robot print to console his move so the human can understand what happened
            if (this.playerWithTurn instanceof RobotPlayer) {
                GameView.getSingletonInstance().printRobotMove((RobotPlayer) this.playerWithTurn, card);
            }
        } else {
//            doing the consequence of the last hand
            this.waitingCardWithConsequence.doConsequence(playerWithTurn);
            this.waitingCardWithConsequence = null;
        }

        this.changeTurnToNextHand();
    }


    /**
     * Change turn to previous hand.
     */
    public void changeTurnToPreviousHand() {
        int playerCount = GameModel.getSingletonInstance().getPlayers().size();
        if (this.gameDirection == GameDirectionEnum.CLOCKWISE) {
            this.playerWithTurnIndex--;
//            go to beginning
            if (this.playerWithTurnIndex >= playerCount) this.playerWithTurnIndex = 0;
        } else {
            this.playerWithTurnIndex++;
//            go to the end
            if (this.playerWithTurnIndex <= -1) this.playerWithTurnIndex = 3;
        }

        this.playerWithTurn = GameModel.getSingletonInstance().getPlayers().get(playerWithTurnIndex);
    }

    /**
     * Change turn to next hand.
     */
    public void changeTurnToNextHand() {
        int playerCount = GameModel.getSingletonInstance().getPlayers().size();
        if (this.gameDirection == GameDirectionEnum.CLOCKWISE) {
            this.playerWithTurnIndex++;
//            go to beginning
            if (this.playerWithTurnIndex >= playerCount) this.playerWithTurnIndex = 0;
        } else {
            this.playerWithTurnIndex--;
//            go to the end
            if (this.playerWithTurnIndex <= -1) this.playerWithTurnIndex = 3;
        }

        this.playerWithTurn = GameModel.getSingletonInstance().getPlayers().get(playerWithTurnIndex);
    }

    /**
     * Gets player with turn.
     *
     * @return the player with turn
     */
    public Player getPlayerWithTurn() {
        return playerWithTurn;
    }

    /**
     * Toggle game direction.
     */
    public void toggleGameDirection() {
        if (this.gameDirection == GameDirectionEnum.CLOCKWISE) {
            this.gameDirection = GameDirectionEnum.ANTICLOCKWISE;
        } else {
            this.gameDirection = GameDirectionEnum.CLOCKWISE;
        }
    }
}
