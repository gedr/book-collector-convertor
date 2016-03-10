/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.gedr.ebooks.entities.eums.CollectionVariant;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author egafarov
 */
@Getter 
@Setter
@ToString
@Entity
@Table(name = "collections")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "variant")
@DiscriminatorValue("1")
public class Collection {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "subTitle")
    private String subTitle;
    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "editDate")
    private Date editDate;
    @Column(name = "readDate")
    private Date readDate;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "tags")
    private String tags;

    @OneToOne()
    @JoinColumn(columnDefinition = "notes_id", referencedColumnName = "id")
    private Memo notes;

    @OneToOne()
    @JoinColumn(columnDefinition = "plot_id", referencedColumnName = "id")
    private Memo plot;
    
    public Collection() {
    }
}

/*
CREATE TABLE COLLECTION (
collection_id	INTEGER PRIMARY KEY AUTO_INCREMENT,
title			VARCHAR(2000), 
sub_title 		VARCHAR(2000), 
create_date		TIMESTAMP NOT NULL,	-- время создания
edit_date		TIMESTAMP NOT NULL, -- время редактирования
read_date		DATE DEFAULT NULL, 	-- дата прочтения 
rate			REAL DEFAULT NULL CHECK(rate>=0 AND rate<=1), 
key_words		VARCHAR(4000) DEFAULT NULL,
variant			INTEGER NOT NULL, 	-- 1-COLLECTION; 2-BOOK; 3-CONTENT;
notes_id 		INTEGER DEFAULT NULL,
plot_id 		INTEGER DEFAULT NULL
);


CREATE TABLE CONTENT (
content_id 	INTEGER PRIMARY KEY,
start_page	INTEGER NOT NULL CHECK(start_page>=0),
book_id 	INTEGER DEFAULT NULL
);
*/