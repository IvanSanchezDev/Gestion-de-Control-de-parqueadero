package com.proyecto.parqueadero.dao;





import java.util.List;



import com.proyecto.parqueadero.modelo.Mensualidad;

public interface IMensualidadDao {
	
	public List<Mensualidad> findAll();
	public void save(Mensualidad mensualidad);
	public Mensualidad findOne(Long id);
	public void delete(Long id);
	//public Integer cantidadMensualidades();
	

}
