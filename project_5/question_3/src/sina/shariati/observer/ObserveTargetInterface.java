package sina.shariati.observer;

import sina.shariati.news.News;

public interface ObserveTargetInterface {
    void attach(ObserverInterface observer);

    void detach(ObserverInterface observer);

    void dispatch(News news);
}

