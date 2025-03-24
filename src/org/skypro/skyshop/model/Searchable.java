package org.skypro.skyshop.model;

public interface Searchable {
    String getSearchTerm();
    String getContentType();
    default void getStringRepresentation() {
        System.out.println(getSearchTerm() + " — " + getContentType());
    }
}
