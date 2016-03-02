/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.parsers.listeners;

import java.util.EventListener;
import ru.gedr.ebooks.entities.Book;

/**
 *
 * @author egafarov
 */
public interface BookCompleteEventListener extends EventListener {
    void complete(Book book);
}
