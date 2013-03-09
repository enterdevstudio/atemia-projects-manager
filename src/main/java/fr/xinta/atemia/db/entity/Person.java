package fr.xinta.atemia.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Person extends AbstractEntity implements Serializable {
    
    
    
    @GeneratedValue(strategy = GenerationType.TABLE, generator="seq")
    @Id 
    private Long person_id;
    private String firstName;
    private String lastName;    
    private List<MasterRelation> relations;
    
    public Person() {
	relations = new ArrayList<MasterRelation>();
    }

    public Long getPerson_id() {
	return person_id;
    }

    public void setPerson_id(Long person_id) {
	this.person_id = person_id;
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

    public List<MasterRelation> getRelations() {
	return relations;
    }
    
    public void setRelations(List<MasterRelation> relations) {
	this.relations = relations;
    }
    
    public Set<Project> getProjects() {
    	HashSet<Project> set = new HashSet<Project>();
    	for (MasterRelation mr : getRelations()) {
	    if (!set.contains(mr.getProject())) {
		set.add(mr.getProject());
	    }
    	}
    	return set;
    }
}
