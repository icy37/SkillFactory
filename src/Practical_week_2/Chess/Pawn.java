package Practical_week_2.Chess;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
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
        if (column == toColumn) {
            // Проверяем перемещение по вертикали
            if (color.equals("White")) {
                if (line == 1 && toLine == 3 && targetPiece == null && chessBoard.board[2][column] == null) {
                    // Допустимое перемещение на 2 клетки вперед из начальной позиции белой пешки
                    return true;
                }
                if (line + 1 == toLine && targetPiece == null) {
                    // Перемещение на 1 клетку вперед для белой пешки
                    return true;
                }
            }
            if (color.equals("Black")) {
                if (line == 6 && toLine == 4 && targetPiece == null && chessBoard.board[5][column] == null) {
                    // Допустимое перемещение на 2 клетки вперед из начальной позиции черной пешки
                    return true;
                }
                if (line - 1 == toLine && targetPiece == null) {
                    // Перемещение на 1 клетку вперед для черной пешки
                    return true;
                }
            }
        } else {
            // Проверяем перемещение по диагонали для съедания фигуры
            if (Math.abs(column - toColumn) == 1) {
                if (color.equals("White") && line + 1 == toLine && targetPiece != null && !targetPiece.getColor().equals(color)) {
                    return true;
                }
                if (color.equals("Black") && line - 1 == toLine && targetPiece != null && !targetPiece.getColor().equals(color)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
