package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Servicios;

@Stateless
public class ServiciosDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Servicios ser) {
		em.persist(ser);
	}

	public void update(Servicios ser) {
		em.merge(ser);
	}

	public Servicios read(int id) {
		Servicios p = em.find(Servicios.class, id);
		return p;
	}

	// ----------------------------------------------------------------
	public List<Servicios> getList() {
		String jpql = "Select s From Servicios s";

		Query q = em.createQuery(jpql, Servicios.class);

		return q.getResultList();
	}
}
