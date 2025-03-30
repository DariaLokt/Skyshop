package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.model.*;

public class App {
    public static void main(String[] args) {
        SimpleProduct lamp = new SimpleProduct("Lamp", 1000);
        DiscountedProduct table = new DiscountedProduct("Table", 15000, 10);
        DiscountedProduct book = new DiscountedProduct("Book", 50, 1);
        SimpleProduct phone = new SimpleProduct("Phone", 20000);
        FixPriceProduct pencil = new FixPriceProduct("Pencil");
        SimpleProduct toy = new SimpleProduct("Toy", 800);

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
        float price = basket2.countPrice();
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

        System.out.println("Тестирование SearchEngine");
        Article tables = new Article("Table", "Tables are funny.");
        Article redTables = new Article("Red Tables Table Table", "Red tables are even funnier.");
        Article lamps = new Article("Lamp", "LLLaaammmppp!");
        SearchEngine searchEngine = new SearchEngine(100);
        searchEngine.add(book);
        searchEngine.add(table);
        searchEngine.add(toy);
        searchEngine.add(lamp);
        searchEngine.add(phone);
        searchEngine.add(pencil);
        searchEngine.add(tables);
        searchEngine.add(redTables);
        searchEngine.add(lamps);
        Searchable [] searchResults = searchEngine.search("Book");
        printResults(searchResults);
        Searchable [] searchResults2 = searchEngine.search("Lamp");
        printResults(searchResults2);
        Searchable [] searchResults3 = searchEngine.search("Table");
        printResults(searchResults3);
        printDivider();

        System.out.println("Демонстрация проверки данных");
        try {
            SimpleProduct space = new SimpleProduct("", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Название не может быть пустым");
        }
        try {
            SimpleProduct negative = new SimpleProduct("Negative", -1);
        } catch (IllegalArgumentException e) {
            System.out.println("Цена не должна быть отрицательной");
        }
        try {
            DiscountedProduct extra = new DiscountedProduct("Extra", 100, 110);
        } catch (IllegalArgumentException e) {
            System.out.println("Проценты должны быть от 0 до 100");
        }
        printDivider();

        System.out.println("Демонстрация нового метода поиска");
        Searchable searchMostRepetitions = null;
        try {
            searchMostRepetitions = searchEngine.searchBestResult("Table");
        } catch (BestResultNotFound e) {
            System.out.println("Для запроса не нашлось подходящей статьи");
        }
        searchMostRepetitions.getStringRepresentation();
        Searchable searchMostRepetitions2 = null;
        try {
            searchMostRepetitions2 = searchEngine.searchBestResult("yogurt");
        } catch (BestResultNotFound e) {
            System.out.println("Для запроса не нашлось подходящей статьи");
        }
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

    public static void printResults(Searchable [] searchResults) {
        for (Searchable result : searchResults) {
            if (result != null) {
                result.getStringRepresentation();
            }
        }
    }
}