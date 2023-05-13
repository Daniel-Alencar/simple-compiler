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

		try {
			do {
				Token currentToken = scanner.scan();
				
				System.out.println();
				System.out.println("TOKEN:");
				System.out.println("speeling:" + currentToken.spelling);
				System.out.println("kind:" + currentToken.kind);
				System.out.println();
			} while(true);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
