package sina.shariati.game.view;

import sina.shariati.card.Card;
import sina.shariati.card.ConsequenceCard;
import sina.shariati.card.NormalCard;
import sina.shariati.enums.CardDeckEnum;
import sina.shariati.enums.CardNumberEnum;
import sina.shariati.enums.CardScoreManger;
import sina.shariati.exceptions.InvalidPlayerCountException;
import sina.shariati.game.controller.GameController;
import sina.shariati.game.model.GameModel;
import sina.shariati.player.HumanPlayer;
import sina.shariati.player.Player;
import sina.shariati.player.RobotPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

/**
 * The type Game view.
 */
public class GameView {
    private static GameView singletonInstance = null;
    /**
     * The Ansi black.
     */
    public final String ANSI_BLACK = "\u001b[35;1m";
    /**
     * The Ansi red.
     */
    public final String ANSI_RED = "\u001B[31m";
    /**
     * The Ansi blue.
     */
    public final String ANSI_BLUE = "\u001B[34m";
    /**
     * The Ansi green.
     */
    public final String ANSI_GREEN = "\u001B[32m";
    /**
     * The Ansi reset.
     */
    public final String ANSI_RESET = "\u001B[0m";

    /**
     * Gets human playable card choice.
     *
     * @param human the human
     * @return the human playable card choice
     */
    public Card getHumanPlayableCardChoice(HumanPlayer human) {
        Card inputCard = createCardFromInput(this.getCardNumber(), this.getCardDeck());
        Card gameTopCard = GameController.getSingletonInstance().getTopCard();

        while (!human.hasCard(inputCard) || !human.isCardPlayable(gameTopCard, inputCard)) {
            System.out.println("invalid card input!");
            inputCard = createCardFromInput(this.getCardNumber(), this.getCardDeck());
        }

        return inputCard;
    }

    private Card createCardFromInput(CardNumberEnum cardNumber, CardDeckEnum cardDeck) {
        HashMap<CardNumberEnum, Integer> cardNumberToScore = new CardScoreManger().getNumberToScoreMap();
        return switch (cardNumber) {
            case ACE, TWO, SEVEN, EIGHT, TEN, JACK -> new ConsequenceCard(cardNumber, cardDeck, cardNumberToScore.get(cardNumber));
            default -> new NormalCard(cardNumber, cardDeck, cardNumberToScore.get(cardNumber));
        };
    }

    private CardDeckEnum getCardDeck() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please input card deck:(❤️->hearts, ♣️->clubs, ♦️->diamonds, ♠️->spades) ");
        String inputCardDeck = scanner.nextLine();

        while (!this.isValidCardDeck(inputCardDeck)) {
            System.out.println("invalid card deck name please try again!");
            System.out.print("please input card deck: ");
            inputCardDeck = scanner.nextLine();
        }

