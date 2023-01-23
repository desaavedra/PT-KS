package com.prueba.apirest.service;

import java.util.List;

import com.prueba.apirest.dto.FloresDto;
import com.prueba.apirest.dto.FloresDtoID;

public interface FloresService {
	public void save(List<FloresDtoID> flores);
	
	public List<FloresDto> findAll();
	
	public FloresDto findById(int id);
	
	public List<FloresDtoID> findAllByPrice(double pPrice);
	
	public void deleteById(int id);
	
	public List<FloresDtoID> findAllByName(String pName);
}
