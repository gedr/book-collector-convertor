/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author egafarov
 */
public class BookDateTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetYearForLegalValue() {
        int year = 2016;
        BookDate bd = new BookDate(year);
        assertThat(bd.getYear(), equalTo(year));
        year = 1960;
        bd.setYear(year);
        assertThat(bd.getYear(), equalTo(year));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetYearForIllegalValue() {
        BookDate bd = new BookDate();
        bd.setYear(2016000);
    }
    
    @Test
    public void testSetMonthForLegalValue() {
        int month = 1;
        BookDate bd = new BookDate();
        bd.setYear(2016);
        bd.setMonth(month);
        assertThat(bd.getMonth(), equalTo(month));
        month = 12;
        bd.setMonth(month);
        assertThat(bd.getMonth(), equalTo(month));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMonthForIllegalValue_13() {
        BookDate bd = new BookDate();
        bd.setYear(2016);
        bd.setMonth(13);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetMonthForIllegalValue_0() {
        BookDate bd = new BookDate();
        bd.setYear(2016);
        bd.setMonth(0);
    }

    @Test
    public void testSetDayForLegalValue() {
        int month = 1;
        BookDate bd = new BookDate();
        bd.setYear(2016);
        bd.setMonth(month);
        bd.set
        assertThat(bd.getMonth(), equalTo(month));
        month = 12;
        bd.setMonth(month);
        assertThat(bd.getMonth(), equalTo(month));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMonthForIllegalValue_13() {
        BookDate bd = new BookDate();
        bd.setYear(2016);
        bd.setMonth(13);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetMonthForIllegalValue_0() {
        BookDate bd = new BookDate();
        bd.setYear(2016);
        bd.setMonth(0);
    }

    @Test
    public void testLeapYear() {
        BookDate bd = new BookDate();
        bd.setYear(2016);
        assertTrue(bd.isLeapYear());
        bd.setYear(1984);
        assertTrue(bd.isLeapYear());
        bd.setYear(1600);
        assertTrue(bd.isLeapYear());
        bd.setYear(2000);
        assertTrue(bd.isLeapYear());
        bd.setYear(2400);
        assertTrue(bd.isLeapYear());

        bd.setYear(1997);
        assertFalse(bd.isLeapYear());
        bd.setYear(2013);
        assertFalse(bd.isLeapYear());
        bd.setYear(1700);
        assertFalse(bd.isLeapYear());
        bd.setYear(1800);
        assertFalse(bd.isLeapYear());
        bd.setYear(1900);
        assertFalse(bd.isLeapYear());
        bd.setYear(2100);
        assertFalse(bd.isLeapYear());
        bd.setYear(2200);
        assertFalse(bd.isLeapYear());
        bd.setYear(2300);
        assertFalse(bd.isLeapYear());
    }
}
