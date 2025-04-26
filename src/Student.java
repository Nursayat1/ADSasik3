public record Student(int id, String name) {
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}