package minitrianglecompiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {
  public static String lerLinhaArquivo(int numeroLinha) throws IOException {

    String nomeArquivo = "";
    
    try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
      String linha;
      int contador = 1;
      while ((linha = reader.readLine()) != null) {
        if (contador == numeroLinha) {
          return linha;
        }
        contador++;
      }
    } catch (IOException e) {
      // Trate a exceção de leitura do arquivo aqui, se necessário
      throw e;
    }
    // Se a linha não for encontrada, retorna null
    return null;
  }
}
