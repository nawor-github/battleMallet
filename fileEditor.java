import java.io.*;

public class fileEditor implements hasVersion{ 
    String writePath;
    String readPath;
    FileWriter w;
    FileReader in;
    BufferedReader r;
    boolean verbose = true;
    File readFile;
    File writeFile;
    abilityDict dict;
    String version;

    String[] line;

    

    fileEditor (String[] args){ // file_path_1 mode(rw/blank = read/write, c = copy) file_path_2 (if copying)
        readPath = args[0];
        writePath = args[0];
        if (args[1].equals("c")){
            writePath = args[2];
        }
        //System.out.println("Preparing to make file");
        writeFile = getFile(writePath);
        try {
            w = new FileWriter(writePath);
            in = new FileReader(readPath);
            r = new BufferedReader(in);
            line = getNextLine();
            version = line[2].trim();
            line = getNextLine();
            if (lineTitled("FACTION")){
                if (verbose){
                    System.out.println("FILE TYPE: FACTION");
                }
                faction f = new faction(version);
                
                f.readFaction(this);
                writeStart(f);
                f.writeFaction(this);
                writeEnd();
            }
            if (lineTitled("ABILITY_DICT")){
                if (verbose){
                    System.out.println("FILE TYPE: ABILITY DICTIONARY");
                }
                dict = dict.getInstance();
                dict.updateAbilityDict(this);
                writeStart(dict);
                dict.writeAbilityDict(this);
                writeEnd();
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

    public static File getFile(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) { 
            return f;
        }
        return makeFile(path);
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

    private void writeStart(hasVersion v){
        String words = "BATTLEMALLET_FILE ROWAN_CLARKE " + v.getVersion() + "\n";
        try {
            w.write(words);
            if (verbose){
                System.out.println("WROTE:" + words);
            }  
        }
        catch (IOException ex) {
            //FIX ME!
        }
        
    }

    private void writeEnd(){
        String words = "ENDFILE";
        try {
            w.write(words);
            if (verbose){
                System.out.println("WROTE:" + words);
            }  
        }
        catch (IOException ex) {
            //FIX ME!
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

    public static String removeLabel (String[] txt) {
        String s = "";
        for (int i = 1; i < txt.length; i++){
            s += txt[i].trim()+" ";
        }
        return s;
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
        String s = line[0].trim();
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
                    System.out.println("READ: " + rawLine);
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

    public String getVersion(){
        return version;
    }
}
