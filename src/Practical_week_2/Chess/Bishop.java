package Practical_week_2.Chess;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
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

        // Проверяем, что путь до позиции свободен от других фигур
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        if (lineDiff == columnDiff) {
            // Проверяем диагональное перемещение
            int lineStep = (toLine - line) / lineDiff;
            int columnStep = (toColumn - column) / columnDiff;

            int currLine = line + lineStep;
            int currColumn = column + columnStep;

            while (currLine != toLine || currColumn != toColumn) {
                if (chessBoard.board[currLine][currColumn] != null) {
                    // В пути есть другая фигура
                    return false;
                }
                currLine += lineStep;
                currColumn += columnStep;
            }

            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
