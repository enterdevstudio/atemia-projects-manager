package fr.xinta.atemia.db.facade;

import fr.xinta.atemia.db.entity.Activity;
import javax.ejb.Stateless;

@Stateless
public class ActivityFacade extends AbstractFacade<Activity> {
    
    public ActivityFacade() {
	super(Activity.class);
    }    
}
