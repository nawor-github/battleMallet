import java.util.ArrayList;

public class ability extends dataStructure{
    float cost;
    String label = "ABILITIES";
    ArrayList<String> rules;
    //F is FORMULA
    scoreFormula hpF, saveF, eHPF, moveF, isMeleeF, rangeF, attackF, damageF, skillF, avgDmgF;

    ability (String n){
        name = n;
        rules = new ArrayList<>();
        tags = new ArrayList<>();
    }
    ability (ability a){
        name = a.name;
        cost = a.cost;
        rules = a.rules;
        tags = a.tags;
    }

    public String writeAbility(fileEditor e){
        if (e.verbose){
            System.out.println("Writing ability string: "+ name);
        }
        String result = String.format("STARTABILITY %s\n", name);
        for (int i = 0; i < rules.size(); i++){ //LINES OF RULE TEXT
            result += String.format("RULES %s\n", rules.get(i));
        }
        result += String.format("\nPOINTMOD %s %s %s %s %s %s %s %s %s %s hp_save_eHP_move_isMelee_range_attack_damage_skill_avgDmg\n", hpF.toString(), saveF.toString(), eHPF.toString(), moveF.toString(), isMeleeF.toString(), rangeF.toString(), attackF.toString(), damageF.toString(), skillF.toString(), avgDmgF.toString());
        result += "\nCONFLICTS ";
        for (int i = 0; i < tags.size(); i++){ //LIST OF CONFLICTS
            result += String.format("%s ", tags.get(i).trim());
        }
        result += "\nENDABILITY\n";
        return result;
    }

    public ability(fileEditor e) {
        if (e.line == null){
            name = "FAILED";
            return;
        }
        rules = new ArrayList<>();
        tags = new ArrayList<>();
        name = e.line[1].trim();
        while (!e.lineTitled("ENDABILITY")){
            if (e.lineTitled("RULES")){
                
                String s = fileEditor.removeLabel(e.line);
                rules.add(s);
                if (e.verbose){
                    System.out.println("READING NEW LINE OF RULES, LINE: "+ rules.size());
                }
            }
            if (e.lineTitled("POINTMOD")){
                if (e.verbose){
                    System.out.println("READING POINTMODS");
                }
                //POINTMOD hp save eHP move isMelee range attack damage skill avgDmg
                //0        1  2    3   4    5       6     7      8      9     10
                hpF = new scoreFormula(e.line[1].trim());
                saveF = new scoreFormula(e.line[2].trim());
                eHPF = new scoreFormula(e.line[3].trim());
                moveF = new scoreFormula(e.line[4].trim());
                isMeleeF = new scoreFormula(e.line[5].trim());
                rangeF = new scoreFormula(e.line[6].trim());
                attackF = new scoreFormula(e.line[7].trim());
                damageF = new scoreFormula(e.line[8].trim());
                skillF = new scoreFormula(e.line[9].trim());
                avgDmgF = new scoreFormula(e.line[10].trim());
            }
            if (e.lineTitled("CONFLICTS")){
                if (e.verbose){
                    System.out.println("READING CONFLICTS");
                }
                for (int i = 1; i < e.line.length; i++){
                    tags.add(e.line[i].trim());
                }
            }
            e.getNextLine();
        }

    }
}
