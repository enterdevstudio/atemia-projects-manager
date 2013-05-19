package fr.xinta.atemia.db.entity;

import fr.xinta.atemia.db.facade.Utils;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project extends AbstractEntity {

    private String name;
    private String department;
    private float nbDaysSold;
    private Week startWeek;
    private Week endWeek;
    private boolean status;

    @ManyToOne
    private Person manager;
    @OneToMany(mappedBy="project")
    private List<Activity> activities = new ArrayList<Activity>();
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Person> workers = new ArrayList<Person>();

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

    public float getNbDaysSold() {
	return nbDaysSold;
    }

    public void setNbDaysSold(float nbDaysSold) {
	this.nbDaysSold = nbDaysSold;
    }

    public Week getStartWeek() {
        return startWeek;
    }

    public Week getEndWeek() {
        return endWeek;
    }

    public void setStartWeek(Week startWeek) {
        this.startWeek = startWeek;
    }

    public void setEndWeek(Week endWeek) {
        this.endWeek = endWeek;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }
    
    public List<Person> getWorkers() {
	return workers;
    }
    
    public List<Activity> getActivities() {
	return activities;
    }
    
    public Activity getActivity(Person worker, Week week) {
        for (Activity activity : worker.getActivities()) {
            if (activity.getWeek().compare(week) == 0)
                return activity;
        }
        return null;
    }
    
    public int getNbDaysAffected() {        
        int nbDaysAffected = 0;
        for (Activity a : getActivities()) {
            nbDaysAffected += a.getNbDaysWork();
        }
        return nbDaysAffected;
    }
    
    public int getNbDaysAffected(Long idWorker) {        
        int nbDaysAffected = 0;
        for (Activity a : getActivities()) {
            if (a.getWorker().getId() == idWorker) {
                nbDaysAffected += a.getNbDaysWork();
            }
        }
        return nbDaysAffected;
    }
    
    public List<Week> getWeeks() {
        return Utils.getWeeks(startWeek, endWeek);
    }
    
    public void AddWorker(Person worker) {
        
	workers.add(worker);
        worker.getProjects().add(this);
    }
    
    public void AddActivity(Week week, Person worker) {
        Activity activity = new Activity();
        activity.setProduction(5); //We put 5 days in production by default
        activity.setProject(this);
        activity.setWorker(worker);
        worker.getActivities().add(activity);
        activity.setWeek(week);
        getActivities().add(activity);
    }
}
