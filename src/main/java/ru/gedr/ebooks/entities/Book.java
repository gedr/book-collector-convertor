/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import java.util.Calendar;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.Validate;
import ru.gedr.ebooks.entities.eums.CollectionVariant;

/**
 *
 * @author egafarov
 */
@ToString
public class Book extends Collection {
    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
    private Date publicationDateStart;
    private Date publicationDateStop;
    private @Getter @Setter int qunatity;
    private @Getter @Setter boolean rare;
    private @Getter @Setter int volume;
    private @Getter @Setter int issue;
    private @Getter @Setter int numberOfPages;
    private @Getter @Setter Collection collection;
    
    public Book() {
        super(CollectionVariant.BOOK);
    }

    public void setPublicationDate(int year, int month, int day) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((Calendar.JANUARY <= month) && (month <= Calendar.DECEMBER), "month have illegal value");
        Validate.isTrue((0 < day) && (day < 32), "day have illegal value");
        
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        publicationDateStart = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        publicationDateStop = c.getTime();
    }
    
    public void setPublicationDateOnlyYear(int year) {
        Validate.isTrue(year > 0, "year have illegal value");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        publicationDateStart = c.getTime();
        c.add(Calendar.YEAR, 1);
        publicationDateStop = c.getTime();
    }

    public void setPublicationDateOnlyYearAndHalfYear(int year, int halfyear) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((0 < halfyear) && (halfyear < 3), "halfyear have illegal value");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY + (halfyear - 1) * 6);        
        publicationDateStart = c.getTime();
        c.add(Calendar.MONTH, 6);
        publicationDateStop = c.getTime();
    }
    
    public void setPublicationDateOnlyYearAndQuarter(int year, int quarter) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((0 < quarter) && (quarter < 5), "quarter have illegal value");
        
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY + (quarter - 1) * 3);
        publicationDateStart = c.getTime();
        c.add(Calendar.MONTH, 3);
        publicationDateStop = c.getTime();
    }

    public void setPublicationDateOnlyYearAndMonth(int year, int month) {
        Validate.isTrue(year > 0, "year have illegal value");
        Validate.isTrue((Calendar.JANUARY <= month) && (month <= Calendar.DECEMBER), "month have illegal value");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        publicationDateStart = c.getTime();
        c.add(Calendar.MONTH, 1);
        publicationDateStop = c.getTime();
    }

    public boolean isPublicationDateHaveYearMonthDay() {
        if ((publicationDateStart == null) || (publicationDateStop == null)) {
            return false;
        }
        return (publicationDateStop.getTime() - publicationDateStart.getTime()) < DAY_IN_MS * 2;
    }

    public boolean isPublicationDateHaveYearMonth() {
        if ((publicationDateStart == null) || (publicationDateStop == null)) {
            return false;
        }
        long diff = publicationDateStop.getTime() - publicationDateStart.getTime();
        return ((DAY_IN_MS < diff) && (diff < DAY_IN_MS * 32));
    }

    public boolean isPublicationDateHaveYearQuarter() {
        if ((publicationDateStart == null) || (publicationDateStop == null)) {
            return false;
        }
        long diff = publicationDateStop.getTime() - publicationDateStart.getTime();
        return ((DAY_IN_MS * 32 < diff) && (diff < DAY_IN_MS * 32 * 3));
    }

    public boolean isPublicationDateHaveYearHalfyear() {
        if ((publicationDateStart == null) || (publicationDateStop == null)) {
            return false;
        }
        long diff = publicationDateStop.getTime() - publicationDateStart.getTime();
        return ((DAY_IN_MS * 32 * 3 < diff) && (diff < DAY_IN_MS * 32 * 6));
    }

    public boolean isPublicationDateHaveYear() {
        if ((publicationDateStart == null) || (publicationDateStop == null)) {
            return false;
        }
        long diff = publicationDateStop.getTime() - publicationDateStart.getTime();
        return ((DAY_IN_MS * 32 * 6 < diff) && (diff < DAY_IN_MS * 32 * 12));
    }
    
}


/*
CREATE TABLE BOOK (
book_id 		INTEGER PRIMARY KEY,

--  м/о отмечать не т/о конкретные даты, но и периоды (месяц, квартал, полугодие)
pub_date_start	INTEGER DEFAULT NULL, 
pub_date_stop	INTEGER DEFAULT NULL,

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