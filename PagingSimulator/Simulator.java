import java.util.*;
/* Simulator java - imulates FIFO page replacement under two resident set management policies
 * by Vishnuvarthan Kumar - c3387042
 * completed on 15/10/2025
*/
public class Simulator {

    // Constant delay applied when a process incurs a page fault (simulating IO wait)
    public static final int IO_DELAY = 4;

    public static void simulateAndPrint(List<Process> original, int totalFrames, int quantum, boolean fixedLocal) {
        // copy of all processes
        List<Process> procs = new ArrayList<>();
        for (Process o : original) {
            Process c = new Process(); 
            c.pid = o.pid;
            c.name = o.name;
            c.pages = new LinkedList<>(o.pages);
            procs.add(c);// Deep copy to avoid modifying original
        }

        // Number of processes
        int n = procs.size();
        // For Fixed-Local: each process gets an equal share of frames
        // For Variable-Global: all frames are managed globally
        int framesPerProc = fixedLocal ? (int) Math.ceil((double) totalFrames / n) : totalFrames;

        // Queues representing process states
        Queue<Process> ready = new ArrayDeque<>(procs);
        List<Process> blocked = new ArrayList<>();
        List<Process> finished = new ArrayList<>();
        Queue<int[]> globalFIFO = new ArrayDeque<>();
        int time = 0;

        while (finished.size() < n) {

            // Unblock processes whose IO delay has passed
            blocked.sort(Comparator.comparingInt(p -> p.unblockTime));
            Iterator<Process> bit = blocked.iterator();
            while (bit.hasNext()) {// Unblock processes whose IO delay has passed
                Process p = bit.next();
                if (p.unblockTime <= time) {
                    ready.add(p);
                    p.unblockTime = -1;
                    bit.remove();
                } else break;
            }

            // If no ready processes, advance time to next unblock or finish
            if (ready.isEmpty()) {
                if (!blocked.isEmpty()) {
                    int nextUnblock = blocked.stream()
                            .mapToInt(p -> p.unblockTime)
                            .min()
                            .orElse(time + 1);
                    time = Math.max(time + 1, nextUnblock);
                    continue;
                } else break;
            }

            // Get next process from ready queue
            Process cur = ready.poll();
            int executed = 0;

            // Simulate process execution for up to 'quantum' time units
            while (executed < quantum && !cur.finished) {
                if (cur.pages.isEmpty()) {
                    cur.finished = true;
                    cur.turnaround = time;
                    finished.add(cur);

                    // Clean up global FIFO entries if using Variable-Global policy
                    if (!fixedLocal) cleanupGlobal(cur, procs, globalFIFO);
                    break;
                }

                int pageNeeded = cur.pages.peek();
                boolean inMem = cur.memory.contains(pageNeeded);

                // Page fault handling
                if (!inMem) {
                    cur.faultTimes.add(time);
                    // Page replacement if memory is full
                    if (fixedLocal) {
                        if (cur.memory.size() >= framesPerProc) {
                            Integer victim = cur.fifoOrder.poll();
                            if (victim != null) cur.memory.remove(victim);
                        }
                        cur.memory.add(pageNeeded);
                        cur.fifoOrder.add(pageNeeded);
                        // No need to track globally in Fixed-Local
                    } else {// Variable-Global
                        if (globalFIFO.size() >= totalFrames) {
                            int[] victim = globalFIFO.poll();
                            int vpid = victim[0], vpage = victim[1];
                            for (Process p : procs) if (p.pid == vpid) {
                                p.memory.remove(vpage);
                                Queue<Integer> newq = new LinkedList<>();
                                for (int v : p.fifoOrder) if (v != vpage) newq.add(v);
                                p.fifoOrder = newq;
                                break;
                            }
                        }// Add the new page
                        cur.memory.add(pageNeeded);
                        cur.fifoOrder.add(pageNeeded);
                        globalFIFO.add(new int[]{cur.pid, pageNeeded});
                    }
                    // Simulate IO delay
                    cur.unblockTime = time + IO_DELAY;
                    blocked.add(cur);
                    break;

                } else {// Page is in memory, execute normally
                    cur.pages.poll();
                    time += 1;
                    executed += 1;
                    // Unblock any processes whose IO delay has passed
                    bit = blocked.iterator();
                    while (bit.hasNext()) {
                        Process p = bit.next();
                        if (p.unblockTime <= time) {
                            ready.add(p);
                            p.unblockTime = -1;
                            bit.remove();
                        } else break;
                    }
                    // Check if process has finished
                    if (cur.pages.isEmpty()) {
                        cur.finished = true;
                        cur.turnaround = time;
                        finished.add(cur);
                        if (!fixedLocal) cleanupGlobal(cur, procs, globalFIFO);
                        break;
                    }
                }
            }
            // If process not finished or blocked, re-add to ready queue
            if (!cur.finished && cur.unblockTime == -1 && !blocked.contains(cur))
                ready.add(cur);
        }
        // Print results
        if (fixedLocal)
            System.out.println("FIFO - Fixed-Local Replacement:");
        else
            System.out.println("FIFO - Variable-Global Replacement:");

        System.out.printf("%-4s %-16s %-17s %-9s %s%n",
                "PID", "Process Name", "Turnaround Time", "# Faults", "Fault Times");
        for (Process p : procs) {
            System.out.printf("%-4d %-16s %-17d %-9d %s%n",
                    p.pid, p.name, p.turnaround, p.faultTimes.size(), format(p.faultTimes));
        }
    }

    // Cleans up global FIFO entries and memory for a finished process
    private static void cleanupGlobal(Process cur, List<Process> procs, Queue<int[]> globalFIFO) {
        Queue<int[]> newGlobal = new ArrayDeque<>();
        // Remove all entries belonging to the finished process
        while (!globalFIFO.isEmpty()) {
            int[] e = globalFIFO.poll();
            if (e[0] != cur.pid) newGlobal.add(e);
        }   
        // Rebuild global FIFO without the finished process's pages
        globalFIFO.clear();
        globalFIFO.addAll(newGlobal);
        // Clear the finished process's memory and FIFO order
        cur.memory.clear();
        cur.fifoOrder = new LinkedList<>();
    }
    // Formats a list of integers as a string (CHATGPT generated)
    private static String format(List<Integer> list) {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
