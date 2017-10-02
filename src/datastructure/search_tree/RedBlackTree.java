package datastructure.search_tree;

public class RedBlackTree<K extends Comparable,V> extends BinSearchTree<K,V>{

    /**
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {

        root=put((Node)root,key,value);
    }


    private Node put(Node current,K key, V value){
        if(current==null)
            return new Node(key,value,false);

        if(key.compareTo(current.getKey())<0)   current.setLeftSon(put((Node)current.getLeftSon(),key,value));
        else if(key.compareTo(current.getKey())>0) current.setRightSon(put((Node)current.getRightSon(),key,value));
        else current.setValue(value);

        if(     current.getRightSon()!=null &&
                ((Node)current.getRightSon()).isRed() &&
                ( current.getLeftSon()==null || !((Node)current.getLeftSon()).isRed() )     ){

            return rotateLeft(current);
        }
        if(     current.getLeftSon()!=null && current.getLeftSon().getLeftSon()!=null &&
                ((Node)current.getLeftSon()).isRed() &&
                ((Node)current.getLeftSon().getLeftSon()).isRed() ){

            return rotateRight(current);
        }
        if(     current.getLeftSon()!=null && ((Node)current.getLeftSon()).isRed() &&
                current.getRightSon()!=null && ((Node)current.getRightSon()).isRed() ){

            current.setRed(true);
            ((Node)current.getLeftSon()).setRed(false);
            ((Node)current.getRightSon()).setRed(false);
            return current;
        }
        return current;
    }

    private Node rotateLeft(Node n){
        if(n.getRightSon()==null)
            return n;

        Node x=(Node)n.getRightSon();
        n.setRightSon(x.getLeftSon());
        x.setLeftSon(n);
        x.setRed(n.isRed);
        n.setRed(true);
        return x;
    }

    private Node rotateRight(Node n){
        if(n.getLeftSon()==null)
            return n;

        Node x=(Node) n.getLeftSon();
        n.setLeftSon(x.getRightSon());
        x.setRightSon(n);
        x.setRed(n.isRed);
        n.setRed(true);
        return x;
    }



    /*public static class Node<K extends Comparable,V>{
        private K key;
        private V value;
        private Node<K,V> left,right;
        private boolean isRed;

        public Node(K key,V value,boolean isRed){
            this.key=key;
            this.value=value;
            this.isRed=isRed;
        }
        public K getKey(){
            return this.key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getLeft() {
            return left;
        }
        public void setLeft(Node left){
            this.left=left;
        }
        public Node<K, V> getRight() {
            return right;
        }
        public void setRight(Node right){
            this.right=right;
        }

        public boolean isRed() {
            return isRed;
        }
        public void setRed(boolean isRed){
            this.isRed=isRed;
        }
    }*/
    public static class Node<K extends Comparable,V> extends BinSearchTree.Node<K,V>{
        private boolean isRed;

        public Node(){}

        public Node(K key, V value, Node<K, V> leftSon, Node<K, V> rightSon, boolean isRed) {
            super(key, value, leftSon, rightSon);
            this.isRed = isRed;
        }

        public Node(K key,V value,boolean isRed){
            super(key,value,null,null);
            this.isRed=isRed;
        }

        public boolean isRed() {
            return isRed;
        }

        public void setRed(boolean red) {
            isRed = red;
        }
    }

}
