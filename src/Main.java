import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            processFile(selectedFile);
        } else {
            System.out.println("File selection canceled.");
        }
    }

    private static void processFile(File file) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (FileReader fr = new FileReader(file)) {
            int character;
            boolean inWord = false;

            while ((character = fr.read()) != -1) {
                if (character == '\n') {
                    lineCount++;
                }
                else
                {
                    charCount++;
                }

                if (Character.isWhitespace(character)) {
                    inWord = false;
                } else if (!inWord) {
                    wordCount++;
                    inWord = true;
                }
            }

            // Add 1 to lineCount as the last line might not end with a newline character
            lineCount++;

            if (lineCount > 1) {
                charCount -= (lineCount - 1);
            }


            System.out.println("File Summary:");
            System.out.println("File Name: " + file.getName());
            System.out.println("Number of Lines: " + lineCount);
            System.out.println("Number of Words: " + wordCount);
            System.out.println("Number of Characters: " + charCount);

        } catch (IOException e) {
            System.err.println("Error while processing the file: " + e.getMessage());
        }
    }
}
