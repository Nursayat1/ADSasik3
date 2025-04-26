public record TestingClass(int someInt, String someString) {
    // Prime numbers used for better hash distribution
    private static final int PRIME1 = 7;
    private static final int PRIME2 = 31;

    @Override
    public int hashCode() {
        int hash = PRIME1 * someInt;
        // Process each character in the string
        for (int i = 0; i < someString.length(); i++) {
            hash = PRIME2 * hash + someString.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestingClass(int anInt, String string))) return false;
        return this.someInt == anInt && this.someString.equals(string);
    }
}