package com.nic.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nic.export.UOMPdfView;
import com.nic.modal.Uom;
import com.nic.service.IUomService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/uom")
public class UomController {
	@Autowired
	IUomService uomservice;
	@GetMapping("/register")
	public String registerUOM(Model model)
	{
		model.addAttribute("uom", new Uom());
		return "UOMRegiter";
		}
	@PostMapping("/save")
	public String saveUOM(Model model,@ModelAttribute Uom uom)
	{
		//model.addAttribute("uom", new Uom());
		Integer id=uomservice.saveuom(uom);
		String message="uom regitered with id>"+id;
		model.addAttribute("message",message);
		return "UOMRegiter";
		}
	@GetMapping("/all")
	public String UOMList(Model model,@ModelAttribute Uom uom)
	{
		List<Uom> list=uomservice.getALLUom();
		model.addAttribute("list", list);
		return "UOMData";
	}
		@GetMapping("/edit")
		public String editList(Model model,@RequestParam("id") Integer id)
		{
			model.addAttribute("uom", uomservice.getoneUom(id));
			return "UOMRegiter";
		}
	@PostMapping("/update")
	public String updateUOMList(Model model,@ModelAttribute Uom uom)
	{
		Integer id=uomservice.saveuom(uom);
		String message="uom edited with with id>"+id;
		model.addAttribute("message",message);
		return  "redirect:all";
		}
	@GetMapping("/delete")
	public String removeUOMList(Model model,@RequestParam Integer id,RedirectAttributes rd)
	{
		String message=uomservice.delete(id);
		rd.addAttribute("message",message);
	   // model.addAttribute("message",message);
		return  "redirect:all";
		}
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView m = new ModelAndView();
		m.setView(new UOMPdfView());
//fetch data from DB
		List<Uom> list= uomservice.getALLUom();
		//send data to View class
		m.addObject("list", list);
		return m;
	}
	@GetMapping("/jasp")
	public String exportToPdf(Model m) throws FileNotFoundException, JRException {
		//ModelAndView m = new ModelAndView();
		//m.setView(new UOMPdfView());
//fetch data from DB
		List<Uom> list= uomservice.getALLUom();
		//send data to View class
		m.addAttribute("list", list);
		File file=ResourceUtils.getFile("classpath:report\\UOM.jrxml");
		JasperReport jsreport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource ds= new JRBeanCollectionDataSource(list);
		Map<String, Object> map=new HashMap<>();
		map.put("created by","Santosh Chouhan");
		JasperPrint jsprint=JasperFillManager.fillReport(jsreport, map,ds);
		JasperExportManager.exportReportToPdfFile(jsprint,"D:\\Report\\uomreport.pdf");
		return "redirect:all";
	}


}
