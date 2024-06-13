import java.util.ArrayList;
public class weapon {
    String name;
    int range;
    int attacks;
    int damage;
    String skillDice;
    int weaponGroup; //Used for a unit with multiple loadouts/variations (Loadout 1, 2, etc). Group 0 is SPECIAL - all loadouts. 
    ArrayList<String> tags;
    
    int skill;
    boolean isRanged;
    int maxRange;
    float avgDamage;
    float pointCost;
    
    public weapon () {
        tags = new ArrayList<String>();
    }

    public weapon (boolean ranged, String title, int Range, int atks, int dmg, String dice, int group, ArrayList<String> t) {
        isRanged = ranged;
        name = title;
        attacks = atks;
        damage = dmg;
        skill = diceToRank(dice);
        skillDice = dice;
        avgDamage = calcAverageDamage(attacks,damage,skill);
        range = Range;
        tags = t;
        weaponGroup = group;
        maxRange = range * 2;     
    }

    public String readOut(){
        String result = "MELEE ";
        if (isRanged == true){
            result = "RANGE "; 
        }
        result += String.format("%s %d %d %d %s %.2f %d name_range_attack_damage_skill_pointcost_weaponGroup\nWTAGS ", name, range, attacks, damage, skillDice, pointCost, weaponGroup);
        for (int i = 0; i < tags.size(); i++){
            result += tags.get(i) + " ";
        }
        return result;
    }

    public String niceReadOut(){
        if (isRanged == true){
            return String.format("%s: Range: %d, %d attacks, %d damage, skill %s, costs %.2f in variant %d name_range_attack_damage_skill_pointcost_weaponGroup", name, range, attacks, damage, skillDice, pointCost, weaponGroup);
        } else {
            return String.format("%s: Reach: %d, %d attacks, %d damage, skill %s, costs %.2f in variant %d name_range_attack_damage_skill_pointcost_weaponGroup", name, range, attacks, damage, skillDice, pointCost, weaponGroup);
        }
    }

    private float calcAverageDamage(int attacks, int damage, int skill){
        return attacks * damage * chanceMult(skill);
    }

    private float chanceMult(int skill){
        if (skill == 1){
            return 3/4;
        }
        if (skill == 2){
            return 3/6;
        }
        if (skill == 3){
            return 3/8;
        }
        if (skill == 4){
            return 3/10;
        }
        if (skill == 5){
            return 3/12;
        }
        if (skill == 6){
            return 3/20;
        }
        return 0;
    }

    private int diceToRank(String dice){
        if (dice.equals("d4")){
            return 1;
        }
        else if (dice.equals("d6")){
            return 2;
        }
        else if (dice.equals("d8")){
            return 3;
        }
        else if (dice.equals("d10")){
            return 4;
        }
        else if (dice.equals("d12")){
            return 5;
        }
        else if (dice.equals("d20")){
            return 6;
        }
        return 0;
    }
}
