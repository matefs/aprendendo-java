public class Printer <T extends Animal > {
   T thingToPrint;

   public Printer(T thingToPrint){
        this.thingToPrint = thingToPrint;
   }

   public void print(){
    thingToPrint.eat(); // available from Animal 
    System.out.println(thingToPrint);
   }
}
