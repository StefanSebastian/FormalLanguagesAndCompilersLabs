package scanner.utils;

import scanner.domain.PIFPair;
import scanner.scannerResult.ProgramInternalForm;

import java.io.*;

/**
 * Created by Sebi on 05-Dec-17.
 */
public class FileWriter {
    public void saveSequence(String path, ProgramInternalForm pif){
        StringBuilder sequence = new StringBuilder();
        String prefix = "";
        for (PIFPair pair : pif.getData()){
            if (!pair.getToken().equals(" ")) {
                sequence.append(prefix);
                sequence.append(pair.getAtomCode());
                prefix = ",";
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))){
            writer.write(sequence.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
