package sina.shariati.observer;

import sina.shariati.news.News;

/**
 * The interface Observer interface.
 */
public interface ObserverInterface {
    /**
     * Update the observer.
     *
     * @param news the news
     */
    void update(News news);
}
