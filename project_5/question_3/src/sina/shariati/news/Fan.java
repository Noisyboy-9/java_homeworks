package sina.shariati.news;

import sina.shariati.observer.ObserverInterface;

import java.util.ArrayList;

/**
 * The type Fan.
 */
public class Fan implements ObserverInterface {
    private String firstName;
    private String lastName;
    private ArrayList<News> receivedNews;

    /**
     * Instantiates a new Fan.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Fan(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.receivedNews = new ArrayList<>();
    }

    @Override
    public void update(News news) {
        this.getNews(news);
    }

    private void getNews(News news) {
        this.receivedNews.add(news);

        System.out.println("-----------------------------------");
        System.out.println("new news was added!");
        System.out.println("receiving fan data :");
        System.out.println("firstname: " + this.firstName);
        System.out.println("last name: " + this.lastName);

        System.out.println(news);
        System.out.println("-----------------------------------");
    }
}
