import java.io.Serializable;
import java.lang.invoke.VolatileCallSite;
import java.util.concurrent.locks.ReentrantLock;

public class MyconcurrrencyMap {
    static final class HashEntry<K,V>{
        final int hash;
        final K key;
        volatile V value;
        volatile HashEntry<K,V> next;

        public HashEntry<K,V> getNext() {
            return next;
        }
        public void setNext(HashEntry<K,V> next) {

        }

        HashEntry(int hash, K key, V value, HashEntry<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    static final class Segment<K,V> extends ReentrantLock implements Serializable {
        volatile HashEntry<K,V>[] table;

        int count;
        /**阈值因子 */
        float loadFactor;
        /** */
        int threshold;
    }
}