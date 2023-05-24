package txt_tools;


import java.io.*;


public class Totxt {
    private static boolean repeat;

    private static void setRepeat(boolean lab){
        if(lab)
            repeat = true;
        else
            repeat = false;
    }
    public static void write(String code, int flag, String fileName , boolean lab){
        setRepeat(lab);
        FileWriter file;

        try {
            if(repeat){
                 file  = new FileWriter("..\\mp_1\\"+ fileName +".txt",true);
            }
            else{
                file  = new FileWriter("..\\mp_1\\"+ fileName +"_Request.txt",true);

            }


                BufferedWriter wr = new BufferedWriter(file);
                if(flag == 0){// to stop having a new line when a new file is crated
                    wr.newLine();
                }

                wr.write(code);

                wr.close();
                file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
