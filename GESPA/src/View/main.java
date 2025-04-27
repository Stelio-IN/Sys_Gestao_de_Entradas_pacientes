/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.Ficheiro;
import Controller.GestorController;
import Model.Especialidade;
import Model.Gestor;
import Model.Medico;
import java.util.ArrayList;

/**
 *
 * @author Stelio Mondlane
 */
public class main {
    public static void main(String[] args) {
        Especialidade b = new Especialidade("Pediatrea", "Trata de sei la ");
        GestorController c = new GestorController();
      //  c.cadastrarMedico(b, "Abc", "456", "456", "1313213", "456", "4", "maputo");
       //   c.cadastrarMedico(b, "Sam", "123", "123", "abcd", "123", "1", "maputo");
           //  GestorController c = new GestorController();
           
        //   c.desativarMedico("Sam");
             c.listarMedico();
             
    /*    Ficheiro f1 = new Ficheiro();
        Gestor a = new Gestor();
        a.setCod("Admin123");
        a.setEmail("admin@gmail.com");
        a.setPassword("1234");
        
        ArrayList<Gestor> lista = new ArrayList<>();
        lista.add(a);
        f1.gravarEmArquivo(lista, "gestor");*/
    }
}
