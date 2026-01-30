/**
* user.h
* Written by : SENG1120 Staff (c1234567)
* Modified: 29/07/2024
*/

#ifndef SENG1120_USER_H
#define SENG1120_USER_H

#include <string>
#include <iostream>
#include <iomanip> //for setprecision

/**
 * This class represents the definition of a user in a cryptocurrency ledger, containing the user_id and their initial balance.
 * Note: the initial balance is never updated - this is not a current balance. Rather, it is simply their initial funds before any
 * transactions have occured.
 */
class user
{
public:
    /**
     * Pre-condition: None
     * Post-condition: A new user is created with the supplied details.
    */
    user(const std::string& user_id="N/A", double initial_balance=0.0);

    /**
     * Pre-condition: None
     * Post-condition: The user is destroyed and all associated memory is freed.
     */
    ~user();

    /**
     * Return a const reference to the user ID.
     *
     * Pre-condition: None
     * Post-condition: A const reference to the user ID is returned.
     */
    const std::string& get_id() const;

    /**
     * Return the initial balance of the user.
     *
     * Pre-condition: None
     * Post-condition: The initial balance of the user is returned.
     */
    double get_initial_balance() const;

    /**
     * Determines if two users are equal by comparing their IDs
     *
     * Pre-condition: None
     * Post-condition: Returns true if the ID of this user matches the ID of the supplied user, otherwise returns false
     */
    bool operator ==(const user& u2) const;
    
private:
    std::string id;          //the user ID
    double initial_balance;  //the user's initial balance
};

/**
 * Appends the user to the supplied stream.
 * 
 * This is appended in the format:
 * <user_id>: $<initial_balance>
 * such that the initial balance is limited to 2 decimal places
 * 
 * For example, a user Alice with an initial balance of $100.00 would display as:
 * Alice: $100.00
 *
 * Pre-condition: The stream has been appropriately initialised
 * Post-condition: The output stream has been updated with the user appended
 */
std::ostream& operator <<(std::ostream& out, const user& u);

#endif