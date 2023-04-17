public class Outcome {

    // Move transition class from the tutorial

    private final Board board;
    private final Move move;
    private final Status status;

    public Outcome(Board board, Move move, Status status) {
        this.board = board;
        this.move = move;
        this.status = status;
    }



    public Status getStatus() {
        return this.status;
    }

    public Board getBoard() {
        return this.board;
    }



}
