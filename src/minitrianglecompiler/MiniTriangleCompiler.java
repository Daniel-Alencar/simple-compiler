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
		Scanner scanner = new Scanner("/test/test3.txt");
		int counter = 0;

		try {
			do {
				Token currentToken = scanner.scan();
				
				System.out.println("TOKEN:");
				System.out.println("speeling:" + currentToken.spelling);
				System.out.println("kind:" + currentToken.kind);
				System.out.println("line:" + currentToken.line);
				System.out.println("column:" + currentToken.column);

				System.out.println();

				counter++;
			} while(scanner.isEOF() == false);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
}