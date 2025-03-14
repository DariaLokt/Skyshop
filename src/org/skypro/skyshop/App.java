package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        Product lamp = new Product("Lamp", 1000);
        Product table = new Product("Table", 15000);
        Product book = new Product("Book", 500);
        Product phone = new Product("Phone", 20000);
        Product pencil = new Product("Pencil", 25);
        Product toy = new Product("Toy", 800);

        ProductBasket basket1 = new ProductBasket();
        ProductBasket basket2 = new ProductBasket();


        System.out.println("Добавление продукта в корзину\n");
        basket1.addProduct(lamp);
        basket1.printBasket();
        printDivider();

        System.out.println("Добавление продукта в заполненную корзину, в которой нет свободного места\n");
        basket2.addProduct(table);
        basket2.addProduct(book);
        basket2.addProduct(phone);
        basket2.addProduct(pencil);
        basket2.addProduct(toy);
        basket2.addProduct(lamp);
        printDivider();

        System.out.println("Печать содержимого корзины с несколькими товарами\n");
        basket2.printBasket();
        printDivider();

        System.out.println("Получение стоимости корзины с несколькими товарами\n");
        int price = basket2.countPrice();
        System.out.println("Стоимость корзины: " + price);
        printDivider();

        System.out.println("Поиск товара, который есть в корзине\n");
        System.out.println(basket2.isInBasket("Pencil"));
        printSearchResult(basket2.isInBasket("Pencil"));
        printDivider();

        System.out.println("Поиск товара, которого нет в корзине\n");
        System.out.println(basket2.isInBasket("Pen"));
        printSearchResult(basket2.isInBasket("Pen"));
        printDivider();

        System.out.println("Очистка корзины\n");
        basket2.emptyBasket();
        printDivider();

        System.out.println("Печать содержимого пустой корзины\n");
        basket2.printBasket();
        printDivider();

        System.out.println("Получение стоимости пустой корзины\n");
        price = basket2.countPrice();
        System.out.println("Стоимость корзины: " + price);
        printDivider();

        System.out.println("Поиск товара по имени в пустой корзине\n");
        System.out.println(basket2.isInBasket("Pencil"));
        printSearchResult(basket2.isInBasket("Pencil"));
        printDivider();
    }

    public static void printDivider() {
        System.out.println("=================================\n");
    }

    public static void printSearchResult(boolean isIt) {
        if (isIt) {
            System.out.println("Такой товар есть");
        } else {
            System.out.println("Такого товара нет");
        }
    }
}