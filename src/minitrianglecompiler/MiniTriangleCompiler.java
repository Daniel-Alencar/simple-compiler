package minitrianglecompiler;

import javax.xml.catalog.Catalog;

/**
 *
 * @author
 */
public class MiniTriangleCompiler {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner("/test/test.txt");
		int counter = 0;
		try {
			do {
				Token currentToken = scanner.scan();
				
				System.out.println("TOKEN:");
				System.out.println("speeling:" + currentToken.spelling);
				System.out.println("kind:" + currentToken.kind);
				System.out.println();

				counter++;
			} while(scanner.isEOF() == false);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
