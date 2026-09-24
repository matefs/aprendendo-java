public class GerericExample {
    public static void main(String[] args) {
        shout("John",null);
        shout(112312,null);

        System.out.println(returnT(1));
    }

    private static <T,V> void shout(T thingToShout, V otherThingToShout){
        System.out.println(thingToShout + " !!!! " + otherThingToShout + " !!! ");
    }

    private static <T> T returnT(T thingToReturn){
        return thingToReturn;
    }
}
