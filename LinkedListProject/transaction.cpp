#include "transaction.h"

//constructor
transaction::transaction(int transaction_id, const std::string& sender_id, const std::string& receiver_id, double transfer_amount)
    : id(transaction_id), sender(sender_id), receiver(receiver_id), amount(transfer_amount)
{

}

transaction::~transaction()
{
   
}

int transaction::get_id() const
{
    return id;
}

const std::string& transaction::get_sender() const
{
    return sender;
}

const std::string& transaction::get_receiver() const
{
    return receiver;
}

double transaction::get_amount() const
{
    return amount;
}

bool transaction::involves_user(const std::string& user_id)
{
    return (user_id == sender || user_id == receiver);
}

bool transaction::operator ==(const transaction& t2) const
{
    return id == t2.id;
}

std::ostream& operator <<(std::ostream& out, const transaction& t)
{
    //use set_precision and std::fixed to limit to 2 decimal places
    out << t.get_id() << " - " << t.get_sender() << " -> " << t.get_receiver() << ": $" << std::setprecision(2) << std::fixed << t.get_amount() << std::endl;
    return out;
}