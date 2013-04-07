package fr.xinta.atemia.db.entity;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Project extends AbstractEntity {

    private String name;
    private String department;
    private int nbDaysSold;
    private int startWeek;
    private int startYear;
    private int endWeek;
    private int endYear;
    @OneToMany
    private List<Week> weeks;
    @ManyToMany
    private List<Person> workers;
    
    public Project() {
	weeks = new ArrayList<Week>();
	workers = new ArrayList<Person>();
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

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }
    
    public List<Week> getWeeks() {
	return weeks;
    }
    
    public List<Person> getWorkers() {
	return workers;
    }
    
    public void AddWorker(Person worker) {
        
	workers.add(worker);
        for (Week week : weeks) {
            int[] tab = new int[Period.values().length - 1];
            tab[Period.PRODUCTION.ordinal()] = 5; //We put 5 days in production by default
//            week.getJob().add(workers.size() - 1, tab);
        }
    }
    
    public void changeStartWeek(int newStartWeek, int newStartYear) {
        
        Week firstWeek = weeks.get(0);
        int actualStartWeek = firstWeek.getNumber();
        int actualStartYear = firstWeek.getYear();        
        
        // Add weeks while the previous start date is superior to the new start date
        while ((actualStartYear == newStartYear && actualStartWeek > newStartWeek)
                || actualStartYear > newStartYear) {
            
            if (actualStartWeek > 1) {
                actualStartWeek--;
            } else {
                actualStartWeek = 52;
                actualStartYear--;
            }
            
            Week week = new Week();
            week.setNumber(actualStartWeek);
            week.setYear(actualStartYear);
            weeks.add(0, week);
        }
    }
    
    public void changeEndWeek(int newEndWeek, int newEndYear) {
        
        Week lastWeek = weeks.get(weeks.size() - 1);
        int actualEndWeek = lastWeek.getNumber();
        int actualEndYear = lastWeek.getYear();        
        
        // Add weeks while the previous end date is inferior to the new end date
        while ((actualEndYear == newEndYear && actualEndWeek < newEndWeek)
                || actualEndYear < newEndYear) {
            
            Week week = new Week();
            week.setNumber(actualEndWeek);
            week.setYear(actualEndYear);
            weeks.add(week);
            
            if (actualEndWeek < 52) {
                actualEndWeek++;
            } else {
                actualEndWeek = 1;
                actualEndYear++;
            }
        }
    }
}
