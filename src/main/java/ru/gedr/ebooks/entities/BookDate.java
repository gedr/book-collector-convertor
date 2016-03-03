/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import org.apache.commons.lang3.Validate;

/**
 *
 * @author egafarov
 */
public final class BookDate {
    private static final int MAX_YEAR_VALUE = 0x0000FFFF;
    private static final int YEAR_OFFSET = 14;
    private int value = 0;
    
    public BookDate(int year) {
        setYear(year);
    }
    
    public void setYear(int year) {
        Validate.isTrue(((0 <= year) && (year <= MAX_YEAR_VALUE)));
        this.value = year << YEAR_OFFSET;
    }
    
    public int getIntValue() {
        return this.value;
    }
}
