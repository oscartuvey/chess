
public class BoardUtility {


    public static final int NUM_ROWS = 8;
    public static final int NUM_COLS = 8;
    public static final int NUM_TILES = 64;
    public static final boolean[] FIRST_COLUMN = initColumn(0);

    public static final boolean[] SECOND_COLUMN = initColumn(1);

    public static final boolean[] SEVENTH_COLUMN = initColumn(6);

    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] FIRST_ROW = initRow(0);

    public static final boolean[] SECOND_ROW = initRow(8);

    public static final boolean[] SEVENTH_ROW = initRow(48);

    public static final boolean[] EIGHTH_ROW = initRow(56);

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[64];

        do {
            column[columnNumber] = true;
            columnNumber += 8;
        } while (columnNumber < 64);

        return column;
    }

    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[64];

        do {
            row[rowNumber] = true;
            rowNumber++;
        }
        while ((rowNumber % 8) != 0);

        return row;
    }

}
