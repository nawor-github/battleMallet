import java.io.*;
import java.util.ArrayList;

public class fileWriter {
    public static void main(String[] args) {
        String path = "Files\\writeTest2.txt";
        File file = makeFile(path);
        try {
            FileWriter w = new FileWriter(path);
            String words = "BATTLEMALLET_FILE ROWAN_CLARKE v0.1\n";
            w.write("WROTE:" + words);

            faction TestFaction = fileReader.read("Files\\test2.txt");
            for (int i = 0; i < TestFaction.models.size(); i++){
                words = TestFaction.models.get(i).printOutString();
                w.write(words);
                System.out.println("WROTE:" + words);
            }
            words = "ENDFILE";
            w.write(words);
            System.out.println("WROTE:" + words);
            w.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    public static File makeFile(String path){
        try {
            File file = new File(path);
            if (file.createNewFile()) {
              System.out.println("File created: " + file.getName());
            } 
            else {
              System.out.println("File already exists.");
            } 
            return file;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}