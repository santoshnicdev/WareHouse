package com.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.modal.Uom;
import com.nic.repo.IUomReo;
import com.nic.service.IUomService;
@Service
public class UOMServiceImple implements IUomService {
	@Autowired
    IUomReo uomrepo;
	@Override
	public Integer saveuom(Uom uom) {
		// TODO Auto-generated method stub
		return uomrepo.save(uom).getId();
	}
	@Override
	public List<Uom> getALLUom() {
		
		return uomrepo.findAll();
	}
	@Override
	public Uom getoneUom(Integer id) {
		
		return uomrepo.getById(id);
	}
	@Override
	public String delete(Integer id) {
		uomrepo.deleteById(id);
		return "sucessfully deleted with id:>>"+id;
	}

}
