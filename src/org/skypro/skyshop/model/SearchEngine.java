package org.skypro.skyshop.model;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SearchEngine {
     List<Searchable> Searchable;

    public SearchEngine() {
        Searchable = new LinkedList<Searchable>();
    }

    public List<Searchable> search(String searchTerm) {
        List<Searchable> results = new LinkedList<Searchable>();
        Iterator<Searchable> iterator = Searchable.iterator();
        while (iterator.hasNext()) {
            Searchable element = iterator.next();
            if (element != null && element.getSearchTerm().contains(searchTerm)) {
                results.add(element);
            }
        }
        return results;
    }

    public void add(Searchable newItem) {
        Searchable.add(newItem);
    }

    public Searchable searchBestResult(String search) throws BestResultNotFound {
        Searchable result = null;
        int maxQuantity = 0;
        Iterator<Searchable> iterator = Searchable.iterator();
        while (iterator.hasNext()) {
            Searchable element = iterator.next();
            if (element != null) {
                int quantity = 0;
                int index = 0;
                int substringIndex = element.getSearchTerm().indexOf(search, index);

                while(substringIndex != -1){
                    quantity++;
                    index = substringIndex + search.length();
                    substringIndex = element.getSearchTerm().indexOf(search, index);
                }

                if (quantity > maxQuantity) {
                    maxQuantity = quantity;
                    result = element;
                }
            }
        }
        if (result == null) {
            throw new BestResultNotFound("Для запроса \"" + search + "\" не нашлось подходящей статьи");
        }
        return result;
    }
}
