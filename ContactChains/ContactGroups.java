import java.util.*;
import java.awt.Color;
//Heavily based on prog02DisjointSets, mainly the disjointsetsrank class
//Added an array for the size
public class ContactGroups {

    private PersonGraphAdjList graph; //stores the graph
    private int numPerson;

    //pulls resources 
    public ContactGroups(PersonGraphAdjList graph) {
        this.graph = graph;
        this.numPerson = graph.size();
    }

    //Code from prog02DisjointSets adapted to have a size array
    //Note the methods such as union and find are the same as in prog02Disjointsets
    /** Disjoint sets with rank, path compression, and size tracking */
    static class DisjointSetsRank {
        private int[] parent;
        private int[] rank;
        private int[] size; //used to track size through the method explained in the report


        // Constructor creates the disjoint sets for elements n
        public DisjointSetsRank(int n) {
            parent = new int[n];
            rank = new int[n];
            size = new int[n];
            for (int k = 0; k < n; k++)
                make(k);
        }

        public void make(int k) {
            parent[k] = k;
            rank[k] = 0;
            size[k] = 1; // each set starts with size 1
        }

        public int find(int k) {
            if (parent[k] != k) {
                parent[k] = find(parent[k]); // path compression
            }
            return parent[k];
        }

        public void union(int i, int j) {
            i = find(i);
            j = find(j);
            if (i == j) return;

            if (rank[i] < rank[j]) {
                parent[i] = j;
                size[j] += size[i];  // merge size into root j
            } else if (rank[i] > rank[j]) {
                parent[j] = i;
                size[i] += size[j];  // merge size into root i
            } else {
                parent[i] = j;
                size[j] += size[i];  // merge size into root j
                rank[j]++;
            }
        }

        public int getSize(int k) {
            return size[find(k)];
        }
    }

    //Code from prog02DisjointSets adapted by vishnu
    /** contact groups */
    public Map<Integer, List<Integer>> computeGroups() {
        DisjointSetsRank dsu = new DisjointSetsRank(numPerson); //create disjoint sets for each person

        // Union all contacts by calling through a for loop
        for (int i = 0; i < numPerson; i++) {
            for (int neighbor : graph.getNeighbors(i)) {
                dsu.union(i, neighbor);
            }
        }

        // Place persons into groups based on their root
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < numPerson; i++) {
            if (!graph.getNeighbors(i).isEmpty()) {
                int root = dsu.find(i);
                groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
            }
        }
        return groups;
    }   
     //Code from prog02DisjointSets adapted by vishnu

    /* code by vishnu */
    public void colorGroups(problemGenerator p) {
    Map<Integer, List<Integer>> groups = computeGroups();
    for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
        int root = entry.getKey();
        
        Color c = p.groupColor(root);  //this chooses a color for each group using the problem generator
        for (int member : entry.getValue()) {
            try {
                p.setNodeColor(member, c); //this colors it 
            } catch (problemGenerator.error e) {
                System.out.println("Error coloring person " + member + ": " + e.getMessage());
            }
        }
    }
    /* code by vishnu */
}

    /* code by vishnu */
    /** Print the groups */
    public void printGroups() {
        Map<Integer, List<Integer>> groups = computeGroups();
        System.out.println("\nContact groups:");
        for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
            int root = entry.getKey();
            int size = entry.getValue().size(); 
            System.out.println("Root " + root + " → Size: " + size);
        }
    }
    /* code by vishnu */
}

