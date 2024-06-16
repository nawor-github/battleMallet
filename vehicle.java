import java.util.ArrayList;

public class vehicle extends model {
    @Override
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
}
