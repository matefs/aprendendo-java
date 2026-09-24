import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Printer<Integer> printer = new Printer<Integer>(23);
        // printer.print();

        System.out.println("\n");

        // Printer<Double> doublePrinter = new Printer<Double>(25.1);
        // doublePrinter.print();

        Printer<Cat> printer = new Printer<>(new Cat());
        printer.print();

        Printer<Dog> dogPrinter = new Printer<>(new Dog());
        dogPrinter.print();

        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(new Cat());

        Cat myCat = cats.get(0);


    }
}
