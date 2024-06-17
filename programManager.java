public class programManager {
    public static void main (String[] args){ 
        args = new String[]{".\\Files\\test2.txt", "c", ".\\Files\\writeTest4.txt"};
        fileEditor f = new fileEditor(args); // file_path_1 mode(rw/blank = read/write, c = copy) file_path_2 (if copying)
    }
}
