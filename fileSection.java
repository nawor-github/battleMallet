import java.io.IOException;

interface fileSection {
    public String getLabel();
    public void write(fileEditor e) throws IOException;
}
