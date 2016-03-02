/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author egafarov
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter 
@Setter
public class Memo {
    private int id;
    private String text;
}

