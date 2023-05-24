package menu.manage_patient;

import java.io.File;
import java.util.Scanner;

import menu.mainmenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

@SuppressWarnings("resource")
public class DeletePatient extends Patient {

    static void deleteMenu(){

        String choice ;
        int flag = 0;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("-----Delete Patient Records-----");
            System.out.println("Do you know the patients UID? [Y/N]");
            choice=in.nextLine();

            if(choice.equalsIgnoreCase("y")){
                deleteUID();
                flag = 1;
            }

            else{
                System.out.println("Do you know the patients National ID? [Y/N]");
                choice=in.nextLine();
                if(choice.equalsIgnoreCase("y")){
                    deleteID();
                    flag = 1;
                }
                else{
                    System.out.println("Do you know the patients Name and Birthday? [Y/N]");
                    choice=in.nextLine();
                    if(choice.equalsIgnoreCase("y")){
                       deleteName();
                        flag = 1;
                    }

                    else{
                        System.out.println("Im sorry, I can't help you now.");
                        deleteAgain();
                        flag =1; // not sure
                    }

                }
            }



        }while(flag == 0);
        in.close();
    }

    //-------------------NATIONAL ID-------------------//
    private static void deleteID() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter National ID");
        NationalID = in.nextLine();
        String line;
        String[] data;
        int flag = 0;
        File fileToBeModified = new File("..\\mp_1\\Patients.txt");
        FileWriter writer ;
        StringBuilder str1 = new StringBuilder();
        String reason;
        int counter = 0, counter2 = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));
            BufferedReader br2 = new BufferedReader(new FileReader(fileToBeModified));

            while ((line = br2.readLine()) != null)
                counter++;

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[8].equals(NationalID)  && data.length <= 9) {
                    counter2++;
                    System.out.println("What is the reason for deletion?");
                    reason = in.nextLine();
                    line=line.concat("D;"+reason+";");
                    str1.append(line);
                     if (counter2 != counter)
                        str1.append('\n');
                    flag=1;

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
            if(flag==0){
                System.out.println("No Record found");
            }


            deleteAgain();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        in.close();
    }

    //-------------------NAME AND BIRTHDAY-------------------//
    private static void deleteName() {

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

        int flag = 0;
        File fileToBeModified = new File("..\\mp_1\\Patients.txt");
        int[] linenumber = new int[10000];
        int counter=1;
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileToBeModified));

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (data[1].equals(Lname) && data[2].equals(Fname) && data[4].equals(Bday) && data.length <= 9) {
    
                    for(int i = 0; i <9; i++){
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
                deleteAgain();
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
            deleteUID();
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        in.close();

    }

    //-------------------UID-------------------//
    private static void deleteUID() {
        String UID;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter UID");
        UID = in.nextLine();
        String line;
        String[] data ;
        int flag = 0;// if there is no record
        File fileToBeModified = new File("..\\mp_1\\Patients.txt");
        FileWriter writer ;
        StringBuilder str1 = new StringBuilder();
        String newline;// the deleted line
        String reason ; // reason for deletion
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
            }
            deleteAgain();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static void deleteAgain(){
        System.out.println("Delete another patient? [Y/N]");
        Scanner in = new Scanner(System.in);
        String again = in.nextLine().toUpperCase();

        if (again.equalsIgnoreCase("y")) {
            deleteMenu();
        } else {
            mainmenu.menu();
        }
        in.close();
    }


}

