/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Stelio Mondlane
 */
public class Paciente extends Pessoa implements Serializable{
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

    public String getNome() {
        return getNome();
    }

    public void setNome(String nome) {
        this.setNome(nome);
    }

    public String getTelefone() {
        return getTelefone();
    }

    public void setTelefone(String telefone) {
        this.setTelefone(telefone);
    }

    public String getEndereco() {
        return getEndereco();
    }

    public void setEndereco(String endereco) {
        this.setEndereco(endereco);
    }
    
}
