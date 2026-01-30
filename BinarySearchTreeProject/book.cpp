#include "book.h"

// Default constructor
book::book() 
    : isbn(""), title(""), genre(""), rating(0.0) 
{
}

// Constructor with parameters
book::book(const std::string& isbn, const std::string& title, const std::string& genre, double rating) 
    : isbn(isbn), title(title), genre(genre), rating(rating) 
{
}

// Get the ISBN (key)
const std::string& book::get_key() const 
{
    return isbn;
}

// Get the title of the book
const std::string& book::get_title() const 
{
    return title;
}

// Get the genre of the book
const std::string& book::get_genre() const 
{
    return genre;
}

// Get the rating of the book
double book::get_rating() const 
{
    return rating;
}

// Equality operator
bool operator ==(const book& b1, const book& b2) 
{
    return b1.get_key() == b2.get_key();
}

// Less than operator
bool operator <(const book& b1, const book& b2) 
{
    return b1.get_key() < b2.get_key();
}

// Greater than operator 
bool operator >(const book& b1, const book& b2) 
{
    return b1.get_key() > b2.get_key();
}

// Output stream overload
std::ostream& operator <<(std::ostream& os, const book& b) 
{
    os << "(" << b.get_key() << ", " << b.get_title() << ", " << b.get_genre() << ", " << b.get_rating() << ")";
    return os;
}
