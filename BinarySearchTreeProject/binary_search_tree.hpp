#ifndef SENG1120_BST_HPP
#define SENG1120_BST_HPP

#include "binary_search_tree.h"
#include <stdexcept>

// Constructor
template<typename T>
binary_search_tree<T>::binary_search_tree() : root(nullptr), count(0) {}

// Destructor
template<typename T>
binary_search_tree<T>::~binary_search_tree() {
    clear();
}

// Insert a new item into the binary search tree
template<typename T>
void binary_search_tree<T>::insert(const T& item) {
    insert(item, root);
}

template<typename T>
void binary_search_tree<T>::insert(const T& item, binary_node*& node) {
    if (node == nullptr) { //if its the first object or tree is empty it starts here
        node = new binary_node(item);
        count++;
    } else if (item.get_key() < node->data.get_key()) { //first checks left
        insert(item, node->left);
    } else if (item.get_key() > node->data.get_key()) { //then right 
        insert(item, node->right);
    } else {
        node->data = item; // update existing node
    }
}

// Remove an item from the binary search tree
template<typename T>
void binary_search_tree<T>::remove(const std::string& key) {
    remove(key, root);
}

//this method was loesly based on the geeksforgeeks solution 
template<typename T>
void binary_search_tree<T>::remove(const std::string& key, binary_node*& node) {
    if (node == nullptr) {
        return; // item not found
    } else if (key < node->data.get_key()) {
        remove(key, node->left);
    } else if (key > node->data.get_key()) {
        remove(key, node->right);
    } else { // key matches
        if (node->left != nullptr && node->right != nullptr) {
            node->data = find_min(node->right)->data; // replace with min on the right side
            remove(node->data.get_key(), node->right);
        } else {
            binary_node* oldNode = node;
            node = (node->left != nullptr) ? node->left : node->right;
            delete oldNode;
            count--;
        }
    }
}

// Check if the tree contains a node with a specific key
template<typename T>
bool binary_search_tree<T>::contains(const std::string& key) const {
    return contains(key, root);
}
//based of lecture slide 
template<typename T>
bool binary_search_tree<T>::contains(const std::string& key, binary_node* node) const {
    if (node == nullptr) {
        return false;
    } else if (key < node->data.get_key()) {
        return contains(key, node->left);
    } else if (key > node->data.get_key()) {
        return contains(key, node->right);
    } else {
        return true;
    }
}

// Find an item by key
template<typename T>
T* binary_search_tree<T>::find(const std::string& key) const {
    binary_node* foundNode = find(key, root); //instead of returing key and root which will cause error 
    //This will instead find the key and root make that equal to a pointer of a node
    return foundNode ? &foundNode->data : nullptr;
    //and instead return the found nounds date 
}

// Recursive find helper function
template<typename T>
typename binary_search_tree<T>::binary_node* binary_search_tree<T>::find(const std::string& key, binary_node* node) const {
    if (node == nullptr) return nullptr;
    if (key < node->data.get_key()) return find(key, node->left);
    if (key > node->data.get_key()) return find(key, node->right);
    return node;  // Found the node
}

// Find and return a reference to the minimum item 
template<typename T>
T& binary_search_tree<T>::find_min() const {
    if (root == nullptr) {
        throw std::underflow_error("Tree is empty");
    }
    return find_min(root)->data;
}

template<typename T>
typename binary_search_tree<T>::binary_node* binary_search_tree<T>::find_min(binary_node* node) const {
    if (node == nullptr || node->left == nullptr) {
        return node;
    }
    return find_min(node->left);
}

// Find and return a reference to the maximum item 
template<typename T>
T& binary_search_tree<T>::find_max() const {
    if (root == nullptr) {
        throw std::underflow_error("Tree is empty");
    }
    return find_max(root)->data;
}

template<typename T>
typename binary_search_tree<T>::binary_node* binary_search_tree<T>::find_max(binary_node* node) const {
    if (node == nullptr || node->right == nullptr) {
        return node;
    }
    return find_max(node->right);
}

// Clear all nodes from the tree
template<typename T>
void binary_search_tree<T>::clear() {
    clear(root);
    root = nullptr;
    count = 0;
}

template<typename T>
void binary_search_tree<T>::clear(binary_node*& node) {
    if (node != nullptr) {
        clear(node->left);
        clear(node->right);
        delete node;
    }
}

// Return the number of items in the tree
template<typename T>
int binary_search_tree<T>::size() const {
    return count;
}

// Check if the tree is empty
template<typename T>
bool binary_search_tree<T>::empty() const {
    return root == nullptr;
}

// Print the tree in an inorder traversal
template<typename T>
void binary_search_tree<T>::print_inorder(std::ostream& out) const {
    print_inorder(out, root);
}

template<typename T>
void binary_search_tree<T>::print_inorder(std::ostream& out, binary_node* node) const {
    if (node != nullptr) {
        print_inorder(out, node->left);
        out << node->data << " ";
        print_inorder(out, node->right);
    }
}

// Print the tree in a preorder traversal
template<typename T>
void binary_search_tree<T>::print_preorder(std::ostream& out) const {
    print_preorder(out, root);
}

template<typename T>
void binary_search_tree<T>::print_preorder(std::ostream& out, binary_node* node) const {
    if (node != nullptr) {
        out << node->data << " ";
        print_preorder(out, node->left);
        print_preorder(out, node->right);
    }
}

// Print the tree in a postorder traversal
template<typename T>
void binary_search_tree<T>::print_postorder(std::ostream& out) const {
    print_postorder(out, root);
}

template<typename T>
void binary_search_tree<T>::print_postorder(std::ostream& out, binary_node* node) const {
    if (node != nullptr) {
        print_postorder(out, node->left);
        print_postorder(out, node->right);
        out << node->data << " ";
    }
}

// Overload operator << to print the entire tree in inorder traversal
template<typename T>
std::ostream& operator<<(std::ostream& out, const binary_search_tree<T>& tree) {
    tree.print_inorder(out);
    return out;
}

#endif
