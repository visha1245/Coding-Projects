/**
* transaction.h
* Written by : SENG1120 Staff (c1234567)
* Modified: 29/07/2024
*/

#ifndef SENG1120_TRANSACTION_H
#define SENG1120_TRANSACTION_H

#include <string>
#include <iostream>
#include <iomanip> //for setprecision

/**
 * This class represents the definition of a transaction in a cryptocurrency ledger, 
 * containing details such as the sender, receiver, and amount of cryptocurrency transferred.
 */
class transaction
{
public:
    /**
     * Pre-condition: None
     * Post-condition: A new transaction is created with the supplied details.
    */
    transaction(int id=-1, const std::string& sender_id="N/A", const std::string& receiver_id="N/A", double transfer_amount=0);

    /**
     * Pre-condition: None
     * Post-condition: The transaction is destroyed and all associated memory is freed.
     */
    ~transaction();

    /**
     * Return the ID of the transaction.
     *
     * Pre-condition: None
     * Post-condition: The ID of the transaction is returned.
     */
    int get_id() const;

    /**
     * Return a const reference to the sender ID of the transaction.
     *
     * Pre-condition: None
     * Post-condition: A const reference to the sender ID is returned.
     */
    const std::string& get_sender() const;
    
    /**
     * Return a const reference to the recipient ID of the transaction.
     *
     * Pre-condition: None
     * Post-condition: A const reference to the recipient ID is returned.
     */
    const std::string& get_receiver() const;

    /**
     * Return the amount of the transaction.
     *
     * Pre-condition: None
     * Post-condition: The amount of the transaction is returned.
     */
    double get_amount() const;

    /**
     * Determines where a user with specified ID was involved in the transaction
     * as either a sender or recipient.
     *
     * Pre-condition: None
     * Post-condition: Returns true if the specified user was the sender or reciever, otherwise returns false
     */
    bool involves_user(const std::string& user_id);

    /**
     * Determines if two transactions are equal by comparing their IDs
     *
     * Pre-condition: None
     * Post-condition: Returns true if the transaction ID of this transaction matches the ID of the supplied transaction, otherwise returns false
     */
    bool operator ==(const transaction& t2) const;
    
private:
    int id;                //the transaction ID
    std::string sender;    //the ID of the sender
    std::string receiver;  //the ID of the recipient
    double amount;         //the amount of the transaction
};

/**
 * Appends the transaction to the supplied stream.
 * 
 * This is appended in the format:
 * <transaction_id> - <sender> -> <receiver>: $<amount>
 * such that the amount is limited to 2 decimal places.
 * 
 * For example, a transfer from Alice to Bob of $20.00 would display as:
 * Alice -> Bob: $20.00
 *
 * Pre-condition: The stream has been appropriately initialised
 * Post-condition: The output stream has been updated with the transaction appended.
 */
std::ostream& operator <<(std::ostream& out, const transaction& t);

#endif