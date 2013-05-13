package fr.xinta.atemia.db.facade;

import fr.xinta.atemia.db.entity.Activity;
import javax.ejb.Stateless;

@Stateless
public class ActivityFacade extends AbstractFacade<Activity> {
    
    public ActivityFacade() {
	super(Activity.class);
    }
    
//    public List<Activity> find(Person person) {
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Activity> cq = cb.createQuery(Activity.class);
//        Root<Activity> activity = cq.from(Activity.class);
//        cq.where(cb.equal(activity.get(), person.getId()));
//
//        Query q = getEntityManager().createQuery(cq);
//        return q.getResultList();      
//    }
}
