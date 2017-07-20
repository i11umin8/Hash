package datastructures;

import java.util.*;

/**
 * Created by i11umin8 on 7/10/17.
 */
public class Hash<K,V> implements Map<K,V>, Iterable<Hash.KVPair<K,V>> {

    private int arrSize;
    private Object[] backingArray;
    private int size;

    public Hash(int len) {
        arrSize = len;
        backingArray = new Object[len];
    }

    public Hash(){
        this(100);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return new HashIterator<>(this).hasNext();
    }

    public boolean containsKey(Object o){
        return get(o) != null;
    }

    public boolean containsValue(Object o) {
        for(KVPair<K,V> p : this){
            if(p.getValue().equals(o)){
                return true;
            }
        }
        return false;
    }

    public V get(Object o) {
        if(o == null)
            return null;

        int i = Math.abs(o.hashCode()) % arrSize;
        if(backingArray[i] != null){
            SinglyLinkedList<KVPair<K,V>> pair = (SinglyLinkedList<KVPair<K,V>>)backingArray[i];
            for(KVPair<K,V> p : pair){
                if(p.getKey().equals(o)){
                    return p.getValue();
                }
            }
        }

        return null;
    }

    public KVPair<K,V> put(KVPair<K,V> pair){
        if(pair == null)
            return null;

        //only hash key so that  changing it's val doesn't affect position
        int i = Math.abs(pair.getKey().hashCode()) % arrSize;
        SinglyLinkedList<KVPair<K,V>> list;
        if(backingArray[i] == null){
            list = new SinglyLinkedList<>();
            list.add(pair);
            backingArray[i] = list;
        } else{
            list = (SinglyLinkedList<KVPair<K,V>>)backingArray[i];
            if(!list.contains(pair)){
                list.add(pair);
            }
        }
        size++;
        return pair;
    }
    public V put(K k, V v) {
        if(k == null || v == null)
            return null;

        KVPair<K,V> pair = new KVPair<>(k,v);
        return put(pair).getValue();
    }

    public V remove(Object o) throws ClassCastException {
        int i = Math.abs(o.hashCode()) % arrSize;
        if(backingArray[i] != null){
            SinglyLinkedList link = (SinglyLinkedList<KVPair<K,V>>) backingArray[i];
            Iterator it = link.iterator();
            while(it.hasNext()){
                KVPair<K,V> pair = (KVPair<K, V>) it.next();
                if(pair.getKey().equals(o)){
                    it.remove();
                    return pair.getValue();
                }
            }
        }
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        Set<? extends Entry<? extends K, ? extends V>> entrySet = map.entrySet();
        for(Entry<? extends K, ? extends V> set : entrySet){
            KVPair<K,V> pair = new KVPair<>(set.getKey(),set.getValue());
            put(pair);
        }
    }

    public void clear() {
        backingArray = new Object[100];
        size = 0;
    }

    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for(KVPair<K,V> pair : this){
            set.add(pair.getKey());
        }
        return set;
    }

    public Collection<V> values() {
        HashSet<V> set = new HashSet<>();
        for(KVPair<K,V> pair : this){
            set.add(pair.getValue());
        }
        return set;
    }

    public Set<Entry<K, V>> entrySet() {
        HashSet<Entry<K,V>> set = new HashSet<>();
        for(Entry<K,V> pair : this){
            set.add(pair);
        }
        return set;
    }

    public Iterator<KVPair<K, V>> iterator() {
        return new HashIterator<K,V>(this);
    }

    private static class HashIterator<K,V> implements Iterator<KVPair<K,V>>{

        private Hash<K, V> hash;
        private int index = -1;
        private KVPair<K,V> currentPair;
        private Iterator<KVPair<K,V>> internalIterator;

        public HashIterator(Hash<K,V> hash){
            this.hash = hash;
            internalIterator = findNextInternalIterator();
        }

        @Override
        public boolean hasNext() {
            if((internalIterator == null || !internalIterator.hasNext())){
                internalIterator = findNextInternalIterator();
            }
            return internalIterator != null;
        }

        @Override
        public KVPair<K,V> next() {
            if(internalIterator == null)
                return null;

            if(internalIterator.hasNext())
                return internalIterator.next();

            internalIterator = findNextInternalIterator();
            return next();

        }

        @Override
        public void remove(){
            if(internalIterator != null){
                internalIterator.remove();
            }
        }

        @SuppressWarnings("unchecked")
        private Iterator<KVPair<K,V>> findNextInternalIterator() {
            index++;
            if(index >= hash.backingArray.length)
                return null;

            Iterator<KVPair<K,V>> it = null;
            SinglyLinkedList<KVPair<K,V>> list = null;
            try{
                list = (SinglyLinkedList<KVPair<K,V>>) hash.backingArray[index];
                if(list != null){
                    it = list.iterator();
                }
            }catch(ClassCastException e){
                e.printStackTrace();
            }
            if(it != null){
                return it;
            } else{
                return findNextInternalIterator();
            }
        }
    }

    static class KVPair<K,V> implements Map.Entry<K,V>{
        private final K key;
        private V value;

        public KVPair(K k, V v){
            key = k;
            value = v;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }

        public V setValue(V v) {
            value = v;
            return value;
        }
        public boolean equals(KVPair<K,V> kv){
            return kv.getKey().equals(this.getKey()) && kv.getValue().equals(this.getValue());
        }

    }
}