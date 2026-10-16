import dsa.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String doc1 = "An array stores elements in contiguous memory. Stacks follow last in first out order.";
        String doc2 = "An array stores elements in contiguous memory. Stacks follow LIFO order.";

        System.out.println("== Search ==");
        System.out.println("KMP        : " + KMP.search(doc1, "stack"));
        System.out.println("Rabin-Karp : " + RabinKarp.search(doc1, "stack"));
        System.out.println("Typo fix   : " + EditDistance.suggest("algorthm", List.of("algorithm", "array", "stack"), 3));
        System.out.println("Distance   : " + EditDistance.distance("kitten", "sitting"));

        System.out.println("\n== Document intelligence ==");
        System.out.println("Shared text: '" + SuffixArray.longestCommonSubstring(doc1, doc2) + "'");
        System.out.println("Similarity : " + SuffixArray.similarity(doc1, doc2) + " %");

        System.out.println("\n== Resource allocation ==");
        Map<String, List<String>> faculty = new LinkedHashMap<>();
        faculty.put("Dr. Kavitha", List.of("CS201", "CS202"));
        faculty.put("Prof. Rao", List.of("CS210", "CS201"));
        faculty.put("Prof. Mehta", List.of("CS230", "CS210"));
        System.out.println(MaxFlow.assignFaculty(faculty, List.of("CS201", "CS202", "CS210", "CS230"), 2));
        List<MaxFlow.Session> sessions = List.of(
            new MaxFlow.Session("CS201", "Dr. Kavitha", "A-101", "Mon 9:00"),
            new MaxFlow.Session("CS202", "Dr. Kavitha", "A-102", "Mon 9:00"));
        System.out.println(MaxFlow.findConflicts(sessions));

        System.out.println("\n== Analytics ==");
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 40; i++) scores.add(45 + (i * 37 + 11) % 55);
        System.out.println("Top 5 : " + SortingSampling.randomizedQuicksort(scores).subList(0, 5));
        System.out.println("Sample: " + SortingSampling.reservoirSample(scores, 5));
    }
}
