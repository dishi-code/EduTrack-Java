# EduTrack (Java)

DSA modules for the EduTrack academic management platform. Needs JDK 17 or newer.

## Run
    javac -d out src/dsa/*.java src/Main.java
    java -cp out Main

## Structure
- `src/dsa/KMP.java`, `src/dsa/RabinKarp.java` - string search
- `src/dsa/EditDistance.java` - typo correction (dynamic programming)
- `src/dsa/SuffixArray.java` - suffix array, LCP, duplicate detection
- `src/dsa/MaxFlow.java` - faculty assignment (network flow) and timetable conflicts
- `src/dsa/SortingSampling.java` - randomized quicksort, reservoir sampling
- `src/Main.java` - demo of every module

## Web interface
`web/` holds the dashboard (HTML, CSS, JavaScript). Open `web/index.html` in a browser.
The page runs its own JavaScript copy of the algorithms; it is not connected to the Java code yet.
