package fr.xinta.atemia.db.entity;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "seq",initialValue=1,allocationSize=50) 
public class Week extends AbstractEntity {
    
    private int num;
    private HashMap<Person, Period> job; //Linked because we need to keep it ordered
    
    public Week() {
	job = new HashMap<Person, Period>();
    }

    public int getNumber() {
	return num;
    }

    public void setNumber(int num) {
	this.num = num;
    }
    
    public HashMap<Person, Period> getJob() {
	return job;
    }
}
