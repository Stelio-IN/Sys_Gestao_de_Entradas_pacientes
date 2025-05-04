/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 * Base class for all persons in the hospital system
 */
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String BI;
    private String nome;
    private String telefone;
    private String endereco;
    
    public Pessoa(String BI, String nome, String telefone, String endereco) {
        this.BI = BI;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Pessoa() {
    }
    
    public String getBI() {
        return BI;
    }
    
    public void setBI(String BI) {
        this.BI = BI;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public String toString() {
        return "Pessoa{" + "BI=" + BI + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + '}';
    }
}