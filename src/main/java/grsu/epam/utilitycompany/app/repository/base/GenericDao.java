package grsu.epam.utilitycompany.app.repository.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericDao<T, PK extends Serializable> {
	
	PK save(T obj);
	
	void update(T obj);
	
	List<T> findAll();
	
	List<T> findByCriteria(Criterion criterion);
	
	T findById(PK id);
	
	void delete(PK id);
	
	void delete(T persistentObject);
}
