package com.proyecto.parqueadero.reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;

/*
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;*/
import com.itextpdf.text.pdf.PdfWriter;

public class FacturaPdfView {

	public void generateFactura(String placa, String tipo_vehiculo, String fecha) throws DocumentException, IOException {

		try {

			FileOutputStream archivo;
			File file = new File("C:/recibo/ticketEntrada.pdf");// C:/Tickets
			archivo = new FileOutputStream(file);

			Document document = new Document(PageSize.A7);

			PdfWriter pdf = PdfWriter.getInstance(document, archivo);

			document.open();

			Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

			fontTiltle.setSize(8);

			Paragraph paragraph1 = new Paragraph("PARQUEADERO SAN ROQUE", fontTiltle);
			paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
			Paragraph rayas1 = new Paragraph("==================");
			Paragraph paragraph2 = new Paragraph("Placa del Vehiculo: " + placa, fontTiltle);
			Paragraph paragraph3 = new Paragraph("Tipo de Vehiculo: " + tipo_vehiculo, fontTiltle);
			Paragraph paragraph4 = new Paragraph("Hora de entrada: " + fecha, fontTiltle);

			Barcode128 codigo39 = new Barcode128();
			codigo39.setCode(placa);
			Image img = codigo39.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
			
			Paragraph rayas2 = new Paragraph("==================");

			Paragraph paragraph6 = new Paragraph("Horario de Atencion: de 7 am a 7 pm", fontTiltle);

			document.add(paragraph1);
			document.add(rayas1);
			document.add(paragraph2);
			document.add(paragraph3);
			document.add(paragraph4);
			
			document.add(img);
			document.add(rayas2);
			document.add(paragraph6);
			
			//archivo.close();
			document.close();

		} catch (Exception e) {
			System.out.println("Eerror" + e.getMessage());
		}
		
		try {

            if ((new File("C:\\recibo/ticketEntrada.pdf")).exists()) {   //C:\\Facturas/recibo.pdf
                Process p = Runtime//creando un proceso
                        .getRuntime()//llamando el proceso
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\recibo/ticketEntrada.pdf");//lo ejecuta
                p.waitFor();//hace esperar hasta que se cumpla el proceso
               

            } else {
                System.out.println("El archivo no existe");
            }
        } catch (Exception ex) {
            ex.printStackTrace();//muestra las lines y lso errores
        }

	}

}
