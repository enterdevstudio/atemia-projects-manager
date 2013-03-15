package fr.xinta.atemia.db.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "seq",initialValue=1,allocationSize=50) 
public class Project extends AbstractEntity {

    private String name;
    private String department;
    private int nbHoursSold;
    @OneToMany
    private List<Week> weeks;
    
    public Project() {
	weeks = new ArrayList<Week>();
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

    public int getNbHoursSold() {
	return nbHoursSold;
    }

    public void setNbHoursSold(int nbHoursSold) {
	this.nbHoursSold = nbHoursSold;
    }

    public List<Week> getWeeks() {
	return weeks;
    }
    
    public Set<Person> getWorkers() {
	return (weeks.isEmpty()) ? null :
		weeks.get(0).getJob().keySet();
    }
}
