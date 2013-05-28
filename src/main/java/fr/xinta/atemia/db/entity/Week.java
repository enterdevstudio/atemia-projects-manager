package fr.xinta.atemia.db.entity;

import java.io.Serializable;

public class Week implements Serializable{
    
    private int num;
    private int yearOfWeek;

    public Week() {}
    
    public Week(int num, int year) {
        this.num = num;
        this.yearOfWeek = year;
    }
    
    public Week(String s) throws Exception {
        num = Integer.parseInt(s.substring(6, 8));
        yearOfWeek = Integer.parseInt(s.substring(0, 4));
        if (yearOfWeek < 1900 || yearOfWeek > 2500 || num < 1 || num > 52) {
            throw new Exception("Week is not correct (check num < 52)");
        }
    }

    public int getNumber() {
	return num;
    }

    public void setNumber(int num) {
	this.num = num;
    }

    public int getYear() {
        return yearOfWeek;
    }

    public void setYear(int year) {
        this.yearOfWeek = year;
    }
    
    public int compare(Week week) {
        if (week.getYear() == getYear()) {
            return week.getNumber() - getNumber();
        } else {
            return week.getYear() - getYear();
        }
    }

    @Override
    public String toString() {
        return yearOfWeek + "-W" + ((num < 10) ? "0" : "") + num;
    }
}
