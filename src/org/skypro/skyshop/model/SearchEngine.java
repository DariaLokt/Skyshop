package org.skypro.skyshop.model;

public class SearchEngine {
     Searchable[] Searchable;
     int size;

    public SearchEngine(int size) {
        this.size = size;
        Searchable = new Searchable[size];
    }

    public Searchable[] search(String searchTerm) {
        Searchable[] results = new Searchable[5];
//        Searchable blank = new Article("-", "-");
        int count = 0;
        for (int i = 0; i < Searchable.length; i++) {
            if (Searchable[i] != null && Searchable[i].getSearchTerm().contains(searchTerm) && count < 5) {
                results[count] = Searchable[i];
                count++;
            }
        }
//        if (count < 5) {
//            while (count != 5) {
//                results[count] = blank;
//                count++;
//            }
//        }
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
}
