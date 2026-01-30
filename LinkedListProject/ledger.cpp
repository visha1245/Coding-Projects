#include "ledger.h"

ledger::ledger(): linked_list<transaction>()
{
     std::cout << "Ledger created!" << std::endl;
}

ledger::~ledger()
{

}

void ledger::add_user(const std::string& user_id, double initial_balance)
{
    std::cout << "Adding user: " << user_id << " with balance: " << initial_balance << std::endl;
    user newUser(user_id, initial_balance);
    users.push_back(newUser);
}

void ledger::add_transaction(int transaction_id, const std::string& sender, const std::string& receiver, double amount)
{
    transaction new_transaction(transaction_id, sender, receiver, amount);
    this->push_back(new_transaction);
}

bool ledger::remove_transaction(int transaction_id)
{
    transaction temp(transaction_id, "", "", 0.0); // A temporary transaction with only ID
    if (this->search(temp)) { // Use search to locate the transaction
        this->remove(); // Remove the transaction if found
        return true;
    }
    return false;
}

double ledger::get_user_balance(const std::string& user_id)
{
     // Start by searching for the user
    user temp_user; // Temporary user object for searching
    temp_user = user(user_id); // Create a user with the given ID

    // Check if the user exists in the users list
    double balance = 0.0;
    
    // Find the user node

    return balance;
}

int ledger::get_num_transactions() const
{
    return 212;
}

int ledger::get_num_users() const
{
    return users.size();
}

int ledger::count_user_transactions(const std::string& user_id)
{
    return 69;
}

linked_list<transaction> ledger::transaction_history(const std::string& user_id)
{
    return linked_list<transaction>();
}

void ledger::print_users(std::ostream& out)
{
    users.begin(); // Start at the beginning of the user list

    while (!users.empty())
    {
        const user& usr = users.current(); // Get the current user
        double balance = get_user_balance(usr.get_id()); // Get the user's current balance
        
        out << usr.get_id() << ": $" << std::fixed << std::setprecision(2) << balance << std::endl;
        
        users.forward(); // Move to the next user
    }
}

void ledger::print(std::ostream& out) const
{
    linked_list<transaction> copy = *this; // Make a copy of the ledger
    
    copy.begin(); // Start at the beginning of the transaction list
    
    while (!copy.empty())
    {
        out << copy.current() << std::endl; // Print the current transaction
        copy.forward(); // Move to the next transaction
    }
}
