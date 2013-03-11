package fr.xinta.atemia.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Project extends AbstractEntity implements Serializable {

    private String name;
    private String department;
    private int nbHoursSold;
    private List<MasterRelation> relations;
    
    public Project() {
	relations = new ArrayList<MasterRelation>();
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
    
    public Set<Person> getWorkers() {
	Set<Person> set = new HashSet<Person>();
	for (MasterRelation mr : getRelations()) {
	    if (!set.contains(mr.getPerson())) {
		set.add(mr.getPerson());
	    }
	}
	return set;
    }
}
