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
    @ManyToOne
    private Person worker;
    @ManyToOne
    private Week week;

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

    public Person getWorker() {
        return worker;
    }

    public void setWorker(Person worker) {
        this.worker = worker;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }
    
    public int getNbDaysWork() {
        return production + terrain + copil;
    }
    
    public int getProductionColspan() {
        switch (production) {
            case 0: return 0;
            case 1: return 1;
            case 3:
                return NBCATEGORIES - (getTerrainColspan() + getCopilColspan()+ getCongesColspan()); 
            case 4: return 3;
            case 5: return 4;
            default: return 1;
        }
    }
    
    public int getTerrainColspan() {
        switch (terrain) {
            case 0: return 0;
            case 1: return 1;
            case 3:
                return NBCATEGORIES - (getProductionColspan() + getCopilColspan()+ getCongesColspan()); 
            case 4: return 3;
            case 5: return 4;
            default: return 1;
        }
    }
    
    public int getCopilColspan() {
        switch (copil) {
            case 0: return 0;
            case 1: return 1;
            case 3:
                return NBCATEGORIES - (getProductionColspan() + getTerrainColspan() + getCongesColspan()); 
            case 4: return 3;
            case 5: return 4;
            default: return 1;
        }
    }
    
    public int getCongesColspan() {
        switch (conges) {
            case 0: return 0;
            case 1: return 1;
            case 3:
                return NBCATEGORIES - (getProductionColspan() + getTerrainColspan() + getCopilColspan()); 
            case 4: return 3;
            case 5: return 4;
            default: return 1;
        }
    }
}
