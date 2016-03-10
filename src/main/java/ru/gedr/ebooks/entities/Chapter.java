/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author egafarov
 */
@NoArgsConstructor
@ToString
@Getter 
@Setter
@Entity
@Table(name = "chapters")
@DiscriminatorValue("3")
public class Chapter extends Collection {
    @Column(name = "start_page")
    private int startPage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "book_id", referencedColumnName = "id")
    private Book collection;

    
}