        return ConvertToCardDeckEnum(inputCardDeck);
    }

    private boolean isValidCardDeck(String inputCardDeck) {
        return inputCardDeck.trim().toLowerCase(Locale.ROOT).equals("hearts") ||
                inputCardDeck.trim().toLowerCase(Locale.ROOT).equals("spades") ||
                inputCardDeck.trim().toLowerCase(Locale.ROOT).equals("clubs") ||
                inputCardDeck.trim().toLowerCase(Locale.ROOT).equals("diamonds");
    }

    private CardDeckEnum ConvertToCardDeckEnum(String deckString) {
        return switch (deckString.trim().toLowerCase(Locale.ROOT)) {
            case "hearts" -> CardDeckEnum.HEARTS;
            case "spades" -> CardDeckEnum.SPADES;
            case "clubs" -> CardDeckEnum.CLUBS;
            default -> CardDeckEnum.DIAMONDS;
        };
    }

    private CardNumberEnum getCardNumber() {
        System.out.print("please input card number(for jack and queen and king enter 11,12,13): ");

        Scanner scanner = new Scanner(System.in);

        int inputCardNumber = scanner.nextInt();

        while (!this.isValidCardNumber(inputCardNumber)) {
            System.out.println("invalid card number please try again!");
            System.out.print("please input card number: ");
            inputCardNumber = scanner.nextInt();
        }

        return convertToCardNumberEnum(inputCardNumber);
    }

    private boolean isValidCardNumber(int cardNumber) {
        return cardNumber >= 1 && cardNumber <= 13;
    }

    private CardNumberEnum convertToCardNumberEnum(int inputCardNumber) {
        return switch (inputCardNumber) {
            case 1 -> CardNumberEnum.ACE;
            case 2 -> CardNumberEnum.TWO;
            case 3 -> CardNumberEnum.THREE;
            case 4 -> CardNumberEnum.FOUR;
            case 5 -> CardNumberEnum.FIVE;
            case 6 -> CardNumberEnum.SIX;
            case 7 -> CardNumberEnum.SEVEN;
            case 8 -> CardNumberEnum.EIGHT;
            case 9 -> CardNumberEnum.NINE;
            case 10 -> CardNumberEnum.TEN;
            case 11 -> CardNumberEnum.JACK;
            case 12 -> CardNumberEnum.QUEEN;
            default -> CardNumberEnum.KING;
        };
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static GameView getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new GameView();
        }
        return singletonInstance;
    }

    /**
     * Gets player count.
     *
     * @return the player count
     * @throws InvalidPlayerCountException the invalid player count exception
     */
    public int getPlayerCount() throws InvalidPlayerCountException {
        System.out.print("please enter players count:\t");
        Scanner scanner = new Scanner(System.in);
        int inputPlayerCount = scanner.nextInt();

        if (inputPlayerCount < 3 || inputPlayerCount > 5) {
            throw new InvalidPlayerCountException("Invalid input player count! player count should be between 3 and 5 players");
        }

        return inputPlayerCount;
    }

    /**
     * Create human player human player.
     *
     * @return the human player
     */
    public HumanPlayer createHumanPlayer() {
        System.out.print("please enter human player name:\t");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return new HumanPlayer(name);
    }

    /**
     * Print card.
     *
     * @param card the card
     */
    public void printCard(Card card) {
        switch (card.getDeck()) {
            case CLUBS -> {
                printCardWithColor(card, this.ANSI_BLACK);
                System.out.print(this.ANSI_RESET);
            }
            case HEARTS -> {
                printCardWithColor(card, this.ANSI_RED);
                System.out.print(this.ANSI_RESET);
            }
            case SPADES -> {
                printCardWithColor(card, this.ANSI_GREEN);
                System.out.print(this.ANSI_RESET);
            }
            case DIAMONDS -> {
                printCardWithColor(card, this.ANSI_BLUE);
                System.out.print(this.ANSI_RESET);
            }
        }
    }

    private void printCardWithColor(Card card, String color) {
        System.out.println(color);
        for (int height = 1; height <= 5; height++) {
            for (int width = 1; width <= 15; width++) {
                if (width == 1 || width == 15 || height == 1 || height == 5) {
                    System.out.print("|");
                } else if (width == 5 && height == 3) {
                    this.printCardDeck(card);
                    this.printCardNumber(card);
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private void printCardNumber(Card card) {
        switch (card.getNumber()) {
            case ACE -> System.out.print("A");
            case TWO -> System.out.print(2);
            case THREE -> System.out.print(3);
            case FOUR -> System.out.print(4);
            case FIVE -> System.out.print(5);
            case SIX -> System.out.print(6);
            case SEVEN -> System.out.print(7);
            case EIGHT -> System.out.print(8);
            case NINE -> System.out.print(9);
            case TEN -> System.out.print(10);
            case JACK -> System.out.print("J");
            case QUEEN -> System.out.print("Q");
            case KING -> System.out.print("K");
            default -> throw new IllegalStateException("Unexpected value: " + card.getNumber());
        }
    }

    private void printCardDeck(Card card) {
        switch (card.getDeck()) {
            case CLUBS -> System.out.print("♣️");
            case HEARTS -> System.out.print("❤️");
            case SPADES -> System.out.print("♠️");
            case DIAMONDS -> System.out.print("♦️");
        }
    }

    /**
     * Print cards.
     *
     * @param cards the cards
     */
    public void printCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            this.printCard(card);
        }
    }

    /**
     * Print direction.
     */
    public void printDirection() {
        System.out.println("Game direction: " + GameController.getSingletonInstance().getGameDirection().toString());
    }

    /**
     * Print player count.
     */
    public void printPlayerCount() {
        System.out.println("player count: " + GameModel.getSingletonInstance().getPlayers().size());
    }

    /**
     * Print robot move.
     *
     * @param robot the robot
     * @param card  the card
     */
    public void printRobotMove(RobotPlayer robot, Card card) {
        System.out.println("robot with name: " + robot.getName());
        System.out.println("put down card: ");
        this.printCard(card);
    }

    /**
     * Print ace ban message.
     */
    public void printAceBanMessage() {
        Card topCard = GameController.getSingletonInstance().getTopCard();
        Player bannedPlayer = GameController.getSingletonInstance().getPlayerWithTurn();

        System.out.println("At last hand Ace of " + topCard.getDeck() + " was added to put down.");
        System.out.println("So this turn player: " + bannedPlayer.getName() + " can not make move.");
    }

    /**
     * Gets human remaining card choice.
     *
     * @param human the human
     * @return the human remaining card choice
     */
    public Card getHumanRemainingCardChoice(HumanPlayer human) {
        Card inputCard = createCardFromInput(this.getCardNumber(), this.getCardDeck());

        while (!human.hasCard(inputCard)) {
            System.out.println("player does not own input card!");
            inputCard = this.createCardFromInput(this.getCardNumber(), this.getCardDeck());
        }

        return inputCard;
    }

    /**
     * Gets human victim choice.
     *
     * @return the human victim choice
     */
    public Player getHumanVictimChoice() {
        System.out.println("Choose victim: ");
        int index = 0;
        for (Player player : GameModel.getSingletonInstance().getPlayers()) {
            if (player instanceof HumanPlayer) {
//                a human can not select itself as victim
                continue;
            }
            System.out.println(index + ") " + player.getName());
            index++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("please input player index: ");
        int inputIndex = scanner.nextInt();

        while (!this.isValidPlayerIndex(inputIndex)) {
            System.out.println("invalid player index!");
            System.out.print("please input player index: ");
            inputIndex = scanner.nextInt();
        }


        return GameModel.getSingletonInstance().getPlayers().get(inputIndex);
    }

    private boolean isValidPlayerIndex(int inputIndex) {
        int playerCount = GameModel.getSingletonInstance().getPlayers().size();
        int lastPlayerIndex = playerCount - 1;

        return 0 <= inputIndex && inputIndex <= lastPlayerIndex;
    }
}
