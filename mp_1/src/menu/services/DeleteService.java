package menu.services;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import menu.mainmenu;

public class DeleteService extends Service {

    private static boolean repeat;

    @SuppressWarnings("resource")
    static void deleteMenu(boolean again){
        setRepeat(again);
        String choice;
        int flag = 0;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("-----Delete Service Code-----");
            System.out.println("Do you know the Service Code? [Y/N]");
            choice = in.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                UsingCode();
                flag = 1;
            }

            else {
                System.out.println("Do you know a descriptive keyword for the Service? [Y/N]");
                choice = in.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    UsingKeyWord();
                    flag = 1;
                } else {
                    System.out.println("Im sorry, I can't help you now. Try Again.");
                    deleteAgain();
                    flag =1; // not sure

                }

            }
        } while (flag == 0);
    }


    @SuppressWarnings("resource")
    private static void UsingKeyWord() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Keyword: ");
        description = in.nextLine();
        File fileToBeModified = new File("..\\mp_1\\Services.txt");
        String line;
        String[] data = new String[13];

        ArrayList<String> sortCode = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));
            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[1].toLowerCase().contains(description) && data.length <= 3) {
                    sortCode.add(data[0] + " " + data[1] + " " + data[2]);
                }
                
            }

            if(sortCode.isEmpty()){
                System.out.println("No Record found\n");
                deleteAgain();
            }
            else{
                System.out.println("<Service Code>\t<Description>\t<Price>");
                Collections.sort(sortCode);
                for (int i = 0; i < sortCode.size();i++)
                System.out.println(sortCode.get(i));
                System.out.println();
                UsingCode();
                br.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("resource")
    private static void UsingCode() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Service Code: ");
        serviceCode = in.nextLine();
        deleter(serviceCode);
        if (repeat)
            deleteAgain();

    }


    @SuppressWarnings("resource")
    private static void deleteAgain() {
        Scanner in = new Scanner(System.in);
        String again;
        System.out.println("Delete a new Service? [Y/N]");
        again = in.nextLine();

        if (again.equalsIgnoreCase("y")) {
            deleteMenu(repeat);
        } else {
            mainmenu.menu();
        }
    }

    @SuppressWarnings("resource")
    private static void deleter(String serviceCode){
        Scanner in = new Scanner(System.in);
        File fileToBeModified = new File("..\\mp_1\\Services.txt");
        String line;
        String reason;
        FileWriter writer;
        StringBuilder str1 = new StringBuilder();
        String newline;// the deleted line
        String[] data = new String[13];
        int counter = 0, counter2 = 0;
        int flag = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));
            BufferedReader br2 = new BufferedReader(new FileReader(fileToBeModified));

            while ((line = br2.readLine()) != null)
                counter++;

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[0].equalsIgnoreCase(serviceCode) && data.length <= 3) {
                    description = data[1];
                    counter2++;
                    System.out.println("What is the reason for deletion?");
                    reason = in.nextLine();
                    line = line.concat("D;" + reason + ";");
                    str1.append(line);
                    if (counter2 != counter)
                        str1.append('\n');
                    flag = 1;
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

            if (flag == 0) {
                System.out.println("No Record found");
                deleteAgain();
            }

            System.out.println("Service Code " + serviceCode + " Description "+ description + " has been deleted");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setRepeat(boolean again){
        if(again)
            repeat = true;
        else
            repeat = false;
    }

}
