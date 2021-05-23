package sina.shariati.news;

import sina.shariati.enums.NewsTypeEnum;
import sina.shariati.exceptions.DuplicateNewsLetterSubscriptionTypeException;
import sina.shariati.exceptions.InvalidFanException;
import sina.shariati.observer.ObserveTargetInterface;
import sina.shariati.observer.ObserverInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The type News letter.
 */
public class NewsLetter implements ObserveTargetInterface {
    private HashSet<Fan> fans;
    private HashMap<Fan, ArrayList<NewsTypeEnum>> fanToNewsTypeMap;
    private HashSet<ObserverInterface> observers;

    /**
     * Instantiates a new News letter.
     */
    public NewsLetter() {
        this.fans = new HashSet<>();
        this.fanToNewsTypeMap = new HashMap<>();
    }

    /**
     * Instantiates a new News letter.
     *
     * @param fans             the fans
     * @param fanToNewsTypeMap the fan to news type map
     */
    public NewsLetter(HashSet<Fan> fans, HashMap<Fan, ArrayList<NewsTypeEnum>> fanToNewsTypeMap) {
        this.fans = fans;
        this.fanToNewsTypeMap = fanToNewsTypeMap;
    }

    /**
     * Add fan.
     *
     * @param fan the fan
     */
    public void addFan(Fan fan) {
        this.fans.add(fan);
    }

    /**
     * Remove fan.
     *
     * @param fan the fan
     */
    public void removeFan(Fan fan) {
        this.fans.remove(fan);
    }

    /**
     * Subscribe to news letter.
     *
     * @param fan  the fan
     * @param type the type
     * @throws InvalidFanException                          the invalid fan exception
     * @throws DuplicateNewsLetterSubscriptionTypeException the duplicate news letter subscription type exception
     */
    public void subscribeToNewsLetter(Fan fan, NewsTypeEnum type) throws InvalidFanException, DuplicateNewsLetterSubscriptionTypeException {
        if (!this.fans.contains(fan)) {
            throw new InvalidFanException("A fan must be first added to fan list and then can subscribe to the news letter");
        }

        if (this.fanToNewsTypeMap.containsKey(fan)) {
//            fan has already subscribed to the news letter
            ArrayList<NewsTypeEnum> alreadySubscribedTypes = fanToNewsTypeMap.get(fan);
            if (alreadySubscribedTypes.contains(type)) {
                throw new DuplicateNewsLetterSubscriptionTypeException("player has already subscribed to newsletter with this type");
            }

//            updating fan to subscription map
            alreadySubscribedTypes.add(type);
            this.fanToNewsTypeMap.replace(fan, alreadySubscribedTypes);
        } else {
//            it is a new subscription to the news letter
            ArrayList<NewsTypeEnum> types = new ArrayList<>();
            types.add(type);
            this.fanToNewsTypeMap.put(fan, types);
        }
    }


    @Override
    public void attach(ObserverInterface observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(ObserverInterface observer) {
        this.observers.remove(observer);
    }

    @Override
    public void dispatch(News news) {
        for (Fan subscribedFan : this.fanToNewsTypeMap.keySet()) {
            ArrayList<NewsTypeEnum> subscribedToTypes = this.fanToNewsTypeMap.get(subscribedFan);

            if (!subscribedToTypes.contains(news.getType())) {
                continue;
            }

            subscribedFan.update(news);
        }
    }

}
