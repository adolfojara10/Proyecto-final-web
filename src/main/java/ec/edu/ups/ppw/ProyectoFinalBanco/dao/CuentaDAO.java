package ec.edu.ups.ppw.ProyectoFinalBanco.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.ppw.ProyectoFinalBanco.model.Cuenta;

@Stateless
public class CuentaDAO{

	@PersistenceContext
	private EntityManager em; 
	
	public void  insert(Cuenta cue) {		
		em.persist(cue);		
	}
	
	public void  update(Cuenta cue) {
		em.merge(cue);
	}
	
	public Cuenta read(int id) {
		Cuenta p = em.find(Cuenta.class, id);
		return p;
	}
	
	//----------------------------------------------------------------
	public List<Cuenta> getList() {
		String jpql = "Select c From Cuenta c";

		Query q = em.createQuery(jpql, Cuenta.class);

		return q.getResultList();
	}
//	
//	public String hexaToString(byte[] digest) {
//		// Convert digest to a string
//		StringBuilder hexString = new StringBuilder();
//		for (int i = 0; i < digest.length; i++) {
//			if ((0xff & digest[i]) < 0x10) {
//				hexString.append("0" + Integer.toHexString((0xFF & digest[i])));
//			} else {
//				hexString.append(Integer.toHexString(0xFF & digest[i]));
//			}
//		}
//		return hexString.toString();
//	}
//
//	// metodo para convertir texto hash en encriptar contraseñas
//	public String sha1(String contraseña) throws NoSuchAlgorithmException {
//		String hash;
//		MessageDigest md = MessageDigest.getInstance("SHA-1");
//		md.update(contraseña.getBytes());
//		byte[] digest = md.digest();
//		hash = hexaToString(digest);
//		return hash;
//	}
//	
//	
//	 public Cuenta logIn(String nombreUsu, String password) {
//	        var listaCuentas = getList();
//	        Cuenta usu;
//	        String passwordHash = null;
//	        try {
//	            passwordHash = sha1(password);
//	        } catch (NoSuchAlgorithmException ex) {
//	            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
//	        }
//
//	        final String pass = passwordHash;
//	        try {
//	            usu = listaCuentas.stream().filter(cue -> cue.getNombre_usuario().equals(nombreUsu)
//	                    && pass.equals(cue.getContraseña())).findFirst().get();
//
//	            return usu;
//	        } catch (NoSuchElementException e) {
//	            e.printStackTrace();
//	        }
//
//	        return null;
//	    }
}
