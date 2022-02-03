package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Transferencia;

@Stateless
public class TransferenciaDAO {
	
	@PersistenceContext
	private EntityManager em;

	public void insert(Transferencia transferencia) {
		em.persist(transferencia);
	}

	public void update(Transferencia transferencia) {
		em.merge(transferencia);
	}

	public Transferencia read(int id) {
		Transferencia p = em.find(Transferencia.class, id);
		return p;
	}

	public List<Transferencia> getList() {
		String jpql = "Select p From Transferencia p";

		Query q = em.createQuery(jpql, Transferencia.class);

		return q.getResultList();
	}

}
