package org.skypro.skyshop.basket;

import org.skypro.skyshop.model.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> basket;
    private int size;

    public ProductBasket() {
        this.basket = new LinkedHashMap<>();
        this.size = 0;
    }

    public void addProduct(Product product) {
        if (!basket.containsKey(product.getName())) {
            List<Product> newList = new LinkedList<Product>();
            newList.add(product);
            basket.put(product.getName(), newList);
            System.out.println("Товар " + product.getName() + " добавлен");
            System.out.println("Это первый товар с таким названием в корзине");
        } else {
            basket.get(product.getName()).add(product);
            System.out.println("Товар " + product.getName() + " добавлен");
            System.out.println("Это не первый товар с таким названием в корзине");
        }
        size++;
    }
    public void addProduct(Map<String,List<Product>> productSet) {
        basket.putAll(productSet);
    }

    public float countPrice() {
        int sum = basket.values().stream().flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .mapToInt(obj -> (int)obj.getPrice())
                .sum();
        return (float) sum;
    }

    public void printProducts() {
        basket.values().stream().flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    public void printBasket() {
        if (size == 0) {
            System.out.println("В корзине пусто");
            return;
        }
        System.out.println("------------------------");
        printProducts();
        System.out.println("Итого: " + countPrice());
        System.out.println("Специальных товаров: " + countSpecials());
        System.out.println("------------------------");
    }

    private int countSpecials() {
        return (int) basket.values().stream().flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(Product::isSpecial)
                .count();
    }

    public boolean isInBasket(String name) {
        return basket.containsKey(name);
    }

    public void emptyBasket() {
        basket.clear();
        size = 0;
        System.out.println("Очистка корзины завершена");
    }

    public Map<String, List<Product>> deleteProduct(String name) {
        Map<String, List<Product>> deletedProducts = new LinkedHashMap<>();
        if (basket.containsKey(name)) {
            deletedProducts.put(name,basket.get(name));
            basket.remove(name);
            size--;
        }
        return deletedProducts;
    }
}
