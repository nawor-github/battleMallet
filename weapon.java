public class weapon {
    String name;
    boolean isRanged;
    int attacks;
    int damage;
    int skill;
    String skillDice;
    int range;
    int maxRange;
    float avgDamage;
    
    public weapon () {
        name = "Null weapon";
        attacks = 1;
        damage = 1;
        skill = 1;
        avgDamage = calcAverageDamage(attacks,damage,skill);
        range = 6;
        skillDice = "d10";
        maxRange = 12;
        isRanged = true;
    }

    public weapon (String title, int Range, int atks, int dmg, String dice) {
        name = title;
        attacks = atks;
        damage = dmg;
        skill = diceToRank(dice);
        skillDice = dice;
        avgDamage = calcAverageDamage(attacks,damage,skill);
        range = Range;
        if (range > 1) {
            isRanged = true;
            maxRange = range * 2;
        }
        else{
            isRanged = false;
            maxRange = range;
        }
        
    }

    public String readOut(){
        String s = ", ";
        return name +s+ range +s+ "range" +s+ attacks +s+ "atk" +s+ damage +s+ "dmg" +s+ skillDice +s+ "skill";
    }

    public String niceReadOut(){
        return name + "|| Range: " + range + ", " + attacks + " attacks, " + damage + " damage, Skilldice: " + skillDice;
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
