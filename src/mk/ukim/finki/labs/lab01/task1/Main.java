package mk.ukim.finki.labs.lab01.task1;

import java.util.Locale;
import java.util.Scanner;

class Employee {
    String id;
    String name;
    double baseSalary;

    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    double calculateSalary() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return String.format("%s %s salary=%.2f", id, name, calculateSalary());
    }
}

class Developer extends Employee {
    int overtimeHours;

    public Developer(String id, String name, double baseSalary, int overtimeHours) {
        super(id, name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    double calculateSalary() {
        return super.calculateSalary() + overtimeHours * 300;
    }

    @Override
    public String toString() {
        return super.toString() + " overtime=" + overtimeHours;
    }
}

class TeamLead extends Developer {
    int teamSize;

    public TeamLead(String id, String name, double baseSalary, int overtimeHours, int teamSize) {
        super(id, name, baseSalary, overtimeHours);
        this.teamSize = teamSize;
    }

    @Override
    double calculateSalary() {
        double salary = super.calculateSalary() + 1000 * teamSize;
        if (teamSize >= 5) {
            salary += 0.05 * salary;
        }
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + " team=" + teamSize;
    }
}

class Intern extends Employee {
    int months;

    public Intern(String id, String name, double baseSalary, int months) {
        super(id, name, baseSalary);
        this.months = months;
    }

    @Override
    double calculateSalary() {
        if (months >= 6) {
            return super.calculateSalary() * 0.8;
        }
        return super.calculateSalary() * 0.6;
    }

    @Override
    public String toString() {
        return super.toString() + " months=" + months;
    }
}

class Company {
    String name;
    Employee[] employees;
    int size;

    public Company(String name, int capacity) {
        this.name = name;
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    void hire(Employee e) {
        employees[size] = e;
        size++;
    }

    void printAll() {
        System.out.println("=== " + name + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    void printStatistics() {
        int dev = 0, teamLead = 0, intern = 0, others = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i] instanceof TeamLead) {
                teamLead++;
            } else if (employees[i] instanceof Developer) {
                dev++;
            } else if (employees[i] instanceof Intern) {
                intern++;
            } else {
                others++;
            }
        }
        System.out.printf("Developers: %d, Team leads: %d, Interns: %d, Others: %d%n", dev, teamLead, intern, others);
    }

    double totalPayroll() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += employees[i].calculateSalary();
        }
        return sum;
    }

    double averageSalary() {
        if (size == 0) return 0;
        return totalPayroll() / size;
    }

    Employee highestPaid() {
        if (size == 0) return null;
        Employee maxEmp = employees[0];
        for (int i = 1; i < size; i++) {
            if (employees[i].calculateSalary() > maxEmp.calculateSalary()) {
                maxEmp = employees[i];
            }
        }
        return maxEmp;
    }

    int countAbove(double threshold) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i].calculateSalary() > threshold) {
                count++;
            }
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        Company company = new Company("FINKI Soft", n);

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String id = scanner.next();
            String name = scanner.next();
            double baseSalary = scanner.nextDouble();

            switch (type) {
                case "E":
                    company.hire(new Employee(id, name, baseSalary));
                    break;
                case "D":
                    int overtimeHours = scanner.nextInt();
                    company.hire(new Developer(id, name, baseSalary, overtimeHours));
                    break;
                case "L":
                    int overtime = scanner.nextInt();
                    int teamSize = scanner.nextInt();
                    company.hire(new TeamLead(id, name, baseSalary, overtime, teamSize));
                    break;
                case "I":
                    int months = scanner.nextInt();
                    company.hire(new Intern(id, name, baseSalary, months));
                    break;
            }
        }

        double threshold = scanner.nextDouble();

        company.printAll();
        company.printStatistics();

        System.out.printf("Total payroll: %.2f%n", company.totalPayroll());
        System.out.printf("Average salary: %.2f%n", company.averageSalary());

        Employee hp = company.highestPaid();
        if (hp != null) {
            System.out.printf("Highest paid: %s %s (%.2f)%n", hp.id, hp.name, hp.calculateSalary());
        }

        System.out.printf("Employees with salary above %.2f: %d%n", threshold, company.countAbove(threshold));

        scanner.close();
    }
}