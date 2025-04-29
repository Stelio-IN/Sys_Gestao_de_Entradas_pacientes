
package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author steli
 */
public class Ficheiro {
    
     // Grava uma ArrayList em arquivo
    public static void gravarEmArquivo(ArrayList<?> lista, String nomeArquivo) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(lista);
            System.out.println("ArrayList gravada em arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar em arquivo: " + e.getMessage());
        }
      
    }

    // Carrega uma ArrayList de um arquivo
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> carregarDoArquivo(String nomeArquivo) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            ArrayList<T> lista = (ArrayList<T>) inputStream.readObject();
            System.out.println("ArrayList carregada do arquivo: " + nomeArquivo);
            return lista;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
