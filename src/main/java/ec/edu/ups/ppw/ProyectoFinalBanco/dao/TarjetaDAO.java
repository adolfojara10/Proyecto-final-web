package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Tarjeta;

@Stateless
public class TarjetaDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Tarjeta tarjeta) {
		em.persist(tarjeta);
	}

	public void update(Tarjeta tarjeta) {
		em.merge(tarjeta);
	}

	public Tarjeta read(int id) {
		Tarjeta p = em.find(Tarjeta.class, id);
		return p;
	}

	public List<Tarjeta> getList() {
		String jpql = "Select p From Tarjeta p";

		Query q = em.createQuery(jpql, Tarjeta.class);

		return q.getResultList();
	}
}
