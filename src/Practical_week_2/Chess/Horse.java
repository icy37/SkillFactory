package Practical_week_2.Chess;

public class Horse extends  ChessPiece{

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за границы доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Получаем фигуру, стоящую на позиции, на которую хотим сходить
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

        // Проверяем, что фигура на позиции, на которую хотим сходить, противоположного цвета
        if (targetPiece != null && !targetPiece.getColor().equals(color)) {
            // Съедание фигуры
            return true;
        }

        // Проверяем возможные варианты хода Horse
        int diffLine = Math.abs(toLine - line);
        int diffColumn = Math.abs(toColumn - column);

        if ((diffLine == 2 && diffColumn == 1) || (diffLine == 1 && diffColumn == 2)) {
            // Ход возможен
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
