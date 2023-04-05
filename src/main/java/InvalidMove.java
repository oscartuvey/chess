public class InvalidMove extends Move{
    public InvalidMove() {
        super(null, null, -1);
    }

    @Override
    public Board execute() {
        throw new RuntimeException("Invalid move.");
    }
}
