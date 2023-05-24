package menu.laboratory;


import menu.mainmenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;

@SuppressWarnings("resource")
public class DeleteRequest extends Request{
    public static void deleteMenu( )  {
        String line;
        FileWriter writer ;
        StringBuilder str1 = new StringBuilder();
        String newline;// the deleted line
        String reason ; // reason for deletion
        int counter = 0, counter2 = 0;
        Scanner in = new Scanner(System.in);

        reqUID = (SearchRequest.searchMenu(false));

        if (!Objects.equals(reqUID, "")){
            String serviceCode = reqUID.substring(0,3);
            File fileToBeModified = new File("..\\mp_1\\"+serviceCode+"_Request.txt");


            try {

                BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));
                BufferedReader br2 = new BufferedReader(new FileReader(fileToBeModified));

                while ((line = br2.readLine()) != null)
                    counter++;

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    if (data[0].equalsIgnoreCase (reqUID) && data.length <= 5) {
                        counter2++;
                        System.out.println("What is the reason for deletion?");
                        reason = in.nextLine();
                        line = line.concat("D;" + reason + ";");
                        str1.append(line);
                        if (counter2 != counter)
                            str1.append('\n');

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


                writer.close();
                br.close();
                br2.close();
  
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("\n" + reqUID + " has been deleted.");
            deleteAgain();
        }

        else{
            System.out.println("No Record Found");
            deleteAgain();
        }


    }


    private static void deleteAgain() {
        System.out.println("Delete deleteAgain? [Y/N]");
        Scanner in = new Scanner(System.in);
        String deleteAgain = in.nextLine();

        if (deleteAgain.equalsIgnoreCase("y")) {
            DeleteRequest.deleteMenu();
        } else {
            mainmenu.menu();
        }

    }
}
