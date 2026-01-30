/** main program
 * @author M A Hakim Newton
 */

/* use tabsize 2 to see proper indentation */

import java.awt.Color;	// to get color names
import java.util.HashSet;
//import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class mainProgram
{
  /** main function
   * @param args array of string arguments
   */
  public static void main(String[] args) 
	{
		try
		{
			// 300 persons, venue length 150, venue width 150, proximity measure 10, 
			// infected person 50, random seed is taken from clock with the 
			// System.nanoTime() passed as the random seed, the locations generated 
			// will be different every time the program is run. Instead of 
			// System.nanoTime(), pass a fixed number so that the same locations 
			// are generated every time the program is run.
			//problemGenerator p = new problemGenerator(300, 150, 150, 10, 50, System.nanoTime());
			problemGenerator p = new problemGenerator(30, 15, 10, 1.5, 5, System.nanoTime());
			
			// YOU CAN PROVIDE SEVERAL PROBLEM GENERATOR ABOVE.
			// EACH ONE CAN HAVE A DIFFERENT PARAMETER SETTING.
			// SOME FIXED SEEDS MAY BE LISTED FOR NICE SCENARIOS. 
			// ALL THOSE SHOULD BE COMMENTED OUT, EXCEPT ONE.
			// WE WILL COMMENT OR UNCOMMENT TO RUN ONLY ONE. 

			System.out.println("seed: " + p.randomSeed);	// do not change this line

			// scale factor 5, node radius 5, line width 2, show node indexes true, 
			// show node coords false, show edge indexes false, show edge lines true 
			//p.setDisplay(5, 7, 2,  true, false, false, true);
			p.setDisplay(30, 10, 2,  true, false, false, true);

			// print locations true, print contacts true, print infected true
			p.setPrint(true, true, true);

			p.display();		// graphically display 
			Thread.sleep(1000);	// pause needed after display

			p.print();			// print on screen

			// IF YOU WANT YOU CAN PROVIDE MULTIPLE DISPLAY SETTINGS ABOVE. 
			// EITHER ONE FOR EACH PROBLEM GENERATOR OR JUST ONE FOR ALL.
			// KEEP THE PRINT SETTING AS IT IS, WE MAY TRY OPTIONS THOUGH.

			// YOU ARE FREE TO WRITE YOUR CODE FROM HERE FOR THE TASKS
			// YOU CAN REMOVE ALL THE CODE FROM BELOW AND WRITE YOUR OWN

			// you can selectively change the color of some nodes
			// for example infected persons are shown with in a color
			for(int k = 0; k < p.numInfected; k++)
				p.setNodeColor(p.infected[k], p.infectedColor);
			
			// suppose persons 0, 1, 2 are in orange, yellow, and gay zones	
			// above assumption is just to show how to change node colors
			// use the predefined colors to these persons with the colors
			p.setNodeColor(0, p.orangeColor);	// person 0 is in Orange Zone
			p.setNodeColor(1, p.yellowColor);	// person 1 is in Yellow Zone
			p.setNodeColor(2, p.grayColor);		// person 2 is in Gray Zone

			// to show persons in a group, use the root to get a color 
			// then use the color for all persons in the group
			// suppose 4 is the root of the group containing 3
			// above assumption is just to show how to change color 
			 p.setNodeColor(4, p.groupColor(4));
			 p.setNodeColor(3, p.groupColor(4));

			// you can selectively change the color of some edges
			for(int k = 0; k < p.numContact / 10 ; k++)
			{
				int i = p.contact[k].i;
				int j = p.contact[k].j;
				p.setEdgeColor(i, j, p.chainColor);
			}

			/* code by vishnu */
			Set<Integer> infected = new HashSet<>(); //used for contact chains and contact groups

			//Task 1: Graph representation
			PersonGraphAdjList gAdj = new PersonGraphAdjList(p);

			//Task 2: Contact Groups
			ContactGroups cg = new ContactGroups(gAdj);
			cg.printGroups();
			cg.colorGroups(p);
			//The infectod people need to be colored again as they will be overwritten 
			for(int k = 0; k < p.numInfected; k++){p.setNodeColor(p.infected[k], p.infectedColor);}
				
			
			
			for (int k = 0; k < p.numInfected; k++) {
				infected.add(p.infected[k]);
			}

			//Task 3: Contact Chains
			ContactChains cc = new ContactChains(gAdj, infected, p);
			Scanner sc = new Scanner(System.in);
			int[] queryPerson = new int[3];
			System.out.println("Enter 3 persons (separated by spaces): ");
			for (int i = 0; i < 3; i++) {
				queryPerson[i] = sc.nextInt();
			}

			// Print chains for each query person
			for (int q : queryPerson) {
				cc.findChains(q);
			}

			//Task 4: Zone Classification
			ZoneClassification zc = new ZoneClassification(gAdj, p.numPerson);
			zc.classifyZones(p.infected);
			zc.printZones();
			// display again 

			/* code by vishnu */
			
			p.display();	// the new window might hide the previous one, 
										// so drag it else where to see the previous one
			Thread.sleep(1000);	// pause needed after display

			// To get three query persons as input for task 2, you use the following code
			// just copy paste where you need and uncomment. Then, use queryPerson array

//			int[] queryPerson = new int[3];
//			p.enterIntArray(queryPerson, 3, "enter indexes of three persons");

			// YOU CAN REMOVE ALL THE CODE ABOVE UP TO THE POINT MENTIONED
			// YOU MOST LIKELY DO NOT NEED TO MAKE ANY CHANGE BELOW
		}
		catch(problemGenerator.error e)
		{
			System.out.println(e.getMessage());
		}
		catch (InterruptedException e) 
		{
    	System.err.println("Thread was interrupted while sleeping!");
    }
	}
}
