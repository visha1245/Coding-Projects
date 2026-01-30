import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** MCKP solver using Backtracking + Branch and Bound */
public class KnapsackBnB {

    // Track the best solution found
    static long nodesExplored = 0;
    static long startTime = System.currentTimeMillis(); //just for progress logging
    static int bestValue; //best maxium total value found
    static int[] bestChoice; //best choices for each item 

    public static void solve(problemGenerator p) {
        //intialises 
        bestValue = 0;
        bestChoice = new int[p.numItem + 1]; 
        int[] currentChoice = new int[p.numItem + 1];
        
        //Array sort method written by Vishnu (pretty sure it doesnt do much)
        // This ensures promising items are explored earlier.
        Arrays.sort(p.items, 1, p.numItem + 1, Comparator.comparingDouble(i -> {
            int category = i.categoryIndex;
            double bestRatio = 0;
            for (int opt = 1; opt <= p.numOption; opt++) {
                double ratio = (double) p.values[category][opt] / p.costs[category][opt];
                if (ratio > bestRatio) bestRatio = ratio;
            }
            return -bestRatio; // sort descending
        }));
        //End Array sort method written by Vishnu

        //starts backtracking, exploring from the first item
        backtrack(p, 1, 0, 0, currentChoice);

        // Copy best choice into p.choices for printing
        for (int i = 1; i <= p.numItem; i++) {
            p.choices[i] = bestChoice[i];
        }

        System.out.println("Optimal value (BnB): " + bestValue);
    }


    /**
     * Backtracking recursion with Branch & Bound 
     */
    //start code adapted from prog04QueenBackTracking.java and https://www.geeksforgeeks.org/dsa/0-1-knapsack-using-branch-and-bound/
    private static void backtrack(problemGenerator p, int level, int currentCost, int currentValue, int[] currentChoice) {

        //used to print of progress every 1 billion nodes, for longer runs and peace of mind
        nodesExplored++;
        if (nodesExplored % 100000000 == 0) { // log every 100k nodes
            System.out.println("working");
        }
          
      
        // If over budget, destroy
        if (currentCost > p.budget) return;

        // If all items processed, and the total value beats the best one then store it 
        if (level > p.numItem) {
            if (currentValue > bestValue) {
                bestValue = currentValue;
                bestChoice = currentChoice.clone();
            }
            return;
        }


        // ---- Branch and Bound pruning ----
        int bound = currentValue + bound(p, level, currentCost);
        //calls bound function to return the best possible upper bound
        if (bound <= bestValue) return; // if not promising means prune, no reason to continye

        // Category of this item
        int category = p.items[level].categoryIndex;

        // Case 1: dont pick any option for this item
        currentChoice[level] = 0;
        backtrack(p, level + 1, currentCost, currentValue, currentChoice);

        // Case 2: try each option for this item so basic, standard extended
        for (int opt = 1; opt <= p.numOption; opt++) {
            //looks up the cost adn value for each option
            int cost = p.costs[category][opt];
            int value = p.values[category][opt];

            //then runs it through backtracking, which
            currentChoice[level] = opt; //temp
            backtrack(p, level + 1, currentCost + cost, currentValue + value, currentChoice);
            
        }
        //end code adapted from prog04QueenBackTracking.java and https://www.geeksforgeeks.org/dsa/0-1-knapsack-using-branch-and-bound/
    }

    /**
     * Bound function: optimistic upper bound on achievable value
     */
  private static int bound(problemGenerator p, int level, int currentCost) {
    int remainingBudget = p.budget - currentCost;
    int optimistic = 0;
    for (int i = level; i <= p.numItem && remainingBudget > 0; i++) {
        int category = p.items[i].categoryIndex;
        int best = 0;
        for (int opt = 1; opt <= p.numOption; opt++) {
            if (p.costs[category][opt] <= remainingBudget)
                best = Math.max(best, p.values[category][opt]);
        }
        optimistic += best;
        remainingBudget -= p.costs[category][1]; // assume cheapest always possible
    }
    return optimistic;
}

}
