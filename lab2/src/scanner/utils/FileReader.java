package scanner.utils;

import scanner.Scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class FileReader {
    /*
    Returns all lines in a file
     */
    public List<String> getLines(String path){
        List<String> lines = new ArrayList<>();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            stream.forEach(lines::add);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
