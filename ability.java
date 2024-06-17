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
}
