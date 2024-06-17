import java.util.ArrayList;
import java.io.*;

public class ability extends dataStructure {
    float cost;
    String label = "ABILITIES";

    ability (String n){
        name = n;
    }
    ability (ability a){
        name = a.name;
        cost = a.cost;
    }

    public ability(fileEditor e) {
        String words = "Temp copy from model nonfunctional";
        try {
            e.w.write(words);
        if (e.verbose){
            System.out.println("WROTE:" + words);
        }  
        }
        catch (IOException ex) {
            //Do nothing lol fix this later
        }  
    }
}
