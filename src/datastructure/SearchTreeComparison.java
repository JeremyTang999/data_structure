package datastructure;

import datastructure.search_tree.BinSearchTree;
import datastructure.search_tree.RedBlackTree;

public class SearchTreeComparison {

    public static void main(String[] args){
        BinSearchTree<Integer,Integer> binSearchTree=new BinSearchTree<Integer,Integer>();
        RedBlackTree<Integer,Integer> redBlackTree=new RedBlackTree<Integer,Integer>();
        for(int i=0;i<10000;i++){
            binSearchTree.put(i,i);
            redBlackTree.put(i,i);
        }
        long begin,end;
        int result;

        begin=System.nanoTime();
        result=binSearchTree.get(9999).getValue();
        end=System.nanoTime();
        System.out.println("二叉查找树：result "+result+" time "+(end-begin));

        begin=System.nanoTime();
        result=redBlackTree.get(9999).getValue();
        end=System.nanoTime();
        System.out.println("红黑树：result "+result+" time "+(end-begin));

    }
}
