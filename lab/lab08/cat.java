import java.util.*;

public class cat{
	public static void cat(File file) throws FileNotFoundException, IOException{
    RandomAccessFile input = null;
    String line = null;

    try {
        input = new RandomAccessFile(file, "r");
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        return;
    }
    catch (FileNotFoundException | IOException e){
    	throw IOException();
    } finally {
        if (input != null) {
            input.close();
        }
    }
}
}