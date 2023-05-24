package menu.laboratory;

import menu.mainmenu;
import menu.manage_patient.SearchPatient;
import menu.services.SearchService;
import txt_tools.Fromtxt;
import txt_tools.Totxt;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


@SuppressWarnings("resource")
public class AddRequest extends Request{

    static void addMenu() {

        patientUID = (SearchPatient.searchMenu(false));
        String Unique;
        int flag = 0;
        String serviceCode;
        int increment =0;
        String AA = "AA";
        String BB = "00";

        if (!Objects.equals(patientUID, "")) {
            reqUID = (SearchService.searchMenu(false));

            if (!Objects.equals(reqUID, "")) {// put here if all input is correct

                try {
                    File list = new File("..\\mp_1\\"+reqUID+"_Request.txt");
                    if (list.createNewFile()) {
                        System.out.println();
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                String Unique_in_File = Fromtxt.read(reqUID , false);

                String localSystemDate = java.time.LocalDate.now().toString();
                String requestTime = localSystemDate.substring(0,4) + localSystemDate.substring(5,7) + localSystemDate.substring(8,10);
                Unique = reqUID.toUpperCase() + requestTime;


                if (Unique_in_File == null) { // if the txt file is empty
                    Unique += AA;
                    Unique += BB;
                    flag=1;
                }
                else if(!Unique_in_File.substring(9, 11).equals(localSystemDate.substring(8, 10))){
                    Unique += AA;
                    Unique += BB;
                }

                else {
                    AA = Unique_in_File.substring(11, 13);
                    BB = Unique_in_File.substring(13, 15);
                    int ToInt = Integer.parseInt(BB);

                    if (ToInt < 9) {
                        ToInt += 1;
                        BB = "0" + Integer.toString(ToInt);

                    } else if (ToInt == 99) {
                        BB = "00";
                        increment = 1;

                    } else {
                        ToInt += 1;
                        BB = Integer.toString(ToInt);

                    }

                    if (increment == 1) {
                        if(AA.equals("ZZ")){
                            AA="AA";
                        }
                        else{
                            char[] digits = AA.toCharArray();

                            for (int i = AA.length() - 1; i >= 0; --i) {
                                if (digits[i] == 'Z') {
                                    digits[i] = 'A';
                                } else {
                                    digits[i] += 1;
                                    break;
                                }
                            }
                            AA = String.valueOf(digits);
                        }

                    }
                    Unique += AA;
                    Unique += BB;
                }
                serviceCode = Unique;
                Unique = Unique + ";" + patientUID + ";" + requestTime + ";"  + java.time.LocalTime.now().toString().substring(0,2) + java.time.LocalTime.now().toString().substring(3,5)+";";

                Totxt.write(Unique, flag, reqUID , false);
                System.out.println("Laboratory Request "+ serviceCode +" has been added to file " +reqUID.toUpperCase()+"_Requests.txt.");

                addAgain();




            }
            else{ // if service code is wrong or pressed n
                System.out.println("Im sorry, I can't help you now.");
                addAgain();
            }
        }
        else// if UID is wrong or pressed n
        {
            System.out.println("Im sorry, I can't help you now.");
                addAgain();
        }


    }
    
    private static void addAgain() {
        System.out.println("Add addAgain? [Y/N]");
        Scanner in = new Scanner(System.in);
        String addAgain = in.nextLine();

        if (addAgain.equalsIgnoreCase("y")) {
            addMenu();
        } else {
            mainmenu.menu();
        }

    }


}