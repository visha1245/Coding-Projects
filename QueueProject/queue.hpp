#include "queue.h"

template<typename T>
queue<T>::queue() : front(nullptr), rear(nullptr)
{

}

template<typename T>
queue<T>::~queue()
{
    while (front) {
        node<T>* temp = front;
        front = front->next;
        delete temp; 
    }
}

template<typename T>
void queue<T>::enqueue(const T& item)
{
    Node<T>* newNode = new Node<T>(item);
    if (rear) {
        rear->next = newNode; 
    }
    rear = newNode
    if (front) {
        front = newNode; 
    }
}

template<typename T>
void queue<T>::dequeue()
{

}

template<typename T>
const T& queue<T>::front() const
{

}

template<typename T>
int queue<T>::size() const
{
    
}

template<typename T>
bool queue<T>::empty() const
{

}

template<typename T>
void print(std::ostream& out)
{
    Node<T>* temp = front;
    while (temp) {
        oss << temp->data <<" "; 
        temp = temp->next;
    }
    return oss.str(); 
}
