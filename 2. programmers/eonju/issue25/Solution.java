class Solution {

    public boolean solution(int[][] key, int[][] lock) {

    }

    public int[][] turnBoard(int[][] board) {
        int[][] turned = new int[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                turned[i][j] = board[board.length - j - 1][i];
            }
        }

        return turned;
    }
}
