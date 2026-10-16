package dsa;

import java.util.Arrays;

public class SuffixArray {
    /** Indices of suffixes in sorted order. */
    public static Integer[] build(String s) {
        Integer[] sa = new Integer[s.length()];
        for (int i = 0; i < sa.length; i++) sa[i] = i;
        Arrays.sort(sa, (x, y) -> s.substring(x).compareTo(s.substring(y)));
        return sa;
    }

    /** Kasai's algorithm. lcp[i] = common prefix of suffixes sa[i-1] and sa[i]. O(n). */
    public static int[] lcp(String s, Integer[] sa) {
        int n = s.length();
        int[] rank = new int[n], lcp = new int[n];
        for (int i = 0; i < n; i++) rank[sa[i]] = i;
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) h++;
                lcp[rank[i]] = h;
                if (h > 0) h--;
            } else {
                h = 0;
            }
        }
        return lcp;
    }

    /** Longest text shared by a and b, found with a suffix array of a + sep + b. */
    public static String longestCommonSubstring(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        String s = a + "\u0001" + b;
        Integer[] sa = build(s);
        int[] lcp = lcp(s, sa);
        int best = 0, at = 0;
        for (int i = 1; i < sa.length; i++) {
            boolean x = sa[i] < a.length(), y = sa[i - 1] < a.length();
            if (x != y && lcp[i] > best) {
                best = lcp[i];
                at = sa[i];
            }
        }
        return s.substring(at, at + best);
    }

    /** Shared text as a percentage of the shorter document. */
    public static int similarity(String a, String b) {
        int len = longestCommonSubstring(a, b).length();
        return (int) Math.round(100.0 * len / Math.min(a.length(), b.length()));
    }
}
