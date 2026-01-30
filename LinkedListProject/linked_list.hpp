#include "linked_list.h"
template<typename T>
linked_list<T>::linked_list()
{
    head = new node(); // Head sentinel node
    tail = new node(); // Tail sentinel node
    head->next = tail;
    head->prev = nullptr; // Head's prev is always nullptr
    tail->prev = head;
    tail->next = nullptr; // Tail's next is always nullptr
    cursor = head;
    count = 0;
}

template<typename T>
linked_list<T>::~linked_list()
{
    clear(); 
    delete head;
    delete tail;
}

template<typename T>
void linked_list<T>::push_front(const T& item)
{
    cursor = head->next; 
    insert(item);
}

template<typename T>
void linked_list<T>::push_back(const T& item)
{
    cursor = tail; 
    insert(item);
}

template<typename T>
void linked_list<T>::insert(const T& item)
{
    node* new_node = new node(item);
    node* rhs = cursor;
    node* lhs = rhs->prev;

    new_node->next = rhs;  // u -> w
    new_node->prev = lhs;  // v <- u
    rhs->prev = new_node;  // u <- w
    lhs->next = new_node;  // v -> u

    cursor = new_node;
    count++;
}

template<typename T>
T& linked_list<T>::front() const
{
    if (empty()) 
    {
        throw std::runtime_error("List is empty");
    }
    return head->next->data;
}

template<typename T>
T& linked_list<T>::back() const
{
    if (empty()) 
    {
        throw std::runtime_error("List is empty");
    }
    return tail->prev->data;
}

template<typename T>
T& linked_list<T>::current() const
{
    if (empty() || cursor == head || cursor == tail) //if its any of these it is invalid
    {
        throw std::runtime_error("Cursor is invalid");
    }
    return cursor->data;
}

template<typename T>
void linked_list<T>::pop_front()
{
    cursor = head->next;
    remove();
}

template<typename T>
void linked_list<T>::pop_back()
{
    cursor = tail->prev;
    remove();

}

template<typename T>
void linked_list<T>::remove()
{
    if (cursor == nullptr || cursor == head || cursor == tail)
    {
        return; // Nothing to remove
    }

    node* rhs = cursor->next;
    node* lhs = cursor->prev;
    lhs->next = rhs;
    rhs->prev = lhs;
    cursor = rhs;
    delete cursor->prev;
    --count;
}

template<typename T>
void linked_list<T>::clear()
{
    node* current = head->next; //starts from the one after head
    while (head->next != tail)
    {
        pop_front();
    }
    cursor = tail;
    count = 0;
}

template<typename T>
void linked_list<T>::begin()
{
    cursor = head->next;
}

template<typename T>
void linked_list<T>::end()
{
    cursor = tail;
}

template<typename T>
void linked_list<T>::forward()
{
    if (cursor != tail) // Only move forward if not at the tail
    {
        cursor = cursor->next;
    }
}

template<typename T>
void linked_list<T>::backward()
{
    if (cursor != head) // Only move backward if not at the head sentinel
    {
        cursor = cursor->prev;
    }
}

template<typename T>
int linked_list<T>::size() const
{
    return count;
}

template<typename T>
bool linked_list<T>::empty() const
{
    return count == 0;
}

template <typename T>
bool linked_list<T>::search(const T &target)
{
    node* p = head->next;
    while (p != tail)
    {
        if (p->data == target)
        {
            cursor = p;
            return true;
        }
        p = p->next;
    }
    return false;
}

template <typename T>
void linked_list<T>::print(std::ostream& out) const
{
    node* p = head->next;
    if (p != tail) 
    {
        out << p->data; 
        p = p->next;    // Move to the next node
        
        while (p != tail)
        {
            out << " " << p->data; 
            p = p->next;          // Move to the next node
        }
    }
}