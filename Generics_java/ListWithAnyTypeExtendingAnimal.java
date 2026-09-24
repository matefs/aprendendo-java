import java.util.ArrayList;
import java.util.List;

public class ListWithAnyTypeExtendingAnimal {
    public static void main(String[] args) {

        // List<Integer> intList = new ArrayList<>();
        // intList.add(3);
        // printList(intList);

        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());
        printList(catList);

    }

    private static void printList(List <? extends Animal> myList){  // wildcard herda animal e só permite o tipo animal 
         System.out.println(myList);
    }
}
