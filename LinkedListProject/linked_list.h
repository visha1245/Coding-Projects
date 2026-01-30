/**
 * linked_list.h
 * Written by : SENG1120 Staff (c1234567)
 * Modified: 24/05/2024
 */

#ifndef SENG1120_LINKED_LIST_H
#define SENG1120_LINKED_LIST_H

#include <stdexcept>
#include <iostream>

/**
 * This class represents the definition of a templated linked_list with sentinel nodes.
 * The implementation uses an internal node class.
 */
template <typename T>
class linked_list
{
    /**
     * A nested node struct for a doubly-linked list.
     */
    struct node
    {
        node(const T& d = T())
        {
            data = d;
            prev = nullptr;
            next = nullptr;
        }

        ~node()
        {
            prev = nullptr;
            next = nullptr;
        }

        T data;
        node* prev;
        node* next;
    };

public:
    /**
     * Pre-condition: None
     * Post-condition: A new linked_list is created, with all variables initialised.
     */
    linked_list();
       
    /**
     * Pre-condition: None
     * Post-condition: The linked_list is destroyed and all associated memory is freed.
     */
    ~linked_list();
       

    /**
     * The supplied item is inserted at the front of the list.
     *
     * Pre-condition: The supplied data is valid.
     * Post-condition: The item is inserted as the first non-sentinel node and cursor points to the new node.
     */
    void push_front(const T& item);
           

    /**
     * The supplied item is inserted at the end of the list.
     *
     * Pre-condition: None
     * Post-condition: The item is inserted as the last non-sentinel node and cursor points to the new node.
     */
    void push_back(const T& item);
        

    /**
     * The supplied item is inserted before the current node.
     *
     * Pre-condition: Cursor points to the node after the insertion point.
     * Post-condition: A new node has been added. Cursor points to the inserted node.
     */
    void insert(const T& item);
        


    /**
     * Return a reference to the first item in the list (not the head sentinel). An exception should be thrown if the list is empty.
     *
     * Pre-condition: The list is not empty.
     * Post-condition: A reference to the first item is returned.
     */
    T& front() const;

    /**
     * Return a reference to the last item in the list (not the tail sentinel). An exception should be thrown if the list is empty.
     *
     * Pre-condition: The list is not empty.
     * Post-condition: A reference to the last item is returned.
     */
    T& back() const;

    /**
     * Return a reference to the item pointed to by cursor. An exception should be thrown if the list is empty or cursor points to a sentinel.
     *
     * Pre-condition: The list is not empty and the current pointer is not pointing to a sentinel node.
     * Post-condition: A reference to the item at cursor is returned.
     */
    T& current() const;

    /**
     * Remove the first item from the list. If the list is empty, nothing happens.
     *
     * Pre-condition: None
     * Post-condition: The first data element has been removed, reducing the count by 1. If the list is empty, nothing happens.
     * Cursor points to the node after the one that has been removed (which should be the first data item, or tail if empty).
     */
    void pop_front();
        
    /**
     * Remove the last item from the list. If the list is empty, nothing happens.
     *
     * Pre-condition: None
     * Post-condition: The last data element has been removed, reducing the count by 1. If the list is empty, nothing happens.
     * Cursor points to the node after the one that has been removed (which should be tail).
     */
    void pop_back();
    /**
     * Remove the item pointed to by cursor from the list. Nothing should happen if the list is empty or
     * if current is pointing to a sentinel node.
     *
     * Pre-condition: The list is not empty and the cursor pointer is not pointing to a sentinel node.
     * Post-condition: The data element pointed to by cursor has been removed, reducing the count by 1.
     * Cursor points to the node after the one that has been removed.
     */
    void remove();
        
    /**
     * Clears all items from the list, leaving the sentinel nodes intact.
     *
     * Pre-condition: None
     * Post-condition: All items have been removed. Sentinels should not be removed. Count should be reset.
     */
    void clear();
      
    /**
     * Set the cursor pointer to the node after head, even if this is tail.
     *
     * Pre-condition: None
     * Post-condition: The cursor pointer is set to the node after head.
     */
    void begin();

    /**
     * Set the cursor pointer to tail (sentinel).
     *
     * Pre-condition: None
     * Post-condition: The cursor pointer is set to the tail (sentinel) node.
     */
    void end();

    /**
     * Move the cursor pointer forward, if valid. Otherwise, nothing happens.
     *
     * Pre-condition: None
     * Post-condition: The cursor pointer is set to the next node, if applicable.
     */
    void forward();

    /**
     * Move the cursor pointer backward, if valid. Otherwise, nothing happens.
     *
     * Pre-condition: None
     * Post-condition: The current pointer is set to the previous node, if applicable.
     */
    void backward();

    /**
     * Return the number of nodes in the list, excluding sentinels.
     *
     * Pre-condition: None
     * Post-condition: The number of (true) nodes is returned.
     */
    int size() const;

    /**
     * Return true if the list is empty, false otherwise.
     *
     * Pre-condition: None
     * Post-condition: None
     */
    bool empty() const;

    /**
     * Return true if the target is found. If found, cursor will point to the node containing target.
     *
     * Pre-condition: None
     * Post-condition: Cursor points to the first node storing the target, and true is returned, if target is found. Otherwise, false is returned.
     */
    bool search(const T& target);

    /**
     * Print the list to the supplied stream. The list should be printed one item at a time, with no space between elements.
     *
     * Pre-condition: out is a valid stream.
     * Post-condition: out has been updated with the printed list.
     */
    void print(std::ostream& out) const;
       

private:
    int count;    // count of the number of nodes in the list
    node* head;   // head of the list - sentinel node
    node* tail;   // tail of the list - sentinel node
    node* cursor; // arbitrary pointer, used as a cursor to traverse the list
};

#include "linked_list.hpp"

#endif
