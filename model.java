import java.util.ArrayList;

public class model {
    String name;
    int hp;
    String save;
    int move;
    String chargeDice;
    ArrayList<weapon> weapons;
    ArrayList<ability> abilities;
    model (String title, int HP, String Save, int move, ArrayList<weapon> Weapons, ArrayList<ability> Abilities){
        name = title;
        hp = Integer.valueOf(HP);
        save = Save;
        weapons = Weapons;
        abilities = Abilities;
    }
    public String printOutString(){
        String l = "\n";
        String result = name + l + hp + " HP" + l + save + " save" + l + move + " move" + l + "Abilities:," + l;
        for (int i = 0; i < weapons.size(); i++){
            result += weapons.get(i).readOut() + l;
        }
        result += "END\n";
        return result;
    }
}
