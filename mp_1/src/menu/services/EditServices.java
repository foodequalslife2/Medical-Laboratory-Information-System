package menu.services;
import menu.mainmenu;

import java.util.Scanner;

public class EditServices extends Service{

    static void editMenu(){
        System.out.println("-----Edit Service Code-----");
        
        try {
            System.out.println("-----Deleting Step-----");
            DeleteService.deleteMenu(false);
            System.out.println("-----Adding New Service Step-----");
            AddService.addMenu(false);
        } catch (Exception e) {
            System.err.println(e + " Error! Could you please try again?");
            editMenu();
        }

        System.out.println("Edit Successful.");
        editAgain();
    }
    
    private static void editAgain(){
        System.out.println("\nDo you want to edit another service? [Y/N]");
        Scanner in = new Scanner(System.in);
        String again = in.nextLine();

        if (again.equals("y") || again.equals("Y")) {
            editMenu();;
        } else {
            mainmenu.menu();
        }
        in.close();
    }
    
    
}
