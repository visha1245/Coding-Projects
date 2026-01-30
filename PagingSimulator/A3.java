import java.io.*;
import java.util.*;

/* A3 java - main driver Main driver program for simulating FIFO page replacement under
 * both Fixed-Local and Variable-Global resident set management policies.
 * by Vishnuvarthan Kumar - c3387042
 * completed on 15/10/2025
*/
public class A3 {

    public static void main(String[] args) throws Exception {
        // Ensure correct number of arguments
        if (args.length < 3) {
            System.out.println("Usage: java A3 <frames> <quantum> <process1.txt> <process2.txt> ...");
            return;
        }

        // Parse memory frame count and CPU quantum
        int totalFrames = Integer.parseInt(args[0]);
        int quantum = Integer.parseInt(args[1]);

        // Collect all process file paths
        List<File> processFiles = new ArrayList<>();
        for (int i = 2; i < args.length; i++) {
            File f = new File(args[i]);
            if (f.exists() && f.isFile()) {
                processFiles.add(f);
            } else {
                System.out.println("Warning: Skipping invalid file -> " + args[i]);
            }
        }

        // Sort by filename to ensure consistent process order (PID assignment)
        processFiles.sort(Comparator.comparing(File::getName));

        if (processFiles.isEmpty()) {
            System.out.println("No valid process files provided.");
            return;
        }

        // Load all process definitions
        List<Process> original = new ArrayList<>();
        int pidCounter = 1;
        for (File f : processFiles) {
            original.add(loadProcess(f, pidCounter++));
        }

        // --- Run simulations ---

        // Fixed-Local replacement policy
        Simulator.simulateAndPrint(original, totalFrames, quantum, true);
        System.out.println("\n------------------------------------------------------------\n");

        // Variable-Global replacement policy
        Simulator.simulateAndPrint(original, totalFrames, quantum, false);
    }

    /**
     * Loads a process definition from a text file. (CHATGPT generated)
     */
    static Process loadProcess(File f, int pid) throws Exception {
        Process p = new Process();
        p.pid = pid;
        p.name = "Process" + pid;  // Default name if unspecified

        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;

        // Read each line and parse process details
        while ((line = br.readLine()) != null) {
            line = line.trim();

            // Parse process name (optional)
            if (line.toLowerCase().startsWith("name:")) {
                String nm = line.substring(5).replace(";", "").trim();
                if (!nm.isEmpty()) p.name = nm;

            // Parse page number lines
            } else if (line.toLowerCase().startsWith("page:")) {
                String num = line.replaceAll("[^0-9]", "");
                if (!num.isEmpty()) p.pages.add(Integer.parseInt(num));
            }
        }

        br.close();
        return p;
    }
}
