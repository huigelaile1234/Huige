package com.it.tree;

public class Test {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(50);
        tree.insert(20);
        tree.insert(80);
        tree.insert(10);
        tree.insert(30);
        tree.insert(60);
        tree.insert(90);
        tree.insert(25);
        tree.insert(85);
        tree.insert(100);
        tree.prefixOrder(tree.root);
        System.out.println("");
        tree.infixOrder(tree.root);
        System.out.println("");
        tree.suffixOrder(tree.root);
        System.out.println("");
        tree.delete(10);//删除没有子节点的节点
        tree.delete(30);//删除有一个子节点的节点
        tree.delete(80);//删除有两个子节点的节点
        System.out.println(tree.findMax().data);
        System.out.println(tree.findMin().data);
        System.out.println(tree.find(50));
        System.out.println(tree.find(200));
    }
}
