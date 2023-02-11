package com.nic.service;

import java.util.List;

import com.nic.modal.ShipmentType;

public interface IShipmentTypeService {

	
	void updateShipmentType(ShipmentType st);
	void deleteShipmentType(Integer id);
	ShipmentType getOneShipmentType(Integer id);
	List<ShipmentType> getAllShipmentTypes();
	Integer saveShipmentType(ShipmentType st);
}
