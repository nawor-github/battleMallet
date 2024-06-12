import java.io.*;
import java.util.ArrayList;

public class fileWriter {
    public static void main(String[] args) {
        String path = "C:\\Users\\nawor\\OneDrive\\Documents\\gitHub\\battleMallet\\Files\\writeTest.txt";
        File file = makeFile(path);
        try {
            FileWriter w = new FileWriter(path);
            w.write("BATTLEMALLET FILE | ROWAN CLARKE | v0.0\n");

            faction TestFaction = fileReader.read("C:\\Users\\nawor\\OneDrive\\Documents\\gitHub\\battleMallet\\Files\\test.txt");
            String text = TestFaction.models.size() + " ENTRIES\n";
            w.write(text);
            for (int i = 0; i < TestFaction.models.size(); i++){
                w.write(TestFaction.models.get(i).printOutString());
            }
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