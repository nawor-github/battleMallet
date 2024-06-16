import java.util.ArrayList;

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
