import java.io.IOException;
import java.util.ArrayList;

public class faction extends dataStructure{
    ArrayList<model> models;
    String version;
    String label = "FACTION";
    faction (String v){
        models = new ArrayList<model>();
        version = v;
    }

    public faction readFaction(fileEditor e){
        if (e.lineTitled(label)){
            name = e.line[1].trim();
            while (!e.lineTitled("ENDFILE")){
                model temp = new model(e);
                models.add(temp);
            }
        }
        return this;
    }

    public String printTitleString(){
        String result = String.format("%s %s\n", label, name);
        return result;
    }

    public void writeFaction(fileEditor e){
        try{
            String words = printTitleString();
            e.w.write(words);
            for (int i = 0; i < models.size(); i++){
                models.get(i).writeModel(e);
            }
            if (e.verbose){
                System.out.println("WROTE:" + words);
            }  
        } catch (IOException ex) {
            //Write error message
        }
        
    }

    public void add(model m){
        models.add(m);
    }
}
