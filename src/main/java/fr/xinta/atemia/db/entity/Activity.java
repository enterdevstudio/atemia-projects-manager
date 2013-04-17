package fr.xinta.atemia.db.entity;

import javax.persistence.Entity;

@Entity
public class Activity extends AbstractEntity {
    
    private int production;
    private int terrain;
    private int copil;
    private int conges;

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
}
