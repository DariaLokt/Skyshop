package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private float price;

    public SimpleProduct(String name, float price) {
        super(name);
        this.price = price;
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
