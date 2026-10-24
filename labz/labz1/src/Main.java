public class Employee {
    String id;
    String name;
    double baseSalary;

    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    double calculateSalary()
    {
        return baseSalary;
    }
    public String toString() {
        return String.format("%s %s salary=%.2f", id, name, calculateSalary());
    }
    public static void main(String[] args) {

    }


}
