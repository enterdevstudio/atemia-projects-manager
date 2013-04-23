package fr.xinta.atemia.db.facade;

import fr.xinta.atemia.db.entity.AbstractEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade<T extends AbstractEntity> {
    
    protected final String PERSISTENCE = "fr.xinta_Atemia";
    
    private Class<T> entityClass;
    
    @PersistenceContext(unitName = PERSISTENCE)
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
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

    public T find(String id) {
        T object = null;
        try {
            object = getEntityManager().find(getEntityClass(), Long.parseLong(id));
        } catch (NumberFormatException e) { } //return null if incorrectly parsed
        return object;
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