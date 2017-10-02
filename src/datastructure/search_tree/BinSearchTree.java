package datastructure.search_tree;

public class BinSearchTree<K extends Comparable,V>{
    //public static final boolean SHOW_CREATE_TREE=true;
    public static final boolean SHOW_CREATE_TREE=false;

    protected Node<K,V> root=null;

    public BinSearchTree(){

    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(K key,V value){
        if(root==null){
            root=new Node<K,V>(
                    key,value,null,null
            );
            if(SHOW_CREATE_TREE) {
                System.out.println("new " + key + " " + value);
            }
        }
        else {
            put(root,key,value);
        }
    }

    /**
     * 递归用插入方法
     * @param current
     * @param key
     * @param value
     */
    private void put(Node<K,V> current, K key, V value){

        if(current.getKey().equals(key)){
            current.setValue(value);
            if(SHOW_CREATE_TREE) {
                System.out.println("put: " + key + " " + value);
            }
        }
        else{
            if(key.compareTo(current.getKey())<0){
                if(current.getLeftSon()==null){
                    if(SHOW_CREATE_TREE) {
                        System.out.println("add left " + key + " " + value);
                    }
                    current.setLeftSon(new Node<K,V>(
                            key,value,null,null
                    ));
                }
                else {
                    if(SHOW_CREATE_TREE) {
                        System.out.println("go left " + current.getKey());
                    }
                    put(current.getLeftSon(), key, value);
                }
            }
            else {
                if(current.getRightSon()==null){
                    if(SHOW_CREATE_TREE) {
                        System.out.println("add right " + key + " " + value);
                    }
                    current.setRightSon(new Node<K,V>(
                            key,value,null,null
                    ));
                }
                else {
                    if(SHOW_CREATE_TREE) {
                        System.out.println("go right " + current.getKey());
                    }
                    put(current.getRightSon(), key, value);
                }
            }
        }
    }

    /**
     *
     * @param k
     * @return
     */
    public Node<K,V> get(K k){
        if(root==null)
            return null;

        return get(root,k);
    }

    private Node<K,V> get(Node<K,V> current, K k){
        if(current==null){
            return null;
        }
        else {
            if(k.equals(current.getKey())){
                return current;
            }
            else if(k.compareTo(current.getKey())<0){
                return get(current.getLeftSon(),k);
            }
            else {
                return get(current.getRightSon(),k);
            }
        }
    }

    /**
     * 向下取整，获取小于等于参数key的最大键值
     * @param key
     * @return
     */
    public Node<K,V> floor(K key){
        return floor(root,key);
    }
    private Node<K,V> floor(Node<K,V> current, K key){
        if(current==null) {
            return null;
        }
        if(current.getKey().equals(key)){
            return current;
        }
        if(key.compareTo(current.getKey())<0){
            return floor(current.getLeftSon(),key);
        }
        else{
            Node<K,V> right=floor(current.getRightSon(),key);
            return right!=null ? right : current;
        }
    }

    /**
     * 向上取整，获取小于等于参数key的最大键值
     * @param key
     * @return
     */
    public Node<K,V> ceiling(K key){
        return ceiling(root,key);
    }
    private Node<K,V> ceiling(Node<K,V> current, K key){
        if(current==null) {
            return null;
        }
        if(current.getKey().equals(key)){
            return current;
        }
        if(key.compareTo(current.getKey())>0){
            return ceiling(current.getRightSon(),key);
        }
        else{
            Node<K,V> left=ceiling(current.getLeftSon(),key);
            return left!=null ? left : current;
        }
    }


    /**
     * 获取最大值
     * @return
     */
    public Node<K,V> max(){
        return max(root);
    }
    private Node<K,V> max(Node<K,V> current){
        if(current==null)
            return null;

        if(current.getRightSon()==null){
            return current;
        }
        else {
            return max(current.getRightSon());
        }
    }

    /**
     * 获取最小值
     * @return
     */
    public Node<K,V> min(){
        return min(root);
    }
    private Node<K,V> min(Node<K,V> current){
        if(current==null)
            return null;

        if(current.getLeftSon()==null){
            return current;
        }
        else {
            return min(current.getLeftSon());
        }
    }

    public static class Node<K,V>{
        private K key;
        private V value;
        private Node<K,V> leftSon;
        private Node<K,V> rightSon;

        public Node() {
        }

        public Node(K key, V value, Node<K, V> leftSon, Node<K, V> rightSon) {
            this.key = key;
            this.value = value;
            this.leftSon = leftSon;
            this.rightSon = rightSon;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

        public void setValue(V value){
            this.value=value;
        }

        public Node<K, V> getLeftSon() {
            return leftSon;
        }

        public Node<K, V> getRightSon() {
            return rightSon;
        }

        public void setLeftSon(Node<K, V> leftSon) {
            this.leftSon = leftSon;
        }

        public void setRightSon(Node<K, V> rightSon) {
            this.rightSon = rightSon;
        }
    }
}