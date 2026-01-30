import java.util.*;
import java.awt.Color;
//Logic from graphsearch file, but the tree method has been removed and instead 
//it pulls directly from persongraphadjlist
public class ContactChains {
    private problemGenerator p;       // for coloring edges
    private PersonGraphAdjList graph; //stores the graph
    private Set<Integer> infected; //stores the ids of infected people
    //used to then find the paths from any person to an infected person

    /* code by vishnu */
    public ContactChains(PersonGraphAdjList graph, Set<Integer> infected, problemGenerator p) {
        this.graph = graph;
        this.infected = infected;
        this.p = p;
        //grabs the details from the ohter classes
    }

    public void findChains(int start) {
        //checks if the start person is infected, if thats the case no point in doing a search
        if (infected.contains(start)) {
            System.out.println("chain: " + start);
            return;
        }

        //path a list used to store the current chain 
        List<Integer> path = new ArrayList<>();
        //visited a hash set to store the visited nodes
        Set<Integer> visited = new HashSet<>();

        path.add(start); //start number gets added to the path
        dfs(start, visited, path); //details get passed to main funciton
    }
    /* code by vishnu */
    
    /* code by vishnu and disjoint set program, this just doesnt use a tree structure */
    private void dfs(int current, Set<Integer> visited, List<Integer> path) {
        visited.add(current); //adds the current node to visited

        //if the current node is infected and the path size is greater than 1, the chain is complete
        if (infected.contains(current) && path.size() > 1) {
            System.out.print("chain: ");
            for (int p : path) System.out.print(p + " ");
            System.out.println();

            // Color the chain edges
            colorChain(path);

        } else {
            //goes throuhh all currrent neighbours and adds to path
            for (int neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    //if the neighbour hasnt been visited yet
                    path.add(neighbor);
                    dfs(neighbor, visited, path);
                    path.remove(path.size() - 1); // backtrack 
                }
            }
        }

        visited.remove(current); // so other paths can be found
    }


    /* code by vishnu */
    // helper to color edges along a chain
    private void colorChain(List<Integer> path) {
        try {
            for (int i = 0; i < path.size() - 1; i++) {
                int from = path.get(i);
                int to = path.get(i + 1);
                p.setEdgeColor(from, to, p.chainColor);
            }
        } catch (problemGenerator.error e) {
            System.err.println("Error coloring edge: " + e.getMessage());
        }
    }
    /* code by vishnu */
}
