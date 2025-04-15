package org.skypro.skyshop.basket;

import org.skypro.skyshop.model.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductBasket {
    private final List<Product> basket;
    private int size;

    public ProductBasket() {
        this.basket = new LinkedList<>();
        this.size = 0;
    }

    public void addProduct(Product product) {
        basket.add(product);
        size++;
        System.out.println("Товар " + product.getName() + " добавлен");
    }

    public float countPrice() {
        float sum = 0f;
        Iterator<Product> iterator = basket.iterator();
        while (iterator.hasNext()) {
            Product element = iterator.next();
            if (element != null) {
                sum += element.getPrice();
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
        Iterator<Product> iterator = basket.iterator();
        while (iterator.hasNext()) {
            Product element = iterator.next();
            if (element != null) {
                System.out.println(element);
            }
        }
        System.out.println("Итого: " + countPrice());
        System.out.println("Специальных товаров: " + countSpecials());
        System.out.println("------------------------");
    }

    private int countSpecials() {
        int special = 0;
        Iterator<Product> iterator = basket.iterator();
        while (iterator.hasNext()) {
            Product element = iterator.next();
            if (element != null && element.isSpecial()) {
                special++;
            }
        }
        return special;
    }

    public boolean isInBasket(String name) {
        boolean isIt = false;
        Iterator<Product> iterator = basket.iterator();
        while (iterator.hasNext()) {
            Product element = iterator.next();
            if (element != null && element.getName().equals(name)) {
                isIt = true;
                break;
            }
        }
        return isIt;
    }

    public void emptyBasket() {
        basket.clear();
        size = 0;
        System.out.println("Очистка корзины завершена");
    }

    public List<Product> deleteProduct(String name) {
        List<Product> deletedProducts = new LinkedList<>();
        Iterator<Product> iterator = basket.iterator();
        while (iterator.hasNext()) {
            Product element = iterator.next();
            if (element.getName().equals(name)) {
                deletedProducts.add(element);
                basket.remove(element);
            }
        }
        return deletedProducts;
    }
}
