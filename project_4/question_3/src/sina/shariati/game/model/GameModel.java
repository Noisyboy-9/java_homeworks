package sina.shariati.game.model;

import sina.shariati.card.Card;
import sina.shariati.game.controller.GameController;
import sina.shariati.player.HumanPlayer;
import sina.shariati.player.Player;
import sina.shariati.player.RobotPlayer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Game model.
 */
public class GameModel {
    private static GameModel singletonInstance = null;
    private final ArrayList<Player> players;

    private GameModel() {
        this.players = new ArrayList<>();
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static GameModel getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new GameModel();
        }

        return singletonInstance;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets initial cards to every player.
     *
     * @param remainingCards the remaining cards
     */
    public void getInitialCardsToEveryPlayer(ArrayList<Card> remainingCards) {
        Random random = new Random();
        for (Player player : players) {
            for (int i = 1; i <= 7; i++) {
                int randomIndex = random.nextInt(remainingCards.size());
                Card card = remainingCards.get(randomIndex);
//                add card to player cards
                player.addCard(card);

//                remove card from game storage
                GameController.getSingletonInstance().getRemainingCards().remove(card);
            }
        }
    }

    /**
     * Sets players.
     *
     * @param human       the human
     * @param playerCount the player count
     */
    public void setPlayers(HumanPlayer human, int playerCount) {
        this.players.add(human);

        for (int i = 1; i <= playerCount - 1; i++) {
            this.players.add(new RobotPlayer("Robot" + i));
        }
    }

    /**
     * Print stats.
     */
    public void printStats() {
        HashMap<Player, Integer> playerToScoreMap = new HashMap<>();

        for (Player player : this.players) {
            playerToScoreMap.put(player, player.getFinalScore());
        }

        Map<Player, Integer> storedPlayersByScore = playerToScoreMap.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));

        int counter = 1;
        for (Map.Entry<Player, Integer> entry : storedPlayersByScore.entrySet()) {
            Player player = entry.getKey();
            int score = entry.getValue();

            System.out.println(counter + ") " + player.getName() + " : " + score);
            counter++;
        }
    }
}
