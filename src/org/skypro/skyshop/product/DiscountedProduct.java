package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private float price;
    private int discount;

    public DiscountedProduct(String name, float price, int discount) {
        super(name);
        this.price = price;
        this.discount = discount;
    }

    @Override
    public float getPrice() {
        return price * (1 - (float)discount/100);
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
