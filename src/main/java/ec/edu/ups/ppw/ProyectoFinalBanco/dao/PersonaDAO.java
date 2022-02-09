package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;
import ec.edu.ups.ppw.ProyectoFinalBanco.model.Persona;

@Stateless
public class PersonaDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(Persona per) {
		em.persist(per);
	}

	public void update(Persona per) {
		em.merge(per);
	}

	public Persona read(int id) {
		Persona p = em.find(Persona.class, id);
		return p;
	}

//	public void  delete(in cedula) {
//		DetalleFactura p = em.find(DetalleFactura.class, cedula);
//		em.remove(p);
//	}

	// ----------------------------------------------------------------

	public List<Persona> getList() {
		String jpql = "Select p From Persona p";

		Query q = em.createQuery(jpql, Persona.class);

		return q.getResultList();
	}

	public List<Persona> getListNombre(String filtro) {
		String jpql = "Select p From Persona p" + " where nombre LIKE ?1";

		Query q = em.createQuery(jpql, Persona.class);
		q.setParameter(1, filtro);
		return q.getResultList();
	}

	public Persona ConsultarCuenta(Cuenta cuenta) {

		String jpql = "SELECT * FROM Persona WHERE cuenta=?1";
		Query query = em.createNativeQuery(jpql, Persona.class);
		query.setParameter(1, cuenta);
		Persona per = (Persona) query.getResultList().get(0);
		return per;
	}

	public Persona readCedula(String cedula) {
		String jpql = "SELECT * FROM Persona WHERE cedula=:cedula";
		Query query = em.createNativeQuery(jpql, Persona.class);
		query.setParameter("cedula", cedula);
		Persona per = (Persona) query.getResultList().get(0);
		return per;
	}

}
