package menu.manage_patient;

import java.util.Scanner;

import menu.mainmenu;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors


public class ManageRecords {


    public static void manageMenu()  {
        Scanner in = new Scanner(System.in);
        int flag = 0;
        String input;

        // if there is no record of the file
        try {
            File list = new File("..\\mp_1\\Patients.txt");
            if (list.createNewFile()) {
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        do{
            System.out.println("Manage Patient Records");
            System.out.println("[1] Add New Patient");
            System.out.println("[2] Edit Patient Record");
            System.out.println("[3] Delete Patient Record");
            System.out.println("[4] Search Patient Record");
            System.out.println("[X] Return to Main Menu");
            System.out.println("Select Transaction");
            input = in.nextLine();

            if(input.equals("1")){
                AddPatient.addMenu();
                flag=1;
            }

            else if(input.equals("2")){
                EditPatient.editMenu();
                flag=1;
            }

            else if(input.equals("3")){
                DeletePatient.deleteMenu();

                flag=1;
            }

            else if(input.equals("4")){
                SearchPatient.searchMenu(true);
                flag=1;
            }
            else if(input.equalsIgnoreCase("X")){
                flag=1;
                mainmenu.menu();
            }

            else{
                System.out.println("Invalid Input. Please Try Again.");
                System.out.println();
            }
        } while(flag==0);

        in.close();
    }

}
