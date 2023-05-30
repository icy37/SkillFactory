package Practical_week_2.Chess;

public class Rook extends  ChessPiece{
    public Rook(String color) {
        super(color);
    }


    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если точка назначения находится за пределами доски
        }

        if (line != toLine && column != toColumn) {
            return false; // Rook может двигаться только по вертикали или горизонтали
        }

        int lineStep = (toLine - line) != 0 ? (toLine - line) / Math.abs(toLine - line) : 0;
        int columnStep = (toColumn - column) != 0 ? (toColumn - column) / Math.abs(toColumn - column) : 0;

        // Проверка, что между начальной и конечной клеткой нет других фигур
        for (int i = line + lineStep, j = column + columnStep; i != toLine || j != toColumn; i += lineStep, j += columnStep) {
            ChessPiece piece = chessBoard.board[i][j];
            if (piece != null) {
                return false; // Нельзя перемещаться через другие фигуры
            }
        }

        // Проверка, что на точке назначения нет фигуры того же цвета
        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];
        if (destinationPiece != null && destinationPiece.getColor().equals(color)) {
            return false; // Нельзя перемещаться на клетку с фигурой того же цвета
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
