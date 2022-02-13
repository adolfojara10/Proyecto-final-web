package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Servicio;

@Stateless
public class ServiciosDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Servicio ser) {
		em.persist(ser);
	}

	public void update(Servicio ser) {
		em.merge(ser);
	}

	public Servicio read(int id) {
		Servicio p = em.find(Servicio.class, id);
		return p;
	}

	// ----------------------------------------------------------------
	public List<Servicio> getList() {
		String jpql = "Select s From Servicio s";

		Query q = em.createQuery(jpql, Servicio.class);

		return q.getResultList();
	}
}
