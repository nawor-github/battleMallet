import java.io.IOException;

public class sectionModel implements fileSection {
    model m;
    String label = "STARTMODEL";

    public String getLabel(){
        return label;
    }
    public String identifier(fileEditor e){
        if (m.name != null){
            return m.name;
        }
        return "";        
    }
    public void write(fileEditor e) throws IOException{
        m.calcPoint();
        String words = m.printOutString();
        e.w.write(words);
        if (e.verbose){
            System.out.println("WROTE:" + words);
        }     
    }
    public dataStructure read(fileEditor e){
        if (e.lineTitled(label)){
            model temp = new model();
            temp.name = e.line[1].trim();
            while (!e.lineTitled("ENDMODEL")){
                if (e.lineTitled("STATLINE")){
                    //STATLINE HP_save_move_type 
                    //0        1  2    3    4  
                    temp.hp = Integer.parseInt(e.line[1].trim());
                    temp.save = e.line[2].trim();
                    temp.move = Integer.parseInt(e.line[3].trim());
                    temp.type = e.line[4].trim(); //Should add some validation to this (unit, elites, hero, terrain, mounted, vehicle, aircraft, warmachine)
                }
                if (e.lineTitled("POINTCOST")){
                    //DO nothing and die :D
                }
                if (e.lineTitled("ABILITIES")){
                    for (int i = 1; i < e.line.length; i++){
                        temp.abilities.add(new ability(e.line[i])); //Add all abilities (points calculated later)
                    }
                }
                if (e.lineTitled("TAGS")){
                    for (int i = 1; i < e.line.length; i++){
                        temp.tags.add(e.line[i]); //Add all tags
                    }
                }
                if (e.lineTitled("RANGE") || e.lineTitled("MELEE")){
                    weapon tempW = new weapon();
                    if (e.lineTitled("RANGE")){
                        tempW.isRanged = true;
                    }
                    else{
                        tempW.isRanged = false;
                    }
                    tempW.name = e.line[1].trim();
                    tempW.range = Integer.parseInt(e.line[2].trim());
                    tempW.attacks = Integer.parseInt(e.line[3].trim());
                    tempW.damage = Integer.parseInt(e.line[4].trim());
                    tempW.skillDice = e.line[5].trim();
                    //Pointcost calculated later, seperately
                    tempW.weaponGroup = Integer.parseInt(e.line[7].trim());
                    if (tempW.weaponGroup>temp.groupNum){
                        temp.groupNum = tempW.weaponGroup; //Sets the groupNum to the highest group number
                    }
                    tempW.calcAverageDamage();
                    e.getNextLine(); //Pull up weapon Tags
                    for (int i = 1; i < e.line.length; i++){
                        tempW.tags.add(e.line[i]); //Add all tags
                    }
                    temp.weapons.add(tempW); //Commit finished weapon to temp model
                }
                e.getNextLine();
            }
            return m;
        }
        System.out.println("CAN'T READ MODEL DATA, NOT RIGHT LINE (BAD FORMAT)");
        return null;
        
    }
}
