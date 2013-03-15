package fr.xinta.atemia.db.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "seq",initialValue=1,allocationSize=50) 
public class Person extends AbstractEntity {    
    
    private String firstName;
    private String lastName;
    
    public Person() {
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
    
    public Set<Project> getProjects() {
    	HashSet<Project> set = new HashSet<Project>();
    	return set;
    }
}
