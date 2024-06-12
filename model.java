import java.util.ArrayList;

public class model {
    String name;
    int hp;
    String save;
    ArrayList<weapon> weapons;
    ArrayList<ability> abilities;
    model (String title, String HP, String Save, ArrayList<weapon> Weapons, ArrayList<ability> Abilities){
        name = title;
        hp = Integer.valueOf(HP);
        save = Save;
        weapons = Weapons;
        abilities = Abilities;
    }
}
