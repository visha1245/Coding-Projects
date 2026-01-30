/**
* main.cpp
* Written by : SENG1120 Staff (c1234567)
* Modified: 29/07/2024
*
*/

#include <iostream>
#include "transaction.h"
#include "linked_list.h"
#include "ledger.h"

/**
 * The main function of the program, which includes a brief demonstration of the ledger class.
 */  
int main()
{
    ledger l;
    std::cout << "***** Welcome to the NewyCoin Ledger! *****" << std::endl;
    int transaction_counter = 1; //a variable to retain the current transaction number
    //create a few users with initial balances
    std::cout << "Creating user Liam with balance of $100.00" << std::endl;
    l.add_user("Liam", 100.0);
    std::cout << "Creating user Aiden with balance of $50.00" << std::endl;
    l.add_user("Aiden", 50.0);
    std::cout << "Creating user Maya with balance of $42.50" << std::endl;
    l.add_user("Maya", 42.50);
    std::cout << "Creating user Amara with (default) balance of $0.00" << std::endl;
    l.add_user("Amara");
    std::cout << std::endl;

    std::cout << "Number of users: " << l.get_num_users() << std::endl;

    //process some transactions
    l.add_transaction(transaction_counter++, "Liam", "Aiden", 10.00);
    l.add_transaction(transaction_counter++, "Aiden", "Maya", 22.50);
    l.add_transaction(transaction_counter++, "Amara", "Maya", 5.00);  //should not work, Amara has no money!
    l.add_transaction(transaction_counter++, "Liam", "Amara", 12.50);
    l.add_transaction(transaction_counter++, "Amara", "Maya", 7.50);
    l.add_transaction(transaction_counter++, "Maya", "Liam", 51.25);

    //display the transactions
    std::cout << "***** Transactions *****" << std::endl;
    std::cout << "Number of transactions: " << l.get_num_transactions() << std::endl;
    l.print(std::cout);
    std::cout << std::endl;

    //filter and display only transactions with the user "Liam"
    std::cout << "***** Transactions Involving Liam *****" << std::endl;
    linked_list<transaction> liam_transactions = l.transaction_history("Liam");
    liam_transactions.print(std::cout);
    std::cout << std::endl;

    //display users and their balances
    std::cout << "***** Users *****" << std::endl;
    l.print_users(std::cout);
    std::cout << std::endl;

    //remove transaction with ID 1
    std::cout << "***** Removing Transaction 1 *****" << std::endl << std::endl;
    l.remove_transaction(1);

    //display transactions again, with transaction 1 removed
    std::cout << "***** Transactions *****" << std::endl;
    std::cout << "Number of transactions: " << l.get_num_transactions() << std::endl;
    l.print(std::cout);
    std::cout << std::endl;

    //display users again, with transaction 1 removed
    std::cout << "***** Users *****" << std::endl;
    l.print_users(std::cout);
    std::cout << std::endl;

    return 0;
}