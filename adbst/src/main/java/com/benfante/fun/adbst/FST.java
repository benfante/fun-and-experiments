package com.benfante.fun.adbst;

import java.util.NoSuchElementException;
import java.util.Scanner;

class BSTNode {

    BSTNode left = null;
    BSTNode rigth = null;
    int data = 0;

    public BSTNode() {
        super();
    }

    public BSTNode(int data) {
        this.left = null;
        this.rigth = null;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BSTNode [left=" + left + ", rigth=" + rigth + ", data=" + data + "]";
    }

}


class BinarySearchTree {

    BSTNode root = null;

    public BinarySearchTree() {

    }

    /*In tal caso è sufficiente sostituire il vecchio root con il nuovo
     e agganciare il vecchio root a sinistra del nuovo, se è più piccolo,
     o a destra se è più grande. 
     Inoltre se è il vecchio root è più piccolo del nuovo,
     cerchi sul ramo di destra del vecchio root il primo elemento più grande del nuovo root,
     e lo sposti agganciandolo come ramo di destra nuovo root.
     Viceversa se è più grande.
     */ 

    public void insert(int data) {
        BSTNode node = new BSTNode(data);
        if (root == null) {
            root = node;
            return;
        }else{

            BSTNode currentNode = root;
            BSTNode parentNode = null;

                    

                if (currentNode.data == data)
                    return;
                    
                if (currentNode.data > data) {
                    parentNode = node;

                    BSTNode existing = searchNode(data);

                    if (existing != null){
                        parentNode = existing;
                        parentNode.rigth = currentNode;
                    }else{
                        parentNode.rigth = currentNode;
                    }        
                } else {
                    parentNode = node;

                    BSTNode existing = searchNode(data);

                    if (existing != null){
                        
                        parentNode = existing;
                        parentNode.left = currentNode;
                    }else{
                        parentNode.left = currentNode;
                    }
                }
                root = parentNode;
            }
        }


    public BSTNode searchNode(int data) {
        if (empty())
            return null;
        return searchNode(data, root);
    }

    public BSTNode searchNode(int data, BSTNode node) {
        if ((node.left != null && node.left.data == data) || (node.rigth != null && node.rigth.data == data)) {
            if (node.left.data == data){
                BSTNode existing = node.left;
                node.left = null;
                return existing;
            }
            if(node.rigth.data == data){
                BSTNode existing = node.rigth;
                node.rigth = null;
                return existing;
            }
            else if (node.data > data)
                return searchNode(data, node.left);
            else if (node.data < data)
                return searchNode(data, node.rigth);  
        }
        return null;
    }

    public boolean delete(int data) {
        if (empty())
            throw new NoSuchElementException("Tree is Empty");

        BSTNode currentNode = root;
        BSTNode parentNode = root;
        boolean isLeftChild = false;

        while (currentNode.data != data) {
            parentNode = currentNode;
            if (currentNode.data > data) {
                isLeftChild = true;
                currentNode = currentNode.left;
            } else if (currentNode.data < data) {
                isLeftChild = false;
                currentNode = currentNode.rigth;
            }
            if (currentNode == null)
                return false;
        }

        // CASE 1: node with no child
        if (currentNode.left == null && currentNode.rigth == null) {
            if (currentNode == root)
                root = null;
            if (isLeftChild)
                parentNode.left = null;
            else
                parentNode.rigth = null;
        }

        // CASE 2: if node with only one child
        else if (currentNode.left != null && currentNode.rigth == null) {
            if (root == currentNode) {
                root = currentNode.left;
            }
            if (isLeftChild)
                parentNode.left = currentNode.left;
            else
                parentNode.rigth = currentNode.left;
        } else if (currentNode.rigth != null && currentNode.left == null) {
            if (root == currentNode)
                root = currentNode.rigth;
            if (isLeftChild)
                parentNode.left = currentNode.rigth;
            else
                parentNode.rigth = currentNode.rigth;
        }

        // CASE 3: node with two child
        else if (currentNode.left != null && currentNode.rigth != null) {

            // Now we have to find minimum element in rigth sub tree
            // that is called successor
            BSTNode successor = getSuccessor(currentNode);
            if (currentNode == root)
                root = successor;
            if (isLeftChild)
                parentNode.left = successor;
            else
                parentNode.rigth = successor;
            successor.left = currentNode.left;
        }

        return true;
    }

    private BSTNode getSuccessor(BSTNode deleteNode) {

        BSTNode successor = null;
        BSTNode parentSuccessor = null;
        BSTNode currentNode = deleteNode.left;

        while (currentNode != null) {
            parentSuccessor = successor;
            successor = currentNode;
            currentNode = currentNode.left;
        }

        if (successor != deleteNode.rigth) {
            parentSuccessor.left = successor.left;
            successor.rigth = deleteNode.rigth;
        }

        return successor;
    }

    public int parent(int data) {
        return parent(root, data);
    }

    private int parent(BSTNode node, int data) {
        if (empty())
            throw new IllegalArgumentException("Empty");
        if (root.data == data)
            throw new IllegalArgumentException("No Parent node found");

        BSTNode parent = null;
        BSTNode current = node;

        while (current.data != data) {
            parent = current;
            if (current.data > data)
                current = current.left;
            else
                current = current.rigth;
            if (current == null)
                throw new IllegalArgumentException(data + " is not a node in tree");
        }
        return parent.data;
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(BSTNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.rigth);
        }
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(BSTNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.rigth);
        }
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(BSTNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.rigth);
            System.out.print(node.data + " ");
        }
    }

    public boolean empty() {
        return root == null;
    }

}

public class FST {
    public static void main(String[] l) {
        System.out.println("Weleome to Binary Search Tree");
        Scanner scanner = new Scanner(System.in);
        boolean yes = true;
        BinarySearchTree tree = new BinarySearchTree();
        do {
            System.out.println("\n1. Insert");
            System.out.println("2. Search Node");
            System.out.println("4. Empty Status");
            System.out.println("5. Delete Node");
            System.out.println("7. Node with Maximum Value");
            System.out.println("8. Find Parent node");

            System.out.println("Enter Your Choice :: ");
            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
                try {
                    System.out.println("Enter Value");
                    tree.insert(scanner.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                System.out.println("Enter the node");
                System.out.println(tree.searchNode(scanner.nextInt()));
                break;

            case 4:
                System.out.println(tree.empty());
                break;

            case 5:
                try {
                    System.out.println("Enter the node");
                    System.out.println(tree.delete(scanner.nextInt()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            case 8:
                try {
                    System.out.println("Enter the node");
                    System.out.println(tree.parent(scanner.nextInt()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                break;
            }
            tree.preorder();
            System.out.println();
            tree.inorder();
            System.out.println();
            tree.postorder();
            
        } while (yes);
        scanner.close();
    }
}