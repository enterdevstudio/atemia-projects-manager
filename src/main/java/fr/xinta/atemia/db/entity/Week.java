package fr.xinta.atemia.db.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Week extends AbstractEntity {
    
    private int num;
    private int yearOfWeek;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Activity> activities;
    
    public Week() {
	activities = new ArrayList<Activity>();
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
    
    public List<Activity> getActivities() {
	return activities;
    }
}
