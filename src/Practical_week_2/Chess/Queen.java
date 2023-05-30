package Practical_week_2.Chess;

public class Queen extends  ChessPiece{

    public Queen(String color) {
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

        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        // Проверка на перемещение по диагонали
        if (lineDiff == columnDiff) {
            int stepLine = (toLine - line) / lineDiff;
            int stepColumn = (toColumn - column) / columnDiff;

            int currentLine = line + stepLine;
            int currentColumn = column + stepColumn;

            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false; // Фигура не может перепрыгивать через другие фигуры
                }
                currentLine += stepLine;
                currentColumn += stepColumn;
            }
            return true;
        }

        // Проверка на перемещение по прямой линии
        if (lineDiff == 0 || columnDiff == 0) {
            int stepLine = line == toLine ? 0 : (toLine - line) / lineDiff;
            int stepColumn = column == toColumn ? 0 : (toColumn - column) / columnDiff;

            int currentLine = line + stepLine;
            int currentColumn = column + stepColumn;

            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false; // Фигура не может перепрыгивать через другие фигуры
                }
                currentLine += stepLine;
                currentColumn += stepColumn;
            }
            return true;
        }

        return false; // Фигура не может двигаться по заданным координатам
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
