package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Solicitud;

@Stateless
public class SolicitudDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Solicitud solicitud) {
		em.persist(solicitud);
	}

	public void update(Solicitud solicitud) {
		em.merge(solicitud);
	}

	public Solicitud read(int id) {
		Solicitud p = em.find(Solicitud.class, id);
		return p;
	}

	public List<Solicitud> getList() {
		String jpql = "Select p From Solicitud p";

		Query q = em.createQuery(jpql, Solicitud.class);

		return q.getResultList();
	}

}
