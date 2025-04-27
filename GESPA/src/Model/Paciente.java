/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 * Class representing a patient
 */
public class Paciente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int dataNasc;
    private String genero;
    
    public Paciente(String BI, String nome, String telefone, String endereco) {
        super(BI, nome, telefone, endereco);
    }
    
    public int getDataNasc() {
        return dataNasc;
    }
    
    public void setDataNasc(int dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    // Remove redundant overrides that cause infinite recursion
}