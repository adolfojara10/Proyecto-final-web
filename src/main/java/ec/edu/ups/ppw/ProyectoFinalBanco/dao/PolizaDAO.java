package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Poliza;

@Stateless
public class PolizaDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Poliza poliza) {
		em.persist(poliza);
	}

	public void update(Poliza poliza) {
		em.merge(poliza);
	}

	public Poliza read(int id) {
		Poliza p = em.find(Poliza.class, id);
		return p;
	}

	public List<Poliza> getList() {
		String jpql = "Select p From Poliza p";

		Query q = em.createQuery(jpql, Poliza.class);

		return q.getResultList();
	}
	
}
