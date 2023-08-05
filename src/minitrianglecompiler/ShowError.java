package minitrianglecompiler;

public class ShowError {

    public ShowError(String message) {
        try {
            throw new Exception(message);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            System.exit(1);
        }
    }

}
