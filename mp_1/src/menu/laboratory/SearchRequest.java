package menu.laboratory;


import menu.mainmenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("resource")
public class SearchRequest extends Request{
    private static boolean repeat;

    
    static String searchMenu(boolean b) {
        String returned = "";
        String choice ;
        int flag = 0;
        Scanner in = new Scanner(System.in);

        do {
            setRepeat(b);
            System.out.println("-----Search Requests-----");
            System.out.println("Do you know the Patient's Request ID?  [Y/N]");
            choice = in.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                returned = searchRUID();
                if(repeat)
                    searchAgain();
                flag = 1;
            }

            else {
                    System.out.println("Do you know the patients UID? [Y/N]");
                    choice = in.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                       returned = searchPUID("", 0);
                       if(repeat)
                            searchAgain();
                        flag = 1;
                    }
                    else {

                            if(repeat){
                                System.out.println("Im sorry, I can't help you now.");
                                searchAgain();
                            }
                        flag = 1;
                    }
                }
        } while (flag == 0);

        return returned;
    }


    // -------------------UID-------------------//
    public static String searchPUID(String PDF, int j) {
        String line;
        String[] data = new String[13];
        String[] data2 = new String[13];
        String[] data3 = new String[13];
        int i =0;
        String patientUID;
        Scanner in = new Scanner(System.in);
        String CONFIRMATION = "";

        if(j==0){

            System.out.println("Enter Patient's UID");
            patientUID = in.nextLine();
        }
        else{
            patientUID = PDF;
        }


        ArrayList<String> Patient = new ArrayList<>();
        ArrayList<String> service_codes = new ArrayList<>();

       // https://www.geeksforgeeks.org/file-listfiles-method-in-java-with-examples/

        try {

            File f = new File("..\\mp_1\\");
            FileFilter filter = new FileFilter() {

                public boolean accept(File f)
                {
                    return f.getName().endsWith("_Request.txt");
                }
            };

            File[] files = f.listFiles(filter);

            for ( i = 0; i < files.length; i++) {
                try {
                    FileReader file = new FileReader("..\\mp_1\\"+files[i].getName());
                    BufferedReader br = new BufferedReader(file);

                    while ((line = br.readLine()) != null) {
                        data = line.split(";");
                        if (data[1].equals(patientUID) && data.length <= 5) {
                            try {
                                  Patient.add(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]); // with results
                            }catch(ArrayIndexOutOfBoundsException e){
                                  Patient.add(data[0] + " " + data[1] + " " + data[2] + " " + data[3]);//without results
                            }
                            service_codes.add(data[0].substring(0,3));
                        }
                    }
                    br.close();
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("There is no record for this request.");
                    if(repeat){
                        searchAgain();
                    }

                }
                catch(StringIndexOutOfBoundsException e) {
                    System.out.println("There is no record for this request.");
                    if(repeat){
                        searchAgain();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("There is no record for this request.");
                    if(repeat){
                        searchAgain();
                    }
                }

            }
            if (Patient.isEmpty()) {

                if(repeat){
                    System.out.println("No Record Found");
                    searchAgain();
                }
            }
            else {
                System.out.println("Request's UID\tLab Test Type\tRequest Date\tResult");

                    for(i=0; i< service_codes.size(); i++){
                        FileReader file2 = new FileReader("..\\mp_1\\Services.txt");
                        BufferedReader br2 = new BufferedReader(file2);
                        data3= Patient.get(i).split(" ");

                        while ((line = br2.readLine()) != null ) {
                            data2 = line.split(";");
                            if (data2[0].equalsIgnoreCase(service_codes.get(i)) && data2.length <= 3) {
                                break;
                            }
                        }
                        try
                        {
                            System.out.println(data3[0]+"\t"+data2[1]+"\t"+ data3[2]+ "\t"+data3[4]);
                        }
                        catch(ArrayIndexOutOfBoundsException e){
                            System.out.println(data3[0]+"\t"+data2[1]+"\t"+data3[2]+"\t"+"No Result Yet"); // if searched is has not result just a little easter egg and precaution
                        }

                        br2.close();
                     }

                     if(!repeat){
                        System.out.println("Enter Request UID");
                        CONFIRMATION= in.nextLine();
                        return CONFIRMATION;
                    }

            }



        }
        catch (Exception e) {
            if(!repeat){
                System.out.println("Enter Request UID");
                CONFIRMATION= in.nextLine();
                return CONFIRMATION;
            }

            if(repeat){
                searchAgain();
            }
        }
        return CONFIRMATION;
    }




    // -------------------RUID-------------------//

    private static String searchRUID() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Request UID");
        String RUID = in.nextLine();
        String line;
        String CONFIRMATION = "";
        String[] data = new String[13];
        String[] data2 = new String[13];
        int flag =0;
        String reqUID ;

                try {
                    reqUID = RUID.substring(0, 3); // used a try catch for strings less than 3 for no stringoutofbounds error
                    FileReader file = new FileReader("..\\mp_1\\"+reqUID+"_Request.txt");
                    BufferedReader br = new BufferedReader(file);

                    while ((line = br.readLine()) != null) {
                        data = line.split(";");
                         if (data[0].equals(RUID) && data.length <= 5) {
                            flag = 0;

                            if(!repeat){
                                CONFIRMATION = RUID;
                                return CONFIRMATION;
                            }

                            break;
                        } else {
                            flag = 1;
                        }
                    }

                    if (flag == 1) {

                        if(repeat){
                            System.out.println("No Record Found");
                            searchAgain();
                        }
                    }
                    else {

                        FileReader file2 = new FileReader("..\\mp_1\\Services.txt");
                        BufferedReader br2 = new BufferedReader(file2);


                        while ((line = br2.readLine()) != null) {
                            data2 = line.split(";");
                            if (data2[0].equalsIgnoreCase(reqUID) && data2.length <= 3) {
                                break;
                            }
                        }


                            System.out.println("Request's UID\tLab Test Type\tRequest Date\tResult");
                            try{
                                System.out.println(RUID+"\t"+data2[1]+"\t"+data[2]+"\t"+data[4]);
                            }
                            catch(ArrayIndexOutOfBoundsException e){
                                System.out.println(RUID+"\t"+data2[1]+"\t"+data[2]+"\t"+"No Result Yet"); // if searched is has not result just a little easter egg and precaution
                            }


                        br2.close();
                            if(repeat){
                                searchAgain();
                            }


                    }

                    br.close();

                }
                catch (FileNotFoundException e)
                {
                    if (repeat){
                        System.out.println("There is no record for this request");
                        searchAgain();
                    }

                }
                catch(StringIndexOutOfBoundsException e) {
                    if (repeat){
                        System.out.println("There is no record for this request");
                        searchAgain();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (repeat){
                        System.out.println("There is no record for this request");
                        searchAgain();
                    }
                }

        return CONFIRMATION;

    }

    private static void setRepeat(boolean b){
        if(b)
            repeat = true;
        else
            repeat = false;
    }

    public static void searchAgain() {
        System.out.println("Search searchAgain? [Y/N]");
        Scanner in = new Scanner(System.in);
        String searchAgain = in.nextLine();

        if (searchAgain.equalsIgnoreCase("y")) {
            SearchRequest.searchMenu(true);
        } else {
            mainmenu.menu();
        }

    }


}
