package designPatterns;

public class CustomHashMap<K, V> {
    public static final int INITIAL_CAPACITY = 1 << 4;
    public static final int MAX_CAPACITY = 1 << 30;
    public Entry[] hashTable;

    public CustomHashMap() {
        hashTable = new Entry[INITIAL_CAPACITY];
    }

    public CustomHashMap(int cap) {
        hashTable = new Entry[getCapacity(cap)];
    }

    private int getCapacity(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAX_CAPACITY) ? MAX_CAPACITY : n + 1;
    }

    //    ahh 32 is not possible because of int
//    constraint and 31 is not possible because of signed constraint so 30
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public void put(K key, V value) {
        int entry = Math.abs(key.hashCode()) % hashTable.length;
        Entry<K, V> node = hashTable[entry];
        if (node == null) {
            Entry<K, V> newNode = new Entry(key, value);
            hashTable[entry] = newNode;
        } else {
            Entry<K, V> dummy = node;
            while (node != null) {
                if (key.equals(node.key)) {
                    node.value = value;
                    return;
                }
                dummy = node;
                node = node.next;
            }
            Entry<K, V> newNode = new Entry<>(key, value);
            dummy.next = newNode;
        }
    }

    public V get(K key) {
        int entry = Math.abs(key.hashCode()) % hashTable.length;
        Entry<K, V> node = hashTable[entry];
        if (node == null) {
            return null;
        } else {
            while (node != null) {
                if (key.equals(node.key)) {
                    return node.value;
                }
                node = node.next;
            }
            return null;
        }
    }
}