package scanner.domain;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class PIFPair {
    private Integer atomCode;
    private Integer symbolTablePosition;
    private String token;

    public PIFPair(Integer atomCode, Integer symbolTablePosition) {
        this.atomCode = atomCode;
        this.symbolTablePosition = symbolTablePosition;
    }

    public Integer getAtomCode() {
        return atomCode;
    }

    public void setAtomCode(Integer atomCode) {
        this.atomCode = atomCode;
    }

    public Integer getSymbolTablePosition() {
        return symbolTablePosition;
    }

    public void setSymbolTablePosition(Integer symbolTablePosition) {
        this.symbolTablePosition = symbolTablePosition;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token == null ?
                atomCode + " " + symbolTablePosition :
                atomCode + " " + symbolTablePosition + " " + token;
    }
}
