import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();
        long INF = 1_000_000_000_000_000L;

        Map<String, Integer> stringToId = new HashMap<>();
        int idCounter = 0;
        for (String s : original) {
            if (!stringToId.containsKey(s)) {
                stringToId.put(s, idCounter++);
            }
        }
        for (String s : changed) {
            if (!stringToId.containsKey(s)) {
                stringToId.put(s, idCounter++);
            }
        }

        long[][] dist = new long[idCounter][idCounter];
        for (int i = 0; i < idCounter; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            int u = stringToId.get(original[i]);
            int v = stringToId.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        for (int k = 0; k < idCounter; k++) {
            for (int i = 0; i < idCounter; i++) {
                for (int j = 0; j < idCounter; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;

            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }

            for (String str : stringToId.keySet()) {
                int len = str.length();
                if (i + len <= n) {
                    if (source.substring(i, i + len).equals(str)) {
                        String tSub = target.substring(i, i + len);
                        if (stringToId.containsKey(tSub)) {
                            int u = stringToId.get(str);
                            int v = stringToId.get(tSub);
                            if (dist[u][v] < INF) {
                                dp[i + len] = Math.min(dp[i + len], dp[i] + dist[u][v]);
                            }
                        }
                    }
                }
            }
        }

        return dp[n] == INF ? -1 : dp[n];
    }
}