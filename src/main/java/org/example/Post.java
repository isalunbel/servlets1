package org.example;

public class Post {
    private long id;
    private String content;

    // Константы
    private static final String DEFAULT_CONTENT = "";

    public Post() {
        this.content = DEFAULT_CONTENT;
    }

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
