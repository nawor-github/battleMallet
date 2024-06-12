import java.io.*;
import java.util.ArrayList;

public class fileReader {
    public static faction read(String path) throws IOException{
        faction F = new faction();
        FileReader in = new FileReader(path);
        BufferedReader r = new BufferedReader(in);
        String line = r.readLine();
        System.out.println(line);
        String[] split = line.split("|");
        String version = split[2].trim();
        line = r.readLine();
        System.out.println(line);
        split = line.split(" ");
        int entries = Integer.valueOf(split[0].trim());
        line = r.readLine();
        System.out.println(line);
        for (int i = 0; i < entries; i++){
            String name = "NULLATOR";
            int HP = 0;
            String save = "d10";
            int move = 0;
            ArrayList<weapon> weapons = new ArrayList<weapon>();
            ArrayList<ability> abilities = new ArrayList<ability>();
            int lineNum = 0;
            while (line.equals("END") != true){
                if (lineNum == 0){ //Name line "Goblin Archer"
                    name = line;
                }
                else if (lineNum == 1){ //HP line "1 HP"
                    split = line.split(" "); 
                    HP = Integer.valueOf(split[0].trim());
                }
                else if (lineNum == 2){ //Save line "d10 save"
                    split = line.split(" "); 
                    save = split[0].trim();
                }
                else if (lineNum == 3){ //Move line "6 move"
                    split = line.split(" "); 
                    move = Integer.parseInt(split[0].trim());
                }
                else if (lineNum == 4){ //Abilities "Abilties:, ABILITY 1, ABILITY 2"
                    split = line.split(","); 
                    for (int j = 1; j < split.length; j++){
                        abilities.add(new ability(split[j].trim()));
                    }
                }
                else{ //WEAPON LINES: "Longbow, 12, range, 2, atk, 1, dmg, d8, skill"
                      //               0        1   2      3  4    5  6    7   8
                    split = line.split(",");
                    String tName = split[0].trim();
                    int tRange = Integer.parseInt(split[1].trim());
                    int tAtks = Integer.parseInt(split[3].trim());
                    int tDmg = Integer.parseInt(split[5].trim());
                    String tDice = split[7].trim();
                    weapon temp = new weapon(tName, tRange, tAtks, tDmg, tDice);
                    weapons.add(temp);
                }
                
                line = r.readLine();
                System.out.println(line);
                lineNum++;
            }
            F.add(new model(name, HP, save, move, weapons, abilities));
        }
        in.close();
        return F;
    }
    
}
