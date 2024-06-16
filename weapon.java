import java.util.ArrayList;
public class weapon implements dataStructure{
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
        pointCost = 0;
    }

    public float calcWeaponPoint(float damageCostMult, float rangeDiscount){
        float weaponPoint = 0;
        avgDamage = calcAverageDamage();
        System.out.println(String.format("%s average damage: %.2f", name, avgDamage));
        if (isRanged){ //Ranged Weapon Calcs
            //For ranged weapons points = (avgDamage x damage Mult) x //For ranged weapons points = (range x range Mult)
            //Range mult is <1 so ranges of 2-4 will be cheaper than a melee weapon of equivalent avgDamage
            System.out.println(String.format("Damage point %f. Range point %f. Range %d", avgDamage*damageCostMult, range/rangeDiscount, range));
            weaponPoint = (avgDamage*damageCostMult) * (range/rangeDiscount); 
        }
        else{ //Melee Weapon Calcs
            System.out.println(String.format("Damage point %f. Range point %f. Range %d", avgDamage*damageCostMult, range/rangeDiscount, range));
            weaponPoint = avgDamage*damageCostMult * range; //For melee weapons points = (avgDamage x damage Mult) * reach
        }
        pointCost = weaponPoint;
        System.out.println(String.format("Weapon %s point cost: %.2f", name, pointCost));
        return weaponPoint;
    }

    public weapon (boolean ranged, String title, int Range, int atks, int dmg, String dice, int group, ArrayList<String> t) {
        isRanged = ranged;
        name = title;
        attacks = atks;
        damage = dmg;
        skill = diceToRank(dice);
        skillDice = dice;
        avgDamage = calcAverageDamage();
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
        String result = "";
        if (isRanged == true){
            result = String.format("%s: Range: %d,", name, range);
        } else {
            result = String.format("%s: Reach: %d,", name, range);
        }
        return result + String.format(" %d attacks, %d damage, skill %s, costs %.2f in variant %d name_range_attack_damage_skill_pointcost_weaponGroup", attacks, damage, skillDice, pointCost, weaponGroup);
    }

    public float calcAverageDamage(){
        skill = diceToRank(skillDice);
        System.out.println(String.format("Skill value %d from dice %s. Damage %d, attacks %d", skill, skillDice, damage, attacks));
        //return attacks * damage * chanceMult(skill);
        return attacks * damage;
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
        if (dice.trim().equals("d4")){
            return 1;
        }
        else if (dice.trim().equals("d6")){
            return 2;
        }
        else if (dice.trim().equals("d8")){
            return 3;
        }
        else if (dice.trim().equals("d10")){
            return 4;
        }
        else if (dice.trim().equals("d12")){
            return 5;
        }
        else if (dice.trim().equals("d20")){
            return 6;
        }
        return 0;
    }
}
