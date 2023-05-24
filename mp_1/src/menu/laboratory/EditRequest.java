package menu.laboratory;


import menu.mainmenu;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;

@SuppressWarnings("resource")
public class EditRequest extends Request{

    static void editMenu(){
        String line;
        FileWriter writer ;
        StringBuilder str1 = new StringBuilder();
        String newline;// the line with edited results
        int counter = 0, counter2 = 0;
        Scanner in = new Scanner(System.in);
        boolean edited = true;

        System.out.println("NOTICE: You can only edit the results of a laboratory request that does not have results yet");
        reqUID = SearchRequest.searchMenu(false);
            

        if (!Objects.equals(reqUID, "")){
            try {
                String serviceCode = reqUID.substring(0,3);
                File fileToBeModified = new File("..\\mp_1\\"+serviceCode+"_Request.txt");
                BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));
                BufferedReader br2 = new BufferedReader(new FileReader(fileToBeModified));

                while ((line = br2.readLine()) != null)
                    counter++;

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    if (data[0].equalsIgnoreCase (reqUID) && data.length < 5) {
                        counter2++;
                        System.out.println("Enter Laboratory Results");
                        result = in.nextLine();
                        line = line.concat(result + ";");
                        str1.append(line);
                        if (counter2 != counter)
                            str1.append('\n');
                        edited = false;

                    } else {
                        counter2++;
                        newline = line;
                        str1.append(newline);
                        if (counter2 != counter)
                            str1.append('\n');

                    }
                }
                writer = new FileWriter(fileToBeModified);
                writer.write(String.valueOf(str1));

                if (edited)
                    System.out.println("\nSorry, that record has already been previously edited.");


                writer.close();
                br.close();
                br2.close();

            }
            catch(StringIndexOutOfBoundsException e){
                System.err.println("ERROR: Please type a valid Request UID");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            editAgain();
        }

        else{
            System.out.println("The Record Does Not Exist.");
            editAgain();
        }
    }   

    
    private static void editAgain() {
        System.out.println("Do you want to edit another laboratory request? [Y/N]");
        Scanner in = new Scanner(System.in);
        String again = in.nextLine();

        if (again.equalsIgnoreCase("y")) {
            editMenu();
        } else {
            mainmenu.menu();
        }
    }

}