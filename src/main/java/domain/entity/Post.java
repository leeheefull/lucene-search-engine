package domain.entity;

public class Post {
    private final String id;
    private final String title;
    private final String writer;
    private final String content;

    public Post(String id, String title, String writer, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return " * " + title +
                " - " + writer +
                "\n ** " + content +
                "\n";
    }
}
