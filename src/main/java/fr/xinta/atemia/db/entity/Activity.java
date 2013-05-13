package fr.xinta.atemia.db.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Activity extends AbstractEntity {
    
    private static final int NBCATEGORIES = 4;
    private int production;
    private int terrain;
    private int copil;
    private int conges;
    private Week week;
    @ManyToOne
    private Person worker;
    @ManyToOne
    private Project project;

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
    }

    public int getCopil() {
        return copil;
    }

    public void setCopil(int copil) {
        this.copil = copil;
    }

    public int getConges() {
        return conges;
    }

    public void setConges(int conges) {
        this.conges = conges;
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
    
    public int getNbDaysWork() {
        return production + terrain + copil;
    }

    public int getNbDaysSet() {
        return getNbDaysWork() + conges;
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
            if (conges > 0)
                col--;
            return col;
        }
        
//        switch (production) {
//            case 0: return 0;
//            case 1: return 1;
//            case 3:
//                return NBCATEGORIES - (getTerrainColspan() + getCopilColspan()+ getCongesColspan()); 
//            case 4: return 3;
//            case 5: return 4;
//            default: return 1;
//        }
    }
    
    public int getTerrainColspan() {
        if (terrain == 0) {
            return 0;
        } else {
            int col = NBCATEGORIES - getProductionColspan();
            if (copil > 0)
                col--;
            if (conges > 0)
                col--;
            return col;
        }
    }
    
    public int getCopilColspan() {
        if (copil == 0) {
            return 0;
        } else {
            int col = NBCATEGORIES - getProductionColspan() - getTerrainColspan();
            if (conges > 0)
                col--;
            return col;
        }
    }
    
    public int getCongesColspan() {
        if (conges == 0) {
            return 0;
        } else {
            return NBCATEGORIES - getProductionColspan() - getTerrainColspan() -getCopilColspan();
        }
    }
}
