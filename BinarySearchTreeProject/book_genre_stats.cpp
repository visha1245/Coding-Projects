#include "book_genre_stats.h"

// Default constructor
book_genre_stats::book_genre_stats() 
    : genre(""), count(0), total_rating(0.0) 
{
    
}

// Constructor with parameter
book_genre_stats::book_genre_stats(const std::string& genre) 
    : genre(genre), count(0), total_rating(0.0) 
{
    // Empty constructor body
}

// Get the key (genre)
const std::string& book_genre_stats::get_key() const 
{
    return genre;
}

// Get the count of books in this genre
int book_genre_stats::get_count() const 
{
    return count;
}

// Increment the count of books
void book_genre_stats::increment_count() 
{
    ++count;
}

// Decrement the count of books
void book_genre_stats::decrement_count() 
{
    if (count > 0) {
        --count;
    }
}

// Add a rating to the total rating
void book_genre_stats::add_rating(double rating) 
{
    total_rating += rating;
}

// Subtract a rating from the total rating
void book_genre_stats::subtract_rating(double rating) 
{
    total_rating -= rating;
}

// Calculate the average rating
double book_genre_stats::average_rating() const 
{
    if (count == 0) {
        return 0.0;
    }
    return total_rating / count;
}

// Output stream overload
std::ostream& operator << (std::ostream& os, const book_genre_stats& s) 
{
    os << "(" << s.get_key() << ", " << s.get_count() << ", " << s.average_rating() << ")";
    return os;
}

// Equality operator 
bool operator == (const book_genre_stats& s1, const book_genre_stats& s2) 
{
    return s1.get_key() == s2.get_key();
}

// Less than operator
bool operator < (const book_genre_stats& s1, const book_genre_stats& s2) 
{
    return s1.get_key() < s2.get_key();
}

// Greater than operator 
bool operator > (const book_genre_stats& s1, const book_genre_stats& s2) 
{
    return s1.get_key() > s2.get_key();
}
