public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 10;

    // Node class for the linked list chain
    private static class HashNode<K, V> {
        private final K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private final HashNode<K, V>[] chainArray;
    private final int M;
    private int size;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int M) {
        this.M = M;
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }

    // Hash function to map keys to bucket indices
    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % M;
    }

    public void put(K key, V value) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        // Check if key already exists
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Add new node at the beginning of the chain
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[bucketIndex];
        chainArray[bucketIndex] = newNode;
        size++;
    }

    public V get(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }

        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) return true;
                head = head.next;
            }
        }
        return false;
    }

    public V remove(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) chainArray[bucketIndex] = head.next;
                else prev.next = head.next;
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) return head.key;
                head = head.next;
            }
        }
        return null;
    }

    public int getBucketCount() {
        return M;
    }

    // Returns the number of elements in a specific bucket
    public int getBucketSize(int index) {
        if (index < 0 || index >= M) throw new IndexOutOfBoundsException("Invalid bucket index.");

        int count = 0;
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}