package menu.laboratory;


import menu.mainmenu;

import java.util.Scanner;

public class ManageLab {
    @SuppressWarnings("resource")
    public static void manageMenu(){
        Scanner in = new Scanner(System.in);
        int flag = 0;
        String input;

        do{
            System.out.println("Labratory Results");
            System.out.println("[1] Add New Laboratory Request ");
            System.out.println("[2] Edit Laboratory Request");
            System.out.println("[3] Delete Laboratory Request ");
            System.out.println("[4] Search Laboratory Request ");
            System.out.println("[X] Return to Main Menu");
            System.out.println("Select Transaction");
            input = in.nextLine();

            if(input.equals("1")){
                AddRequest.addMenu();
                flag=1;
            }

            else if(input.equals("2")){
                EditRequest.editMenu();
                flag=1;
            }
            else if(input.equals("3")){
                DeleteRequest.deleteMenu();
                flag=1;
            }

            else if(input.equals("4")){
                SearchRequest.searchMenu(true) ;
                flag=1;
            }

            else if(input.equalsIgnoreCase("X")){
                mainmenu.menu();
                flag=1;
            }

            else{
                System.out.println("Invalid Input. Please Try Again.");
                System.out.println();
            }
        } while(flag==0);

    }
}
