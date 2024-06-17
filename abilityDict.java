import java.util.ArrayList;
import java.io.*;

public class abilityDict { //Only one abilityDict exists at any one time
    private static abilityDict single_Instance = null;
    ArrayList<ability> ability; //Array of abilities
    String label = "STARTABILITY";
    int entries;

    private abilityDict(){
        single_Instance = this;
        ability = readDict();
        entries = 0;
    }

    public void updateAbilityDict(fileEditor e){
        getInstance();
        ability = new ArrayList<ability>();
        while (!e.lineTitled("ENDFILE")){
            ability temp = new ability(e); //This is so fucked up but does seem to work
            ability.add(temp);
        }
        entries = ability.size();
    }

    public static abilityDict getInstance(){
        if (single_Instance == null){
            single_Instance = new abilityDict();
        }
        return single_Instance;
    }

    private ArrayList<ability> readDict(){
        ArrayList<ability> dict = new ArrayList<ability>();
        String[] args = new String[]{"//programManagerTest.txt", "c", "//AbilityDictTESTCOPY.txt"};
        fileEditor f = new fileEditor(args); // file_path_1 mode(rw/blank = read/write, c = copy) file_path_2 (if copying)
        return dict;
    }
}
