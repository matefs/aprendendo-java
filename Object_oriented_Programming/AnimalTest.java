//JAVA 17+
//DEPS junit:junit:4.13.2
//SOURCES Animal.java
//SOURCES Cachorro.java

package Object_oriented_Programming;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnimalTest {
    @Test
    public void deveEmitirSomGenericoParaAnimal() {
        Animal animal = new Animal();
        assertEquals("Som generico caraio ", animal.emitirSom());
    }

    @Test
    public void deveAdicionarLatidoAoSomDoCachorro() {
        Cachorro cachorro = new Cachorro();
        assertEquals("Som generico caraio  / au auau auau", cachorro.emitirSom());
    }
}
