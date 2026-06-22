import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int INF = 1_000_000_000;

        int[][][] dp = new int[k + 1][m][n];
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[t][i], INF);
            }
        }

        dp[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i - 1][j] + grid[i][j]);
                if (j > 0) dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i][j - 1] + grid[i][j]);
            }
        }

        TreeMap<Integer, List<int[]>> groupedCells = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                groupedCells.computeIfAbsent(grid[i][j], x -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        for (int t = 1; t <= k; t++) {
            int currentMinCost = INF;
            for (int val : groupedCells.descendingKeySet()) {
                List<int[]> cells = groupedCells.get(val);
                
                for (int[] cell : cells) {
                    currentMinCost = Math.min(currentMinCost, dp[t - 1][cell[0]][cell[1]]);
                }
                
                for (int[] cell : cells) {
                    dp[t][cell[0]][cell[1]] = Math.min(dp[t][cell[0]][cell[1]], currentMinCost);
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i - 1][j] + grid[i][j]);
                    if (j > 0) dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i][j - 1] + grid[i][j]);
                }
            }
        }

        int minCost = INF;
        for (int t = 0; t <= k; t++) {
            minCost = Math.min(minCost, dp[t][m - 1][n - 1]);
        }

        return minCost;
    }
}