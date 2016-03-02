/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.text.StrBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.gedr.ebooks.parsers.listeners.BookCompleteEventListener;
import ru.gedr.ebooks.entities.Book;
import ru.gedr.ebooks.parsers.helpers.BookCreator;

/**
 *
 * @author egafarov
 */
public class CatalogXmlParser extends DefaultHandler {
    private final StrBuilder text = new StrBuilder();
    private final StrBuilder path = new StrBuilder("");
    private final List<BookCompleteEventListener> lst = new ArrayList<>();
    private final Map<String, String> attrs = new TreeMap<>();
    private Book book = null;
    

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        path.append("/").append(qName.toLowerCase());
        if (qName.equalsIgnoreCase("book")) {
             book = new Book();
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            attrs.put(attributes.getQName(i), attributes.getValue(i));
        }
        text.clear();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        BookCreator.configureBook(book, path.toString(), text.toString(), attrs);
        attrs.clear();
        path.delete(path.lastIndexOf("/"), path.length());
        if (qName.equalsIgnoreCase("book")) {
            fireBookCompleteEvent(book);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(Arrays.copyOfRange(ch, start, start + length));
    }
    
    public void addBookCompleteEventListener(BookCompleteEventListener listener) {
        lst.add(listener);
    }

    public void removeBookCompleteEventListener(BookCompleteEventListener listener) {
        lst.remove(listener);
    }
    
    protected void fireBookCompleteEvent(Book book) {
        lst.stream().forEach((BookCompleteEventListener event)-> {event.complete(book);});
    }
}