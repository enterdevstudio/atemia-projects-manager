package fr.xinta.atemia.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Week extends AbstractEntity implements Serializable {
    
    private int num;
    private List<MasterRelation> relations;

    public int getNum() {
	return num;
    }

    public void setNum(int num) {
	this.num = num;
    }

    public List<MasterRelation> getRelations() {
	return relations;
    }

    public void setRelations(List<MasterRelation> relations) {
	this.relations = relations;
    }
}
