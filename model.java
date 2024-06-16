import java.util.ArrayList;

public class model implements dataStructure{
    String name;
    int hp;
    String save;
    int move;
    String type;
    ArrayList<Float> pointCost;
    ArrayList<weapon> weapons;
    ArrayList<ability> abilities;
    ArrayList<String> tags;

    int groupNum = 0;
    String chargeDice;
    float eHP;
    model (String title, int HP, String Save, int move, ArrayList<weapon> Weapons, ArrayList<ability> Abilities, ArrayList<String> tags, int gNum){
        name = title;
        hp = Integer.valueOf(HP);
        save = Save;
        weapons = Weapons;
        abilities = Abilities;
        eHP = eHP();
        groupNum = gNum+1;
        pointCost = calcPoint();
    }

    model (String title){
        name = title;
        weapons = new ArrayList<weapon>();
        abilities = new ArrayList<ability>();
        tags = new ArrayList<String>();
        pointCost = new ArrayList<Float>();
    }

    model (){
        name = "temp";
        weapons = new ArrayList<weapon>();
        abilities = new ArrayList<ability>();
        tags = new ArrayList<String>();
        pointCost = new ArrayList<Float>();
    }

    public ArrayList<Float> calcPoint(){
        System.out.println("Calcing points of: " + name);
        ArrayList<Float> result = new ArrayList<Float>();
        eHP = effectiveHPMult();
        for (int group = 0; group <= groupNum; group++){
            System.out.println(String.format("%s eHP: %.2f", name, eHP));
            float groupCost = eHP;
            float damageCostMult = 1;
            float rangeDiscount = 4;
            for (int i = 0; i < weapons.size(); i++){
                if (weapons.get(i).weaponGroup == group || weapons.get(i).weaponGroup == 0){ //Count only weapons of the current group or in the universal group (0)
                    float weaponPoint = weapons.get(i).calcWeaponPoint(damageCostMult, rangeDiscount);
                    groupCost += weaponPoint;
                }
            }
            groupCost += move/6; //Temp - think of better way to score movement
            result.add(groupCost);
        }
        pointCost = result;
        return result;
    }

    public float eHP (){
        return hp * effectiveHPMult();
    }

    public float effectiveHPMult (){
        if (save.equals("d4")){
            return (1+(3/4));
        }
        else if (save.equals("d6")){
            return (1+(3/6));
        }
        else if (save.equals("d8")){
            return (1+(3/8));
        }
        else if (save.equals("d10")){
            return (1+(3/10));
        }
        else if (save.equals("d12")){
            return (1+(3/12));
        }
        else if (save.equals("d20")){
            return (1+(3/20));
        }
        return 1;
    }

    public String printOutString(){
        String l = "\n";
        System.out.println("Printing string");
        
        String result = String.format("STARTMODEL %s\nSTATLINE %d %s %d %s HP_save_move_type\nPOINTCOST ", name, hp, save, move, type);
        for (int i = 0; i < pointCost.size(); i++){ //Point costs for loadouts
            result += String.format("%.2f ", pointCost.get(i));
        }
        result += "\nABILITIES ";
        for (int i = 0; i < abilities.size(); i++){ //Ability list
            result += abilities.get(i).name + " ";
        }
        result += l;
        for (int i = 0; i < weapons.size(); i++){ //Weapons
            result += weapons.get(i).readOut() + l;
        }
        result += "ENDMODEL\n";
        return result;
    }
}
