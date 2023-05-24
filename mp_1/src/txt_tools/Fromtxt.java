package txt_tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Fromtxt {
    private static boolean repeat;

    private static void setRepeat(boolean lab){
        if(lab)
            repeat = true;
        else
            repeat = false;
    }

     public static String read(String fileName ,boolean lab ){
         setRepeat(lab);
         File file;
         if(repeat){
              file = new File("..\\mp_1\\"+ fileName +".txt");
         }
         else{
              file = new File("..\\mp_1\\"+ fileName +"_Request.txt");
         }

         Scanner in = null;
         String lastline = null;

        try {

            in = new Scanner(file);

            while (in.hasNextLine()) {
                lastline = in.nextLine();
                }


            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lastline;
    }
}
