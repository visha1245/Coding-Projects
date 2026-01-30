/**
 * queue.h
 * Written by : SENG1120 Staff (c1234567)
 * Modified: 05/09/2024
 * 
 * This class represents a templated queue using std::list.
 */ 

#ifndef SENG1120_QUEUE_H
#define SENG1120_QUEUE_H

#include <list>
#include <stdexcept>
#include <iostream>

template <typename T>
class queue
{
public:
    /**
    * Precondition: None
    * Postcondition: A new queue is created, with all variables initialised.
    */
    queue();

    /**
    * Precondition: None
    * Postcondition: The queue is destroyed and all associated memory is freed.
    */
    ~queue();

    /**
    * The supplied item is inserted at the rear of the queue.
    *
    * Precondition: None
    * Postcondition: The item is inserted at the rear of the queue.
    */
    void enqueue(const T& data);

    /**
    * Removes the front item from the queue. If the queue is empty, nothing happens.
    *
    * Precondition: None
    * Postcondition: The front item is removed. If the queue is empty, nothing happens.
    */
    void dequeue();

    /**
    * Returns a const reference to the front item in the queue.
    * If the queue is empty, an exception is thrown.
    *
    * Precondition: The queue is not empty.
    * Postcondition: The queue has not changed. If the queue is empty, an exception is thrown.
    */
    const T& front() const;

    /**
    * Returns the number of items in the queue.
    *
    * Precondition: None.
    * Postcondition: The queue has not changed.
    */
    int size() const;

    /**
    * Returns true if the queue is empty, false otherwise.
    *
    * Precondition: None.
    * Postcondition: The queue has not changed.
    */
    bool empty() const;

    /**
     * Print the queue to the supplied stream. The list queue be printed one item at a time, with a single space between elements.
     * Your implementation *must* use an iterator to traverse the elements, printing them one at a time.
     *
     * Hint: You will need to use a const_iterator to ensure the iterator does not modify the list.
     * You can declare and initialise the iterator as: typename std::list<T>::const_iterator iter = list.begin();
     * The remainder of the functionality will be as discussed in lecture.
     * 
     * As we saw with the node in linked list, C++ needs some help to identify that 'std::list<T>::const_iterator' is actually a type, so we preface it with typename.
     * 
     * Pre-condition: out is a valid stream.
     * Post-condition: out has been updated with the printed list.
     */
    void print(std::ostream& out) const;

private:
    std::list<T> list; //the underlying storage for the queue elements
};

/**
 * Append the queue to the supplied stream. This method should delegate to the queue's print method.
 * 
 * Pre-condition: out is a valid stream.
 * Post-condition: out has been updated with the queue.
 */
template <typename T>
std::ostream& operator << (std::ostream& out, const queue<T>& q);

#include "queue.hpp"

#endif
