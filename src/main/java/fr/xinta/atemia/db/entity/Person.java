package fr.xinta.atemia.db.entity;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Person extends AbstractEntity {    
    
    private String firstName;
    private String lastName;
    private int nbDaysAvailable;
    private boolean isAdmin;
    
    @ManyToMany(mappedBy="workers")
    private List<Project> projects;
    @OneToMany(mappedBy="worker")
    private List<Activity> activities;
    
    public Person() {
        projects = new ArrayList<Project>();
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public int getNbDaysAvailable() {
        return nbDaysAvailable;
    }

    public void setNbDaysAvailable(int nbDaysAvailableByYear) {
        this.nbDaysAvailable = nbDaysAvailableByYear;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Activity> getActivities() {
        return activities;
    }
    
    public int getAffectedDays() {
        int nb = 0;
        for (Activity a : getActivities()) {
            nb += a.getNbDaysWork();
        }
        return nb;
    }
}
