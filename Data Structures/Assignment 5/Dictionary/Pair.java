
public class Pair<K,V> {
    private K key;
    private V value;
    private Pair<K,V> leftChild;
    private Pair<K,V> rightChild;

    public Pair(K key,V value){
        this.key = key;
        this.value = value;
        leftChild = rightChild =  null;
    }

    public K getKey(){
        return this.key;
    }
    public V getValue(){
        return this.value;
    }

    public Pair<K,V> getLeftChild(){
        return this.leftChild;
    }
    public Pair<K,V> getRightChild(){
        return this.rightChild;
    }

    public void setLeftChild(Pair<K,V> newPair){
        this.leftChild = newPair;
    }
    public void setRightChild(Pair<K,V> newPair){
        this.rightChild = newPair;
    }
    public void setKey(K key){
        this.key = key;
    }
    public void setValue(V value){
        this.value = value;
    }
}
