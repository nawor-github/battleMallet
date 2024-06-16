import java.io.IOException;

interface fileSection {
    public String getLabel();
    public void write(dataStructure d);
    public void write(fileEditor e) throws IOException;
    public dataStructure read(fileEditor e) throws IOException;
    public String identifier(fileEditor e);
}
