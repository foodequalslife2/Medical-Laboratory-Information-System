package menu.manage_patient;

import java.io.FileReader;
import java.io.BufferedReader;

// import java.util.Arrays; this isn't used yet so i'm commenting it out
import java.util.*;

import menu.laboratory.SearchRequest;
import menu.mainmenu;

@SuppressWarnings("resource")
public class SearchPatient extends Patient {
    private static boolean repeat;




    public static String searchMenu(boolean lab) {

        String choice ;
        int flag = 0;
        String returned = new String();
        Scanner in = new Scanner(System.in);
        do {
            setRepeat(lab);
            System.out.println("-----Search Patient Records-----");
            System.out.println("Do you know the patients UID? [Y/N]");
            choice = in.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                returned=UID() ;
                flag = 1;
            }

            else {
                if(repeat){
                    System.out.println("Do you know the patients National ID? [Y/N]");
                    choice = in.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        ID();
                        flag = 1;
                    }
                    else {
                        System.out.println("Do you know the patients Name and Birthday? [Y/N]");
                        choice = in.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            Name();
                            flag = 1;
                        }
                        else {
                            System.out.println("Im sorry, I can't help you now.");
                                searchAgain();
                                flag=1;
                        }
                    }
                }
                else {
                    flag=1;
                }
            }


        } while (flag == 0);


        return returned;
    }

    // -------------------NATIONAL ID-------------------//
    private static void ID() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter National ID");
        NationalID = in.nextLine();
        String line;
        String[] data = new String[13];
        int flag = 0;
        try {
            FileReader file = new FileReader("..\\mp_1\\Patients.txt");
            BufferedReader br = new BufferedReader(file);

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[8].equals(NationalID) && data.length <= 9) {
                    flag = 0;
                    break;
                } else {
                    flag = 1;
                }
            }

            if (flag == 1) {
                System.out.println("No Record Found");
                    searchAgain();
            }
            else {
                if(repeat){
                    printData(data);
                }


            }
            br.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }


    }

    // -------------------NAME AND BIRTHDAY -------------------//
    private static void Name( ) {

        int flag = 0;
        int flag2 = 0;
        int j = 0;
        String line;

        Scanner in = new Scanner(System.in);

        System.out.println("Enter Last Name");
        Lname = in.nextLine();
        System.out.println("Enter First Name");
        Fname = in.nextLine();
        System.out.println("Enter Birthday(YYYYMMDD)");
        Bday = in.nextLine();

        String[][] duplicates = new String[10000][13];
        String[] data = new String[13];
        try {
            FileReader file = new FileReader("..\\mp_1\\Patients.txt");
            BufferedReader br = new BufferedReader(file);

            while ((line = br.readLine()) != null) {

                data = line.split(";");

                if (data[1].equalsIgnoreCase(Lname) && data[2].equalsIgnoreCase(Fname) && data[4].equalsIgnoreCase(Bday) && data.length <= 9) {

                    for (int i = 0; i < 9; i++) {
                        duplicates[j][i] = data[i];
                    }
                    j++;
                    flag = 1;

                }
                if (flag == 1) {
                    flag2 = 1;
                }
            } // end of while loop

            if (flag2 == 0) { // if there is no same name and birthday inside the system
                System.out.println("No Record Found");
                searchAgain();
            } else {
                System.out.println("Patient's UID\tLast Name\tFirst Name\tMiddle Name\t Birthday\tGender\tAddress\tPhone\tNumber\tNational ID no.");
                for (int l = 0; l < j; l++) {
                    for (int k = 0; k < 9; k++) {
                        System.out.print(duplicates[l][k] + "\t");
                    }
                    System.out.println();
                }
                    UID();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // -------------------UID-------------------//
    private static String UID() {

        String ID;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter UID");
        ID = in.nextLine();
        String line;
        String UID = new String();
        String[] data = new String[13];

        int flag = 0;
        try {
            FileReader file = new FileReader("..\\mp_1\\Patients.txt");
            BufferedReader br = new BufferedReader(file);

            while ((line = br.readLine()) != null) { // reads the line int the txt file until no more lines are present
                data = line.split(";"); // splits individual catagories using the ; as a delimitter
                if (data[0].equals(ID) && data.length <= 9) { // if the UID is found it instantly goes out of the loop
                    flag = 0;
                    UID= data[0];
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
                    printData(data);
                }

            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return UID;
    }

    static void searchAgain() {
        System.out.println("Search a patient again? [Y/N]");
        Scanner in = new Scanner(System.in);
        String again = in.nextLine();

        if (again.equalsIgnoreCase("y")) {
            searchMenu(repeat);
        } else {
            mainmenu.menu();
        }

    }


    private static void printData(String[] data) {
        String RUID;

        // Prints all the data
        System.out.println("Patients UID:\t" + data[0]);
        System.out.println("Name:\t" + data[1]+",\t"+data[2]+ " " + data[3]);
        System.out.println("Birthday:\t" + data[4]);
        System.out.println("Address:\t" + data[6]);
        System.out.println("Phone number:\t" + data[7]);
        System.out.println("National ID:\t" + data[8]);


        RUID = SearchRequest.searchPUID(data[0] , 1);


        // turn the data into a pdf if the user chooses
        String choice;
        do {
            System.out.println("Do you want to print a laboratory test result? [Y/N]");
            Scanner in = new Scanner(System.in);
            choice = in.nextLine().toUpperCase();
            if (choice.equalsIgnoreCase("y")) {
                PrintPDF.create(data, RUID);
                System.out.println("Do you want to print another laboratory test result? [Y/N]");
                choice = in.nextLine().toUpperCase();
                if (choice.equalsIgnoreCase("y"))
                    searchMenu(repeat);
                else
                    mainmenu.menu();
            } else if (choice.equalsIgnoreCase("n"))
                mainmenu.menu();
            else
                System.err.println("Error. Invalid Input. Try Again.");
        } while (!choice.equals("Y") && !choice.equals("N"));

    }

    private static void setRepeat(boolean lab){
        if(lab)
            repeat = true;
        else
            repeat = false;
    }

}
