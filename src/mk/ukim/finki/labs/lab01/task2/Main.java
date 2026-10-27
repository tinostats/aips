package mk.ukim.finki.labs.lab01.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double finalPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s price=%.2f final=%.2f", name, price, finalPrice());
    }
}

class Food extends Product {
    int daysToExpiry;

    public Food(String name, double price, int daysToExpiry) {
        super(name, price);
        this.daysToExpiry = daysToExpiry;
    }

    @Override
    public double finalPrice() {
        if (daysToExpiry <= 3) {
            return price * 0.5;
        }
        return price;
    }

    @Override
    public String toString() {
        return super.toString() + " days=" + daysToExpiry;
    }
}

class Electronics extends Product {
    int warrantyMonths;

    public Electronics(String name, double price, int warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public double finalPrice() {
        if (warrantyMonths > 24) {
            return price * 1.15;
        }
        return price;
    }

    @Override
    public String toString() {
        return super.toString() + " warranty=" + warrantyMonths;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class Container<T extends Product> {
    private String name;
    private int capacity;
    private List<T> items;

    public Container(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public boolean add(T item) {
        if (items.size() >= capacity) {
            return false;
        }
        items.add(item);
        return true;
    }

    public int size() {
        return items.size();
    }

    public double totalValue() {
        double sum = 0;
        for (T item : items) {
            sum += item.finalPrice();
        }
        return sum;
    }

    public T mostExpensive() {
        if (items.isEmpty()) {
            return null;
        }
        T maxItem = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (items.get(i).finalPrice() > maxItem.finalPrice()) {
                maxItem = items.get(i);
            }
        }
        return maxItem;
    }

    public Pair<String, Double> summary() {
        return new Pair<>(name, totalValue());
    }

    public void print() {
        System.out.printf("=== %s (%d/%d) ===%n", name, size(), capacity);
        for (T item : items) {
            System.out.println(item);
        }
        System.out.printf("Total: %.2f%n", totalValue());
        T max = mostExpensive();
        if (max != null) {
            System.out.printf("Most expensive: %s (%.2f)%n", max.name, max.finalPrice());
        } else {
            System.out.println("Most expensive: none");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        if (!scanner.hasNextInt()) return;
        int capFridge = scanner.nextInt();
        int capTech = scanner.nextInt();
        int capOverflow = scanner.nextInt();

        Container<Food> fridge = new Container<>("Fridge", capFridge);
        Container<Electronics> tech = new Container<>("Tech", capTech);
        Container<Product> overflow = new Container<>("Overflow", capOverflow);

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double price = scanner.nextDouble();

            if (type.equals("F")) {
                int days = scanner.nextInt();
                Food food = new Food(name, price, days);
                if (!fridge.add(food)) {
                    if (!overflow.add(food)) {
                        System.out.println("Rejected: " + name);
                    }
                }
            } else if (type.equals("E")) {
                int warranty = scanner.nextInt();
                Electronics el = new Electronics(name, price, warranty);
                if (!tech.add(el)) {
                    if (!overflow.add(el)) {
                        System.out.println("Rejected: " + name);
                    }
                }
            }
        }

        fridge.print();
        tech.print();
        overflow.print();

        Pair<String, Double> s1 = fridge.summary();
        Pair<String, Double> s2 = tech.summary();
        Pair<String, Double> s3 = overflow.summary();

        Pair<String, Double> best = s1;
        if (s2.getValue() > best.getValue()) {
            best = s2;
        }
        if (s3.getValue() > best.getValue()) {
            best = s3;
        }

        System.out.printf("Most valuable container: %s (%.2f)%n", best.getKey(), best.getValue());

        Food topFood = fridge.mostExpensive();
        if (topFood != null) {
            System.out.printf("Top food expires in %d days%n", topFood.daysToExpiry);
        }

        Electronics topTech = tech.mostExpensive();
        if (topTech != null) {
            System.out.printf("Top electronics warranty: %d months%n", topTech.warrantyMonths);
        }

        scanner.close();
    }
}