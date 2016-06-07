/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name="memos")
public class Memo implements Serializable {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="txt")
    private String text;
}

