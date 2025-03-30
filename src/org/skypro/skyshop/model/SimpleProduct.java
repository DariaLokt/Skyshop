package org.skypro.skyshop.model;

public class SimpleProduct extends Product {
    private final float price;

    public SimpleProduct(String name, float price) {
        super(name);
        checkPrice(price);
        this.price = price;
    }

    private static void checkPrice(float price) throws IllegalArgumentException{
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не должна быть отрицательной");
        }
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + ": " + price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
