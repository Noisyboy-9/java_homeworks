package sina.shariati.news;

import sina.shariati.enums.NewsTypeEnum;

public class News {
    private final String title;
    private final String content;
    private final NewsTypeEnum type;

    public News(String title, String content, NewsTypeEnum type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public NewsTypeEnum getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\n" +
                "content: " + this.content;
    }
}
