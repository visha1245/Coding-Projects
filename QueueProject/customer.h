/**
 * customer.h
 * Written by : SENG1120 Staff (c1234567)
 * Modified: 05/09/2024
 * 
 * This class represents a customer in a bank queuing system.
 * This file should be used in conjunction with Assignment 2 for SENG1120.
 */

#ifndef SENG1120_CUSTOMER_H
#define SENG1120_CUSTOMER_H

#include <string>
#include <iostream>

class customer
{
public:
	/**
	 * Constructor for a customer.
	 * 
	 * Pre-condition: None
	 * Post-condition: The customer's information is set, as provided.
	*/
	customer(int cust_id, int arrive_time, int serve_time);


    /**
     * Destructor for a customer.
     * 
	 * Pre-condition: None
     * Post-condition: The customer is destroyed.
    */ 
	~customer();

	/**
	 * Return the waiting time.
	 * 
	 * Pre-condition: None
	 * Post-condition: The value of the waiting time is returned.
	*/
	int get_waiting_time() const;

	/**
	 * Increment the waiting time by 1 unit.
	 * 
	 * Pre-condition: None
	 * Post-condition: The value of the waiting time is incremented by 1.
	*/
	void increment_waiting_time();

	/**
	 * Return the arrival time.
	 * 
	 * Pre-condition: None
	 * Post-condition: The value of the arrival time is returned.
	*/
	int get_arrival_time() const;

	/**
	 * Return the number of time steps it will take to serve this customer.
	 * This is not the remaining service time, but the total time.
	 * 
	 * Pre-condition: None
	 * Post-condition: The value of the service time is returned.
	*/
	int get_service_time() const;

	/**
	 * Return the customer ID number.
	 * 
	 * Pre-condition: None
	 * Post-condition: The value of the customer ID number is returned.
	*/
	int get_customer_id() const;

private:
	int customer_id;      	 // The customer ID number
	int arrival_time;        // The arrival time
	int waiting_time;        // The time they have spent waiting
	int service_time;        // The time to serve this customer
};

/**
 * Append the customer to the supplied stream.
 * The expected format is: Customer(<customer ID>).
 * You should not append a newline character to the stream in this method.
 * 
 * Pre-condition: out is a valid stream.
 * Post-condition: out has been updated with the customer.
 */
std::ostream& operator << (std::ostream& out, const customer& cust);

#endif