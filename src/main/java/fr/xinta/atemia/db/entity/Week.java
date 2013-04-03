package fr.xinta.atemia.db.entity;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Week extends AbstractEntity {
    
    private int num;
    private int year;
    private ArrayList<int[]> job;
    //the index of the list corresponds to the number of the person
    //the array key is the period (which is an enum)
    //the array value is the number of days of this period worked this week
    //by the worker num the index.
    
    public Week() {
	job = new ArrayList<int[]>();
    }

    public int getNumber() {
	return num;
    }

    public void setNumber(int num) {
	this.num = num;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public ArrayList<int[]> getJob() {
	return job;
    }
}
