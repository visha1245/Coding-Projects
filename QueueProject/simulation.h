/**
 * simulation.h
 * Written by : SENG1120 Staff (c1234567)
 * Modified: 05/09/2024
 * 
 * This class represents the main simulation for a bank queuing system.
 * This file should be used in conjunction with Assignment 2 for SENG1120.
 */

#ifndef SENG1120_SIMULATION_H
#define SENG1120_SIMULATION_H

#include <iostream>
#include <random>
#include "bank_branch.h"
#include "customer.h"
#include "teller.h"

class simulation
{
public:
    /**
     * Create the simulation using the provided parameters.
     * 
     * Pre-condition: None
     * Post-condition: The simulations parameters are set, as provided.
     *                A bank_branch with the specified number of tellers has been created.
     *                customers_arrived is set to 0.
     */
    simulation(int sim_time, int num_tellers, int time_between_arrivals);

    /**
     * Destructor for a simulation.
     * 
     * Pre-condition: None
     * Post-condition: The simulation is destroyed.
    */ 
    ~simulation();

    /**
     * Run the simulation.
     * 
     * Pre-condition: The simulation parameters have been appropriately set.
     * Postcondition: The simulation has been executed.
     */
    void run_simulation();

private:
    int sim_time;              // The simulation time, as the number of time steps
    int num_tellers;           // The number of tellers
    int time_between_arrivals; // The average time between customer arrivals
    int customers_arrived;     // The number of customers that have arrived, used to supply customer ID numbers

    bank_branch bank;

    /**
     * Determine if a customer has arrived at this time step.
     * 
     * Note: the argument passed is the time_between_arrivals, NOT the current time.
     * Note: this method can be called directly from here and should not be redefined in your .cpp file.
     * 
     * Pre-condition: The time_between_arrivals should be the average time steps between customer arrivals.
     * Post-condition: Returns true if a customer has arrived.
     */
    static bool has_customer_arrived(int time_between_arrivals)
    {
        double value = rand() / static_cast<double>(RAND_MAX);
        return value > std::exp(-1.0 / static_cast<double>(time_between_arrivals));
    }

    /**
     * Generate a random service time. This generates a random integer between 3 and 12, both inclusive.
     * 
     * Note: this method can be called directly from here and should not be redefined in your .cpp file.
     * 
     * Pre-condition: None
     * Post-condition: Returns a random integer between 3 and 12.
     */
    static int generate_random_service_time()
    {
        return rand() % 10 + 3;
    }
    
    /**
     * Display a message when a customer has arrived.
     * 
     * Note: this method can be called directly from here and should not be redefined in your .cpp file.
     * 
     * Pre-condition: None
     * Post-condition: A message is printed to std::cout.
     */
    static void display_customer_arrived(int cust_id, int time)
    {
        std::cout << "\tCustomer " 
                  << cust_id
                  << " arrived at time " 
                  << time 
                  << std::endl;
    }

};

#endif