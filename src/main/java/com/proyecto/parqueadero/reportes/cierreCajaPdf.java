package com.proyecto.parqueadero.reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;

public class cierreCajaPdf {

	public void generate(Double ingresosCarro, Double ingresosMoto, Integer cantidadCarros, Double ingresosMensualidadCarro, Integer cantidadMensualidadesCarro, Double ingresosMensualidadMoto, Integer cantidadMensualidadesMoto,Integer cantidadMotos, Double valor, String usuario
			) throws DocumentException, IOException {
		
		FileOutputStream archivo;
		File file = new File("C:/recibo/CdCaja.pdf");// C:/Tickets
		archivo = new FileOutputStream(file);

		Document document = new Document(PageSize.A4);

	PdfWriter.getInstance(document, archivo);

		document.open();

		final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
		final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		

		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Date date = new Date();

		Paragraph titulo = new Paragraph("INFORME INGRESOS DIARIOS PARQUEADERO SAN ROQUE", categoryFont);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		Paragraph rayas1 = new Paragraph("========================================================================");
		Paragraph espacio = new Paragraph(" ");

		PdfPTable table = new PdfPTable(2);
		

		PdfPCell celdaFinal = new PdfPCell(new Paragraph("Cierre de Caja", subcategoryFont));
		celdaFinal.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaFinal.setColspan(2);
		
		table.addCell(celdaFinal);

		table.addCell("cantidad carros");
		table.addCell(" " + cantidadCarros);

		table.addCell("Ingresos Carros");
		table.addCell(" " + ingresosCarro);

		table.addCell("cantidad Motos");
		table.addCell(" " + cantidadMotos);

		table.addCell("Ingresos Motos");
		table.addCell(" " + ingresosMoto);
		
		table.addCell("cantidad Mensualidades Carro");
		table.addCell(" " + cantidadMensualidadesCarro);

		table.addCell("Ingresos Mensualidades Carro");
		table.addCell(" " + ingresosMensualidadCarro);
		
		table.addCell("cantidad Mensualidades Moto");
		table.addCell(" " + cantidadMensualidadesMoto);

		table.addCell("Ingresos Mensualidades Moto");
		table.addCell(" " + ingresosMensualidadMoto);

		table.addCell("Total de ingresos");
		table.addCell(" " + valor);

		Paragraph titulohoraActual = new Paragraph("Fecha y hora Actual: ", categoryFont);
		Paragraph horaActual = new Paragraph("" + dateFormat.format(date));
		Paragraph NombreEmpleado = new Paragraph("Usuario Actual:  ", categoryFont );
		Paragraph empleado = new Paragraph("" + usuario);

		document.add(titulo);
		document.add(rayas1);
		document.add(espacio);
		document.add(table);
		document.add(rayas1);
		document.add(titulohoraActual);
		document.add(horaActual);
		document.add(rayas1);
		document.add(NombreEmpleado);
		document.add(empleado);

		

		document.close();
		
		
		try {

            if ((new File("C:\\recibo/CdCaja.pdf")).exists()) {   //C:\\Facturas/recibo.pdf
                Process p = Runtime//creando un proceso
                        .getRuntime()//llamando el proceso
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\recibo/CdCaja.pdf");//lo ejecuta
                p.waitFor();//hace esperar hasta que se cumpla el proceso
               

            } else {
                System.out.println("El archivo no existe");
            }
        } catch (Exception ex) {
            ex.printStackTrace();//muestra las lines y lso errores
        }
	}

}
