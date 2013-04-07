package fr.xinta.atemia.db.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Week extends AbstractEntity {
    
    private int num;
    private int yearOfWeek;
    /* the index of the list corresponds to the number of the person
     * the array key is the period (which is an enum)
     * the array value is the number of days of this period worked this week
     * by the worker num the index.
     */
    //@OneToMany
    //private List<int[]> job;
    
    public Week() {
	//job = new ArrayList<int[]>();
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
    
//    public List<int[]> getJob() {
//	return job;
//    }
}
