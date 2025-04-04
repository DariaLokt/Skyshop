package org.skypro.skyshop.model;

import org.skypro.skyshop.exceptions.BestResultNotFound;

public class SearchEngine {
     Searchable[] Searchable;
     int size;

    public SearchEngine(int size) {
        this.size = size;
        Searchable = new Searchable[size];
    }

    public Searchable[] search(String searchTerm) {
        Searchable[] results = new Searchable[5];
        int count = 0;
        for (int i = 0; i < Searchable.length; i++) {
            if (Searchable[i] != null && Searchable[i].getSearchTerm().contains(searchTerm) && count < 5) {
                results[count] = Searchable[i];
                count++;
            }
        }
        return results;
    }

    public void add(Searchable newItem) {
        for (int i = 0; i < Searchable.length; i++) {
            if (Searchable[i] == null) {
                Searchable[i] = newItem;
                break;
            }
        }
    }

    public Searchable searchBestResult(String search) throws BestResultNotFound {
        Searchable result = null;
        int maxQuantity = 0;
        for (int i = 0; i < Searchable.length; i++) {
            if (Searchable[i] != null) {
                int quantity = 0;
                int index = 0;
                int substringIndex = Searchable[i].getSearchTerm().indexOf(search, index);

                while(substringIndex != -1){
                    quantity++;
                    index = substringIndex + search.length();
                    substringIndex = Searchable[i].getSearchTerm().indexOf(search, index);
                }

                if (quantity > maxQuantity) {
                    maxQuantity = quantity;
                    if (Searchable[i] != null) {
                        result = Searchable[i];
                    }
                }
            }
        }
        if (result == null) {
            throw new BestResultNotFound("Для запроса \"" + search + "\" не нашлось подходящей статьи");
        }
        return result;
    }
}
