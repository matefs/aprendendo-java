import java.util.ArrayList;
import java.util.List;

public class ListWithAnyType {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.add(3);
        printList(intList);
    }

    private static void printList(List<?> myList){ //wild card = ?  (unkown) same as any in typescript.
        System.out.println(myList);
    }
}
