package View;

import Controller.Ficheiro;
import Model.Gestor;
import Model.Pessoa;
import java.util.ArrayList;


public class add_gestor {
    public static void main(String[] args) {
         Ficheiro f1 = new Ficheiro();
         ArrayList<Pessoa> lista = new ArrayList<>();
        lista  = f1.carregarDoArquivo("pessoa");
         
        Gestor a = new Gestor("00001","admin@gmail.com", "1234", "123456789123","Admin","822235466","Magoanine");
        
        lista.add(a);
        
        f1.gravarEmArquivo(lista,"pessoa");
        
    }
}
