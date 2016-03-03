/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.parsers.helpers;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import ru.gedr.ebooks.entities.Book;

/**
 *
 * @author egafarov
 */
public class BookCreator {
    private static final BookCreator instance = new BookCreator();
    private static final String[] pathPart = {
        "book/mainsection/title",       // 0
        "book/mainsection/subtitle",    // 1

        "book/rare",                    // 2
    };
    
    public static void configureBook(Book book, String path, String text, Map<String, String> attrs) {
        if ((book == null) || StringUtils.isBlank(path) || StringUtils.isBlank(text)) {
            return;
        }
        int ind = BookCreator.instance.processPath(path);
        if (ind < 0) {
            return;
        }
        BookCreator.instance.fillBookField(book, ind, text);
    }

    private int processPath(String path) {
        for (int i = 0; i < pathPart.length; i++) {
            if (path.endsWith(pathPart[i])) {
                return i;
            }
        }
        return -1;
    }
    
    private void fillBookField(Book book, int ind, String text) {
        switch(ind) {
            case 0:
                book.setTitle(text);
                break;
            case 1:
                book.setSubTitle(text);
                break;
            case 2:
                book.setRare(toBool(text));
                break;
        }
            
    }
    
    private boolean toBool(String txt) {
        if ("no".equalsIgnoreCase(txt.trim())) {
            return false;
        }
        if ("yes".equalsIgnoreCase(txt.trim())) {
            return true;
        }
        throw new IllegalArgumentException("cann't convert [" + txt +"] to boolean" );
    }
}
