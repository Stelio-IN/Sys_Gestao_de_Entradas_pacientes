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
     //   Especialidade b = new Especialidade("Pediatrea", "Trata de sei la ");
     //   GestorController c = new GestorController();
    //    c.cadastrarMedico(b, "Abc", "123asdsad", "123dsfdsf", "1313213", "dsasadsadd", "sadsd", "maputo");
       //   c.cadastrarMedico(b, "Sam", "123", "123", "1313213", "dsasadsadd", "sadsd", "maputo");
           //  GestorController c = new GestorController();
           
        //   c.desativarMedico("Sam");
       //      c.listarMedico();
             
        Ficheiro f1 = new Ficheiro();
        Gestor a = new Gestor();
        a.setCod("Admin123");
        a.setEmail("admin@gmail.com");
        a.setPassword("1234");
        
        ArrayList<Gestor> lista = new ArrayList<>();
        lista.add(a);
        f1.gravarEmArquivo(lista, "gestor");
    }
}
