package fr.xinta.atemia.db.entity;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
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
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Week> weeks;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Person> workers;
    
    public Project() {
	weeks = new ArrayList<Week>();
	workers = new ArrayList<Person>();
        startWeek = 1;
        startYear = 2013;
        endWeek = 1;
        endYear = 2013;
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

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public List<Week> getWeeks() {
	return weeks;
    }
    
    public List<Person> getWorkers() {
	return workers;
    }
    
    public int getNbDaysAffected() {        
        int nbDaysAffected = 0;
        for (Week w : getWeeks()) {
            for (Activity a : w.getActivities()) {
                nbDaysAffected += a.getNbDaysWork();
            }
        }
        return nbDaysAffected;
    }
    
    public int getNbDaysAffected(Long idWorker) {        
        int nbDaysAffected = 0;
        for (Week w : getWeeks()) {
            for (Activity a : w.getActivities()) {
                if (a.getWorker().getId() == idWorker) {
                    nbDaysAffected += a.getNbDaysWork();
                }
            }
        }
        return nbDaysAffected;
    }
    
    public void AddWorker(Person worker) {
        
	workers.add(worker);
        worker.getProjects().add(this);
        for (Week week : getWeeks()) {
            Activity activity = new Activity();
            activity.setProduction(5); //We put 5 days in production by default
            activity.setWorker(worker);
            worker.getActivities().add(activity);
            week.getActivities().add(activity);
        }
    }
    
    public void initWeeks(int sw, int sy, int ew, int ey) {
        startWeek = sw;
        startYear = sy;
        endWeek = ew;
        endYear = ey;
        
        int week = endWeek;
        int year = endYear;
        // Add weeks between end date and start date, starting by the end
        // to have an ordered list (add at the head is O(1))
        while ((year == startYear && week >= startWeek)
                || year > startYear) {
            
            Week w = new Week();
            w.setNumber(week);
            w.setYear(year);
            weeks.add(0, w);
            
            if (week > 1) {
                week--;
            } else {
                week = 52;
                year--;
            }
        }
    }
    
    //TODO the case where the number of weeks is reduce is not treated
    public void updateStartWeek(int newStartWeek, int newStartYear) {
        
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
        
        startWeek = newStartWeek;
        startYear = newStartYear;
    }
    
    //TODO the case where the number of weeks is reduce is not treated
    public void updateEndWeek(int newEndWeek, int newEndYear) {
        
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
        
        endWeek = newEndWeek;
        endYear = newEndYear;
    }
}
