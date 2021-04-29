package sina.shariati;

import sina.shariati.exceptions.InvalidPlayerCountException;
import sina.shariati.game.controller.GameController;
import sina.shariati.game.model.GameModel;
import sina.shariati.game.view.GameView;
import sina.shariati.player.HumanPlayer;

public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InvalidPlayerCountException the invalid player count exception
     */
    public static void main(String[] args) throws InvalidPlayerCountException {
//        setting up the game MVC
        GameController controller = GameController.getSingletonInstance();
        GameModel model = GameModel.getSingletonInstance();
        GameView view = GameView.getSingletonInstance();

//        game players setup
        int playerCount = view.getPlayerCount();
        HumanPlayer humanPlayer = view.createHumanPlayer();
        model.setPlayers(humanPlayer, playerCount);

//        game env setup
        model.getInitialCardsToEveryPlayer(controller.getRemainingCards());
        controller.randomizeGameDirection();
        controller.randomizeStartingPlayer();
        controller.randomizeStartingCard();


//        actual game progress
        while (!controller.haveWinner()) {
            view.printDirection();
            System.out.println("top card: ");
            view.printCard(controller.getTopCard());
            view.printPlayerCount();

            if (controller.getWaitingCardWithConsequence() != null) {
                System.out.println("card with consequence is:");
                view.printCard(controller.getWaitingCardWithConsequence());
            }

            controller.playOneHand();
        }

        model.printStats();
        System.out.println("hope you have enjoyed");
    }
}
