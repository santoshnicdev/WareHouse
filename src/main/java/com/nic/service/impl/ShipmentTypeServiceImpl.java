package com.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.exception.ShipmentTypeNotFoundException;
import com.nic.modal.ShipmentType;
import com.nic.modal.ShipmentTypeRepository;
import com.nic.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {
	@Autowired
	private ShipmentTypeRepository repo;
	
	@Override
	public Integer saveShipmentType(ShipmentType st) {
		return repo.save(st).getId();
	}

	@Override
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);
	}

	@Override
	public void deleteShipmentType(Integer id) {
		ShipmentType st = getOneShipmentType(id);
		repo.delete(st);
	}

	@Override
	public ShipmentType getOneShipmentType(Integer id) {
		ShipmentType st = repo.findById(id)
				.orElseThrow(
						()-> new ShipmentTypeNotFoundException(
								"ShipmentType '"+id+"' Not exist"));
		return st;
	}

	@Override
	public List<ShipmentType> getAllShipmentTypes() {
		return repo.findAll();
	}

	

}
