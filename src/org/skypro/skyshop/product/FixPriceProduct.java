package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static float PRICE = 250;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public float getPrice() {
        return PRICE;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
