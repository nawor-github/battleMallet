import java.io.IOException;

public class sectionAbility implements fileSection {
    ability a;
    String label = "STARTABILITY";
    String key = "hp_save_eHP_move_isMelee_range_attack_damage_skill_avgDmg";
    

    public String getLabel(){
        return label;
    }
    public void write(fileEditor e) throws IOException{
        e.w.write(String.format("%s %s %s", label, author, version));
    }
    public String[] read(fileEditor e){
        return new String[]{label, author, version};
    }
}
