package menu.services;

import java.util.Scanner;

import menu.mainmenu;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors


public class ManageServices {
    
    public static void manageMenu(){
        Scanner in = new Scanner(System.in);
        int flag = 0;
        String input;

        // if there is no record of the file
        try {
            File list = new File("..\\mp_1\\Services.txt");
            if (list.createNewFile()) {
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        do{
            System.out.println("Manage Services");
            System.out.println("[1] Add New Service");
            System.out.println("[2] Edit Service");
            System.out.println("[3] Delete Service");
            System.out.println("[4] Search Service");
            System.out.println("[X] Return to Main Menu");
            System.out.println("Select Transaction");
            input = in.nextLine();

            if(input.equals("1")){
                AddService.addMenu(true);
                flag=1;
            }

            else if(input.equals("2")){
                System.out.println("The services cannot be edited. If you would like to edit an existing service, the service will first be deleted, and a new service will be created. Would you like to proceed? [Y/N]");
                input = in.nextLine();
                if(input.equalsIgnoreCase("Y")){
                    EditServices.editMenu();
                    flag=1;
                }       
            }
            else if(input.equals("3")){
               DeleteService.deleteMenu(true);
                flag=1;
            }

            else if(input.equals("4")){
                SearchService.searchMenu(true);
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

//class placeholder{
//    public static void nap(){
//        System.out.println("The Edit Service Menu is current under construction");
//        returntoServices.Return();
//    }
//    public static void rest(){
//        System.out.println("The Delete Services Menu is currently under construction");
//        returntoServices.Return();
//    }
//
//    public static void sleep(){
//        System.out.println("The Search Services Menu is currently under construction");
//        returntoServices.Return();
//    }
//}

@SuppressWarnings("resource")
class returntoServices{
    public static void Return(){
        String choice;
        do{
        System.out.println("Return to Service Menu? [Y/N]");
        Scanner in = new Scanner(System.in);
        choice = in.nextLine().toUpperCase();
            if (choice.equals("Y"))
                ManageServices.manageMenu();
            else if (choice.equals("N"))
                return;
            else
                System.err.println("Error. Invalid Input. Try Again.");
        }while (!choice.equals("Y"));
    }
}