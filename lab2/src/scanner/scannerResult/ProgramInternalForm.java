package scanner.scannerResult;

import scanner.domain.PIFPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class ProgramInternalForm {
    List<PIFPair> data;

    public ProgramInternalForm() {
        data = new ArrayList<>();
    }

    public void insert(PIFPair pair){
        data.add(pair);
    }
}
