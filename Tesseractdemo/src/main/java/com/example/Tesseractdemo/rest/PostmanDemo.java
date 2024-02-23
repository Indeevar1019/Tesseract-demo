package com.example.Tesseractdemo.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ch.qos.logback.core.model.Model;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Controller
public class PostmanDemo {
//	  @RequestMapping("/")
//	    public String index() {
//	        return "upload";
//	    }
	    @RequestMapping(value = "/", method = RequestMethod.POST,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	    public ResponseEntity<Object> singleFileUpload(@RequestPart MultipartFile file) throws IOException, TesseractException {

//	        byte[] bytes = file.getBytes();
//	        Path path = Paths.get("E:\\tess4j\\Tess4J\\test\\resources\\test-data\\" + file.getOriginalFilename());
//	        Files.write(path, bytes);

	        File convFile = convert(file);
	        Tesseract tesseract = new Tesseract();
	        tesseract.setDatapath("F:\\TesseractENG\\tessdata");
	        String text = tesseract.doOCR(convFile);
	        //redirectAttributes.addFlashAttribute("file", file);
	        //redirectAttributes.addFlashAttribute("text", text);
	        System.out.print(text);
	        return ResponseEntity.ok().build();
	        //return new RedirectView("result");
	    }

	    @RequestMapping("/result")
	    public String result() {
	        return "result";
	    }

	    public static File convert(MultipartFile file) throws IOException {
	        File convFile = new File(file.getOriginalFilename());
	        convFile.createNewFile();
	        FileOutputStream fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
	        return convFile;
	    }
}
