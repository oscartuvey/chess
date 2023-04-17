import java.io.InputStream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.StackPane;

public class ChessUI extends Application {

    private static final int RECTANGLE_WIDTH = 90;
    private static final int RECTANGLE_HEIGHT = 90;
    private Board board;
    private List<SquareNode> squares;

    @Override
    public void start(Stage stage) {

        this.squares = new ArrayList<>();
        this.board = Board.initialiseBoard();

        GridPane root = new GridPane();
        root.setHgap(0.0);
        root.setVgap(0.0);

        for (int i = 0; i < 64; i++) {
            SquareNode square = new SquareNode(i); // add to list too

            // The toString method which converts to lowercase is for the SQUARE ITSELF AND NOT THE PIECE ITSELF

            root.add(square, i % BoardUtility.NUM_COLS, i / BoardUtility.NUM_ROWS);
        }

        Scene scene = new Scene(root, RECTANGLE_WIDTH * BoardUtility.NUM_COLS, RECTANGLE_HEIGHT * BoardUtility.NUM_ROWS);

        stage.setScene(scene);
        stage.setTitle("Chess");
        stage.show();
    }

    private class SquareNode extends StackPane {

        private final int position;

        SquareNode(int position) {
            // Remember to clear previous image off
            super();
            this.position = position;
            Rectangle square = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
            square.setFill((this.position % BoardUtility.NUM_COLS + this.position / BoardUtility.NUM_ROWS) % 2 == 0 ? Color.TAN : Color.SADDLEBROWN);
            this.getChildren().add(square);
            if (board.getSquare(this.position).isOccupied()){
                String pngFile = renderPiece(board.getSquare(this.position).toString()); // See above for explanation (the to-do)
                // Exception for if filePath is null
                InputStream filePath = this.getClass().getResourceAsStream(pngFile);
                Image image = new Image(filePath, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, false, false);
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                this.getChildren().add(imageView);
            }
        }

        public String renderPiece(String piece) {
            return switch (piece) {
                case ("P") -> "wP.png";
                case ("R") -> "wR.png";
                case ("H") -> "wH.png";
                case ("B") -> "wB.png";
                case ("Q") -> "wQ.png";
                case ("K") -> "wK.png";
                case ("p") -> "bP.png";
                case ("r") -> "bR.png";
                case ("h") -> "bH.png";
                case ("b") -> "bB.png";
                case ("q") -> "bQ.png";
                case ("k") -> "bK.png";
                default -> null;
            };
        }
    }
}



