package dsa;

import java.util.*;

public class MaxFlow {
    /** Edmonds-Karp on a capacity matrix (modified in place). Returns the flow value. */
    public static int maxFlow(int[][] cap, int s, int t) {
        int n = cap.length, flow = 0;
        while (true) {
            int[] parent = new int[n];
            Arrays.fill(parent, -1);
            parent[s] = s;
            Deque<Integer> q = new ArrayDeque<>();
            q.add(s);
            while (!q.isEmpty() && parent[t] < 0) {
                int u = q.poll();
                for (int v = 0; v < n; v++) {
                    if (parent[v] < 0 && cap[u][v] > 0) {
                        parent[v] = u;
                        q.add(v);
                    }
                }
            }
            if (parent[t] < 0) return flow;
            int f = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) f = Math.min(f, cap[parent[v]][v]);
            for (int v = t; v != s; v = parent[v]) {
                cap[parent[v]][v] -= f;
                cap[v][parent[v]] += f;
            }
            flow += f;
        }
    }

    /** faculty: name -> course codes they can teach. Returns name -> assigned courses. */
    public static Map<String, List<String>> assignFaculty(Map<String, List<String>> faculty,
                                                          List<String> courses, int load) {
        List<String> names = new ArrayList<>(faculty.keySet());
        int n = names.size(), m = courses.size(), N = n + m + 2, s = N - 2, t = N - 1;
        int[][] cap = new int[N][N];
        for (int i = 0; i < n; i++) {
            cap[s][i] = load;
            for (String c : faculty.get(names.get(i))) {
                int j = courses.indexOf(c);
                if (j >= 0) cap[i][n + j] = 1;
            }
        }
        for (int j = 0; j < m; j++) cap[n + j][t] = 1;
        int[][] orig = new int[N][];
        for (int i = 0; i < N; i++) orig[i] = cap[i].clone();
        maxFlow(cap, s, t);
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> assigned = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (orig[i][n + j] == 1 && cap[i][n + j] == 0) assigned.add(courses.get(j));
            }
            result.put(names.get(i), assigned);
        }
        return result;
    }

    /** One timetable entry. */
    public record Session(String course, String faculty, String room, String slot) {}

    public static List<String> findConflicts(List<Session> sessions) {
        List<String> out = new ArrayList<>();
        for (int i = 0; i < sessions.size(); i++) {
            for (int j = i + 1; j < sessions.size(); j++) {
                Session a = sessions.get(i), b = sessions.get(j);
                if (!a.slot().equals(b.slot())) continue;
                if (a.faculty().equals(b.faculty()))
                    out.add(a.faculty() + " double-booked at " + a.slot() + ": " + a.course() + ", " + b.course());
                if (a.room().equals(b.room()))
                    out.add("Room " + a.room() + " double-booked at " + a.slot() + ": " + a.course() + ", " + b.course());
            }
        }
        return out;
    }
}
