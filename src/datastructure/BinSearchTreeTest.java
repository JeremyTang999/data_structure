package datastructure;

import datastructure.search_tree.BinSearchTree;

public class BinSearchTreeTest {
    public static void main(String[] args){
        BinSearchTree<Integer,String> tree=new BinSearchTree<Integer,String>();
        for(int i=0;i<10;i++){
            int k=(int)(100*Math.random());
            double v=1000*Math.random();
            tree.put(new Integer(k),v+"");
            System.out.println("k:"+k+",v:"+v);
        }
        int k=(int)(100*Math.random());
        BinSearchTree.Node node =tree.floor(new Integer(k));
        BinSearchTree.Node node2 =tree.ceiling(new Integer(k));
        System.out.println("k "+k);
        System.out.println("floor: k:"+ node.getKey()+",v:"+ node.getValue());
        System.out.println("ceiling: k:"+ node2.getKey()+",v:"+ node2.getValue());

    }
}





