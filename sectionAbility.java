import java.io.IOException;

public class sectionAbility implements fileSection {
    ability a;
    String label = "STARTABILITY";
    String key = "hp_save_eHP_move_isMelee_range_attack_damage_skill_avgDmg";
    public String identifier(fileEditor e){
        return "ABILITIES";
    }

    public void write(dataStructure d){
        if (d.getClass() == a.getClass()){
            dataStructure temp = d.getData().get(0);
            temp = new model();
        }
        else {
            System.out.println("WRONG DATA TYPE");
        }
        //Does nothing
    }
    public String getLabel(){
        return label;
    }
    public void write(fileEditor e) throws IOException{
        //UNFINISHED
    }
    public dataStructure read(fileEditor e){
        return new ability("UNFINSIHED");
    }
}
