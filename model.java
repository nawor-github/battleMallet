import java.util.ArrayList;

public class model {
    String name;
    int hp;
    String save;
    int move;
    String type;
    ArrayList<Float> pointCost;
    int groupNum = 0;

    ArrayList<weapon> weapons;
    ArrayList<ability> abilities;
    ArrayList<String> tags;

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
    }

    model (){
        name = "temp";
        weapons = new ArrayList<weapon>();
        abilities = new ArrayList<ability>();
        tags = new ArrayList<String>();
    }

    public ArrayList<Float> calcPoint(){
        ArrayList<Float> result = new ArrayList<Float>();
        for (int group = 0; group < groupNum; group++){
            float groupCost = eHP;
            float damageCostMult = 1;
            float rangeMult = 1/4;
            for (int i = 0; i < weapons.size(); i++){
                if (weapons.get(i).weaponGroup == group || weapons.get(i).weaponGroup == 0){ //Count only weapons of the current group or in the universal group (0)
                    float weaponPoint = weapons.get(i).weaponPointCost(damageCostMult, rangeMult);
                    weapons.get(i).pointCost = weaponPoint;
                    groupCost += weaponPoint;
                }
            }
            groupCost += move/6; //Temp - think of better way to score movement
            result.add(groupCost);
        }
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
        String result = String.format("STARTMODEL %s\nSTATLINE %d %s %d %s %.2f HP_save_move_type_pointcost\nABILITIES ", name, hp, save, move, type, pointCost);
        for (int i = 0; i < abilities.size(); i++){
            result += abilities.get(i).name + " ";
        }
        result += l;
        for (int i = 0; i < weapons.size(); i++){
            result += weapons.get(i).readOut() + l;
        }
        result += "ENDMODEL\n";
        return result;
    }
}
