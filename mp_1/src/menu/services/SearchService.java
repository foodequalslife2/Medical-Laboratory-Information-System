package menu.services;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import menu.mainmenu;

public class SearchService extends Service{
    private static boolean repeat;


    private static void setRepeat(boolean lab){
        if(lab)
            repeat = true;
        else
            repeat = false;
    }
    @SuppressWarnings("resource")
    public static String searchMenu(boolean lab){
        String choice;
        int flag = 0;
        String returned = "";
        Scanner in = new Scanner(System.in);
        do {
            setRepeat(lab);
            System.out.println("-----Search Service Code-----");
            System.out.println("Do you know the Service Code? [Y/N]");
            choice = in.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                  returned = findCode("", 0);
                flag = 1;
            }

            else {
                if(repeat){
                    System.out.println("Do you know a descriptive keyword for the Service? [Y/N]");
                    choice = in.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        findKeyWord();
                        flag = 1;
                    }
                    else {
                        System.out.println("Im sorry, I can't help you now.");
                            searchAgain();
                            flag=1; // not sure
                    }
                }
                else{
                    flag=1;
                }

            }

        } while (flag == 0);
        return returned;
    }
    @SuppressWarnings("resource")
    public static String findCode(String PDF, int i){
        Scanner in = new Scanner(System.in);
        if(i == 0){
            System.out.println("Enter Service Code: ");
            serviceCode = in.nextLine();
        }
        else{
            serviceCode = PDF;
        }

        String line;
        String[] data = new String[13];
        String Code = "";
        int flag = 0;
        try {
            FileReader file = new FileReader("..\\mp_1\\Services.txt");
            BufferedReader br = new BufferedReader(file);

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[0].equalsIgnoreCase(serviceCode) && data.length <= 3) {
                    flag = 0;
                    Code = data[0];
                    break;
                } else {
                    flag = 1;
                }
            }

            if (flag == 1) {
                System.out.println("No Record Found");
                if(repeat){
                    searchAgain();
                }


            } else {
                if(repeat){
                System.out.println("<Service Code>\t<Description>\t<Price>");
                System.out.println(data[0] + '\t' + data[1] + '\t' + data[2]);
                    searchAgain();
                }

            }
            br.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    if(i==1){
        return data[1];
    }
        return Code;
    }
    @SuppressWarnings("resource")
    private static void findKeyWord(){
        int flag = 0, flag2 = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a Descriptive Keyword for the Service: ");
        description = in.nextLine().toLowerCase();
        
        ArrayList<String> sortCode = new ArrayList<>();
        String[] data;
        String line;

        try {
            FileReader file = new FileReader("..\\mp_1\\Services.txt");
            BufferedReader br = new BufferedReader(file);

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[1].toLowerCase().contains(description) && data.length <= 3) {
                    sortCode.add(data[0] + " " + data[1] + " " + data[2]);
                    flag = 1;
                }
                if (flag == 1) {
                    flag2 = 1;
                }
            } // end of while loop

            if (flag2 == 0) { // If there is no service with the description
                System.out.println("No Record Found");


            } else {
                System.out.println("<Service Code>\t<Description>\t<Price>");
                Collections.sort(sortCode);
                for (int i = 0; i < sortCode.size();i++)
                    System.out.println(sortCode.get(i));
                System.out.println();
                searchAgain();

            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @SuppressWarnings("resource")
    public static void searchAgain() {
        System.out.println("Search for a new Service? [Y/N]");
        Scanner in = new Scanner(System.in);
        String searchAgain = in.nextLine();

        if (searchAgain.equalsIgnoreCase("y")) {
            searchMenu(true);
        } else {
            mainmenu.menu();
        }

    }
        
}


