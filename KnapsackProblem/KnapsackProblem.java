

/** MCKP solver using dynamic programming
 *  Adapted from prog05KnapsackProblem.java
 */
public class KnapsackProblem {

  /* -----------------------------
   * begin adapted from prog05KnapsackProblem.java
   * ----------------------------- */
  public static void knapsackSolution(problemGenerator p) {
    int n = p.numItem;          // total number of items
    int W = p.budget;           // the total budget

    // maximum value avaliable with the budget
    int[][] f = new int[n + 1][W + 1];

    // store which option was chosen
    int[][] choice = new int[n + 1][W + 1];

    // initialise base cases
    for (int l = 0; l <= W; l++) {
      f[0][l] = 0;
      choice[0][l] = 0;
    }

    // DP iteration
    for (int k = 1; k <= n; k++) { //loops through every item
      int category = p.items[k].categoryIndex;

      for (int l = 0; l <= W; l++) { //loops over each budget
        // by default: do not pick any option
        f[k][l] = f[k - 1][l];
        choice[k][l] = 0;

        // try all options for this item
        for (int opt = 1; opt <= p.numOption; opt++) {
          int cost = p.costs[category][opt]; //grabs the cost
          int value = p.values[category][opt]; //grabs the value

          if (l >= cost) {
            int candidate = f[k - 1][l - cost] + value;
            //if a candidate is better then current then update in choice 
            if (candidate > f[k][l]) {
              f[k][l] = candidate;
              choice[k][l] = opt;
            }
          }
        }
      }
    }
    /* -----------------------------
     * end adapted from prog05KnapsackProblem.java
     * ----------------------------- */

    // solve (new code)
    int remaining = W;
    for (int k = n; k >= 1; k--) {
      int opt = choice[k][remaining]; // which option was chosen
      p.choices[k] = opt; //store to array
      if (opt > 0) { //if there was an option chosen
        int category = p.items[k].categoryIndex;
        remaining -= p.costs[category][opt]; //subtract cost from remaining budget
      }
    }

    // show results
    System.out.println("Optimal value: " + f[n][W]);
  }
}
