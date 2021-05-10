package sina.shariati.observer;

import sina.shariati.news.News;

/**
 * The interface Observe target interface.
 */
public interface ObserveTargetInterface {
    /**
     * Attach observer.
     *
     * @param observer the observer
     */
    void attach(ObserverInterface observer);

    /**
     * Detach observer.
     *
     * @param observer the observer
     */
    void detach(ObserverInterface observer);

    /**
     * Dispatch news to observer.
     *
     * @param news the news
     */
    void dispatch(News news);
}

