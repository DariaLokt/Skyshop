package org.skypro.skyshop.product;

public interface Searchable {
    String getSearchTerm();
    String getContentType();
    default void getStringRepresentation() {
        System.out.println(getSearchTerm() + " — " + getContentType());
    }
}
