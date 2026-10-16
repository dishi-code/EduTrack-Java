package dsa;

import java.util.List;

public class EditDistance {
    /** Levenshtein distance by dynamic programming. O(a * b). */
    public static int distance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        return dp[a.length()][b.length()];
    }

    /** Closest word in the vocabulary, or null if nothing is within maxDistance. */
    public static String suggest(String word, List<String> vocabulary, int maxDistance) {
        String best = null;
        int bestD = Integer.MAX_VALUE;
        for (String w : vocabulary) {
            int d = distance(word.toLowerCase(), w);
            if (d < bestD) {
                bestD = d;
                best = w;
            }
        }
        return bestD <= maxDistance ? best : null;
    }
}
