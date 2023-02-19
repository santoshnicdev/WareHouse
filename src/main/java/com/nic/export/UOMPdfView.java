package com.nic.export;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nic.modal.Uom;

public class UOMPdfView extends AbstractPdfView {
	

	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception 
	{
		
		response.addHeader("Content-Disposition", "attachment;filename=uom.pdf");
		
		//create elements
		@SuppressWarnings("unchecked")

		List<Uom> list=(List<Uom>) model.get("list");
		Font font = new Font(Font.TIMES_ROMAN, 22, Font.BOLD, new Color(84,192,247));
		Paragraph p = new Paragraph("HELLO USER!!",font);
		p.setSpacingAfter(10.0f);
		p.setAlignment(Element.ALIGN_CENTER);
		//add element to document
		document.add(p);
		
		//create table that one row should contains - n columns.
		PdfPTable table = new PdfPTable(4);
		table.addCell("Id");
		table.addCell("Type");
		table.addCell("Model");
		table.addCell("Description");
		for(Uom uom:list) {
			table.addCell(String.valueOf(uom.getId()));
			//table.addCell(uom.getId().toString());
			table.addCell(uom.getUomType());
			table.addCell(uom.getUomModel());
			table.addCell(uom.getDescription());
		}

		document.add(table);
		
		}

	
}
