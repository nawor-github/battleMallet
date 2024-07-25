public class scalars {
    Double hp, save, eHP, move, isMelee, range, attack, damage, skill, avgDmg;

    public scalars(weapon w){
        //HP, save, eHP, move = 0 (this i a weapon)
        hp = 0.0;
        save = 0.0;
        eHP = 0.0;
        move = 0.0;
        //isMelee AS IS (0 ranged, 1 melee)
        scalarIsMelee(!w.isRanged);
        //range
        range = scalarFloatNormalised(w.range, 20.0);
        //attacks
        attack = scalarFloatNormalised(w.attacks, 10.0);
        //damage
        damage = scalarFloatNormalised(w.damage, 5.0);
        //skill
        skill = scalarDiceLowGood(w.skillDice);
        //avgDmg
        avgDmg = scalarFloatNormalised(w.avgDamage, 2.0);

    }

    public scalars(model m){
        //HP: 0.05 at 1 and 1 at 20
        scalarHP(m.hp);
        //Save: 0.1 at D20 and 1 at D4
        save = scalarDiceLowGood(m.save);
        //eHP: 0.05 at 1 and 1 at 20 
        eHP = scalarFloatNormalised(m.eHP, 20.0);
        //Move: 0.1 at d4 and 1 at D20
        move = scalarDiceLowBad(m.chargeDice);
        //isMelee, range, attack, damage, skill, avgDmg -1 (this is a model)
        isMelee = 0.0;
        attack = 0.0;
        damage = 0.0;
        skill = 0.0;
        avgDmg = 0.0;
    }


    private Double scalarDiceLowGood(String s){
        if (s.equals("d4")){
            Double result = 1.0;
            return result;
        }
        else if (s.equals("d6")){
            Double result = 0.7;
            return result;
        }
        else if (s.equals("d8")){
            Double result = 0.4;
            return result;
        }
        else if (s.equals("d10")){
            Double result = 0.3;
            return result;
        }
        else if (s.equals("d12")){
            Double result = 0.25;
            return result;
        }
        else if (s.equals("d20")){
            Double result = 0.15;
            return result;
        }
        Double result = 2.0;
        return result;
    }

    private Double scalarDiceLowBad(String s){
        if (s.equals("d4")){
            Double result = 0.15;
            return result;
        }
        else if (s.equals("d6")){
            Double result = 0.3;
            return result;
        }
        else if (s.equals("d8")){
            Double result = 0.45;
            return result;
        }
        else if (s.equals("d10")){
            Double result = 0.6;
            return result;
        }
        else if (s.equals("d12")){
            Double result = 0.8;
            return result;
        }
        else if (s.equals("d20")){
            Double result = 1.0;
            return result;
        }
        Double result = 2.0;
        return result;
    }

    private Double scalarFloatNormalised(float x, Double normaliser){
        double i = x;
        return i / normaliser;
    }

    private void scalarHP(int mhp){
        double i = mhp;
        hp = i / 20;
    }

    private void scalarEHP(float i){
        double x = i / 20;
        eHP = x;
    }

    private void scalarIsMelee(boolean mIsMelee){
        Double x = 0.0;
        if (mIsMelee){
            x = 1.0;
        }
        isMelee = x;
    }
}
