import java.io.*;
import java.util.ArrayList;

public class fileEditor {
    File file;
    String path;
    FileWriter w;
    FileReader in;
    BufferedReader r;
    boolean verbose;

    String[] line;

    fileEditor (String p){
        path = p;
        file = makeFile(p);
        try {
            FileWriter w = new FileWriter(path);
            FileReader in = new FileReader(path);
            BufferedReader r = new BufferedReader(in);
        }
        catch (IOException e) {
            System.out.println("An error occurred with the file path.");
            e.printStackTrace();
        }
    }

    public void findTarget(String target){ //Finds first line of a certain label
        resetCursor(); //This is probably hugely inefficient
        while (lineTitled("ENDFILE") == false){
            getLine();
            if (lineTitled(target)){
                return;
            }
        }
    }

    public void findTarget(String targetLabel, String targetName){ //Finds first line of a certain label with a certain first value. e.g. STARTMODEL Golbin_Warchief
        resetCursor(); //This is probably hugely inefficient
        while (lineTitled("ENDFILE") == false){
            getLine();
            if (lineTitled(targetLabel)){
                if (line[1].equals(targetName)){
                    return;
                }
            }
        }
    }

    private void resetCursor(){
        try {
            FileWriter w = new FileWriter(path);
            FileReader in = new FileReader(path);
            BufferedReader r = new BufferedReader(in);
        }
        catch (IOException e) {
            System.out.println("An error occurred with the file path.");
            e.printStackTrace();
        }
    }

    private boolean lineTitled(String target){
        String s = getLine()[0];
        if (s.equals(target)){
            return true;
        }
        return false;
    }

    private String[] getLine(){ //Reads next line and also returns it as a list of Strings
        try{
            String rawLine = r.readLine();
            if (rawLine != null){
                if (verbose){
                    System.out.println("READ: " + line);
                }
                line = rawLine.split(" ");
                return line;
            }
            else{
                if (verbose){
                    System.out.println("READ: NULL");
                }
                return new String[0];
            }
        }
        catch(IOException e){
            e.getStackTrace();
            return new String[0];
        }
        
    }

    public static File makeFile(String path){
        try {
            File f = new File(path);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } 
            else {
                System.out.println("File already exists.");
            } 
            return f;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }   
}
