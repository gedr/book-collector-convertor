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

/**
 *
 * @author egafarov
 */
@ToString
public class Collection {
    private @Getter @Setter Integer id;
    private @Getter @Setter String title;
    private @Getter @Setter String subTitle;
    private @Getter @Setter Date createDate;
    private @Getter @Setter Date editDate;
    private @Getter @Setter Date readDate;
    private @Getter @Setter Double rate;
    private @Getter @Setter String tags;
    private @Getter @Setter Memo notes;
    private @Getter @Setter Memo plot;
    private short variant;
    
    protected Collection(CollectionVariant variant) {
        setVariant(variant);
    }
    
    public Collection() {
        this(CollectionVariant.COLLECTION);
    }
            
    public CollectionVariant getVariant() {
        return CollectionVariant.find(variant);
    }

    public void setVariant(CollectionVariant variant) {
        this.variant = variant.getValue();
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