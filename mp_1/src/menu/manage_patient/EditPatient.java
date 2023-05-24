package menu.manage_patient;

import java.io.File;
import java.util.Scanner;

import menu.mainmenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class EditPatient extends Patient{

    static void editMenu(){
        String choice;
        int flag = 0;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("-----Update Patient Records-----");
            System.out.println("Do you know the patients UID? [Y/N]");
            choice=in.nextLine();

            if(choice.equalsIgnoreCase("y")){
                UID();
                flag = 1;
            }

            else{
                System.out.println("Do you know the patients National ID? [Y/N]");
                choice=in.nextLine();
                if(choice.equalsIgnoreCase("y")){
                    ID();
                    flag = 1;
                }
                else{
                    System.out.println("Do you know the patients Name and Birthday? [Y/N]");
                    choice=in.nextLine();
                    if(choice.equalsIgnoreCase("y")){
                        Name();
                        flag = 1;
                    }

                    else{
                        System.out.println("Im sorry, I can't help you now.");
                        editAgain();
                        flag =1; //notsure

                    }

                }
            }

        }while(flag == 0);
        in.close();
    }
   
  
    //-------------------NATIONAL ID-------------------//
    private static void ID() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter National ID");
        NationalID = in.nextLine(); // user input for National ID
        String line; // stores individual lines of the .txt file
        String[] data; // stores the data in an individual line into an array
        int flag = 0;// if there is no record
        File fileToBeModified = new File("..\\mp_1\\Patients.txt");
        FileWriter writer;
        StringBuilder str1 = new StringBuilder();
        char choice ; // stores the user input
        int counter = 0, counter2 = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));
            BufferedReader br2 = new BufferedReader(new FileReader(fileToBeModified));

            while ((line = br2.readLine()) != null)
                counter++;

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[8].equals(NationalID) && data.length <= 9) {
                    counter2++;
                    System.out.println("Which data would you like to update?");
                    System.out.println("[1] Patient Address");
                    System.out.println("[2] Patient Phone Number");
                    choice = in.nextLine().charAt(0);
                    if(choice == '1'){
                        str1.append(updateAddress(data));
                        if (counter2 != counter)
                        str1.append('\n');
                        System.out.println("The Address of Patient " + data[0] + " has been updated.");
                        flag = 1;
                    } else if(choice == '2'){
                        str1.append(updatePNumber(data));
                        if (counter2 != counter)
                        str1.append('\n');
                        System.out.println("The Phone Number of Patient " + data[0] + " has been updated.");
                        flag = 1;
                    }
                    else{
                        flag = 2; // In the case of invalid input
                    }
                } else {
                    counter2++;
                    str1.append(line);
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
            }
            if (flag == 2){
                System.out.println("Invalid Input.");
            }
            editAgain();


        } catch (Exception e) {
            e.printStackTrace();
        }
        in.close();
    }
    
    //-------------------NAME AND BIRTHDAY -------------------//
    private static void Name() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Last Name");
        Lname = in.nextLine();
        System.out.println("Enter First Name");
        Fname = in.nextLine();
        System.out.println("Enter Birthday(YYYYMMDD)");
        Bday = in.nextLine();

        int j=0;
        String[][] duplicates = new String[10000][13];
        String[] data;
        String line;

        int flag = 0;// if there is no record
        File fileToBeModified = new File("..\\mp_1\\Patients.txt");
        int[] linenumber = new int[10000];
        int counter=1;
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[1].equals(Lname) && data[2].equals(Fname) && data[4].equals(Bday) && data.length <= 9) {

                    for(int i = 0; i < 9; i++){
                        duplicates[j][i]=data[i];
                    }
                    linenumber[j]= counter;
                    j++;
                    flag = 1;   
                }
                counter++;
            }// end of while loop

            if (flag == 0) { // if there is no same name and birthday inside the system
                System.out.println("No Record Found");
                editAgain();
            }
            else {
                System.out.println("Patient's UID\tLast Name\tFirst Name\tMiddle Name\t Birthday\tGender\tAddress\tPhone\tNumber\tNational ID no.");
                for (int l = 0; l < j; l++) {
                    for (int k = 0; k < 9; k++) {
                        System.out.print(duplicates[l][k]+ "\t");
                       
                    }
                    System.out.println();
                   
                }     
            }
            UID();
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        editAgain();

        in.close();

    }

        //-------------------UID-------------------//
    private static void UID() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter UID");
        String UID = in.nextLine(); // user input for UID
        String line; // stores individual lines of the .txt file
        String[] data; // stores the data in an individual line into an array
        int flag = 0;// if there is no record
        File fileToBeModified = new File("..\\mp_1\\Patients.txt");
        FileWriter writer;
        StringBuilder str1 = new StringBuilder();
        char choice ; // stores the user input
        int counter = 0, counter2 = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));
            BufferedReader br2 = new BufferedReader(new FileReader(fileToBeModified));

            while ((line = br2.readLine()) != null)
                counter++;

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[0].equals(UID) && data.length <= 9) {
                    counter2++;
                    System.out.println("Which data would you like to update?");
                    System.out.println("[1] Patient Address");
                    System.out.println("[2] Patient Phone Number");
                    choice = in.nextLine().charAt(0);
                    if(choice == '1'){
                        str1.append(updateAddress(data));
                        if (counter2 != counter)
                            str1.append('\n');
                        System.out.println("The Address of Patient " + data[0] + " has been updated.");

                        flag = 1;
                    } else if(choice == '2'){
                        str1.append(updatePNumber(data));
                        if (counter2 != counter)
                            str1.append('\n');
                        System.out.println("The Phone Number of Patient " + data[0] + " has been updated.");
                        
                        flag = 1;
                    }
                    else{
                        flag = 2; // In the case of invalid input
                    }
                } else {
                    counter2++;
                    str1.append(line);
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
            }
            if (flag == 2){
                System.out.println("Invalid Input.");
            }
            editAgain();


        } catch (Exception e) {
            e.printStackTrace();
        }
        in.close();
    }

    @SuppressWarnings("resource")
    private static StringBuilder updateAddress(String[] data){
        String update;
        StringBuilder newLine = new StringBuilder();
        int i;
        
        Scanner in = new Scanner(System.in);
        System.out.println("The Current Address Entry is: " + data[6]);
        System.out.println("What would you like to update it to?");
        update = in.nextLine();
        data[6] = update;
        for (i = 0; i <= 8; i++){
            newLine.append(data[i] + ";");
            // System.out.println(data[i] + '\n'); // Test viewing of data
        }
        return newLine;
    }
    
    @SuppressWarnings("resource")
    private static StringBuilder updatePNumber(String[] data){
        String update;
        StringBuilder newLine = new StringBuilder();
        int i;
        Scanner in = new Scanner(System.in);

        
        System.out.println("The Current Phone Number Entry is: " + data[7]);
        System.out.println("What would you like to update it to?");
        update = in.next();
        data[7] = update;
        for (i = 0; i <= 8; i++){
            newLine.append(data[i] + ";");
            // System.out.println(data[i] + '\n'); // Test viewing of data
        }
        return newLine;
    }
    
    private static void editAgain(){
        System.out.println("Edit another patient's information again? [Y/N]");
        Scanner in = new Scanner(System.in);
        String again = in.nextLine();

        if (again.equalsIgnoreCase("y")) {
            editMenu();
        } else {
            mainmenu.menu();
        }
        in.close();
    }
}
    

