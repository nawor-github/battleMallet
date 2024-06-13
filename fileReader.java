import java.io.*;
import java.util.ArrayList;

public class fileReader {
    public static faction read(String path) throws IOException{
        
        FileReader in = new FileReader(path);
        BufferedReader r = new BufferedReader(in);

        
        String[] line = read(r);
        String version = line[2].trim(); //Extracts header and title
        line = read(r);
        faction F = new faction(version);
        model temp = new model();
        while (line[0].trim().equals("ENDFILE") == false){
            if (line[0].trim().equals("FACTION")){
                F.name = line[1].trim();
            }
            if (line[0].trim().equals("STARTMODEL")){
                temp = new model();
                temp.name = line[1].trim();
            }
            if (line[0].trim().equals("STATLINE")){
                //STATLINE HP_save_move_type 
                //0        1  2    3    4  
                temp.hp = Integer.parseInt(line[1].trim());
                temp.save = line[2].trim();
                temp.move = Integer.parseInt(line[3].trim());
                temp.type = line[4].trim(); //Should add some validation to this (unit, elites, hero, terrain, mounted, vehicle, aircraft, warmachine)
            }
            if (line[0].trim().equals("POINTCOST")){
                //DO nothing and die :D
            }
            if (line[0].trim().equals("ABILITIES")){
                for (int i = 1; i < line.length; i++){
                    temp.abilities.add(new ability(line[i])); //Add all abilities (points calculated later)
                }
            }
            if (line[0].trim().equals("TAGS")){
                for (int i = 1; i < line.length; i++){
                    temp.tags.add(line[i]); //Add all tags
                }
            }
            //RANGED_OR_MELEE name_range_attack_damage_skill_pointcost_weaponGroup
            //0               1    2     3      4      5     6         7
            if (line[0].trim().equals("RANGE") || line[0].trim().equals("MELEE")){
                weapon tempW = new weapon();
                if (line[0].trim().equals("RANGE")){
                    tempW.isRanged = true;
                }
                else{
                    tempW.isRanged = false;
                }
                tempW.name = line[1].trim();
                tempW.range = Integer.parseInt(line[2].trim());
                tempW.attacks = Integer.parseInt(line[3].trim());
                tempW.damage = Integer.parseInt(line[4].trim());
                tempW.skillDice = line[5].trim();
                //Pointcost calculated later, seperately
                tempW.weaponGroup = Integer.parseInt(line[7].trim());
                if (tempW.weaponGroup>temp.groupNum){
                    temp.groupNum = tempW.weaponGroup; //Sets the groupNum to the highest group number
                }
                tempW.calcAverageDamage();
                line = read(r); //Pull up weapon Tags
                for (int i = 1; i < line.length; i++){
                    tempW.tags.add(line[i]); //Add all tags
                }
                temp.weapons.add(tempW); //Commit finished weapon to temp model
            }
            if (line[0].trim().equals("ENDMODEL")){
                F.add(temp); //Commit model to faction
            }
            line = read(r); //Next line
        }
        in.close();
        return F;
    }

    private static String[] read(BufferedReader reader){
        try{
            String line = reader.readLine();
            if (line != null){
                System.out.println("READ: " + line);
                String[] split = line.split(" ");
                return split;
            }
            else{
                System.out.println("READ: NULL");
                return new String[0];
            }
            
        }
        catch(IOException e){
            e.getStackTrace();
            return new String[0];
        }
        
    }
    
}
