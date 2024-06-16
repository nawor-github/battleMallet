public class programManager {
    public static void main (String[] args){
        if (args[0] != null){
            fileEditor f = new fileEditor(args[0]);
        }
        else {
            System.out.println("No file path provided (argument 0).");
            return; 
        }
        
    }
    
}
