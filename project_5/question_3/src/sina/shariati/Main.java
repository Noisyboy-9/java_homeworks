package sina.shariati;

import sina.shariati.club.Club;
import sina.shariati.club.Player;
import sina.shariati.enums.NewsTypeEnum;
import sina.shariati.exceptions.DuplicateNewsLetterSubscriptionTypeException;
import sina.shariati.exceptions.InvalidFanException;
import sina.shariati.exceptions.InvalidMatchDateException;
import sina.shariati.matchs.Match;
import sina.shariati.news.Fan;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
//        creating fans
        Fan sina = new Fan("Sina", "Shariati");
        Fan bardia = new Fan("Bardia", "Ardakanian");
        Fan john = new Fan("John", "Doe");
        Fan jane = new Fan("Jane", "Doe");
        Fan jim = new Fan("Jim", "Jenkins");
        Fan darla = new Fan("Darla", "Jennings");

//        creating clubs
        Club lakers = new Club("L.A.Lakers");
        Club bulls = new Club("Chicago Bulls");
        Club miami = new Club("Miami Heat");

//        adding fans to their clubs
        lakers.addFan(sina);
        lakers.addFan(bardia);
        lakers.addFan(jim);

        bulls.addFan(john);
        bulls.addFan(jane);

        miami.addFan(jim);
        miami.addFan(darla);
//        creating players to join the clubs
        Player player1 = new Player("Lebron", "James", UUID.randomUUID(), 38);
        Player player2 = new Player("Abbas", "Boazar", UUID.randomUUID(), 30);
        Player player3 = new Player("Danny", "Green", UUID.randomUUID(), 35);
        Player player4 = new Player("Anthony", "Davis", UUID.randomUUID(), 29);
        Player player5 = new Player("Lonzo", "Ball", UUID.randomUUID(), 30);


        try {
            lakers.subscribeToNewsLetter(sina, NewsTypeEnum.CLUB_NEWS);
            lakers.subscribeToNewsLetter(bardia, NewsTypeEnum.PLAYERS_NEWS);
            lakers.subscribeToNewsLetter(jim, NewsTypeEnum.MATCH_NEWS);

            bulls.subscribeToNewsLetter(john, NewsTypeEnum.MATCH_NEWS);
            bulls.subscribeToNewsLetter(jane, NewsTypeEnum.CLUB_NEWS);

            miami.subscribeToNewsLetter(jim, NewsTypeEnum.PLAYERS_NEWS);
            miami.subscribeToNewsLetter(darla, NewsTypeEnum.MATCH_NEWS);
        } catch (DuplicateNewsLetterSubscriptionTypeException | InvalidFanException e) {
            e.printStackTrace();
        }

//        adding players
        lakers.addPlayer(player1);
        lakers.addPlayer(player2);

        bulls.addPlayer(player3);

        miami.addPlayer(player4);
        miami.addPlayer(player5);

//        adding matches
        try {
            Match lakersVsBulls = new Match(lakers, bulls, new Date());
            lakers.addMatch(lakersVsBulls);
            bulls.addMatch(lakersVsBulls);


            Match miamiVsLakers = new Match(miami, lakers, new Date());
            lakers.addMatch(miamiVsLakers);
            bulls.addMatch(miamiVsLakers);
        } catch (InvalidMatchDateException e) {
            e.printStackTrace();
        }


//        removing players
        lakers.removePlayer(player1);
        lakers.removePlayer(player2);

        bulls.removePlayer(player3);

        miami.removePlayer(player4);
        miami.removePlayer(player5);
    }
}
