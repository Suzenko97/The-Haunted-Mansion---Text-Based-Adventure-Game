import java.util.ArrayList;

// [Omar] Gather Puzzle Data
public class Puzzle {
    private int PuzzleID;
    private String PuzzleQues;
    private String PuzzleAns;
    private int Attempts;
    private boolean check = false;
    private double PuzzleLocate;
    private final String checked = "I've seen this puzzle before";
    static ArrayList<Puzzle> puzzleList = new ArrayList<>();
    public Puzzle(int PuzzleID, String PuzzleQues, String PuzzleAns, int Attempts, double PuzzleLocate) {
        this.PuzzleID = PuzzleID;
        this.PuzzleQues = PuzzleQues;
        this.PuzzleAns = PuzzleAns;
        this.Attempts = Attempts;
        this.PuzzleLocate = PuzzleLocate;
    }

    // [Omar] Getters and Setters
    public int getPuzzleID() {
        return PuzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        PuzzleID = puzzleID;
    }

    public String getPuzzleQues() {
        return PuzzleQues;
    }

    public void setPuzzleQues(String puzzleQues) {
        PuzzleQues = puzzleQues;
    }

    public String getPuzzleAns() {
        return PuzzleAns;
    }

    public void setPuzzleAns(String puzzleAns) {
        PuzzleAns = puzzleAns;
    }

    public int getAttempts() {
        return Attempts;
    }

    public void setAttempts(int attempts) {
        Attempts = attempts;
    }

    public double getPuzzleLocate() {
        return PuzzleLocate;
    }

    public void setPuzzleLocate(double puzzleLocate) {
        PuzzleLocate = puzzleLocate;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
