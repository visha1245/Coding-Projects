#include "inventory.h"

// Add a new book to the inventory
void inventory::add_book(const book& new_book) 
{
    // Insert the book into the BST
    bst.insert(new_book);
    
    // Check if genre stats exist in the hash table for the book's genre
    book_genre_stats* genre_stats = ht.get(new_book.get_genre());
    // In the case it does'nt it will create a new genre 
    if (genre_stats == nullptr)
    {
        
        book_genre_stats new_stats(new_book.get_genre());
        new_stats.increment_count();
        new_stats.add_rating(new_book.get_rating());
        ht.insert(new_stats); //inserts into hashtable
    }
    else //if it does exist all that needs to be updated is count and rating 
    {
        genre_stats->increment_count();
        genre_stats->add_rating(new_book.get_rating());
    }
}

// Remove a book from the inventory using its ISBN
bool inventory::remove_book(const std::string& isbn) 
{
    // gets the book to remove
    book* book_to_remove = get_book(isbn);
    
    if (!book_to_remove) 
    {   
        //in the case book is not found
        return false;
    }
    
    //gets genre so we can later decrease count 
    std::string genre = book_to_remove->get_genre();
    bst.remove(isbn);
    //similar to add stats but instead decreases count 
    book_genre_stats* stats = get_stats(genre);
    
    if (stats) 
    {
        // Decrement the count and subtract the rating of the removed book
        stats->decrement_count();
        stats->subtract_rating(book_to_remove->get_rating());

        // If its the last of that genre, it will then be removed ttoatly 
        if (stats->get_count() == 0) 
        {
            ht.remove(genre);
        }
    } 

    return true;
}

// Check if a book exists in the inventory
bool inventory::book_exists(const std::string& isbn) 
{
    return get_book(isbn) != nullptr;
}

// Get a pointer to a book by its ISBN
book* inventory::get_book(const std::string& isbn) 
{
    return bst.find(isbn);  
}

// Display all books in the inventory
void inventory::display_books() const 
{
    std::cout << bst;  
}

// Clear both the BST and hash table
void inventory::clear() 
{
    bst.clear();  
    ht.clear();   
}

// Return the count of books in a specific genre
// Three functions below simply get a pointer to the stats and then use that for the get fucntions 
int inventory::genre_count(const std::string& genre) 
{
    book_genre_stats* stats = get_stats(genre);
    return stats ? stats->get_count() : 0;
}

// Return the average rating of books in a specific genre
double inventory::genre_average_rating(const std::string& genre) 
{
    book_genre_stats* stats = get_stats(genre);
    return stats ? stats->average_rating() : 0.0;
}

// Get a pointer to the genre stats object by genre
book_genre_stats* inventory::get_stats(const std::string& genre) 
{
     book_genre_stats* stats = ht.get(genre);
    return stats; 
}

// Print the inventory
void inventory::print(std::ostream& os) const 
{
    os << "Books: " << bst << "\n";  

    os << "Stats:\n" << ht;  
}

// Overload the << operator to print the inventory
std::ostream& operator<<(std::ostream& os, const inventory& inv) 
{
    inv.print(os); 
    return os;
}
