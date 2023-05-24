package menu.services;


import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import txt_tools.*;
import menu.mainmenu;

public class AddService extends Service {
    private static boolean repeat;

    @SuppressWarnings("resource")
    static void addMenu(boolean again){
        setRepeat(again);
        System.out.println("-----Add Service Code-----");
        Scanner in = new Scanner(System.in);
        String YorN;
        boolean invalidCode = true;
        int flag = 0, flag2 = 0; // flag is for checking validity of service code, flag2 is for checking file existence
        
        do{ // Checks if the Service code is valid and unique
            System.out.println("Enter a UNIQUE 3-character Service Code:");
            serviceCode = in.nextLine().toUpperCase();

            if (serviceCode.length() != 3){
                System.out.println("Please Enter Exactly THREE Characters for the Service Code\n");
            }
            else if(serviceCode.length() == 3){
                String Code_in_File = Fromtxt.read("Services" , true);
                if (Code_in_File == null){ // if the txt file is empty
                    invalidCode = false;
                    flag2 = 1;
                }
                else{
                    try {
                        FileReader file = new FileReader("..\\mp_1\\Services.txt");
                        BufferedReader br = new BufferedReader(file);
                        String line;
                        String[] data;
            
                        while ((line = br.readLine()) != null) {
                            data = line.split(";");
                            if (data[0].equals(serviceCode) && data.length <= 3) {
                                flag = 0;
                                break;
                            } else {
                                flag = 1;
                            }
                        }
                        if (flag == 1) {
                            invalidCode = false;
                        } else {
                            System.out.println("This code has already been taken by the following service:");
                            System.out.println(line + '\n');
                            System.out.println("Please try again.");
                        }
                        br.close();
                    }      
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }while(invalidCode);

        System.out.println("Enter a Description for the Service:");
        description = in.nextLine();

        System.out.println("Enter a Price for the Service: ");
        price = in.nextLine();

        System.out.println("Save Service to System? [Y/N]");
        YorN = in.nextLine().toUpperCase();

        System.out.println();
        System.out.println();

        if (YorN.equals("Y") || YorN.equals("YES")) {

            System.out.println(serviceCode + " " + description + " " + price + " has been added.");
            serviceCode += ";" + description + ";" + price + ";";
            Totxt.write(serviceCode, flag2, "Services", true);

        }
        else // if recorder presses no
        {
            addMenu(repeat);
        }

        if(repeat)
            addAgain();
    }

    private static void addAgain(){
        System.out.println("Do you want to add another service? [Y/N]");
        Scanner in = new Scanner(System.in);
        String again = in.nextLine();

        if (again.equals("y") || again.equals("Y")) {
            addMenu(repeat);
        } else {
            mainmenu.menu();
        }
        in.close();

    }

    private static void setRepeat(boolean again){
        if(again)
            repeat = true;
        else
            repeat = false;
    }
    
}
