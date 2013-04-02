package fr.xinta.atemia.db.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Project extends AbstractEntity {

    private String name;
    private String department;
    private int nbDaysSold;
    @OneToMany
    private HashMap<Integer, Week> weeks;
    @OneToMany
    private List<Person> workers;
    
    public Project() {
	weeks = new HashMap<Integer, Week>();
	workers = new LinkedList<Person>();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDepartment() {
	return department;
    }

    public void setDepartment(String department) {
	this.department = department;
    }

    public int getNbDaysSold() {
	return nbDaysSold;
    }

    public void setNbDaysSold(int nbHoursSold) {
	this.nbDaysSold = nbHoursSold;
    }

    public HashMap<Integer, Week> getWeeks() {
	return weeks;
    }
    
    public List<Person> getWorkers() {
	return workers;
    }
    
    public void AddWorker(Person worker, int startWeek, int endWeek) {
	workers.add(worker);
	
	for (int i = startWeek; i <= endWeek; i++) {
	    Week week = weeks.get(i);
	    if (week == null) {
		week = new Week();
		week.setNumber(i);
		weeks.put(i, week);
	    }
	    
	    week.getJob().put(worker, Period.PRODUCTION);
	}
    }
}
