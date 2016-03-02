/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ed
 */
public class BookTest {
    
    public BookTest() {
    }
    
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

    /**
     * Test of setPublicationDate method, of class Book.
     */
    @Test
    public void testSetPublicationDate() {
        System.out.println("testSetPublicationDate");
        Book inst = new Book();
        inst.setPublicationDate(2015, Calendar.JANUARY, 2);
        assertTrue(inst.isPublicationDateHaveYearMonthDay());
        assertFalse(inst.isPublicationDateHaveYear());
        assertFalse(inst.isPublicationDateHaveYearMonth());
        assertFalse(inst.isPublicationDateHaveYearQuarter());
        assertFalse(inst.isPublicationDateHaveYearHalfyear());
    }

    /**
     * Test of setPublicationDateOnlyYear method, of class Book.
     */
    @Test
    public void testSetPublicationDateOnlyYear() {
        System.out.println("testSetPublicationDateOnlyYear");
        Book inst = new Book();
        inst.setPublicationDateOnlyYear(2016);
        assertFalse(inst.isPublicationDateHaveYearMonthDay());
        assertTrue(inst.isPublicationDateHaveYear());
        assertFalse(inst.isPublicationDateHaveYearMonth());
        assertFalse(inst.isPublicationDateHaveYearQuarter());
        assertFalse(inst.isPublicationDateHaveYearHalfyear());
    }

    /**
     * Test of setPublicationDateOnlyYearAndHalfYear method, of class Book.
     */
    @Test
    public void testSetPublicationDateOnlyYearAndHalfYear() {
        System.out.println("testSetPublicationDateOnlyYearAndHalfYear");
        Book inst = new Book();
        inst.setPublicationDateOnlyYearAndHalfYear(2016, 2);
        assertFalse(inst.isPublicationDateHaveYearMonthDay());
        assertFalse(inst.isPublicationDateHaveYear());
        assertFalse(inst.isPublicationDateHaveYearMonth());
        assertFalse(inst.isPublicationDateHaveYearQuarter());
        assertTrue(inst.isPublicationDateHaveYearHalfyear());
    }

    /**
     * Test of setPublicationDateOnlyYearAndQuarter method, of class Book.
     */
    @Test
    public void testSetPublicationDateOnlyYearAndQuarter() {
        System.out.println("testSetPublicationDateOnlyYearAndQuarter");
        Book inst = new Book();
        inst.setPublicationDateOnlyYearAndQuarter(2016, 3);
        assertFalse(inst.isPublicationDateHaveYearMonthDay());
        assertFalse(inst.isPublicationDateHaveYear());
        assertFalse(inst.isPublicationDateHaveYearMonth());
        assertTrue(inst.isPublicationDateHaveYearQuarter());
        assertFalse(inst.isPublicationDateHaveYearHalfyear());
    }

    /**
     * Test of setPublicationDateOnlyYearAndMonth method, of class Book.
     */
    @Test
    public void testSetPublicationDateOnlyYearAndMonth() {
        System.out.println("testSetPublicationDateOnlyYearAndMonth");
        Book inst = new Book();
        inst.setPublicationDateOnlyYearAndMonth(2015, 9);
        assertFalse(inst.isPublicationDateHaveYearMonthDay());
        assertFalse(inst.isPublicationDateHaveYear());
        assertTrue(inst.isPublicationDateHaveYearMonth());
        assertFalse(inst.isPublicationDateHaveYearQuarter());
        assertFalse(inst.isPublicationDateHaveYearHalfyear());
    }
}
