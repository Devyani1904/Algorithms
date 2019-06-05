package edu.gwu.csci6212;

public class CoinPickingGame {
    private MemoizationTableCell[][] memoizationTable;
    private int[] coins;

    private class MemoizationTableCell {
        public int playerOneScore;
        public int playerTwoScore;

        MemoizationTableCell(int playerOneScore, int playerTwoScore) {
            this.playerOneScore = playerOneScore;
            this.playerTwoScore = playerTwoScore;
        }
    }

    public void setup(int coins[]) {
        if (hasOddCount(coins) || isEmpty(coins))
            throw new IllegalCapacity();
        this.coins = coins;
        this.memoizationTable = new MemoizationTableCell[coins.length][coins.length];
    }

    private boolean hasOddCount(int[] coins) {
        return coins.length % 2 == 1;
    }

    private boolean isEmpty(int[] coins) {
        return coins.length == 0;
    }

    private void calculateMemoizationTable() {
        calculateMainDiagonal();
        calculateCells();
    }

    private void calculateMainDiagonal() {
        for (int coinIndex = 0; coinIndex < this.coins.length; coinIndex++) {
            this.memoizationTable[coinIndex][coinIndex] = new MemoizationTableCell(coins[coinIndex], 0);
        }
    }

    private void calculateCells() {
        for (int startBounds = 1; startBounds < this.coins.length; startBounds++) {
            for (int x = startBounds; x < this.coins.length; x++) {
                int y = x - startBounds;
                calculateCell(x, y);
            }
        }
    }

    private void calculateCell(int x, int y) {
        this.memoizationTable[x][y] = calculatePlayerOneScoreFromOneCellDown(x,
                y) >= calculatePlayerOneScoreFromOneCellLeft(x, y)
                        ? new MemoizationTableCell(calculatePlayerOneScoreFromOneCellDown(x, y),
                                calculatePlayerTwoScoreFromOneCellDown(x, y))
                        : new MemoizationTableCell(calculatePlayerOneScoreFromOneCellLeft(x, y),
                                calculatePlayerTwoScoreFromOneCellLeft(x, y));
    }

    private int calculatePlayerOneScoreFromOneCellDown(int x, int y) {
        return this.memoizationTable[x][y + 1].playerTwoScore + coins[y];
    }

    private int calculatePlayerTwoScoreFromOneCellDown(int x, int y) {
        return this.memoizationTable[x][y + 1].playerOneScore;
    }

    private int calculatePlayerOneScoreFromOneCellLeft(int x, int y) {
        return this.memoizationTable[x - 1][y].playerTwoScore + coins[x];
    }

    private int calculatePlayerTwoScoreFromOneCellLeft(int x, int y) {
        return this.memoizationTable[x - 1][y].playerOneScore;
    }

    public int play() {
        this.calculateMemoizationTable();
        return this.calculateTotalValue();
    }

    private int calculateTotalValue() {
        return this.memoizationTable[this.coins.length - 1][0].playerOneScore;
    }

    public static class IllegalCapacity extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

}
