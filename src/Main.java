public class Main {
    public static void main(String[] args) {
        System.out.println("Testing MyHashTable:");
        testMyHashTable();

        System.out.println("\n" + "Testing BST:");
        testBST();
    }

    // Test hash table with random elements
    private static void testMyHashTable() {
        HashTable<TestingClass, Student> table = new HashTable<>(1000);
        java.util.Random random = new java.util.Random();

        // Add 10,000 random elements to test distribution
        for (int i = 0; i < 10000; i++) {
            TestingClass key = new TestingClass(
                    random.nextInt(1000000),
                    "Test-" + random.nextInt(1000000)
            );
            Student value = new Student(i, "Student" + i);
            table.put(key, value);
        }

        // Print bucket sizes to analyze distribution
        for (int i = 0; i < table.getBucketCount(); i++) {
            System.out.println("Bucket " + i + " size = " + table.getBucketSize(i));
        }

        System.out.println("Total elements in hash table: " + table.size());
    }

    // Test BST operations
    private static void testBST() {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "Five");
        tree.put(2, "Two");
        tree.put(8, "Eight");
        tree.put(1, "One");
        tree.put(3, "Three");
        tree.put(3, "Three - Updated");

        System.out.println("BST size before deletion: " + tree.size());

        // Test iterator with key-value access
        for (var elem : tree) {
            System.out.println("Key is " + elem.key() + " and value is " + elem.value());
        }

        System.out.println("Value for key 3: " + tree.get(3));

        tree.delete(2);
        System.out.println("\n" + "BST size after deleting key 2: " + tree.size());

        System.out.println("In-order traversal after deletion:");
        for (var elem : tree) {
            System.out.println("Key is " + elem.key() + " and value is " + elem.value());
        }
    }
}