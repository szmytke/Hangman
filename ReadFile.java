import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class ReadFile {

    private Scanner dataFile;

    public void openFile() {
        try {    
            dataFile = new Scanner(new File("countries_and_capitals.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public void readFile() {
        while (dataFile.hasNextLine()) {
            String s = dataFile.nextLine();
            // System.out.println(s);
            String[] splitted = s.split(" \\| ");
            Capital capital = new Capital(splitted[1], splitted[0]);
            Capital.addCapital(capital);
        }
    }

    public void closeFile() {
        dataFile.close();
    }
}

 
