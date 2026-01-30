/** main program
 * @author M A Hakim Newton
 */

/* use tabsize 2 to see proper indentation */

/* ALL INDEXES ARE EFFECTIVELY 1 BASED WITH NULL or 0 AT INDEX 0 */

// YOU CAN CREATE OTHER CLASSES HERE AS NEEDED

public class mainProgram
{
	// YOU CAN ADD ATTRIBUTES OR METHODS HERE

  /** main function
   * @param args array of string arguments
   */
  public static void main(String[] args) 
	{
		try
		{
			// the first parameter is the number of persons (max 67600)
			// the second parameter is the number of categories (max 6) per person
			// the third parameter is the number of number of options (max 5) per category
			// the fourth parameter is the max cost for an option in a category
			// the fifth parameter is the max value for an option in a category
			problemGenerator p = new problemGenerator(7, 3, 3, 10, 10, System.nanoTime());

			// YOU CAN PROVIDE SEVERAL PROBLEM GENERATOR ABOVE.
			// EACH ONE CAN HAVE A DIFFERENT PARAMETER SETTING.
			// SOME FIXED SEEDS MAY BE LISTED FOR NICE SCENARIOS. 
			// ALL THOSE SHOULD BE COMMENTED OUT, EXCEPT ONE.
			// WE WILL COMMENT OR UNCOMMENT TO RUN ONLY ONE. 

			System.out.println("seed: " + p.randomSeed);	// do not change this line

			// YOU ARE FREE TO WRITE YOUR CODE FROM HERE BELOW FOR THE TASKS

			p.printProblem();		// print the problem details

			// keep track of the clock to measure time
			long startTime = System.nanoTime();

			// we are going to construct a dummy solution here
			// you will develop two algorithms to construct solutions
			// a dynamic programming method and a (backtracking plus branch and bound) mehtod
			
			long start = System.nanoTime();
			KnapsackProblem.knapsackSolution(p);
			long end = System.nanoTime();
			double elapsedSec = (end - start) / 1e9;
			System.out.println("Elapsed time: " + elapsedSec + " sec");
			long start2 = System.nanoTime();
			KnapsackBnB.solve(p); // or backtracking without BnB
			long end2 = System.nanoTime();
			double elapsedSec2 = (end2 - start2) / 1e9;
			System.out.println("Elapsed time BNB: " + elapsedSec2 + " sec");
			// keep track of the clock to measure time
			long endTime = System.nanoTime();

			p.printSolution();	// print the solution details
	
			// print the execution time between start and end
			System.out.println("execution time: " + (endTime - startTime));

			// YOU MOST LIKELY DO NOT NEED TO MAKE ANY CHANGE BELOW
		}
		catch(problemGenerator.error e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	// YOU CAN ADD ATTRIBUTES OR METHODS HERE
}
