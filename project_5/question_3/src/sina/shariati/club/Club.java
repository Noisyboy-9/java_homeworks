package sina.shariati.club;

import sina.shariati.enums.NewsTypeEnum;
import sina.shariati.exceptions.DuplicateNewsLetterSubscriptionTypeException;
import sina.shariati.exceptions.InvalidFanException;
import sina.shariati.matchs.Match;
import sina.shariati.news.Fan;
import sina.shariati.news.News;
import sina.shariati.news.NewsLetter;

import java.util.ArrayList;

/**
 * The type Club.
 */
public class Club {
    private final String name;
    private ArrayList<Player> players;
    private ArrayList<Match> matches;
    private final NewsLetter newsLetter;

    /**
     * Instantiates a new Club with just the club name.
     *
     * @param name the name
     */
    public Club(String name) {
        this.name = name;

        this.newsLetter = new NewsLetter();
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    /**
     * Instantiates a new Club with a predefined list of matches and players.
     *
     * @param name    the name
     * @param players the players
     * @param matches the matches
     */
    public Club(String name, ArrayList<Player> players, ArrayList<Match> matches) {
        this.name = name;
        this.players = players;
        this.matches = matches;

        this.newsLetter = new NewsLetter();
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
     * Gets matches.
     *
     * @return the matches
     */
    public ArrayList<Match> getMatches() {
        return matches;
    }

    /**
     * Add player to list of players.
     *
     * @param player the player
     */
    public void addPlayer(Player player) {
        this.players.add(player);
        String newsTitle = player.getName() + " Has joined " + this.name;
        String newsContent = player.getName() + " joined the team today. He is " + player.getAge() + " years old.";
        News news = this.createNews(newsTitle, newsContent, NewsTypeEnum.PLAYERS_NEWS);

        this.newsLetter.dispatch(news);
    }

    /**
     * Remove player from list of players.
     *
     * @param player the player
     */
    public void removePlayer(Player player) {
        this.players.remove(player);

        String newsTitle = player.getName() + "Has left " + this.name;
        String newsContent = player.getName() + "left the team today.";
        News news = this.createNews(newsTitle, newsContent, NewsTypeEnum.PLAYERS_NEWS);

        this.newsLetter.dispatch(news);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Add match to list of matches.
     *
     * @param match the match
     */
    public void addMatch(Match match) {
        this.matches.add(match);

        String newsTitle = match.getGuest().getName() + " is next!";
        String newsContent = match.getGuest().getName() + "is one of the opponents in the upcoming matches";
        News news = this.createNews(newsTitle, newsContent, NewsTypeEnum.MATCH_NEWS);

        this.newsLetter.dispatch(news);
    }

    private News createNews(String title, String content, NewsTypeEnum type) {
        return new News(title, content, type);
    }

    /**
     * Remove match from matches list.
     *
     * @param match the match
     */
    public void removeMatch(Match match) {
        this.matches.remove(match);

        String newsTitle = "match is over";
        String newsContent = "match with " + match.getGuest().name + "is finished!";
        News news = this.createNews(newsTitle, newsContent, NewsTypeEnum.MATCH_NEWS);

        this.newsLetter.dispatch(news);
    }

    /**
     * Add fan.
     *
     * @param fan the fan
     */
    public void addFan(Fan fan) {
        this.newsLetter.addFan(fan);
    }

    /**
     * Subscribe to news letter.
     *
     * @param fan  the fan
     * @param type the type
     * @throws DuplicateNewsLetterSubscriptionTypeException the duplicate news letter subscription type exception
     * @throws InvalidFanException                          the invalid fan exception
     */
    public void subscribeToNewsLetter(Fan fan, NewsTypeEnum type) throws DuplicateNewsLetterSubscriptionTypeException, InvalidFanException {
        this.newsLetter.subscribeToNewsLetter(fan, type);
    }
}

