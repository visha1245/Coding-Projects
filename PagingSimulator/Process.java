import java.util.*;
/* Process java - Stores process state and data
 * by Vishnuvarthan Kumar - c3387042
 * completed on 15/10/2025
*/
public class Process {
    public int pid; // Process ID
    public String name; // Process name
    public Queue<Integer> pages = new LinkedList<>(); // Pages to be accessed
    public LinkedHashSet<Integer> memory = new LinkedHashSet<>(); // Currently loaded pages (in memory)
    public Queue<Integer> fifoOrder = new LinkedList<>(); // Order of pages for FIFO replacement
    public List<Integer> faultTimes = new ArrayList<>(); // Times when page faults occurred
    public int turnaround = 0; // Total time from start to finish
    public boolean finished = false; // Whether the process has finished
    public int unblockTime = -1; // Time when process will be unblocked after a page fault
}
