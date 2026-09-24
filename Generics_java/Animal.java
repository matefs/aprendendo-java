public class Animal {
    protected String name;

    public Animal() {
    }

    public void eat(){
        System.out.println("Munch munch munch");
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
