public class Puzzle {
    private int PuzzleID;
    private String PuzzleQues;
    private String PuzzleAns;
    private int Attempts;
    private boolean check = false;
    private final String checked = "I've seen this puzzle before";

    public Puzzle(int PuzzleID, String PuzzleQues, String PuzzleAns, int Attempts) {
        this.PuzzleID = PuzzleID;
        this.PuzzleQues = PuzzleQues;
        this.PuzzleAns = PuzzleAns;
        this.Attempts = Attempts;
    }

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

    public void setCheck(boolean check) {
        this.check = check;
    }

}
