import java.util.Optional;


public class Main{
  public static void main(String[] args) {
    String existingUser = "Mateus";
    String missinguser = null;

    Optional<String> optionalUser = Optional.ofNullable(existingUser);
    Optional<String> optionalNullUser = Optional.ofNullable(missinguser);

    System.out.println(optionalUser.orElse("Usuario padrao"));
    System.out.println(optionalNullUser.orElse("Usuario padrao"));
    
  }
	}
