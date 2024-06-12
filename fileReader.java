import java.io.*;
import java.util.ArrayList;

public class fileReader {
    public static void main(String[] args) throws IOException{
        FileReader in = new FileReader("C:\\Users\\nawor\\OneDrive\\Documents\\gitHub\\battleMallet\\Files\\text.txt");
        BufferedReader r = new BufferedReader(in);

        ArrayList<model> models = new ArrayList<model>();
        String line = r.readLine();
        String[] split = line.split("|");
        String version = split[2].trim();
        line = r.readLine();
        split = line.split(" ");
        String date = split[1].trim();
        line = r.readLine();
        split = line.split(" ");
        int entries = Integer.valueOf(split[0].trim());
        for (int i = 0; i < entries; i++){
            ArrayList<weapon> weapons = new ArrayList<weapon>();
            ArrayList<ability> abilities = new ArrayList<ability>();
            int lineNum = 0;
            while (line.equals("END") != true){
                
                split = line.split(" ");
                line = r.readLine();
                lineNum++;
            }
        }
        in.close();
    }
    
}
