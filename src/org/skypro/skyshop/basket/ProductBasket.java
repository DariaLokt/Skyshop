package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Arrays;

public class ProductBasket {
    private Product[] basket;
    private int size;

    public ProductBasket() {
        this.basket = new Product[5];
        this.size = 0;
    }

    public void addProduct(Product product) {
        if (size >= 5) {
            System.out.println("Невозможно добавить продукт");
            return;
        }
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] == null) {
                basket[i] = product;
                size++;
                System.out.println("Товар " + product.getName() + " добавлен");
                break;
            }
        }
    }

    public int countPrice() {
        int sum = 0;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] != null) {
                sum += basket[i].getPrice();
            }
        }
        return sum;
    }

    public void printBasket() {
        if (size == 0) {
            System.out.println("в корзине пусто");
            return;
        }
        System.out.println("------------------------");
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] != null) {
                System.out.println(basket[i]);
            }
        }
        System.out.println("Итого: " + countPrice());
        System.out.println("------------------------");
    }

    public boolean isInBasket(String name) {
        boolean isIt = false;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] != null && basket[i].getName().equals(name)) {
                isIt = true;
                break;
            }
        }
        return isIt;
    }

    public void emptyBasket() {
        Arrays.fill(basket, null);
        size = 0;
        System.out.println("Очистка корзины завершена");
    }
}
