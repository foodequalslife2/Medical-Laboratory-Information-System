package menu.manage_patient;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import menu.services.SearchService;



// Under Construction
public class PrintPDF {



        public static void create(String[] data, String RUID)
        {
            String Service_Code = "";
            try {
                Service_Code = RUID.substring(0,3);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("This patient does not have any laboratory tests at the moment.");
                SearchPatient.searchAgain();
            }
            
            File fileToBeModified = new File("..\\mp_1\\"+Service_Code+"_Request.txt");
            String line;
            String[] data2 = new String[13];
            Font bold = new Font(Font.FontFamily.HELVETICA , 12, Font.BOLD);
            
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));

                while ((line = br.readLine()) != null) {
                    data2 = line.split(";");
                    if (data2[0].equals(RUID) && data2.length <= 5) {
                        break;
                    }
                }


                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int age;
            String birthday = data[4];
            int year =Integer.parseInt(birthday.substring(0,4) );
            int month= Integer.parseInt(birthday.substring(4,6) );
            int day= Integer.parseInt(birthday.substring(6,8) );
            //obtains an instance of LocalDate from a year, month and date
            LocalDate dob = LocalDate.of(year, month , day);
            LocalDate curDate = LocalDate.now();
            Period period = Period.between(dob, curDate);
            age= period.getYears();
            String description ;



            try
            {
    //generate a PDF at the specified location
                String dest = "..\\mp_1\\"+data[1]+".pdf";

                OutputStream fos = new FileOutputStream(new File(dest));

                Document doc = new Document();

                PdfWriter.getInstance(doc, fos);

                doc.open();


                Paragraph p = new Paragraph("[Company Logo]\n155 Make Believe Street, Manila, Philippines\n8-9000-3002");
                Paragraph header = new Paragraph("______________________________________________________________________________\n\n", bold);
                p.setAlignment(Element.ALIGN_CENTER);

                doc.add(p);
                doc.add(header);

                PdfPTable table2 = new PdfPTable(2);

                PdfPCell C2 = new PdfPCell(new Phrase("Name: "+data[2]+" "+data[1]+" "+data[3]));
                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);

                C2= new PdfPCell(new Phrase("Specimens UID: " + data2[0]));
                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);
                table2.setHeaderRows(1);


                C2= new PdfPCell(new Phrase("Patient UID: "+data[0]));
                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);


                C2= new PdfPCell(new Phrase("Collection Date: " + data2[2].substring(0,4) + '/' + data2[2].substring(4,6) + '/' + data2[2].substring(6,8)));
                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);


                C2= new PdfPCell(new Phrase("Age: " +age));
                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);

                C2= new PdfPCell(new Phrase(    "Birthday: " + data[4].substring(0,4) + '/' + data[4].substring(4,6) + '/' + data[4].substring(6,8)));

                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);

                C2= new PdfPCell(new Phrase("Gender: "+data[5]));
                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);

                C2= new PdfPCell(new Phrase("Phone Number: " +data[7]));
                C2.setBorder(Rectangle.NO_BORDER);
                table2.addCell(C2);


                doc.add(table2);

                p = new Paragraph("______________________________________________________________________________\n\n", bold);
                doc.add(new Paragraph(p));

                PdfPTable table = new PdfPTable(2);
                PdfPCell c1 = new PdfPCell(new Phrase("Test", bold));
                table.addCell(c1);

                c1= new PdfPCell(new Phrase("Result", bold));
                table.addCell(c1);
                table.setHeaderRows(1);


                description = SearchService.findCode(Service_Code , 1);
                c1= new PdfPCell(new Phrase(description));
                table.addCell(c1);

                try{
                    c1= new PdfPCell(new Phrase(data2[4]));
                    table.addCell(c1);

                }catch(StringIndexOutOfBoundsException e){
                    c1= new PdfPCell(new Phrase("No Result Yet"));
                    table.addCell(c1);

                }catch(ArrayIndexOutOfBoundsException e){
                    c1= new PdfPCell(new Phrase("No Result Yet"));
                    table.addCell(c1);
                }catch(Exception e){
                    e.printStackTrace();
                }

                doc.add(table);
                p = new Paragraph("______________________________________________________________________________\n\n", bold);
                doc.add(new Paragraph(p));


                PdfPTable table3 = new PdfPTable(2);

                PdfPCell C3 = new PdfPCell(new Phrase("Chua, Edric Jarvis"));
                C3.setBorder(Rectangle.NO_BORDER);
                table3.addCell(C3);

                C3= new PdfPCell(new Phrase("Cruz, Francesca Bautista"));
                C3.setBorder(Rectangle.NO_BORDER);
                table3.addCell(C3);
                table3.setHeaderRows(1);


                C3= new PdfPCell(new Phrase("Medical Technologist"));
                C3.setBorder(Rectangle.NO_BORDER);
                table3.addCell(C3);


                C3= new PdfPCell(new Phrase("Pathologist"));
                C3.setBorder(Rectangle.NO_BORDER);
                table3.addCell(C3);


                C3= new PdfPCell(new Phrase("#LIC 123456789"));
                C3.setBorder(Rectangle.NO_BORDER);
                table3.addCell(C3);


                C3= new PdfPCell(new Phrase("#LIC 987654321"));
                C3.setBorder(Rectangle.NO_BORDER);
                table3.addCell(C3);


                doc.add(table3);

                //close the PDF file
                doc.close();



            } catch (FileNotFoundException | DocumentException e)
            {
                e.printStackTrace();
            }
        }


}
