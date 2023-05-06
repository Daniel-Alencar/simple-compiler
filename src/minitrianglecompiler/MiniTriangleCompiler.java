package minitrianglecompiler;

/**
 *
 * @author
 */
public class MiniTriangleCompiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        for(int i = 'A'; i <= 'Z'; i++) {
            System.out.println((int)i);

            boolean isValid = scanner.isLetter((char)i);
            System.out.println("Valor válido: " + isValid);
        }
    }
}
