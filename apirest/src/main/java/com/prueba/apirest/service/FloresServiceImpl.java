package com.prueba.apirest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.prueba.apirest.dto.FloresDto;
import com.prueba.apirest.dto.FloresDtoID;
import com.prueba.apirest.entity.Flores;

@Service
public class FloresServiceImpl implements FloresService{
	static List<Flores> flores =new ArrayList<Flores>();
	@Autowired
	private Mapper mapper;
	
	@Override
	public void save(List<FloresDtoID> pFlores) {
		flores=pFlores
				.stream()
				.map(mapper::toFlor)
				.toList();	
	}

	
	@Override
	public List<FloresDto> findAll() {
		System.out.println(flores);
		List<Flores> modifiableList = new ArrayList<Flores>(flores);
		modifiableList.sort((o1,o2)-> o2.getName().compareTo(o1.getName()));
		List<FloresDto> floresdto=modifiableList
									.stream()
									.map(mapper::toDto)
									.toList();
		return floresdto;
	}

	@Override
	public List<FloresDtoID> findAllByPrice(double pPrice) {
		List<FloresDtoID> floresdto=flores
				.stream()
				.filter(flor->flor.getPrice()>=pPrice)
				.map(mapper::toDtoId)
				.toList();
		return floresdto;
	}

	@Override
	public void deleteById(int id) {
		System.out.println(id);
		flores=flores
				.stream()
				.filter(flor->flor.getId()!=id)
				.toList();
		
	}
	
	@Override
	public List<FloresDtoID> findAllByName(String pName) {
		List<FloresDtoID> floresdto=flores
				.stream()
				.filter(flor->flor.getName().contains(pName))
				.map(mapper::toDtoId)
				.toList();
		return floresdto;
	}


	@Override
	public FloresDto findById(int id) {
		FloresDto actual=null;
		for(int i=0;i<flores.size();i++) {
			if(flores.get(i).getId()==id) {
				return mapper.toDto(flores.get(i));
			}
		}
		return actual;
	}
	

}

@Component
class Mapper {
    public FloresDto toDto(Flores pFlor) {
        String name = pFlor.getName()+ "-kometsales";
        double price = pFlor.getPrice();
        return new FloresDto(name, price);
    }
    public FloresDtoID toDtoId(Flores pFlor) {
        String name = pFlor.getName();
        double price = pFlor.getPrice();
        int id=pFlor.getId();
        return new FloresDtoID(id,name, price);
    }
    public Flores toFlor(FloresDtoID florDTO) {
        return new Flores(florDTO.getName(), florDTO.getPrice(),florDTO.getId());
    }
}