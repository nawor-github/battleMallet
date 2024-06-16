import java.io.IOException;

public class sectionHeader implements fileSection {
    String version = "vSUPERALPHA";
    String author = "Rowan_Clarke";
    String label = "BATTLEMALLET_FILE";

    public String getLabel(){
        return label;
    }
    public void write(fileEditor e) throws IOException{
        e.w.write(String.format("%s %s %s", label, author, version));
    }
}
