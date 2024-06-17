import java.util.ArrayList;
import java.io.*;

public class abilityDict { //Only one abilityDict exists at any one time
    private static abilityDict single_Instance = null;
    ArrayList<ability> ability; //Array of abilities

    private abilityDict(){
        single_Instance = this;
        ability = readDict();
    }

    public void updateAbilityDict(fileEditor e){

    }

    public static abilityDict getInstance(){
        if (single_Instance == null){
            single_Instance = new abilityDict();
        }
        return single_Instance;
    }

    private ArrayList<ability> readDict(){
        ArrayList<ability> dict = new ArrayList();
        String[] args = new String[]{"//programManagerTest.txt", "c", "//AbilityDictTESTCOPY.txt"};
        fileEditor f = new fileEditor(args); // file_path_1 mode(rw/blank = read/write, c = copy) file_path_2 (if copying)
        return dict;
    }
}
