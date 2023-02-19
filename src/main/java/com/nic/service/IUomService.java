package com.nic.service;

import java.util.List;

import com.nic.modal.Uom;

public interface IUomService {

	Integer saveuom(Uom uom);

	List<Uom> getALLUom();

	public Uom getoneUom(Integer id);

	String delete(Integer id);

}
