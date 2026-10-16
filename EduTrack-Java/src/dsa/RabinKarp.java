package dsa;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    static final int BASE = 256, MOD = 1_000_003;

    /** Rolling-hash search. Average O(n + m). */
    public static List<Integer> search(String text, String pattern) {
        text = text.toLowerCase();
        pattern = pattern.toLowerCase();
        int n = text.length(), m = pattern.length();
        List<Integer> result = new ArrayList<>();
        if (m == 0 || m > n) return result;
        long h = 1, hp = 0, ht = 0;
        for (int i = 0; i < m - 1; i++) h = h * BASE % MOD;
        for (int i = 0; i < m; i++) {
            hp = (hp * BASE + pattern.charAt(i)) % MOD;
            ht = (ht * BASE + text.charAt(i)) % MOD;
        }
        for (int i = 0; i <= n - m; i++) {
            if (hp == ht && text.startsWith(pattern, i)) result.add(i);
            if (i < n - m) {
                ht = ((ht - text.charAt(i) * h) * BASE + text.charAt(i + m)) % MOD;
                if (ht < 0) ht += MOD;
            }
        }
        return result;
    }
}
