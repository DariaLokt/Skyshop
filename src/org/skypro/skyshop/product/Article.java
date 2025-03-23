package org.skypro.skyshop.product;

public class Article implements Searchable {
    String title;
    String text;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return getTitle() + '\n' + getText();
    }

    @Override
    public String getSearchTerm() {
        return getTitle();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}
