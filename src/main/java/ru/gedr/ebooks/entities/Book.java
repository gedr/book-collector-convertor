/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author egafarov
 */
@NoArgsConstructor
@ToString
@Getter 
@Setter
@Entity
@Table(name = "books")
@DiscriminatorValue("2")
public class Book extends Collection {
    @Column(name = "pub_date")
    private int publicationDate;
    @Column(name = "qunatity")
    private int qunatity;
    @Column(name = "rare")
    private boolean rare;
    @Column(name = "volume")
    private int volume;
    @Column(name = "issue")
    private int issue;
    @Column(name = "num_of_pages")
    private int numberOfPages;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "collection_id", referencedColumnName = "id")
    private Collection collection;
    
    @OneToMany
    private List<Chapter> chapters;
    
    
    
    public void setPublicationDate(int year, int month, int day) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((1 <= month) && (month <= 12), "month have illegal value");
        Validate.isTrue((0 < day) && (day < 32), "day have illegal value");
        publicationDate = new BookDate(year, month, day).getIntValue();
    }
    
    public void setPublicationDateOnlyYear(int year) {
        Validate.isTrue(year > 0, "year have illegal value");
        publicationDate = new BookDate(year).getIntValue();
    }

    public void setPublicationDateOnlyYearAndHalfYear(int year, int halfyear) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((0 < halfyear) && (halfyear < 3), "halfyear have illegal value");
        publicationDate = new BookDate(year, halfyear, BookDate.PeriodType.HALFYEAR).getIntValue();
    }
    
    public void setPublicationDateOnlyYearAndQuarter(int year, int quarter) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((0 < quarter) && (quarter < 5), "quarter have illegal value");
        publicationDate = new BookDate(year, quarter, BookDate.PeriodType.QUARTER).getIntValue();
    }

    public void setPublicationDateOnlyYearAndMonth(int year, int month) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((1 <= month) && (month <= 12), "month have illegal value");
        publicationDate = new BookDate(year, month, BookDate.PeriodType.MONTH).getIntValue();
    }

    public boolean isPublicationDateHaveYearMonthDay() {
        return BookDate.from(publicationDate).getDay() != 0;
    }

    public boolean isPublicationDateHaveYearMonth() {
        return BookDate.from(publicationDate).getMonth() != 0;
    }

    public boolean isPublicationDateHaveYearQuarter() {
        return BookDate.from(publicationDate).getQuarter() != 0;
    }

    public boolean isPublicationDateHaveYearHalfyear() {
        return BookDate.from(publicationDate).getHalfyear() != 0;
    }

    public boolean isPublicationDateHaveYear() {
        return BookDate.from(publicationDate).getYear() != 0;
    }
    
}


/*
CREATE TABLE BOOK (
book_id 		INTEGER PRIMARY KEY,

--  м/о отмечать не т/о конкретные даты, но и периоды (месяц, квартал, полугодие)
pub_date	INTEGER DEFAULT NULL, 

quantity		INTEGER DEFAULT 1 NOT NULL,
rare			BOOLEAN DEFAULT FALSE NOT NULL,
volume			INTEGER DEFAULT 0 NOT NULL,
issue			INTEGER DEFAULT 0 NOT NULL,
page_count		INTEGER DEFAULT 0 NOT NULL CHECK(page_count>=0), 
edition_id		INTEGER DEFAULT NULL,
series_id		INTEGER DEFAULT NULL,
country_id		INTEGER DEFAULT NULL,
language_id		INTEGER DEFAULT NULL,
collection_id	INTEGER DEFAULT NULL,
book_status_id	INTEGER DEFAULT NULL
);
*/