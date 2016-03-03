/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.gedr.ebooks.entities;

import java.util.Arrays;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author egafarov
 */
public final class BookDate {
    private static final int YEAR_MAX = 0x0000FFFF;
    private static final int YEAR_OFFSET = 14;
    private static final int YEAR_MASK = 0x0000FFFF << YEAR_OFFSET;
    private static final int HALFYEAR_OFFSET = 12;
    private static final int HALFYEAR_MASK = 3 << HALFYEAR_OFFSET;
    private static final int QUARTER_OFFSET = 9;
    private static final int QUARTER_MASK = 7 << QUARTER_OFFSET;
    private static final int MONTH_OFFSET = 5;
    private static final int MONTH_MASK = 0xf << MONTH_OFFSET;
    private static final int DAY_OFFSET = 0;
    private static final int DAY_MASK = 0x1f << MONTH_OFFSET;

    private int value = 0;

    public BookDate() {
    }
    
    public BookDate(int year) {
        setYear(year);
    }

    public BookDate(int year, int period, PeriodType type) {
        setYear(year);
        switch(type) {
            case MONTH :
                setMonth(period);
                break;
            case QUARTER :
                setQuarter(period);
                break;
            case HALFYEAR :
                setHalfyear(period);
                break;
        }
    }
    
    public void setYear(int year) {
        Validate.isTrue((0 <= year) && (year <= YEAR_MAX));
        this.value &= ~YEAR_MASK;
        this.value |= year << YEAR_OFFSET;
    }
    
    public int getYear() {
        return (this.value & YEAR_MASK) >> YEAR_OFFSET;
    }

    public void setHalfyear(int halfyear) {
        Validate.isTrue((1 <= halfyear) && (halfyear <= 2));
        if (getQuarter() != 0) {
            Validate.isTrue((halfyear == 1) && (1 <= getQuarter()) && (getQuarter() <= 2));
            Validate.isTrue((halfyear == 2) && (3 <= getQuarter()) && (getQuarter() <= 4));
        }

        this.value &= ~HALFYEAR_MASK;
        this.value |= halfyear << YEAR_OFFSET;

    }

    public int getHalfyear() {
        return (this.value & HALFYEAR_MASK) >> HALFYEAR_OFFSET;
    }

    public void setQuarter(int quarter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getQuarter() {
        return (this.value & QUARTER_MASK) >> QUARTER_OFFSET;
    }

    
    public int getIntValue() {
        return this.value;
    }

    public void setMonth(int month) {
        Validate.isTrue((1 <= month) && (month <= 12));
        this.value &= ~MONTH_MASK;
        this.value |= month << MONTH_OFFSET;
        if (month < 7) {
            setHalfyear(1);
            setQuarter(month < 4 ? 1 : 2);
        } else {
            setHalfyear(2);
            setQuarter(month < 10 ? 1 : 2);
        }
    }
    
    public int getMonth() {
        return (this.value & MONTH_MASK) >> MONTH_OFFSET;
    }

    private int getDay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public boolean isMonthHave31Days() {
        return (getMonth() == 1) || (getMonth() == 3) || (getMonth() == 5) || (getMonth() == 7) || (getMonth() == 8) 
                || (getMonth() == 10) || (getMonth() == 12);
    }

    public boolean isMonthHave30Days() {
        return (getMonth() == 4) || (getMonth() == 6) || (getMonth() == 9) || (getMonth() == 11);
    }

    public boolean isFebruaryHave29Days() {
        return isLeapYear();
    }
    
    public int getDayCountInMonth() {
        if (getMonth() == 0) {
            return 0;
        }
        if (isMonthHave31Days()) {
            return 31;
        }
        if (isMonthHave30Days()) {
            return 30;
        }
        return isFebruaryHave29Days() ? 29 : 28;
    }
    
    public boolean isLeapYear() {
        int divisor = (getYear() % 100 != 0 ? 4 : 400);
        return getYear() % divisor == 0; 
    }

    
    /**
     *
     * @return
     */
    private boolean checkQuarter() {
        boolean res = true;
        if ((getHalfyear() != 0) && (getQuarter() != 0)){
            res = ((getHalfyear() == 1) && (1 <= getQuarter()) && (getQuarter() <= 2))
                    || ((getHalfyear() == 2) && (3 <= getQuarter()) && (getQuarter() <= 4));
        }
        return res;
    }

    private boolean checkMonth() {
        boolean res = true;
        if ((getHalfyear() != 0) && (getMonth() != 0)){
            res = ((getHalfyear() == 1) && (1 <= getMonth()) && (getMonth() <= 6))
                    || ((getHalfyear() == 2) && (7 <= getMonth()) && (getMonth() <= 12));
        }
        if (res && (getQuarter() != 0) && (getMonth() != 0)) {
            res = ((getQuarter() == 1) && (1 <= getMonth()) && (getMonth() <= 3))
                    || ((getQuarter() == 2) && (4 <= getMonth()) && (getMonth() <= 6))
                    || ((getQuarter() == 3) && (7 <= getMonth()) && (getMonth() <= 9))
                    || ((getQuarter() == 4) && (10 <= getMonth()) && (getMonth() <= 12));
        }
        return res;
    }

    private boolean checkDay() {
        boolean res = true;
        if ((getMonth()!= 0) && (getDay() != 0)){
            res = (1 <= getDay()) && (getDay() <= getDayCountInMonth());
        }
        return res;
    }


    public enum PeriodType {
        MONTH, QUARTER, HALFYEAR
    }
}
