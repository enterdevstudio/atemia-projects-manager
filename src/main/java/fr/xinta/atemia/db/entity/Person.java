package fr.xinta.atemia.db.entity;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Person extends AbstractEntity {    
    
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy="workers")
    private List<Project> projects;
    
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

    public List<Project> getProjects() {
        return projects;
    }
}
