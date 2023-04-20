public class InvalidMove extends Move{
    public InvalidMove() {
        super(null, -1);
    }

    @Override
    public Board execute() {
        throw new InvalidMoveException("Invalid move.");
    }

    @Override
    public int getPosition() {
        return -1;
    }

}
