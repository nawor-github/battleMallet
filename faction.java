import java.io.IOException;
import java.util.ArrayList;

public class faction extends dataStructure implements hasVersion{
    ArrayList<model> models;
    String version;
    String label = "FACTION";

    faction (String v){
        models = new ArrayList<model>();
        version = v;
    }      

    public faction readFaction(fileEditor e){
        version = e.version;
        if (e.lineTitled(label)){
            name = e.line[1].trim();
            while (!e.lineTitled("ENDFILE")){
                
                model temp = new model(e); //This is so fucked up but does seem to work
                models.add(temp);
                e.getNextLine();
            }
        }
        return this;
    }

    public String getVersion(){
        return version;
    }

    public String printTitleString(){
        String result = String.format("%s %s\n", label, name);
        return result;
    }

    public void writeFaction(fileEditor e){
        try{
            String words = printTitleString();
            if (e.verbose){
                System.out.println("WROTE:" + words);
            }  
            e.w.write(words);
            for (int i = 0; i < models.size(); i++){
                models.get(i).writeModel(e); //This writes model :)
            }
            
        } catch (IOException ex) {
            //Write error message
        }      
    }

    public void add(model m){
        models.add(m);
    }
}
