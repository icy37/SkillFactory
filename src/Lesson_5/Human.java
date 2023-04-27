package Lesson_5;

public class Human {
    String name, address, work;
    int age, weight;

    Human(String name, int age) {
        this.name = (name == null || name.isEmpty()) ? "John" : name;
        this.age = (age <= 0) ? 18 : age;
        this.weight = 55;
        this.address = null;
        this.work = null;
    }

    Human(String name, String address) {
        this.name = (name == null || name.isEmpty()) ? "Kate" : name;
        this.address = (address == null || address.isEmpty()) ? null : address;
        this.age = 24;
        this.weight = 63;
        this.work = null;
    }

   public Human(String name, int age, int weight) {
        this(name, age);
        this.weight = (weight <= 0) ? 70 : weight;
        this.address = null;
        this.work = null;
    }

   Human(String name, int age, String work) {
        this(name, age);
        this.work = (work == null || work.isEmpty()) ? null : work;
        this.address = null;
        this.weight = 84;
    }

    Human(int age, int weight, String address, String work) {
        this.age = (age <= 0) ? 37 : age;
        this.weight = (weight <= 0) ? 73 : weight;
        this.work = (work == null || work.isEmpty()) ? null : work;
        this.address = (address == null || address.isEmpty()) ? null : address;
        this.name = "Liza";
    }

    @Override
    public String toString() {
        return "Lesson_5.Human{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", work='" + work + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}