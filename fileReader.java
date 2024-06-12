import java.io.*;
import java.util.ArrayList;

public class fileReader {
    public static void main() throws IOException{
        FileReader in = new FileReader("C:\\Users\\nawor\\OneDrive\\Documents\\gitHub\\battleMallet\\Files\\text.txt");
        BufferedReader r = new BufferedReader(in);
        ArrayList<model> models = new ArrayList<model>();
        String line = r.readLine();
        String[] split = line.split("|");
        String version = split[2].trim();
        line = r.readLine();
        split = line.split(" ");
        String date = split[1].trim();
        line = r.readLine();
        split = line.split(" ");
        int entries = Integer.valueOf(split[0].trim());
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
                      //               0        1   2      3       4  5    6   7
                    split = line.split(",");
                    weapon temp = new weapon(split[0].trim(), Integer.parseInt(split[1]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[6]);
                    weapons.add(temp);
                }
                
                line = r.readLine();
                lineNum++;
            }
            models.add(new model(name, HP, save, move, weapons, abilities));
        }
        in.close();
    }
    
}
