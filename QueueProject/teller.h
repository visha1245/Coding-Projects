/**
 * teller.h
 * Written by : SENG1120 Staff (c1234567)
 * Modified: 05/09/2024
 * 
 * This class represents a teller in a bank queuing system.
 * This file should be used in conjunction with Assignment 2 for SENG1120.
 */
#ifndef SENG1120_TELLER_H
#define SENG1120_TELLER_H

#include "queue.h"
#include "customer.h"

class teller
{
public:
	/**
	 * Constructor for a teller with a given teller ID. A default value of 0 is given to 
     * permit default construction of tellers - a unique ID should always be given.
	 * 
     * Pre-condition: None
	 * Post-condition: The teller's ID has been set.
     *                 The teller's status is free.
	 *                 The remaining_service_time is set to 0.
     *                 The current_customer_id is set to -1.
	*/
    teller(int tell_id=0);

    /**
     * Destructor for a teller.
     * 
     * Pre-condition: None
     * Post-condition: The teller is destroyed.
    */ 
    ~teller();

	/**
	 * Determine if the teller is free.
	 * 
     * Pre-condition: None
	 * Post-condition: Returns true if the teller is free. Otherwise, returns false.
	*/
    bool is_teller_free() const;

    
	/**
	 * Return the remaining time to serve the current customer. If no customer is being served,
     * this should return 0.
	 * 
     * Pre-condition: None
	 * Post-condition: The remaining service time is returned.
	*/
    int get_remaining_service_time() const;

    /**
     * The teller has been updated at the supplied time step.
     * In short, this should:
     * 1. Decrement the remaining service time and determine if the teller is now free.
     * 2. If the teller is free, serve the next customer from the queue (if any)
     * 3. For each customer in the queue, update their waiting time by one unit.
     * 
     * Pre-condition: None
     * Post-condition: The teller and their queue have been updated.
     */ 
    void update(int time);

    /**
     * Return the number of customers in the queue.
     * 
     * Pre-condition: None
     * Post-condition: The length of the queue is returned.
     */
    int get_queue_length() const;

    /**
     * Add a customer to this teller's queue
     * 
     * Pre-condition: None
     * Post-condition: The queue has been updated with the supplied customer added to the end.
     */
    void add_to_queue(const customer& cust);

    /**
     * Return the teller ID.
     * 
     * Pre-condition: None
     * Post-condition: The ID of the teller is returned.
     */
    int get_teller_id() const;

    /**
     * Return a constant reference to the customer queue.
     * As a constant reference, this means the queue cannot be modified using this reference.
     * Think of it as a read-only copy!
     * 
     * Pre-condition: None
     * Post-condition: A constant reference to the customer queue is returned.
     */
    const queue<customer>& get_queue() const;

private:
    int teller_id;              //The ID of the teller
    int current_customer_id;    //The ID of the current customer being served. If a teller is not surrently serving a customer, the current_customer_id should be -1.
    queue<customer> customers;  //The queue of customers for this teller
    int remaining_service_time; //The amount of time remaining to serve the current customer
    bool is_free;               //The teller status - true if free, otherwise false.

    /**
     * Display a message when a customer has been removed from the queue and is being served.
     * 
     * Note: this method can be called directly from here and should not be redefined in your .cpp file.
     * 
     * Pre-condition: None
     * Post-condition: A message is printed to std::cout.
     */
    void display_customer_served(const customer& c, int time)
    {
        std::cout << "\tTeller " 
                  << teller_id
                  << " serving customer "
                  << c.get_customer_id()
                  << " at time "
                  << time
                  << " after waiting "
                  << c.get_waiting_time()
                  << " time steps"
                  << std::endl;
    }

    /**
     * Display a message when the teller has finished serving a customer.and u
     * 
     * Note: this method can be called directly from here and should not be redefined in your .cpp file.
     * 
     * Pre-condition: None
     * Post-condition: A message is printed to std::cout.
     */
    void display_customer_done(int cust_id, int time)
    {
        std::cout << "\tTeller " 
                  << teller_id
                  << " (Customer "
                  << cust_id
                  << ") free at time "
                  << time
                  << std::endl;
    }
};

#endif