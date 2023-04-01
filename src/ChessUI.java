import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ChessUI extends Application {

    private static final int RECTANGLE_WIDTH = 100;
    private static final int RECTANGLE_HEIGHT = 100;
    public static String BOARD_STRING;

    public static void main(String[] args) {

        Board board = Board.initialiseBoard(); // Initialise board is being set here

        launch(args); // What purpose does args serve here?

        BOARD_STRING = board.toString();



    }
    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setHgap(0.0);
        root.setVgap(0.0);

        // Need to check the string representation of the chessboard

        // Split the board string into rows
        String[] rows = BOARD_STRING.split("\n");

        for (int row = 0; row < BoardUtility.NUM_ROWS; row++) {

            for (int col = 0; col < BoardUtility.NUM_COLS; col++) {
                Rectangle square = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);

                // Set the color of the square based on the piece in the board string
                char piece = rows[row].charAt(col);
                String pngFile;
                if (piece == '-') {
                    square.setFill((row + col) % 2 == 0 ? Color.SADDLEBROWN : Color.TAN);
                } else if (Character.isUpperCase(piece)) {
                    square.setFill((row + col) % 2 == 0 ? Color.SADDLEBROWN : Color.TAN);
                    pngFile = getWhitePiece(String.valueOf(piece));
                    Image icon = new Image(pngFile);
                    ImageView iconView = new ImageView(icon);
                    iconView.setFitWidth(RECTANGLE_WIDTH / 2);
                    iconView.setFitHeight(RECTANGLE_HEIGHT / 2);
                } else {
                    square.setFill((row + col) % 2 == 0 ? Color.SADDLEBROWN : Color.TAN);
                    pngFile = getBlackPiece(String.valueOf(piece));
                    Image icon = new Image(pngFile);
                    ImageView iconView = new ImageView(icon);
                    iconView.setFitWidth(RECTANGLE_WIDTH / 2);
                    iconView.setFitHeight(RECTANGLE_HEIGHT / 2);
                }

                root.add(square, col, row);
            }
        }

        Scene scene = new Scene(root, RECTANGLE_WIDTH * BoardUtility.NUM_COLS, RECTANGLE_HEIGHT * BoardUtility.NUM_ROWS);

        stage.setScene(scene);
        stage.setTitle("Chess");
        stage.show();
    }

    public String getWhitePiece(String piece) { // Throw error here
        return switch (piece) {
            case ("P") -> "wP.png";
            case ("R") -> "wR.png";
            case ("H") -> "wH.png";
            case ("B") -> "wB.png";
            case ("Q") -> "wQ.png";
            case ("K") -> "wK.png";
            default -> null;
        };
    }

    public String getBlackPiece(String piece) {
        return switch (piece) {
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
