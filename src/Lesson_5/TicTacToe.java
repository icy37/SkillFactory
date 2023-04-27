package Lesson_5;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        newGame();
    }

    public void newGame() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        currentPlayer = 'X';
    }
    char[][] getField() {
        return board;
    }
    public String getFieldArray() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append("[");
            for (int j = 0; j < 3; j++) {
                sb.append("'");
                sb.append(board[i][j]).append("',");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String makeMove(int x, int y) {
        if (board[x - 1][y - 1] != '-') {
            return String.format("Cell %d, %d is already occupied", x, y);
        }
        board[x - 1][y - 1] = currentPlayer;
        String result = checkGame();
        if (result.equals("Game not finished")) {
            currentPlayer = currentPlayer == 'X' ? '0' : 'X';
            return "Move completed";
        }
        return result;
    }

    public String checkGame() {
        boolean emptyCellExists = false;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return String.format("Player %c won", board[i][0]);
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return String.format("Player %c won", board[0][i]);
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return String.format("Player %c won", board[0][0]);
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return String.format("Player %c won", board[0][2]);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    emptyCellExists = true;
                    break;
                }
            }
            if (emptyCellExists) {
                break;
            }
        }
        return emptyCellExists ? "Game not finished" : "Draw";
    }
}





