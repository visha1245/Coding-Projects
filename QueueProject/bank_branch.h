/**
 * bank_branch.h
 * Written by : SENG1120 Staff (c1234567)
 * Modified: 05/09/2024
 * 
 * This class represents a bank branch in a queuing system, mainly a wrapper for a vector of teller objects.
 * This file should be used in conjunction with Assignment 2 for SENG1120.
 */

#ifndef SENG1120_BANK_BRANCH_H
#define SENG1120_BANK_BRANCH_H

#include <vector>
#include "teller.h"
#include "customer.h"

class bank_branch
{
public:
    /**
     * Constructor for a bank branch. This method should construct and push the appropriate number of tellers to the vector.
     * 
     * Pre-condition: None
     * Post-condition: A vector of tellers is initialised with the appropriate number of tellers.
    */
    bank_branch(int num_tells = 1);

    /**
     * Destructor for a bank branch.
     * 
     * Pre-condition: None
     * Post-condition: The bank branch is destroyed and all associated memory is freed.
    */ 
    ~bank_branch();

    /**
     * Allocate a customer to the queue beloning to the teller that is the shortest. 
     * This does not consider if the teller is free or not, and simply considers the size of the queues.
     * If multiple teller's queues are the same length, this should allocate to the first one encountered.
     * 
     * Once allocated, the display_customer_allocated method should be called to display a message to the console.
     * 
     * Pre-condition: None
     * Post-condition: The customer has been allocated to a queue and a message has been displayed to the console.
     */ 
    void allocate_customer_to_queue(const customer& cust);

    /**
     * Loop through and update each teller, calling their update method, at the given time step.
     * 
     * Pre-condition: None
     * Post-condition: Each teller has been updated.
     */ 
    void update_tellers(int time);

    /**
     * Loop through and display each of the teller's queues to the console. 
     * The expected format is one line per teller, as:
     * 
     * Teller <teller id>: <customer queue>
     * 
     * Pre-condition: None
     * Post-condition: The console (cout) has been updated with the queue for each teller.
     */ 
    void display_queues() const;

private:
    std::vector<teller> tellers;  // The vector of tellers

    /**
     * Display a message when a customer has been allocated to a queue.
     * 
     * Note: this method can be called directly from here and should not be redefined in your .cpp file.
     * 
     * Pre-condition: None
     * Post-condition: A message is printed to std::cout.
     */
    void display_customer_allocated(int cust_id, int teller_id)
    {
        std::cout << "\tCustomer " 
                  << cust_id
                  << " allocated to queue for teller " 
                  << teller_id 
                  << std::endl;
    }
};

#endif