package sina.shariati.matchs;

import sina.shariati.club.Club;
import sina.shariati.exceptions.InvalidMatchDateException;

import java.util.Date;

/**
 * The type Match.
 */
public class Match {
    private final Club guest;
    private final Club home;
    private final Date scheduledDate;

    /**
     * Instantiates a new Match.
     *
     * @param guest         the guest
     * @param home          the home
     * @param scheduledDate the scheduled date
     * @throws InvalidMatchDateException the invalid match date exception
     */
    public Match(Club guest, Club home, Date scheduledDate) throws InvalidMatchDateException {
        this.guest = guest;
        this.home = home;

        if (scheduledDate.before(new Date())) {
            throw new InvalidMatchDateException("Match can not be scheduled for the past");
        }

        this.scheduledDate = scheduledDate;
    }

    /**
     * Gets scheduled date.
     *
     * @return the scheduled date
     */
    public Date getScheduledDate() {
        return scheduledDate;
    }

    /**
     * Gets guest.
     *
     * @return the guest
     */
    public Club getGuest() {
        return guest;
    }

    /**
     * Gets home.
     *
     * @return the home
     */
    public Club getHome() {
        return home;
    }
}
