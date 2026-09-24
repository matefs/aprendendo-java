public class Cat extends Animal{
    private String litterPreference;

    public Cat(String name){
        this.name = name ;
    }

    public Cat(){}

    public String getLitterPreference(){
        return litterPreference;
    }

    public void setLitterPreference(String litterPreference){
        try {
            this.litterPreference = litterPreference;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
