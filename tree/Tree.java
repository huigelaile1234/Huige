package com.it.tree;

public class Tree {
    Node root;

    public Node find(int key) {
        Node current = root;
        while (key != current.data) {
            if (key < current.data) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void insert(int data) {
        Node newNode = new Node();
        newNode.data = data;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (data < current.data) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void prefixOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.data + " ");
            prefixOrder(localRoot.leftChild);
            prefixOrder(localRoot.rightChild);
        }
    }

    public void infixOrder(Node localRoot) {
        if (localRoot != null) {
            infixOrder(localRoot.leftChild);
            System.out.print(localRoot.data + " ");
            infixOrder(localRoot.rightChild);
        }

    }
    public void suffixOrder(Node localRoot) {
        if (localRoot != null) {
            suffixOrder(localRoot.leftChild);
            suffixOrder(localRoot.rightChild);
            System.out.print(localRoot.data + " ");
        }
    }

    public Node findMin() {
        Node current = root;
        Node minNode = current;
        while (current != null) {
            minNode = current;
            current = current.leftChild;
        }
        return minNode;
    }
    public Node findMax() {
        Node current = root;
        Node maxNode = current;
        while (current != null) {
            maxNode = current;
            current = current.rightChild;
        }
        return maxNode;
    }
    public boolean delete(int key) {
        Node current = root;    //当前节点
        Node parent = root;     //当前节点的父节点
        boolean isLeftChild = true; //被删除节点是否为父节点的左子节点，默认为左子节点
        while (current.data != key) {   //判断是否有和key相等的节点
            parent = current;
            if (key < current.data) {   //如果小于当前节点值，肯定在左边
                isLeftChild = true;
                current = current.leftChild;
            } else {    //如果大于当前节点值，肯定在右边
                isLeftChild = false;
                current = current.rightChild;
            }
        }
        if (current == null) {  //如果没找到，直接返回false
            return false;
        }

        if (current.leftChild == null && current.rightChild == null) {  //删除的节点没有子节点
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            return true;
        } else if (current.rightChild == null) {    //删除的节点只有左节点
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {   //如果被删除节点为父节点的左子节点，把delNode的右子节点赋值给父节点的左子节点
                parent.leftChild = current.leftChild;
            } else {    //如果被删除节点为父节点的右子节点，把delNode的右子节点赋值给父节点的右子节点
                parent.rightChild = current.rightChild;
            }
            return true;
        } else if (current.leftChild == null) {    //删除的节点只有右节点
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else {        //删除的节点右两个子节点
            Node succuessor = getSuccessor(current);    //获取后继节点
            if (current == root) {  //被删除节点为root，直接把后继节点赋值给root
                root =succuessor;
            } else if (isLeftChild) {   //如果被删除节点为父节点的左子节点，父节点的左子节点指向后继节点
                parent.leftChild = succuessor;
            } else {   //如果被删除节点为父节点的右子节点，父节点的右子节点指向后继节点
                parent.rightChild = succuessor;
            }
            succuessor.leftChild = current.leftChild;   //把被删除节点的左子节点指向后继节点的左子节点
            return true;
        }

        return false;
    }

    //获取后继节点，默认删除节点有两个节点
    private static Node getSuccessor(Node delNode) {
        Node successorParent = delNode; //后继节点父节点
        Node successor = delNode;   //后继节点
        Node current = delNode.rightChild;  //当前节点从删除节点右节点开始
        while (current != null) {   //不断遍历当前节点的左子节点，直到找到最后一个，就是后继节点
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {  //后继节点不是删除节点的右子节点，也就是为右子节点的左子节点，或者后序左节点，然后替换删除节点
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public void other(int key) {}
}