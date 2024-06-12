import java.util.ArrayList;

public class faction {
    ArrayList<model> models;
    faction (){
        models = new ArrayList<model>();
    }

    public void add(model m){
        models.add(m);
    }
}
