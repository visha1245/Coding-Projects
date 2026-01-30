#ifndef SENG1120_HASH_TABLE_HPP
#define SENG1120_HASH_TABLE_HPP

#include "hash_table.h"

template <typename T>
//constructor for hash table, intials as num cells which is 101
hash_table<T>::hash_table(int num_cells) : table(num_cells), count(0) {

}

//destroys hastbale
template <typename T>
hash_table<T>::~hash_table() {
     clear();
}


// Insert item into the hash table
template <typename T>
void hash_table<T>::insert(const T& item)
{
    size_t index = hash_function(item.get_key());
    table[index].push_back(item);
    ++count;
}

// Remove item with the given key
template <typename T>
void hash_table<T>::remove(const std::string& key)
{
    size_t index = hash_function(key);
    if (list_remove(table[index], key))
    {
        --count;
    }
}

// Get the item with the given key
template <typename T>
T* hash_table<T>::get(const std::string& key)
{
    size_t index = hash_function(key);
    return list_find(table[index], key);
}

// Check if an item with the given key exists
template <typename T>
bool hash_table<T>::contains(const std::string& key)
{
    size_t index = hash_function(key);
    return list_contains(table[index], key);
}

// Clear all items from the hash table
template <typename T>
void hash_table<T>::clear()
{
    for (auto& chain : table)
    {
        chain.clear();  // Clear each linked list
    }
    count = 0;
}

// Return true if the hash table is empty
template <typename T>
bool hash_table<T>::empty() const
{
    return count == 0;
}

// Return the number of items in the hash table
template <typename T>
int hash_table<T>::size() const
{
    return count;
}

// Print the hash table
template <typename T>
void hash_table<T>::print(std::ostream& out) const
{
    for (size_t i = 0; i < table.size(); ++i)
    {
        out << i << ": ";
        list_print(out, table[i]);
        out << std::endl;
    }
}

// Operator overload to print the entire hash table
template <typename T>
std::ostream& operator<<(std::ostream& out, const hash_table<T>& ht)
{
    ht.print(out);
    return out;
}

#endif
