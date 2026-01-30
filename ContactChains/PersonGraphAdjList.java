import java.util.*;

public class PersonGraphAdjList {
    private int numPerson;
    private ArrayList<ArrayList<Integer>> adjList;

    /* copied code prog03GraphSearch modifed to pull from problem generator instead by vishnu */
    // Constructor builds the adjacency list from problemGenerator
    public PersonGraphAdjList(problemGenerator p) {
        this.numPerson = p.numPerson; // grabs the number of persons from problemGenerator
        adjList = new ArrayList<>(numPerson);
        for (int i = 0; i < numPerson; i++) 
        {
            adjList.add(new ArrayList<>());
        }

        // Add edges from contacts
        for (int k = 0; k < p.numContact; k++) {
            // goes through each contact and then adds them to each other on the list, so i will be added to j and j to i
            int i = p.contact[k].i;
            int j = p.contact[k].j;
            adjList.get(i).add(j);
            adjList.get(j).add(i); 
        }
    }
    /* copied code prog03GraphSearch modifed to pull from problem generator instead by vishnu */

    /* code from prog03GraphSearch */
    // Return neighbors of a person (the persons they are in conact with)
    public ArrayList<Integer> getNeighbors(int person) {
        return adjList.get(person);
    }

    // Return number of persons
    public int size() {
        return numPerson;
    }
    /* code from prog03GraphSearch adapted by vishnu */

}
