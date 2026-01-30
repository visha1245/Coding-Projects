#include <random>
#include <iostream>
#include <iomanip>
#include "simulation.h"

/**
 * Parse the arguments and run the simulation.
 * 
 * Post-condition: 0 is returned on a successful run. -1 is returned if an error occurs.
*/
int main(int argc, char *argv[])
{
    if (argc < 5) //quick check that we have 5 arguments passed - the program name is an argument!
    {
        std::cout << "Too few arguments. You must run the program as './Simulation <seed> <sim_time> <num_tellers> <time_between_arrivals>'" << std::endl;
        return -1;
    }

    try
    {
        //arg[0] is the program name
        int seed = std::stoi(argv[1]);
        int sim_time = std::stoi(argv[2]);
        int num_tellers = std::stoi(argv[3]);
        int time_between_arrival = std::stoi(argv[4]);

        std::srand(seed); // random seed, for reproducibility

        simulation sim(sim_time, num_tellers, time_between_arrival);
        sim.run_simulation();
        //generate_statistics(sim);
    }
    catch (std::exception& e) // catch a std::exception
    {
        std::cout << "Standard exception caught: " << e.what() << std::endl;
        return -1;
    }
    catch (...) // catch anything else that may have been thrown
    {
        std::cout << "Unknown error occured." << std::endl;
        return -1;
    }

    return 0;
}
