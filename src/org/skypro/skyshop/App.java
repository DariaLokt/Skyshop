package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.model.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Article tables = new Article("Tablee", "Tables are funny.");
        Article redTables = new Article("Red Tables Table Table", "Red tables are even funnier.");
        Article lamps = new Article("Lamppp", "LLLaaammmppp!");
        Article tables2 = new Article("Tableb", "Tables are funny. Really.");
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.add(book);
        searchEngine.add(table);
        searchEngine.add(toy);
        searchEngine.add(lamp);
        searchEngine.add(phone);
        searchEngine.add(pencil);
        searchEngine.add(tables);
        searchEngine.add(redTables);
        searchEngine.add(lamps);
        searchEngine.add(tables2);
        Set<Searchable> searchResults = searchEngine.search("Book");
        printSearchEngineResults(searchResults);
        Set<Searchable> searchResults2 = searchEngine.search("Lamp");
        printSearchEngineResults(searchResults2);
        Set<Searchable> searchResults3 = searchEngine.search("Table");
        printSearchEngineResults(searchResults3);
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
        Searchable searchMostRepetitions;
        try {
            searchMostRepetitions = searchEngine.searchBestResult("Table");
            searchMostRepetitions.getStringRepresentation();
        } catch (BestResultNotFound e) {
            System.out.println("Для запроса не нашлось подходящей статьи");
        }
        Searchable searchMostRepetitions2;
        try {
            searchMostRepetitions2 = searchEngine.searchBestResult("yogurt");
        } catch (BestResultNotFound e) {
            System.out.println("Для запроса не нашлось подходящей статьи");
        }
        printDivider();

        System.out.println("Добавление метода удаления продукта по имени из корзины");
        Map<String, List<Product>> trash;
        basket2.addProduct(table);
        basket2.addProduct(book);
        basket2.addProduct(phone);
        basket2.addProduct(pencil);
        basket2.addProduct(toy);
        basket2.addProduct(lamp);
        trash = basket2.deleteProduct("Lamp");
        printTrashList(trash);
        basket2.printBasket();
        trash = basket2.deleteProduct("Dog");
        printTrashList(trash);
        basket2.printBasket();
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

    public static void printTrashList (Map<String,List<Product>> trash) {
        if (trash.isEmpty()) {
            System.out.println("Ничего не было удалено");
        } else {
            ProductBasket trashBin = new ProductBasket();
            trashBin.addProduct(trash);
            System.out.println("----------------");
            System.out.println("Удалили:");
            trashBin.printProducts();
        }
    }

    public static void printSearchEngineResults(Set<Searchable> result) {
        System.out.println("Результаты поиска:");
        int count = 1;
        Iterator<Searchable> iterator = result.iterator();
        while (iterator.hasNext()) {
            Searchable element = iterator.next();
            System.out.println(count + ". " + element);
            count++;
        }
        System.out.println("-------------");
    }
}