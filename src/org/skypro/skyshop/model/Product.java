package org.skypro.skyshop.model;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        checkName(name);
        this.name = name;
    }

    private static void checkName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Нет названия");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
    }


    public String getName() {
        return name;
    }

    public abstract float getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}
