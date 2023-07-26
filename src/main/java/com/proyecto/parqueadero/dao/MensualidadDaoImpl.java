package com.proyecto.parqueadero.dao;

import java.time.LocalDate;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;

import com.proyecto.parqueadero.modelo.Mensualidad;

@Repository
public class MensualidadDaoImpl implements IMensualidadDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Mensualidad> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Mensualidad").getResultList();
	}

	@Override
	public void save(Mensualidad mensualidad) {
		if (mensualidad.getId() != null && mensualidad.getId() > 0) {
			LocalDate hoy = LocalDate.now();
			mensualidad.setFecha_inicio(hoy);
			mensualidad.setFecha_fin(hoy.plusMonths(1));
			mensualidad.setHabilitado(true);
			em.merge(mensualidad);
			
		} else {
			em.persist(mensualidad);
		}
	}

	@Override
	public Mensualidad findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Mensualidad.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));

	}

	


}
