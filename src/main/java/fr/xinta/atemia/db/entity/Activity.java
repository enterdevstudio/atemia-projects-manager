package fr.xinta.atemia.db.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Activity extends AbstractEntity {
    
    private static final int NBCATEGORIES = 3;
    private float production;
    private float terrain;
    private float copil;
    
    private Week week;
    @ManyToOne
    private Person worker;
    @ManyToOne
    private Project project;

    public float getProduction() {
        return production;
    }

    public void setProduction(float production) {
        this.production = production;
    }

    public float getTerrain() {
        return terrain;
    }

    public void setTerrain(float terrain) {
        this.terrain = terrain;
    }

    public float getCopil() {
        return copil;
    }

    public void setCopil(float copil) {
        this.copil = copil;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Person getWorker() {
        return worker;
    }

    public void setWorker(Person worker) {
        this.worker = worker;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    public float getNbDaysWork() {
        return production + terrain + copil;
    }

    public int getProductionColspan() {
        if (production == 0) {
            return 0;
        } else {
            int col = NBCATEGORIES;
            if (terrain > 0)
                col--;
            if (copil > 0)
                col--;
            return col;
        }
    }
    
    public int getTerrainColspan() {
        if (terrain == 0) {
            return 0;
        } else {
            int col = NBCATEGORIES - getProductionColspan();
            if (copil > 0)
                col--;
            return col;
        }
    }
    
    public int getCopilColspan() {
        if (copil == 0) {
            return 0;
        } else {
            return NBCATEGORIES - getProductionColspan() - getTerrainColspan();
        }
    }
}
