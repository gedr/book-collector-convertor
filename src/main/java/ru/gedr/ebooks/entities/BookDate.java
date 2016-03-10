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
    private static final int DAY_MASK = 0x1f;

    private int value = 0;

    public BookDate() {
    }

    public BookDate(int year) {
        setYear(year);
    }

    public BookDate(int year, int period, PeriodType type) {
        setYear(year);
        switch (type) {
            case MONTH:
                setMonth(period);
                break;
            case QUARTER:
                setQuarter(period);
                break;
            case HALFYEAR:
                setHalfyear(period);
                break;
        }
    }

    public BookDate(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public void setYear(int year) {
        Validate.isTrue((0 <= year) && (year <= YEAR_MAX));
        int tmp = value;
        setValue(year, YEAR_MASK, YEAR_OFFSET);
        restoreValueIfError(tmp, checkDateFileds());
    }

    public int getYear() {
        return (this.value & YEAR_MASK) >> YEAR_OFFSET;
    }

    public void setHalfyear(int halfyear) {
        Validate.isTrue((0 <= halfyear) && (halfyear <= 2));
        int tmp = value;
        setValue(halfyear, HALFYEAR_MASK, HALFYEAR_OFFSET);
        restoreValueIfError(tmp, checkDateFileds());
    }

    public int getHalfyear() {
        return (this.value & HALFYEAR_MASK) >> HALFYEAR_OFFSET;
    }

    public void setQuarter(int quarter) {
        Validate.isTrue((0 <= quarter) && (quarter <= 4));
        int tmp = value;
        if (quarter != 0) {
            setValue((quarter - 1) / 2 + 1, HALFYEAR_MASK, HALFYEAR_OFFSET);
        }
        setValue(quarter, QUARTER_MASK, QUARTER_OFFSET);
        restoreValueIfError(tmp, checkDateFileds());
    }

    public int getQuarter() {
        return (this.value & QUARTER_MASK) >> QUARTER_OFFSET;
    }

    public void setMonth(int month) {
        Validate.isTrue((0 <= month) && (month <= 12));
        int tmp = value;
        if (month != 0) {
            setValue((month - 1) / 6 + 1, HALFYEAR_MASK, HALFYEAR_OFFSET);
            setValue((month - 1) / 3 + 1, QUARTER_MASK, QUARTER_OFFSET);
        }
        setValue(month, MONTH_MASK, MONTH_OFFSET);
        restoreValueIfError(tmp, checkDateFileds());
    }

    public int getMonth() {
        return (this.value & MONTH_MASK) >> MONTH_OFFSET;
    }

    public void setDay(int day) {
        Validate.isTrue((0 <= day) && (day <= 31));
        int tmp = value;
        setValue(day, DAY_MASK, DAY_OFFSET);
        restoreValueIfError(tmp, checkDateFileds());
    }

    public int getDay() {
        return (this.value & DAY_MASK);
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

    public int getIntValue() {
        return this.value;
    }
    
    public static BookDate from(int value) {
        BookDate bd = new BookDate();
        bd.value = value;
        bd.restoreValueIfError(0, bd.checkDateFileds());
        return bd;
    }

    private void restoreValueIfError(int prevValue, String err) {
        if (err != null) {
            value = prevValue;
            throw new IllegalArgumentException(err);
        }
    }

    private String checkDateFileds() {
        if (!checkQuarter()) {
            return "Illegal quarter field";
        }
        if (!checkMonth()) {
            return "Illegal month field";
        }
        if (!checkDay()) {
            return "Illegal day field";
        }
        return null;
    }

    private boolean checkQuarter() {
        boolean res = true;
        if (getQuarter() != 0) {
            res = ((getHalfyear() == 1) && (1 <= getQuarter()) && (getQuarter() <= 2))
                    || ((getHalfyear() == 2) && (3 <= getQuarter()) && (getQuarter() <= 4));
        }
        return res;
    }

    private boolean checkMonth() {
        System.out.println("halfyear = " + getHalfyear() + ";  quarter = " + getQuarter() + ";  month = " + getMonth());
        boolean res = true;
        if (getMonth() != 0) {
            res = ( ((getHalfyear() == 1) && (1 <= getMonth()) && (getMonth() <= 6))
                    || ((getHalfyear() == 2) && (7 <= getMonth()) && (getMonth() <= 12)) )
                    && 
                    ( ((getQuarter() == 1) && (1 <= getMonth()) && (getMonth() <= 3))
                    || ((getQuarter() == 2) && (4 <= getMonth()) && (getMonth() <= 6))
                    || ((getQuarter() == 3) && (7 <= getMonth()) && (getMonth() <= 9))
                    || ((getQuarter() == 4) && (10 <= getMonth()) && (getMonth() <= 12)) );
        }
        return res;
    }

    private boolean checkDay() {
        boolean res = true;
        if (getDay() != 0) {
            System.out.println("checkday " + getDay());
            res = (1 <= getDay()) && (getDay() <= getDayCountInMonth());
        }
        return res;
    }
    
    private int setValue(int v, int mask, int offset) {
        value &= ~mask;
        value |= v << offset;
        return value;
    }

    public enum PeriodType {
        MONTH, QUARTER, HALFYEAR
    }
}