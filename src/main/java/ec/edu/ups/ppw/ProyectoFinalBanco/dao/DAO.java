package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Prestamo;

@Stateless
public abstract class DAO<E> {

	@PersistenceContext
	private EntityManager em;
	private Class<E> clase;

	public DAO() {
		java.lang.reflect.Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		clase = (Class) pt.getActualTypeArguments()[0];
	}

	public void insert(E objeto) {
		getEm().persist(objeto);
	}

	public E read(int id) {
		E obj = getEm().find(clase, id);
		return obj;
	}

	public void update(E objeto) {
		getEm().merge(objeto);
	}

	public void delete(int id) {
		E obj = getEm().find(clase, id);
		getEm().remove(obj);
	}

	public abstract List<E> getList();

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
