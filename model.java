import java.util.ArrayList;

public class model {
    String name;
    int hp;
    String save;
    int move;
    String chargeDice;
    ArrayList<weapon> weapons;
    ArrayList<ability> abilities;
    ArrayList<String> tags;
    float pointCost;
    float eHP;
    model (String title, int HP, String Save, int move, ArrayList<weapon> Weapons, ArrayList<ability> Abilities, ArrayList<String> tags){
        name = title;
        hp = Integer.valueOf(HP);
        save = Save;
        weapons = Weapons;
        abilities = Abilities;
        eHP = eHP();
    }

    public float calcPoint(){
        float result = eHP;
        float damageCostMult = 1;
        float rangeMult = 1/2;
        for (int i = 0; i < weapons.size(); i++){
            if (weapons.get(i).isRanged){ //Ranged Weapon Calcs
                result += (weapons.get(i).avgDamage*damageCostMult) * (weapons.get(i).range*rangeMult);
            }
            else{ //Melee Weapon Calcs
                result += (weapons.get(i).avgDamage*damageCostMult);
            }
            
        }
        result += move/6;
        return result;
    }

    public float eHP (){
        return hp * effectiveHPMult(save);
    }

    public float effectiveHPMult (String save){
        if (save.equals("d4")){
            return (1+3/4);
        }
        else if (save.equals("d6")){
            return (1+3/6);
        }
        else if (save.equals("d8")){
            return (1+3/8);
        }
        else if (save.equals("d10")){
            return (1+3/10);
        }
        else if (save.equals("d12")){
            return (1+3/12);
        }
        else if (save.equals("d20")){
            return (1+3/20);
        }
        return 1;
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
