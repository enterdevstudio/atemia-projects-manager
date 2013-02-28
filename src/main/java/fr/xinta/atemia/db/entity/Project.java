package fr.xinta.atemia.db.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.Entity;

@Entity
public class Project extends AbstractEntity implements Serializable {
    
    private int number;
    private String name;
    private String department;
    private int nbHoursSold;
    private List<MasterRelation> relations;

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
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

    public List<MasterRelation> getRelations() {
	return relations;
    }

    public void setRelations(List<MasterRelation> relations) {
	this.relations = relations;
    }
    
    public Set<Person> getWorkers {
     HashSet<Project> set = new HashSet<Person>();
     for (MasterRelation mr : getRelations()) {
	if (!set.contains(mr.getPerson()) {
     		set.add(mr.getPerson());
     	}
     }
     return set;
    }
}
