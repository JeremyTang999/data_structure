package datastructure;

public class BinSearchTreeTest {
    public static void main(String[] args){
        BinSearchTree<CustomInteger,String> tree=new BinSearchTree<CustomInteger,String>();
        for(int i=0;i<10;i++){
            int k=(int)(100*Math.random());
            double v=1000*Math.random();
            tree.put(new CustomInteger(k),v+"");
            System.out.println("k:"+k+",v:"+v);
        }
        int k=(int)(100*Math.random());
        BinSearchTree.Entry entry=tree.floor(new CustomInteger(k));
        BinSearchTree.Entry entry2=tree.ceiling(new CustomInteger(k));
        System.out.println("k "+k);
        System.out.println("floor: k:"+entry.getKey()+",v:"+entry.getValue());
        System.out.println("ceiling: k:"+entry2.getKey()+",v:"+entry2.getValue());

    }
}

class BinSearchTree<K extends Comparable,V>{
    //public static final boolean SHOW_CREATE_TREE=true;
    public static final boolean SHOW_CREATE_TREE=false;

    Entry<K,V> root=null;

    public BinSearchTree(){

    }

    /**
     *
     * @param k
     * @param v
     */
    public void put(K k,V v){
        if(root==null){
            root=new Entry<K,V>(
                    k,v,null,null
            );
            if(SHOW_CREATE_TREE) {
                System.out.println("new " + k + " " + v);
            }
        }
        else {
            put(root,k,v);
        }
    }

    private void put(Entry<K,V> current,K k,V v){

        if(current.getKey().equals(k)){
            current.setValue(v);
            if(SHOW_CREATE_TREE) {
                System.out.println("put: " + k + " " + v);
            }
        }
        else{
            if(k.less(current.getKey())){
                if(current.getLeftSon()==null){
                    if(SHOW_CREATE_TREE) {
                        System.out.println("add left " + k + " " + v);
                    }
                    current.setLeftSon(new Entry<K,V>(
                            k,v,null,null
                    ));
                }
                else {
                    if(SHOW_CREATE_TREE) {
                        System.out.println("go left " + current.getKey());
                    }
                    put(current.getLeftSon(), k, v);
                }
            }
            else {
                if(current.getRightSon()==null){
                    if(SHOW_CREATE_TREE) {
                        System.out.println("add right " + k + " " + v);
                    }
                    current.setRightSon(new Entry<K,V>(
                            k,v,null,null
                    ));
                }
                else {
                    if(SHOW_CREATE_TREE) {
                        System.out.println("go right " + current.getKey());
                    }
                    put(current.getRightSon(), k, v);
                }
            }
        }
    }

    /**
     *
     * @param k
     * @return
     */
    public V get(K k){
        if(root==null)
            return null;

        return get(root,k);
    }

    private V get(Entry<K,V> current,K k){
        if(current==null){
            return null;
        }
        else {
            if(k.equals(current.getKey())){
                return current.getValue();
            }
            else if(k.less(current.getKey())){
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
    public Entry<K,V> floor(K key){
        return floor(root,key);
    }
    private Entry<K,V> floor(Entry<K,V> current,K key){
        if(current==null) {
            return null;
        }
        if(current.getKey().equals(key)){
            return current;
        }
        if(key.less(current.getKey())){
            return floor(current.getLeftSon(),key);
        }
        else{
            Entry<K,V> right=floor(current.getRightSon(),key);
            return right!=null ? right : current;
        }
    }

    /**
     * 向上取整，获取小于等于参数key的最大键值
     * @param key
     * @return
     */
    public Entry<K,V> ceiling(K key){
        return ceiling(root,key);
    }
    private Entry<K,V> ceiling(Entry<K,V> current,K key){
        if(current==null) {
            return null;
        }
        if(current.getKey().equals(key)){
            return current;
        }
        if(key.more(current.getKey())){
            return ceiling(current.getRightSon(),key);
        }
        else{
            Entry<K,V> left=ceiling(current.getLeftSon(),key);
            return left!=null ? left : current;
        }
    }


    /**
     * 获取最大值
     * @return
     */
    public Entry<K,V> max(){
        return max(root);
    }
    private Entry<K,V> max(Entry<K,V> current){
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
    public Entry<K,V> min(){
        return min(root);
    }
    private Entry<K,V> min(Entry<K,V> current){
        if(current==null)
            return null;

        if(current.getLeftSon()==null){
            return current;
        }
        else {
            return min(current.getLeftSon());
        }
    }

    static class Entry<K,V>{
        private K k;
        private V v;
        private Entry<K,V> leftSon;
        private Entry<K,V> rightSon;

        public Entry() {
        }

        public Entry(K k, V v, Entry<K, V> leftSon, Entry<K, V> rightSon) {
            this.k = k;
            this.v = v;
            this.leftSon = leftSon;
            this.rightSon = rightSon;
        }

        public K getKey(){
            return k;
        }

        public V getValue(){
            return v;
        }

        public void setValue(V value){
            this.v=value;
        }

        public Entry<K, V> getLeftSon() {
            return leftSon;
        }

        public Entry<K, V> getRightSon() {
            return rightSon;
        }

        public void setLeftSon(Entry<K, V> leftSon) {
            this.leftSon = leftSon;
        }

        public void setRightSon(Entry<K, V> rightSon) {
            this.rightSon = rightSon;
        }
    }
}

interface Comparable<T>{
    boolean less(Comparable<T> comparable);
    boolean more(Comparable<T> comparable);
    T getValue();
}

class CustomInteger implements Comparable<Integer> {
    private Integer value;

    public CustomInteger(int value){
        this.value=value;
    }

    @Override
    public Integer getValue(){
        return new Integer(this.value);
    }
    @Override

    public boolean less(Comparable<Integer> comparable) {
        return comparable!=null && value<comparable.getValue();
    }

    @Override
    public boolean more(Comparable<Integer> comparable) {
        return comparable!=null && value>comparable.getValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CustomInteger){
            return value.equals(((CustomInteger) obj).getValue());
        }
        return false;
    }
}