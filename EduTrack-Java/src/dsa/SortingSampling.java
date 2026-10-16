package dsa;

import java.util.*;

public class SortingSampling {
    static final Random RNG = new Random();

    /** Descending order. Expected O(n log n). */
    public static List<Integer> randomizedQuicksort(List<Integer> a) {
        if (a.size() < 2) return a;
        int p = a.get(RNG.nextInt(a.size()));
        List<Integer> hi = new ArrayList<>(), eq = new ArrayList<>(), lo = new ArrayList<>();
        for (int x : a) {
            if (x > p) hi.add(x);
            else if (x == p) eq.add(x);
            else lo.add(x);
        }
        List<Integer> out = new ArrayList<>(randomizedQuicksort(hi));
        out.addAll(eq);
        out.addAll(randomizedQuicksort(lo));
        return out;
    }

    /** k uniform samples from a stream of unknown size. O(n) time, O(k) memory. */
    public static List<Integer> reservoirSample(List<Integer> stream, int k) {
        List<Integer> sample = new ArrayList<>();
        for (int i = 0; i < stream.size(); i++) {
            if (i < k) {
                sample.add(stream.get(i));
            } else {
                int j = RNG.nextInt(i + 1);
                if (j < k) sample.set(j, stream.get(i));
            }
        }
        return sample;
    }
}
