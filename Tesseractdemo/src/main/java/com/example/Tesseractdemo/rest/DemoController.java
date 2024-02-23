package com.example.Tesseractdemo.rest;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.GetMapping;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract; 
import net.sourceforge.tess4j.TesseractException; 
@RestController
public class DemoController {
	@GetMapping("/")
	public String say() {
	ITesseract tesseract = new Tesseract(); 

    try { 

       // the path of your tess data folder inside the extracted file
       tesseract.setDatapath("F:\\TesseractENG\\tessdata"); 
       tesseract.setLanguage("eng");
       //tesseract.setPageSegMode(1);
       //tesseract.setOcrEngineMode(1);
       File imageFile = new File("E:\\tess4j\\Tess4J\\test\\resources\\test-data\\aa.jpg");
       
       BufferedImage bufferedImage = ImageIO.read(imageFile);
       // path of your image file 
       String text = tesseract.doOCR(new File("E:\\tess4j\\Tess4J\\test\\resources\\test-data\\aa.jpg"));         
       System.out.print(text);
       // Create a FileWriter with the specified file path
       FileWriter fileWriter = new FileWriter("E:\\tess4j\\Tess4J\\test\\result\\output.txt.txt");

       // Wrap FileWriter in BufferedWriter for efficient writing
       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

       // Write the text to the file
       bufferedWriter.write(text);

       // Close the BufferedWriter to flush and release resources
       bufferedWriter.close();
       System.out.println("Text has been written to the file successfully.");
       return text;

    } catch (TesseractException | IOException e) { 
         e.printStackTrace(); 
    }
    return "None";
	}


}
