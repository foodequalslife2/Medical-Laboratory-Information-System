package menu;
//CHUA, EDRIC JARVIS 11913991
//CRUZ, FRANCESCA RODEE 12002062
import java.util.*;

import menu.laboratory.ManageLab;
import menu.manage_patient.ManageRecords;
import menu.services.ManageServices;

import static java.lang.System.exit;



public class mainmenu {

     public static void main(String[] args) {
          menu();
     }

     @SuppressWarnings("resource")
     public static void menu(){
             Scanner in = new Scanner(System.in);
             String input;
             int check = 0;
             do{

                 System.out.println("Medical Laboratory Information System");
                 System.out.println("[1] Manage Patient Records");
                 System.out.println("[2] Manage Services");
                 System.out.println("[3] Manage Laboratory Results");
                 System.out.println("[4] EXIT");
                 input = in.nextLine();
                 
                 try {
                    check = Integer.parseInt(input);
                 } catch (Exception e) {
                    System.err.println("Error: A Number was not Inputted");
                 }

                 switch (input) {
                     case "1" -> ManageRecords.manageMenu();
                     case "2" -> ManageServices.manageMenu();
                     case "3" -> ManageLab.manageMenu();
                     case "4" -> exit(0);
                     default -> {
                         System.out.println("Invalid Input. Please Try Again.");
                         System.out.println();
                     }
                 }

             }while(check > 3||check <1);

         }
}


//class placeholder{
//    public static void rest(){
//        System.out.println("The Services Menu is currently under construction");
//        returntoMenu.Return();
//    }
//
//    public static void sleep(){
//        System.out.println("The Laboratory Results is currently under construction");
//        returntoMenu.Return();
//    }
//}
