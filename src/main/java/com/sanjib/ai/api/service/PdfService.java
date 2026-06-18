package com.sanjib.ai.api.service;

	import java.io.File;
	import org.apache.pdfbox.Loader;
	import org.apache.pdfbox.pdmodel.PDDocument;
	import org.apache.pdfbox.text.PDFTextStripper;
	import org.springframework.stereotype.Service;

	@Service
	public class PdfService {

	    public String getResumeText() {

	        try {

	            File file =
	                new File("src/main/resources/resume/Sharma M.pdf");

	            PDDocument document =
	                Loader.loadPDF(file);

	            PDFTextStripper stripper =
	                new PDFTextStripper();

	            String text =
	                stripper.getText(document);

	            document.close();

	            return text;

	        } catch(Exception e) {

	            return e.getMessage();
	        }
	    }
	}
