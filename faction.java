import java.util.ArrayList;

public class faction {
    String name;
    ArrayList<model> models;
    String version;
    faction (String v){
        models = new ArrayList<model>();
        version = v;
    }

    public void add(model m){
        models.add(m);
    }
}
