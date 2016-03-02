/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities.eums;

/**
 *
 * @author egafarov
 */
public enum CollectionVariant {
    COLLECTION((short) 1),
    BOOK((short) 2),
    CONTENT((short) 3);
    
    private final short value;
    
    private CollectionVariant(short value) {
        this.value = value;
    }
    
    public short getValue() {
        return this.value;
    }
    
    public static final CollectionVariant find(short val) {
        for (CollectionVariant e : CollectionVariant.values()) {
            if (e.getValue() == val) {
                return e;
            }
        }
        throw new IllegalArgumentException("No enum const CollectionVariant with id = " + val);
    }
}