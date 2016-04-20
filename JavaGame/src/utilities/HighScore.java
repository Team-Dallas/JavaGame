package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class HighScore {
    private static final String FILE_NAME = "/text_files/highscores.txt";

    public static void writeHighscore(int highscore){
        try {

            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(highscore);
            bufferedWriter.newLine();

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + FILE_NAME + "'");
        }
    }

    public static List<String> readHighscore(){
        String line;
        List<String> highscores = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(FILE_NAME);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();

            while(line != null) {
                line = bufferedReader.readLine();
                highscores.add(line);
            }

            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            FILE_NAME + "'");
        } catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + FILE_NAME + "'");
        }

        return highscores;
    }
}
