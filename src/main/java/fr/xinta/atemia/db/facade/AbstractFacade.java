package fr.xinta.atemia.db.facade;

import fr.xinta.atemia.db.entity.AbstractEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade<T extends AbstractEntity> {
    
    private Class<T> entityClass;
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
	if (entityManager == null)
	    entityManager = Persistence.createEntityManagerFactory("fr.xinta_Atemia_war_1.0-SNAPSHOTPU").createEntityManager();
        return this.entityManager;
    }
    
    public Class<T> getEntityClass() {
	return this.entityClass;
    }
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void persist(AbstractEntity entity) {
        getEntityManager().persist(entity);
    }

    public void merge(AbstractEntity entity) {
        getEntityManager().merge(entity);
    }

    public void remove(AbstractEntity entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(getEntityClass(), id);
    }
    
    public List<T> findAll() {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        List<T> entities;

        cq.select(cq.from(getEntityClass()));

        Query q = getEntityManager().createQuery(cq);
        entities = q.getResultList();

        return entities;
    }

    public int count() {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<? extends AbstractEntity> rt = cq.from(getEntityClass());

        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    }
}