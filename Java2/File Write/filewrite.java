import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteToFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Please enter a sentence: ");
        String sentence = scanner.nextLine();
        
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write(sentence);
            writer.close();
            System.out.println("Sentence written to output.txt successfully.");
        } catch (IOException e) {
            System.out.println( "An error occurred while writing to the file.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}