
public abstract class Animal {
    private String nome;

    public Animal(String nome) {
        this.nome = nome;
    }

    // Método concreto (comum a todos)
    public void dormir() {
        System.out.println(nome + " está dormindo.");
    }

    // Método abstrato (cada animal faz de um jeito)
    public abstract void emitirSom();
}
