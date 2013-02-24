package fr.xinta.atemia.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Person extends AbstractEntity implements Serializable {
    
    private String firstName;
    private String lastName;    
    private List<MasterRelation> relations;

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
}
