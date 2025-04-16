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
            size++;
            System.out.println("Товар " + product.getName() + " добавлен");
            System.out.println("Это первый товар с таким названием в корзине");
        } else {
            basket.get(product.getName()).add(product);
            System.out.println("Товар " + product.getName() + " добавлен");
            System.out.println("Это не первый товар с таким названием в корзине");
        }
    }

    public float countPrice() {
        float sum = 0f;
        for (Map.Entry<String, List<Product>> products : basket.entrySet()) {
            List<Product> productList = products.getValue();
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product element = iterator.next();
                if (element != null) {
                    sum += element.getPrice();
                }
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
        for (Map.Entry<String, List<Product>> products : basket.entrySet()) {
            List<Product> productList = products.getValue();
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product element = iterator.next();
                if (element != null) {
                    System.out.println(element);
                }
            }
        }
        System.out.println("Итого: " + countPrice());
        System.out.println("Специальных товаров: " + countSpecials());
        System.out.println("------------------------");
    }

    private int countSpecials() {
        int special = 0;
        for (Map.Entry<String, List<Product>> products : basket.entrySet()) {
            List<Product> productList = products.getValue();
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product element = iterator.next();
                if (element != null && element.isSpecial()) {
                    special++;
                }
            }
        }
        return special;
    }

    public boolean isInBasket(String name) {
        boolean isIt = false;
        for (Map.Entry<String, List<Product>> products : basket.entrySet()) {
            List<Product> productList = products.getValue();
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product element = iterator.next();
                if (element != null && element.getName().equals(name)) {
                    isIt = true;
                    break;
                }
            }
        }
        return isIt;
    }

    public void emptyBasket() {
        basket.clear();
        size = 0;
        System.out.println("Очистка корзины завершена");
    }

    public Map<String, List<Product>> deleteProduct(String name) {
        Map<String, List<Product>> deletedProducts = new LinkedHashMap<>();
        List<Product> deleted = new LinkedList<>();
        for (Map.Entry<String, List<Product>> products : basket.entrySet()) {
            List<Product> productList = products.getValue();
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product element = iterator.next();
                if (element.getName().equals(name)) {
                    deleted.add(element);
                    deletedProducts.put(element.getName(),deleted);
                    basket.remove(element.getName());
                }
            }
        }
        return deletedProducts;
    }
}
