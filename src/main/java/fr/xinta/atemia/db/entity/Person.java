package fr.xinta.atemia.db.entity;

import fr.xinta.atemia.db.facade.Utils;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Person extends AbstractEntity {
    
    private String firstName;
    private String lastName;
    private int nbDaysAvailable;
    private int production;
    private int prospection;
    private int gestion;
    private int communication;
    private boolean isAdmin;
    
    @ManyToMany(mappedBy="workers")
    private List<Project> projects = new ArrayList<Project>();
    @OneToMany(mappedBy="manager")
    private List<Project> managedProjects = new ArrayList<Project>();
    @OneToMany(mappedBy="worker")
    private List<Activity> activities = new ArrayList<Activity>();
    private TreeMap<String, Float> conges = new TreeMap<String, Float>();

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

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getProspection() {
        return prospection;
    }

    public void setProspection(int prospection) {
        this.prospection = prospection;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void isAdmin(boolean admin) {
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

    public TreeMap<String, Float> getConges() {
        return conges;
    }
    
    public float getNbDaysConges(String week) {
        Float f = getConges().get(week);
        return (f != null) ? f : 0;
    }
    
    public float getNbDaysAffected() {
        float nb = 0;
        for (Activity a : getActivities()) {
            nb += a.getNbDaysWork();
        }
        return nb;
    }
    
    /**
     * @param week the week looked
     * @return the number of days this person work for a given week
     */    
    public Activity getNbDaysAffected(Week week) {
        Activity act = new Activity();
        for (Activity a : getActivities()) {
            if (a.getWeek().compare(week) == 0) {
                act.setProduction(act.getProduction() + a.getProduction());
                act.setTerrain(act.getTerrain() + a.getTerrain());
                act.setCopil(act.getCopil() + a.getCopil());
            }
        }
        return act;
    }
    
    public List<Week> getWeeks() {
        if (getActivities().isEmpty() && getConges().isEmpty())
            return null;
        
        Week startWeek = null;
        Week endWeek = null;
        if (!getActivities().isEmpty()) {
        
            // Get the first and the last week of all project
            startWeek = getActivities().get(0).getWeek();
            endWeek = getActivities().get(0).getWeek();

            for (Activity activity : getActivities()) {
                if (startWeek.compare(activity.getWeek()) < 0) {
                    startWeek = activity.getWeek();
                }
                if (endWeek.compare(activity.getWeek()) > 0) {
                    endWeek = activity.getWeek();
                }
            }
        }
        
        Week firstCongesWeek = null;
        Week lastCongesWeek = null;
        if (!getConges().isEmpty()) {
            try {
                firstCongesWeek = new Week(getConges().firstKey());
                lastCongesWeek = new Week(getConges().lastKey());
            } catch (Exception e) {}
            
            if (getActivities().isEmpty()) {
                startWeek = firstCongesWeek;
                endWeek = lastCongesWeek;
            } else {
                // Check if there is conges before or after that
                if (startWeek.compare(firstCongesWeek) < 0) {
                    startWeek =  firstCongesWeek;
                }
                if (endWeek.compare(lastCongesWeek) > 0) {
                    endWeek =  lastCongesWeek;
                }
            }
        }
        
        return Utils.getWeeks(startWeek, endWeek);
    }
    
    public void removeProject(Project project) {
        removeActivities(project);
        removeProject(project, getProjects().iterator());
    }
    
    public void removeManagedProject(Project project) {
        removeProject(project, getManagedProjects().iterator());
    }
    
    private void removeProject(Project project, Iterator<Project> iterator) {
        boolean notFound = iterator.hasNext();
        while (notFound) {
            Project p = iterator.next();
            if (p.getId() == project.getId()) {
                iterator.remove();
                notFound = false;
            } else {
                notFound = iterator.hasNext();
            }
        }
    }
    
    private void removeActivities(Project project) {
        Iterator<Activity> iterator = getActivities().iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getProject().getId() == project.getId()) {
                iterator.remove();
            }
        }
    }
}
