package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Prestamo;

public class PrestamoDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void insert(Prestamo prestamo) {
		em.persist(prestamo);
	}

	public void update(Prestamo prestamo) {
		em.merge(prestamo);
	}

	public Prestamo read(int id) {
		Prestamo p = em.find(Prestamo.class, id);
		return p;
	}

	public List<Prestamo> getList() {
		String jpql = "Select p From Prestamo p";

		Query q = em.createQuery(jpql, Prestamo.class);

		return q.getResultList();
	}

}
