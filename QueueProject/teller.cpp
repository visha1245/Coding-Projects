#include "teller.h"

teller::teller(int tell_id)
{
    //check this, there should be more being intialised
}

teller::~teller()
{

}

bool teller::is_teller_free() const
{
    return true;
}

int teller::get_remaining_service_time() const
{
    return 0;
}

void teller::update(int time) 
{

}

int teller::get_queue_length() const
{
    return 0;
}

void teller::add_to_queue(const customer& cust)
{
    
}

int teller::get_teller_id() const
{
    return 0;
}

const queue<customer>& get_queue()
{

}
