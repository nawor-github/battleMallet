import java.util.ArrayList;
import java.io.*;

public class abilityDict  implements hasVersion { //Only one abilityDict exists at any one time
    private static abilityDict single_Instance = null;
    ArrayList<ability> aDict; //Array of abilities
    String label = "STARTABILITY";
    int entries;
    String version;

    public String getVersion(){
        return version;
    }

    private abilityDict(){
        single_Instance = this;
        aDict = readDict();
        entries = 0;
    }

    public void updateAbilityDict(fileEditor e){
        version = e.version;
        getInstance();
        aDict = new ArrayList<ability>();
        while (!e.lineTitled("ENDFILE")){
            ability temp = new ability(e); //This is so fucked up but does seem to work
            if (e.verbose){
                System.out.println("FINISHED READING ABILITY: " + temp.name);
            }
            aDict.add(temp);
        }
        entries = aDict.size();
    }

    public void writeAbilityDict(fileEditor e){
        try{
            String words = "ABILITY_DICT\n";
            e.w.write(words);
            if (e.verbose){
                System.out.println("WROTE:" + words);
            } 
            for (int i = 0; i < aDict.size(); i++){
                aDict.get(i).writeAbility(e);
                if (e.verbose){
                    System.out.println("WROTE:" + words);
                }  
            }
        } catch (IOException ex) {
            //Write error message
        }      
    }
    

    public static abilityDict getInstance(){
        if (single_Instance == null){
            single_Instance = new abilityDict();
        }
        return single_Instance;
    }

    private ArrayList<ability> readDict(){
        ArrayList<ability> dict = new ArrayList<ability>();
        String[] args = new String[]{".\\Files\\abilityDict.txt", "c", ".\\Files\\abilityDictTestCopy.txt"};
        fileEditor f = new fileEditor(args); // file_path_1 mode(rw/blank = read/write, c = copy) file_path_2 (if copying)
        return dict;
    }
}
