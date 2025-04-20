package org.skypro.skyshop.model;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;

public class SearchEngine {
     HashSet<Searchable> Searchable;

    public SearchEngine() {
        Searchable = new HashSet<>();
    }

    public Set<Searchable> search(String searchTerm) {
        Set<Searchable> results = new TreeSet<Searchable>(new LengthSearchableComparator());
        Iterator<Searchable> iterator = Searchable.iterator();
        while (iterator.hasNext()) {
            Searchable element = iterator.next();
            if (element != null && element.getSearchTerm().contains(searchTerm)) {
                results.add(element);
            }
        }
        return results;
    }

    public static class LengthSearchableComparator implements Comparator<Searchable> {
        @Override
        public int compare(org.skypro.skyshop.model.Searchable o1, org.skypro.skyshop.model.Searchable o2) {
            int a = Integer.compare(o1.getSearchTerm().length(),o2.getSearchTerm().length());
            if (a == 0) {
                return o1.getSearchTerm().compareTo(o2.getSearchTerm());
            } else {
                return a;
            }
        }
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
