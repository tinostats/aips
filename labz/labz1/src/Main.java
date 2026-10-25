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
public class Developer extends Employee{
    int overtimeHours;
    public Developer(String id, String name, double baseSalary, int overtimeHours) {
        super(id, name, baseSalary);
        this.overtimeHours = overtimeHours;
    }
    @Override
    double calculateSalary()
    {
        return super.calculateSalary() + overtimeHours*300;
    }

    @Override
    public String toString() {
        return super.toString() + " overtime=" + overtimeHours;
    }

}

public class TeamLead extends Developer{
    int teamSize;
    public TeamLead(String id, String name, double baseSalary, int overtimeHours, int teamSize) {
        super(id, name, baseSalary, overtimeHours);
        this.teamSize = teamSize;
    }
    @Override
    double calculateSalary()
    {
        double salary = super.calculateSalary() + 1000*teamSize;
        if (teamSize >= 5) {
            salary += 0.05*salary;
        }
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + " team="+teamSize;
    }

}


public class Intern extends Employee{
    int months;
    public Developer(String id, String name, double baseSalary, int months) {
        super(id, name, baseSalary);
        this.months = months;
    }
    @Override
    double calculateSalary()
    {
        if (months >= 6) {
            return super.calculateSalary()*0.8;
        }
        return super.calculateSalary()*0.6;
    }

    @Override
    public String toString() {
        return super.toString() + " months=" + months;
    }

}

public class Company {
    String name;
    Employee[] employees;
    int size;

    public Company(String name, int capacity) {
        this.name = name;
        this.employees = new Employee[capacity];
        this.size = 0;
    }
}