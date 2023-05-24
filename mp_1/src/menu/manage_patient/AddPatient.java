package menu.manage_patient;

import java.util.Scanner;

import txt_tools.*;
import menu.mainmenu;



public class AddPatient extends Patient{

    static void addMenu() {
    
        Scanner in = new Scanner(System.in);

        String DDD = "AAA";
        String EE = "00";
        int ToInt;
        int increment = 0;
        String Code_in_File = Fromtxt.read("Patients" , true);
        
        String YorN;
        String Code = "P";
        int flag = 0;// to check if the file is a newly created file

        System.out.println("First Name:");
        Fname = in.nextLine();

        System.out.println("Last Name:");
        Lname = in.nextLine();

        System.out.println("Middle Name:");
        Mname = in.nextLine();

        System.out.println("Birthday(YYYYMMDD):");
        Bday = in.nextLine();

        System.out.println("Gender:");
        Gender = in.nextLine().toUpperCase().charAt(0);

        System.out.println("Address:");
        Address = in.nextLine();

        System.out.println("Phone No.:");
        Phone = in.nextLine();

        System.out.println("National ID no.:");
        NationalID = in.nextLine();	

        System.out.println("Save Patient Record[Y/N]?");
        YorN = in.nextLine().toUpperCase();


        System.out.println();
        System.out.println();
        String localSystemDate = java.time.LocalDate.now().toString();

        Code += localSystemDate.substring(0, 4);
        Code += localSystemDate.substring(5, 7);

        if (Code_in_File == null) { // if the txt file is empty
            Code += DDD;
            Code += EE;
            flag=1;
        }
        else if(!Code_in_File.substring(5, 7).equals(localSystemDate.substring(5, 7))){
            Code += DDD;
            Code += EE;
        }


        else { // if the txt file has patients listed
            DDD = Code_in_File.substring(7, 10); // gets the previous patients DDD code
            EE = Code_in_File.substring(10, 12); // gets the previous patients EE code
            ToInt = Integer.parseInt(EE);

            if (ToInt < 9) {               //EE is less than 9 so there is a need to add a "0" to the string
                ToInt += 1;
                EE = "0" + Integer.toString(ToInt);

            } else if (ToInt == 99) {  //if EE is over the limit
                EE = "00";
                increment = 1;

            } else {
                ToInt += 1;
                EE = Integer.toString(ToInt);

            }



            if (increment == 1) { // if EE is going over 99 DDD needs to be incremented
                if(DDD.equals("ZZZ")){       //the DDD doesnt work help // fixed
                    DDD="AAA";
                }

                else{
                    // https://stackoverflow.com/questions/3047119/increment-a-value-from-aaa-to-zzz-with-cyclic-rotation
                        char[] digits = DDD.toCharArray();

                        for (int i = DDD.length() - 1; i >= 0; --i) {
                            if (digits[i] == 'Z') {
                                digits[i] = 'A';
                            } else {
                                digits[i] += 1;
                                break;
                            }
                        }
                        DDD = String.valueOf(digits);
                    }

            }
            Code += DDD;
            Code += EE;

        }


        Code += ";" + Lname + ";"+ Fname + ";" + Mname + ";"+ Bday + ";"+ Gender + ";" + Address + ";" + Phone + ";" + NationalID+ ";";


        if (YorN.equals("Y") || YorN.equals("YES")) {

            Totxt.write(Code, flag, "Patients" , true);

        }

        else // if recorder presses no
        {
            addMenu();
        }

        addAgain();
        in.close();
    }
   
    private static void addAgain(){
        System.out.println("Add a patient again? [Y/N]");
        Scanner in = new Scanner(System.in);
        String again = in.nextLine();

        if (again.equals("y") || again.equals("Y")) {
            addMenu();
        } else {
            mainmenu.menu();
        }
        in.close();
    }
}
