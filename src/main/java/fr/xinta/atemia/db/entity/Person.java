package fr.xinta.atemia.db.entity;

import fr.xinta.atemia.db.facade.Utils;
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
    private List<Project> projects = new ArrayList<Project>();
    @OneToMany(mappedBy="manager")
    private List<Project> managedProjects = new ArrayList<Project>();
    @OneToMany(mappedBy="worker")
    private List<Activity> activities = new ArrayList<Activity>();

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
    
    public List<Project> getManagedProjects() {
        return managedProjects;
    }

    public List<Activity> getActivities() {
        return activities;
    }
    
    public int getNbDaysAffected() {
        int nb = 0;
        for (Activity a : activities) {
            nb += a.getNbDaysWork();
        }
        return nb;
    }
    
    /**
     * @param week the week looked
     * @return the number of days this person work for a given week
     */
    public int getNbDaysAffected(Week week) {
        int nb = 0;
        for (Activity a : activities) {
            if (a.getWeek().compare(week) == 0) {
                nb += a.getNbDaysWork();
            }
        }
        return nb;
    }
    
    
    public List<Week> getWeeks() {
        if (getActivities().isEmpty())
            return null;
        
        Week startWeek = getActivities().get(0).getWeek();
        Week endWeek = getActivities().get(0).getWeek();
        
        for (Activity activity : getActivities()) {
            if (startWeek.compare(activity.getWeek()) < 0) {
                startWeek = activity.getWeek();
            }
            if (endWeek.compare(activity.getWeek()) > 0) {
                endWeek = activity.getWeek();
            }
        }
        
        return Utils.getWeeks(startWeek, endWeek);
    }
}
