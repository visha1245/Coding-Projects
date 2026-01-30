#include "user.h"

user::user(const std::string& user_id, double initial_balance)
    : id(user_id), initial_balance(initial_balance)
{

}

user::~user()
{

}

const std::string& user::get_id() const
{
    return id;
}

double user::get_initial_balance() const
{
    return initial_balance;
}

bool user::operator ==(const user& u2) const
{
    return id == u2.id;
}

std::ostream& operator <<(std::ostream& out, const user& u)
{
    //use set_precision and std::fixed to limit to 2 decimal places
    out << u.get_id() << ": $" << std::setprecision(2) << std::fixed << u.get_initial_balance() << std::endl;
    return out;
}