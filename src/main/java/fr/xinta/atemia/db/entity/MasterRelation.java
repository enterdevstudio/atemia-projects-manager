package fr.xinta.atemia.db.entity;

public class MasterRelation {
    
    private Person person;
    private Project project;
    private Week week;
    private Period period;

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    public Project getProject() {
	return project;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    public Week getWeek() {
	return week;
    }

    public void setWeek(Week week) {
	this.week = week;
    }

    public Period getPeriod() {
	return period;
    }

    public void setPeriod(Period period) {
	this.period = period;
    }
}
