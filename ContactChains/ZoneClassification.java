import java.util.*;

/* code by vishnu however the logic for bfs from disjointsets was used */
public class ZoneClassification {
    private PersonGraphAdjList graph;
    private int numPerson;
    //zone array will be used to store the zone of each person
    private int[] zone; // -1 = none, 1 = orange, 2 = yellow, 3 = gray

    public ZoneClassification(PersonGraphAdjList graph, int numPerson) {
        this.graph = graph;
        this.numPerson = numPerson;
        //adds everyone to the zone array and gives them a default value of -1
        this.zone = new int[numPerson];
        Arrays.fill(zone, -1);
    }

    public void classifyZones(int[] infected) {
        Queue<int[]> q = new LinkedList<>();
        //queue for bfs

        // Run BFS from all infected persons
        for (int inf : infected) {
            q.add(new int[]{inf, 0}); //infected people will be zero/ignored
        }

        //while the queue isnt empty, grabs all the people
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int person = curr[0];
            int dist = curr[1];

            // Assign zone if within 1–3 hops
            if (dist >= 1 && dist <= 3) {
                int zoneLevel = dist; // 1=orange, 2=yellow, 3=gray
                if (zone[person] == -1 || zoneLevel < zone[person]) {
                    zone[person] = zoneLevel;
                }
            }

            // To stop the search beyond 3 hops, otherwise it can infinite loop
            if (dist < 3) {
                for (int neighbor : graph.getNeighbors(person)) {
                    if (dist + 1 <= 3) {
                        q.add(new int[]{neighbor, dist + 1});
                    }
                }
            }
        }
    }

    /* code by vishnu */
    // Print all people in a specific zone
    public void printZones() {
        System.out.print("orange: ");
        for (int i = 0; i < numPerson; i++) if (zone[i] == 1) System.out.print(i + " ");
        System.out.println();

        System.out.print("yellow: ");
        for (int i = 0; i < numPerson; i++) if (zone[i] == 2) System.out.print(i + " ");
        System.out.println();

        System.out.print("gray: ");
        for (int i = 0; i < numPerson; i++) if (zone[i] == 3) System.out.print(i + " ");
        System.out.println();
    }
    /* code by vishnu */
}
