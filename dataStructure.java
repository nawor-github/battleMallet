import java.util.ArrayList;

public class dataStructure{
    String name;
    ArrayList<String> tags; //Tags in weapon/model, conflicts in ability
    String label = "GENERIC";
    public dataStructure(){
        name = "Generic";
    }

    public String label(){
        return label;
    }

    public dataStructure(String[] d){
        for (int i = 0; i < d.length; i++){
            tags.add(d[i]);
        }
        name = "Generic";
    }

    public ArrayList<? extends dataStructure> getData(){
        ArrayList<dataStructure> result = new ArrayList<>();
        result.add(this);
        return result;
    }
}
