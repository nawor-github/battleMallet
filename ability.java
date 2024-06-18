import java.util.ArrayList;

public class ability extends dataStructure{
    float cost;
    String label = "ABILITIES";
    ArrayList<String> rules;
    Float hpMult;
    Float saveMult;
    Float eHPMult;
    Float moveMult;
    Float isMeleeMult;
    Float rangeMult;
    Float attackMult;
    Float damageMult;
    Float skillMult;
    Float avgDmgMult;

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
        result += String.format("\nPOINTMOD %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f %.2f hp_save_eHP_move_isMelee_range_attack_damage_skill_avgDmg\n", hpMult, saveMult, eHPMult, moveMult, isMeleeMult, rangeMult, attackMult, damageMult, skillMult, avgDmgMult);
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
                hpMult = Float.parseFloat(e.line[1].trim());
                saveMult = Float.parseFloat(e.line[2].trim());
                eHPMult = Float.parseFloat(e.line[3].trim());
                moveMult = Float.parseFloat(e.line[4].trim());
                isMeleeMult = Float.parseFloat(e.line[5].trim());
                rangeMult = Float.parseFloat(e.line[6].trim());
                attackMult = Float.parseFloat(e.line[7].trim());
                damageMult = Float.parseFloat(e.line[8].trim());
                skillMult = Float.parseFloat(e.line[9].trim());
                avgDmgMult = Float.parseFloat(e.line[10].trim());
                
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
