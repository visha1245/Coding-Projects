/**
* ledger.h
* Written by : SENG1120 Staff (c1234567)
* Modified: 24/05/2024
*/

#ifndef SENG1120_LEDGER_H
#define SENG1120_LEDGER_H

#include <string>
#include <iostream>
#include <iomanip> //for setprecision
#include "linked_list.h"
#include "transaction.h"
#include "user.h"

/**
 * This class represents the definition of a transaction in a cryptocurrency ledger
 * containing details such as the sender, receiver, and amount of cryptocurrency transferred.
 * 
 * The ledger (privately) inherits from linked_list<transaction>.
 */
class ledger : private linked_list<transaction>
{
public:
    /**
     * Pre-condition: None
     * Post-condition: A new ledger is created with the supplied details.
    */
    ledger();

    /**
     * Pre-condition: None
     * Post-condition: The ledger is destroyed and all associated memory is freed.
     */
    ~ledger();

    /**
     * A new user is constructed from the supplied user ID and initial balance. 
     * The user is then inserted at the end of the user list.
     *
     * Pre-condition: None
     * Post-condition: A new user is inserted into the user list.
     */
    void add_user(const std::string& user_id, double initial_balance=0.0);

    /**
     * A new transaction is constructed from the supplied information.
     * The transaction is then inserted at the end of the ledger.
     *
     * Pre-condition: None
     * Post-condition: A new transaction is inserted into the ledger.
     */
    void add_transaction(int transaction_id, const std::string& sender, const std::string& receiver, double amount);

    /**
     * A transaction with the supplied ID is removed from the ledger. 
     * A boolean return value is used to indicate if a transaction was removed.
     * Notably, if the transaction ID does not exist, the method should return false.
     *
     * Pre-condition: None
     * Post-condition: Returns true if a transaction with the supplied ID was removed from the ledger, otherwise returns false.
     */
    bool remove_transaction(int transaction_id);

    /**
     * Determine the balance of the user with supplied ID.
     * As discussed in the assignment spec, this requires starting with the initial balance and applying the transactions that involve this user.
     * These transactions may be either as the sender or the receiver, which have different effects on the balance!
     * If no user with the supplied ID exists, return 0.0.
     * 
     * Pre-condition: None
     * Post-condition: Returns the balance of the user with the supplied ID. If the user does not exist, returns 0.0.
     */ 
    double get_user_balance(const std::string& user_id);

    /**
     * Return the number of transactions stored in the ledger.
     *
     * Pre-condition: None
     * Post-condition: The number of transactions is returned.
     */
    int get_num_transactions() const;

    /**
     * Return the number of users.
     *
     * Pre-condition: None
     * Post-condition: The number of users is returned.
     */
    int get_num_users() const;

    /**
     * Return the number of transactions that the user with the supplied ID is involved in.
     *
     * Pre-condition: None
     * Post-condition: The number of transactions involving the user with supplied ID is returned.
     */
    int count_user_transactions(const std::string& user_id);

    /**
     * Return a list containing only the transactions involving the user with the supplied ID.
     * The list should include transactions where the user is either a sender or recipient.
     * This should return a copy of the transactions and should not modify or remove transactions from the ledger.
     * The list should include the transactions in the order the are added to the ledger.
     *
     * Pre-condition: None
     * Post-condition: A list of transactions involving the user is returned
     */
    linked_list<transaction> transaction_history(const std::string& user_id);

    /**
     * Print the list of users to the supplied stream along with their current balance, limited to 2 decimal places.
     * The format of the output should be one user per line, in the following format:
     * <user_id>: $<balance>
     * 
     * Hint: This should not use the print function of the user list, as this will display only the initial balance.
     * Rather, you will need to use your function get_user_balance.
     * 
     * Hint: Use '<< std::setprecision(2) << std::fixed' to limit the balance to 2 decimal places - use the << operator
     * from user or transaction as a reference to see how to do this.
     * 
     * Pre-condition: The stream has been appropriately initialised
     * Post-condition: The list of users and their balances has been appended to the supplied stream.
     */ 
    void print_users(std::ostream& out);

    /**
     * Print the list of transactions to the supplied stream. 
     * Hint: This should use the print function of the inherited list of transactions.
     * 
     * Pre-condition: The stream has been appropriately initialised
     * Post-condition: The list of transactions has been appended to the supplied stream.
     */ 
    void print(std::ostream& out) const;

private:
    linked_list<user> users;  //the list of users that have been added to the ledger
};

#endif