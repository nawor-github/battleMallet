import java.util.ArrayList;

public class faction extends dataStructure{
    ArrayList<model> models;
    String version;
    String label = "FACTION";
    faction (String v){
        models = new ArrayList<model>();
        version = v;
    }

    public void add(model m){
        models.add(m);
    }
}
