import java.io.*;
import java.util.ArrayList;

public class fileEditor { 
    String writePath;
    String readPath;
    FileWriter w;
    FileReader in;
    BufferedReader r;
    boolean verbose;
    File readFile;
    File writeFile;
    abilityDict dict;

    String[] line;

    fileEditor (String[] args){ // file_path_1 mode(rw/blank = read/write, c = copy) file_path_2 (if copying)
        readPath = args[0];
        writePath = args[0];
        if (args[1].equals("c")){
            writePath = args[2];
        }
        
        writeFile = makeFile(writePath);
        try {
            FileWriter w = new FileWriter(writePath);
            FileReader in = new FileReader(readPath);
            BufferedReader r = new BufferedReader(in);
            line = getNextLine(); //Currently we do not look at the main header :)
            String version = line[3].trim();
            line = getNextLine();
            if (lineTitled("FACTION")){
                faction f = new faction(version);
                f.readWriteFaction(this);
            }
            if (lineTitled("ABILITY_DICT")){
                dict.updateAbilityDict(this);
            }
            r.close();
            in.close();
            w.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred with the file path.");
            e.printStackTrace();
        }
    }

    public void findTarget(String target){ //Finds first line of a certain label
        resetCursor(); //This is probably hugely inefficient
        while (lineTitled("ENDFILE") == false){
            getNextLine();
            if (lineTitled(target)){
                return;
            }
        }
    }

    public void findTarget(String targetLabel, String targetName){ //Finds first line of a certain label with a certain first value. e.g. STARTMODEL Golbin_Warchief
        resetCursor(); //This is probably hugely inefficient
        while (lineTitled("ENDFILE") == false){
            getNextLine();
            if (lineTitled(targetLabel)){
                if (line[1].equals(targetName)){
                    return;
                }
            }
        }
    }

    private void resetCursor(){
        try {
            FileWriter w = new FileWriter(writePath);
            FileReader in = new FileReader(readPath);
            BufferedReader r = new BufferedReader(in);
        }
        catch (IOException e) {
            System.out.println("An error occurred with the file path.");
            e.printStackTrace();
        }
    }

    public boolean lineTitled(String target){
        String s = getNextLine()[0];
        if (s.equals(target)){
            return true;
        }
        return false;
    }

    public String[] getNextLine(){ //Reads next line and also returns it as a list of Strings
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
